import de.htwg.se.tictactoe.model.{Stone, GameState}

type StepInDirection = 0 | 1 | -1
enum Direction(val xStep: StepInDirection, val yStep: StepInDirection) {
  case Row extends Direction(0, 1)
  case Column extends Direction(1, 0)
  case MainDiagonal extends Direction(1, 1)
  case AntiDiagonal extends Direction(1, -1)
}

case class Board(
    cells: Vector[Vector[Stone]] = Vector.fill(3, 3)(Stone.Empty),
    size: Int = 3,
    gameState: GameState = GameState.Playing
):
  def this(size: Int) =
    this(Vector.fill(size, size)(Stone.Empty), size)

  def makeMove(row: Int, col: Int, stone: Stone): Board = {
    copy(cells.updated(row, cells(row).updated(col, stone)))
  }

  override def toString: String = {
    cells.map(_.map(_.toString).mkString("|", "", "|\n")).mkString("")
  }

  val directions: List[Direction] =
    import Direction._
    List(Row, Column, MainDiagonal, AntiDiagonal)

  def getCellsInDirection(
      xStart: Int,
      yStart: Int,
      dir: Direction
  ): List[Stone] =
    (for {
      i <- 0 until size
      x = dir.xStep * i + xStart
      y = dir.yStep * i + yStart
    } yield cells(x)(y)).toList

val board = new Board(3)
val updatedBoard = board
  .makeMove(0, 0, Stone.X)
  .makeMove(0, 1, Stone.X)
  .makeMove(0, 2, Stone.X)
  .makeMove(1, 0, Stone.O)
  .makeMove(1, 1, Stone.X)
  .makeMove(1, 2, Stone.O)
  .makeMove(2, 0, Stone.O)
  .makeMove(2, 1, Stone.X)
  .makeMove(2, 2, Stone.X)

updatedBoard.getCellsInDirection(0, 0, Direction.Row)
updatedBoard.getCellsInDirection(1, 0, Direction.Row)
updatedBoard.getCellsInDirection(2, 0, Direction.Row)
updatedBoard.getCellsInDirection(0, 0, Direction.Column)
updatedBoard.getCellsInDirection(0, 1, Direction.Column)
updatedBoard.getCellsInDirection(0, 2, Direction.Column)

def checkWin(board: Board): GameState = {
  val rows = for i <- 0 until board.size yield {
    board.getCellsInDirection(i, 0, Direction.Row)
  }.toList

  val columns = for j <- 0 until board.size yield {
    board.getCellsInDirection(0, j, Direction.Column)
  }.toList

  val mainDiagonal = board.getCellsInDirection(0, 0, Direction.MainDiagonal).toList
  val antiDiagonal = board.getCellsInDirection(0, board.size - 1, Direction.AntiDiagonal).toList

  val allLines = rows ++ columns ++ List(mainDiagonal) ++ List(antiDiagonal)

  if allLines.exists(_.forall(_ == Stone.X)) then GameState.XWon
  else if allLines.exists(_.forall(_ == Stone.O)) then GameState.OWon
  else if board.cells.forall(_.forall(_ != Stone.Empty)) then GameState.Draw
  else GameState.Playing
}

checkWin(updatedBoard)
