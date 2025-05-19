package de.htwg.se.tictactoe.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class BoardSpec extends AnyWordSpec with Matchers {
  "Board" should {
    "have a default empty board" in {
      val board = new Board(size = 3)
      val expectedBoard = "+-------+-------+-------+\n" +
        "|       |       |       |\n" +
        "+-------+-------+-------+\n" +
        "|       |       |       |\n" +
        "+-------+-------+-------+\n" +
        "|       |       |       |\n" +
        "+-------+-------+-------+\n"
      board.display(cellSize = 7) shouldBe expectedBoard
    }

    "allow moves" in {
      val board = new Board(size = 3)
      val updatedBoard = board.makeMove(0, 0, Stone.X)
      updatedBoard.grid(0)(0) shouldBe Stone.X
      updatedBoard.gameState shouldBe GameState.Playing
    }

    "detect a full board" in {
      val board1x1 = new Board(size = 1)
      board1x1.checkWin shouldBe GameState.Playing
      board1x1.makeMove(0, 0, Stone.X).checkWin shouldBe GameState.XWon

      val board3x3 = new Board(size = 3)
      board3x3.checkWin shouldBe GameState.Playing
      val fullBoard = board3x3
        .makeMove(0, 0, Stone.X)
        .makeMove(0, 1, Stone.O)
        .makeMove(0, 2, Stone.X)
        .makeMove(1, 0, Stone.O)
        .makeMove(1, 1, Stone.X)
        .makeMove(1, 2, Stone.O)
        .makeMove(2, 0, Stone.O)
        .makeMove(2, 1, Stone.X)
        .makeMove(2, 2, Stone.O)
      fullBoard.checkWin shouldBe GameState.Draw
    }

    "detect a game is ongoing" in {
      val board = new Board(size = 3)
      board.gameState shouldBe GameState.Playing
      board.makeMove(0, 0, Stone.X).gameState shouldBe GameState.Playing
    }

    "detect wins" in {

      val board1 = new Board(size = 1)
      val updatedBoard1 = board1.makeMove(0, 0, Stone.X)
      updatedBoard1.checkWin shouldBe GameState.XWon

      val board2 = new Board(size = 1)
      val updatedBoard2 = board2.makeMove(0, 0, Stone.O)
      updatedBoard2.checkWin shouldBe GameState.OWon

      val board3 = new Board(size = 3)
      val updatedBoard3 = board3
        .makeMove(0, 0, Stone.X)
        .makeMove(0, 1, Stone.X)
        .makeMove(0, 2, Stone.X)
      updatedBoard3.checkWin shouldBe GameState.XWon
    }

    "detect draws" in {
      val board4 = new Board(size = 3)
      val finalBoard = board4
        .makeMove(0, 0, Stone.X)
        .makeMove(0, 1, Stone.O)
        .makeMove(0, 2, Stone.X)
        .makeMove(1, 0, Stone.O)
        .makeMove(1, 1, Stone.X)
        .makeMove(1, 2, Stone.O)
        .makeMove(2, 0, Stone.O)
        .makeMove(2, 1, Stone.X)
        .makeMove(2, 2, Stone.O)
      finalBoard.checkWin shouldBe GameState.Draw
    }

    "have correct dimensions" in {
      val board = new Board()
      board.grid.size shouldBe 3
      board.grid.map(_.size).forall(_ == 3) shouldBe true
    }

    "work with different board sizes" in {
      val board1x1 = new Board(Vector.fill(1, 1)(Stone.Empty), 1)
      board1x1.grid.size shouldBe 1
      board1x1.grid.map(_.size).forall(_ == 1) shouldBe true

      val board3x3 = new Board(Vector.fill(3, 3)(Stone.Empty), 3)
      board3x3.grid.size shouldBe 3
      board3x3.grid.map(_.size).forall(_ == 3) shouldBe true

      val board5x5 = new Board(Vector.fill(5, 5)(Stone.Empty), 5)
      board5x5.grid.size shouldBe 5
      board5x5.grid.map(_.size).forall(_ == 5) shouldBe true
    }
  }
}
