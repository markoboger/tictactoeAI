object TicTacToe {
  def main(args: Array[String]): Unit = {
    // Create players
    val player1 = Player("Alice")
    val player2 = Player("Bob")

    // Create board
    var board = new Board()

    // Display initial board
    println("\nInitial Board:")
    println(board.display())

    // Make some moves
    println("\nMaking moves...")
    board = board.makeMove(0, 0, "X")
    board = board.makeMove(1, 1, "O")
    board = board.makeMove(0, 1, "X")

    // Display updated board
    println("\nUpdated Board:")
    println(board.display())

    // Display players
    println("\nPlayers:")
    println(player1)
    println(player2)
  }
}
