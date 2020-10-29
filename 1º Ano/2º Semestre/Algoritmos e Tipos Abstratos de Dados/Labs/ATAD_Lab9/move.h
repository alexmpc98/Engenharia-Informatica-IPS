#pragma once

typedef struct move{
  int x; 
  int y;
}Move;

/**
 * @brief Print information about a specific Move.
 * 
 * The format is "(x,y)"".
 * 
 * @param move The Move to be printed.
 */
void printMove(Move move);

/**
 * @brief Creates a new Move
 * 
 * @param x   from tower number
 * @param y   to tower number
 
 * @return The new created movement.
 */

Move createMove(int x, int y);