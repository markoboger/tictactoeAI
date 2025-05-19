package de.htwg.se.tictactoe.model

case class Board(
    grid: Vector[Vector[Stone]] = Vector.fill(3, 3)(Stone.Empty),
    size: Int = 3,
    gameState: GameState = GameState.Playing
) {
  def this(size: Int) = this(Vector.fill(size, size)(Stone.Empty), size)

  def display(cellSize: Int = 3): String = {
    require(cellSize % 2 != 0, "cellSize should be uneven")
    val cellSeparator = "-"
    val separator =
      s"+" + (cellSeparator * (cellSize) + "+") * size + "\n"
    val rows = grid.map { row =>
      val cells = row.map(_.toString)
      s"${cells.mkString("|" + " " * (cellSize / 2), " " * (cellSize / 2) + "|" + " " * (cellSize / 2), " " * (cellSize / 2) + "")}|\n"
    }
    rows.mkString(s"$separator", s"$separator", s"$separator")
  }

  def checkWin: GameState = {
    type StepInDirection = 0 | 1 | -1
    enum Direction(val xStep: StepInDirection, val yStep: StepInDirection) {
      case Row extends Direction(0, 1)
      case Column extends Direction(1, 0)
      case MainDiagonal extends Direction(1, 1)
      case AntiDiagonal extends Direction(1, -1)
    }

    val directions: List[Direction] =
      import Direction._
      List(Row, Column, MainDiagonal, AntiDiagonal)

    def getCellsInDirection(xStart: Int, yStart: Int, dir: Direction): List[Stone] =
      (for {
        i <- 0 until size
        x = dir.xStep * i + xStart
        y = dir.yStep * i + yStart
      } yield grid(x)(y)).toList

    val rows = for i <- 0 until size yield {
      getCellsInDirection(i, 0, Direction.Row)
    }.toList

    val columns = for j <- 0 until size yield {
      getCellsInDirection(0, j, Direction.Column)
    }.toList

    val mainDiagonal = getCellsInDirection(0, 0, Direction.MainDiagonal).toList
    val antiDiagonal = getCellsInDirection(0, size - 1, Direction.AntiDiagonal).toList

    val allLines = rows ++ columns ++ List(mainDiagonal) ++ List(antiDiagonal)

    if allLines.exists(_.forall(_ == Stone.X)) then GameState.XWon
    else if allLines.exists(_.forall(_ == Stone.O)) then GameState.OWon
    else if grid.forall(_.forall(_ != Stone.Empty)) then GameState.Draw
    else GameState.Playing
  }

  def makeMove(row: Int, col: Int, stone: Stone): Board = {
    val newGrid = grid.updated(row, grid(row).updated(col, stone))
    val newGameState = {
      val win = checkWin
      if win == GameState.XWon then GameState.XWon
      else if win == GameState.OWon then GameState.OWon
      else if newGrid.forall(_.forall(_ != Stone.Empty)) then GameState.Draw
      else GameState.Playing
    }
    new Board(newGrid, size, newGameState)
  }

  def isFull: Boolean = grid.forall(_.forall(_ != Stone.Empty))

}
