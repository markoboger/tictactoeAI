package de.htwg.se.tictactoe.model

enum Stone private (symbol: Char):
  case X extends Stone('X')
  case O extends Stone('O')
  case Empty extends Stone(' ')
  def toChar: Char = symbol
  override def toString: String = symbol.toString

object Stone:
  def opponent(stone: Stone): Stone = stone match
    case Stone.X     => Stone.O
    case Stone.O     => Stone.X
    case Stone.Empty => Stone.Empty

enum GameState:
  case Playing, Draw, XWon, OWon

case class Move(row: Int, col: Int, stone: Stone)
