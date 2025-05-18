package de.htwg.se.tictactoe

case class Board(
    board: Vector[Vector[String]] = Vector.fill(3, 3)(" ")
) {

  // Method to display the board using string interpolation and multiline strings
  def display(): String = {
    val horizontal = "+-------+-------+-------+"
    val rows = board
      .map(row => {
        val cells = row.map(cell => s" $cell ").mkString("|  ", "  |  ", "  |")
        s"$cells\n$horizontal"
      })
      .mkString(s"$horizontal\n", "\n", "")
    rows
  }

  // Method to make a move
  def makeMove(row: Int, col: Int, symbol: String): Board = {
    if (row < 0 || row >= 3 || col < 0 || col >= 3 || board(row)(col) != " ") {
      this
    } else {
      val newRow = board(row).updated(col, symbol)
      val newBoard = board.updated(row, newRow)
      new Board(newBoard)
    }
  }
}
