package de.htwg.se.tictactoe.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class PlayerSpec extends AnyWordSpec with Matchers {
  "Player" should {
    "be created with a name and symbol" in {
      val player = new Player("Alice")
      player.name shouldBe "Alice"
      player.symbol shouldBe "X"
    }

    "have default score of 0" in {
      val player = new Player("Bob")
      player.score shouldBe 0
    }

    "update score correctly" in {
      val player = new Player("Charlie")
      player.score = 1
      player.score shouldBe 1
      player.score = 3
      player.score shouldBe 3
    }

  }
}
