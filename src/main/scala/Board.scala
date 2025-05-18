class Board(
    private val board: Vector[Vector[String]] = Vector.fill(3, 3)(" ")
) {

  // Method to display the board using string interpolation and multiline strings
  def display(): String = {
    val horizontal = "+-------+-------+-------+"
    val vertical = "|       |       |       |"

    val rows = board
      .map(row => {
        val cells = row.mkString("|   ", "   |   ", "   |")
        s"$cells"
      })
      .mkString("\n")

    s"$horizontal\n$rows\n$horizontal"
  }

  // Method to make a move
  def makeMove(row: Int, col: Int, symbol: String): Board = {

    val newRow = board(row).updated(col, symbol)
    val newBoard = board.updated(row, newRow)
    new Board(newBoard)
  }
}
