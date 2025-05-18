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
      val board = new Board()
      val updatedBoard = board.makeMove(0, 0, Stone.X)
      updatedBoard.gameState shouldBe GameState.Playing
    }

    "detect wins" in {
      val board = new Board()
      val updatedBoard = board
        .makeMove(0, 0, Stone.X)
        .makeMove(0, 1, Stone.X)
        .makeMove(0, 2, Stone.X)
      updatedBoard.gameState shouldBe GameState.XWon
    }

    "detect draws" in {
      val board = new Board()
      val finalBoard = board
        .makeMove(0, 0, Stone.X)
        .makeMove(0, 1, Stone.O)
        .makeMove(0, 2, Stone.X)
        .makeMove(1, 0, Stone.O)
        .makeMove(1, 1, Stone.X)
        .makeMove(1, 2, Stone.O)
        .makeMove(2, 0, Stone.X)
        .makeMove(2, 1, Stone.O)
        .makeMove(2, 2, Stone.X)
      finalBoard.gameState shouldBe GameState.Draw
    }

    "have correct dimensions" in {
      val board = new Board()
      board.board.size shouldBe 3
      board.board.map(_.size).forall(_ == 3) shouldBe true
    }

    "work with different board sizes" in {
      val board4x4 = new Board(Vector.fill(4, 4)(Stone.Empty), 4)
      board4x4.board.size shouldBe 4
      board4x4.board.map(_.size).forall(_ == 4) shouldBe true

      val board5x5 = new Board(Vector.fill(5, 5)(Stone.Empty), 5)
      board5x5.board.size shouldBe 5
      board5x5.board.map(_.size).forall(_ == 5) shouldBe true
    }
  }
}
