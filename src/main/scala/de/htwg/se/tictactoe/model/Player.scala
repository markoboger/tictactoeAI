package de.htwg.se.tictactoe.model

case class Player(
    val name: String,
    val symbol: String = "X", // Default symbol is X
    var score: Int = 0
) {
  // Private method that can only be accessed within this class
  private def updateScore(points: Int): Unit = {
    score += points
  }

  // Override toString method
  override def toString: String = s"Player(name: $name, score: $score)"

  // Public method to get current score
  def getScore: Int = score

  // Public method to display player info
  def displayInfo(): String = {
    s"Player: $name, Score: $score"
  }

  // Public method to make a move
  def makeMove(row: Int, col: Int): Unit = {
    println(s"$name makes move at ($row, $col)")
  }
}

// Companion object for Player class
object Player {

  // Static method to create multiple players
  def createPlayers(names: List[String]): List[Player] = {
    names.map(name => new Player(name))
  }

  // Factory method to create a new player
  def apply(name: String): Player = new Player(name)

}
