package de.htwg.se.tictactoe.model

case class Board(
    board: Vector[Vector[Stone]] = Vector.fill(3, 3)(Stone.Empty),
    size: Int = 3
) {
  def this(size: Int) = this(Vector.fill(size, size)(Stone.Empty), size)

  def display(cellSize: Int = 3): String = {
    require(cellSize % 2 != 0, "cellSize should be uneven")
    val cellSeparator = "-"
    val separator =
      s"+" + (cellSeparator * (cellSize) + "+") * size + "\n"
    val rows = board.map { row =>
      val cells = row.map(_.toString)
      s"${cells.mkString("|" + " " * (cellSize / 2), " " * (cellSize / 2) + "|" + " " * (cellSize / 2), " " * (cellSize / 2) + "")}|\n"
    }
    rows.mkString(s"$separator", s"$separator", s"$separator")
  }

  def checkWin: Stone = {
    // Check rows
    val rowWin = board.find(row => {
      val nonEmpty = row.filter(_ != Stone.Empty)
      nonEmpty.nonEmpty && (nonEmpty.forall(_ == Stone.X) || nonEmpty.forall(
        _ == Stone.O
      ))
    })

    // Check columns
    val colWin = board.transpose.find(col => {
      val nonEmpty = col.filter(_ != Stone.Empty)
      nonEmpty.nonEmpty && (nonEmpty.forall(_ == Stone.X) || nonEmpty.forall(
        _ == Stone.O
      ))
    })

    // Check diagonals
    val diag1 = board.indices.forall(i => {
      val stone = board(i)(i)
      stone != Stone.Empty && (board.indices.forall(j => board(j)(j) == stone))
    })

    val diag2 = board.indices.forall(i => {
      val stone = board(i)(size - 1 - i)
      stone != Stone.Empty && (board.indices.forall(j =>
        board(j)(size - 1 - j) == stone
      ))
    })

    if rowWin.isDefined then rowWin.get.head
    else if colWin.isDefined then colWin.get.head
    else if diag1 then board(0)(0)
    else if diag2 then board(0)(size - 1)
    else Stone.Empty
  }

  def makeMove(row: Int, col: Int, stone: Stone): Board = {
    new Board(board.updated(row, board(row).updated(col, stone)), size)
  }

  def isFull: Boolean = board.forall(_.forall(_ != Stone.Empty))

  def gameState: GameState = {
    val win = checkWin
    if win == Stone.X then GameState.XWon
    else if win == Stone.O then GameState.OWon
    else if board.flatten.exists(_ == Stone.Empty) then GameState.Playing
    else GameState.Draw
  }
}
