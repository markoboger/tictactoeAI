package de.htwg.se.tictactoe

import de.htwg.se.tictactoe.model.{Stone, Board}

object TicTacToe extends App {
  // Create a new board with size 4x4
  println("Creating a new board...")
  var board = new Board(Vector.fill(4, 4)(Stone.Empty), 4)

  // Display initial board
  println("\nInitial Board:")
  println(board.display())

  // Make some moves
  println("\nMaking moves...")
  board = board.makeMove(0, 0, Stone.X)
  board = board.makeMove(1, 1, Stone.O)
  board = board.makeMove(0, 1, Stone.X)

  // Display updated board
  println("\nUpdated Board:")
  println(board.display())

  // Check game state
  println("\nGame State:")
  println(board.gameState)

  // Create a 5x5 board
  println("\nCreating a 5x5 board...")
  var board5x5 = new Board(Vector.fill(5, 5)(Stone.Empty), 5)
  println(board5x5.display())
}
