/**
 * @file hanoiGame.h
 * @brief Definition of the hanoiGame
 * 
 * Defines the type PtHanoiGame and associated operations.
 * 
 * @author Patricia Macedo 
 * @bug No known bugs.
 */

#pragma once
#include "disk.h"
#include "move.h"
#include "stack.h"
#include <stdbool.h>

#define HANOI_OK        0 
#define HANOI_NULL      1
#define HANOI_INVALID   2

#define TOWERS 3

struct hanoiGame;

typedef struct hanoiGame* PtHanoiGame;

/**
 * @brief Creates a new Hanoi Game
 * 
 * initialize the three towers of the game
 *
 * @return HanoiGame pointer to allocated data structure
 */
PtHanoiGame hanoiGameCreate();

/**
 * @brief validate if the towerNumber is valid
 * 
 * validate if the towerNumber is >0 and <3
 *
 * @return true if valid
 */

bool hanoiGameValidatePos(int towerNumber);

/**
 * @brief initialize the Hanoi Game , pout the disks on the forst tower
 * 
 * @param ptGame -[in] pointer to the game to initialize
 * @param n - number of disks in the game
 *
 * @return HANOI_NULL if ptGame is NULL , HANNOI_OK otherwise
 */

int hanoiGameinit(PtHanoiGame ptGame,int n);

/**
 * @brief Prints the contents of the game: the content of each tower
 * 
 * @param ptGame [in] pointer to the stack
 */
int hanoiGamePrint(PtHanoiGame ptGame);
/**
 * @brief make a move in the game
 * 
 * @param ptGame [in]- Pointer to the game
 * @param move - receive the move to execute in the Game
 *
 * @return HANOI_NULL if ptGame is NULL , HANOI_INVALID if the move is not allowed acoording to Hanoi Game rules, HANOI_OK otherwise
 */

int hanoiGameMakeMove(PtHanoiGame ptGame, Move move);

/**
 * @brief check if the game is over
 * 
 * @param ptGame [in]- Pointer to the game
 * 
 * @return true if the game is complete false otherwise. 
 */
 
bool hanoiGameCheckGameOver(PtHanoiGame ptGame);

/**
 * @brief reinitialize the game , put all the disks in the first tower.
 * 
 * @param ptGame [in]- Pointer to the game
 * 
 */


/**
 * @brief replay the movements of a game.Execute from an initial game,all the movements saved.
 * 
 * @param ptGame [in] ADDRESS OF pointer to the ptGame
 */

void hanoiReplayGame(PtHanoiGame ptGame);

/**
 * @brief devolves the number of moves executed
 * 
 * @param ptGame [in] ADDRESS OF pointer to the ptGame
 * @return int number of moves executed
 */


int  numberOfMovementsDone(PtHanoiGame ptGame);//nivel 5

/**
 * @brief Free all resources of a ptGame.
 * 
 * @param ptGame [in] ADDRESS OF pointer to the ptGame
 * 
 */
void hanoiGameDestroy(PtHanoiGame* ptGame);

