package de.htwg.se.tictactoe

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class BoardSpec extends AnyWordSpec with Matchers {
  "Board" should {
    "have a default empty board" in {
      val board = new Board()
      val expectedBoard = "+-------+-------+-------+\n" +
        "|       |       |       |\n" +
        "+-------+-------+-------+\n" +
        "|       |       |       |\n" +
        "+-------+-------+-------+\n" +
        "|       |       |       |\n" +
        "+-------+-------+-------+"
      board.display() shouldBe expectedBoard
    }

    "allow valid moves" in {
      val board = new Board()
      val updatedBoard = board.makeMove(0, 0, "X")
      updatedBoard.board(0)(0) shouldBe "X"
    }

    "not allow invalid moves" in {
      val board = new Board()
      val updatedBoard = board.makeMove(0, 0, "X")
      val finalBoard = updatedBoard.makeMove(0, 0, "O")
      finalBoard.board(0)(0) shouldBe "X"
    }

    "have correct dimensions" in {
      val board = new Board()
      board.board.size shouldBe 3
    }
  }
}
