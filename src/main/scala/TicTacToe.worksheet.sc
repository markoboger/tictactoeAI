// TicTacToe String Examples
1 + 2

// 1. Basic String Output
println("Hello World!")
println("Welcome to Tic Tac Toe!")
println("Let's learn about strings in Scala!")

// 2. String Concatenation
val playerName = "Player X"
println("Welcome " + playerName + "!")
println("Your turn, " + playerName)

// 3. String Interpolation
println(s"Welcome $playerName!")
val score = 10
println(s"Your score is $score points")

// 4. Multiline Strings
val gameRules = """Tic Tac Toe Rules:
      |1. Players take turns
      |2. Place X or O on the board
      |3. First to get 3 in a row wins""".stripMargin
println("\nGame Rules:")
println(gameRules)

// 5. String Formatting with Colors
enum Color(val code: String):
  case RED extends Color("\u001b[31m")
  case GREEN extends Color("\u001b[32m")
  case BLUE extends Color("\u001b[34m")
  case RESET extends Color("\u001b[0m")

import Color.*

println("\nColored Text:")
println(s"${RED.code}Red text${RESET.code}")
println(s"${GREEN.code}Green text${RESET.code}")
println(s"${BLUE.code}Blue text${RESET.code}")

// 6. Board Display
val horizontal = "+-------+-------+-------+"
val vertical = "|       |       |       |"
val boardString =
  s"$horizontal\n$vertical\n$horizontal\n$vertical\n$horizontal\n$vertical\n$horizontal"
println("\nEmpty Board:")
println(boardString)

// 7. Centering Text
def centerText(text: String, width: Int = 40): String = {
  val padding = (width - text.length) / 2
  " " * padding + text + " " * padding
}

val welcome = "Welcome to Tic Tac Toe!"
println("\nCentered Text:")
println(centerText(welcome))
