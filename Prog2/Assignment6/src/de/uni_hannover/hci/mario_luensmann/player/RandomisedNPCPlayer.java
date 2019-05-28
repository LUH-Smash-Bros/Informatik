package de.uni_hannover.hci.mario_luensmann.player;

import de.uni_hannover.hci.mario_luensmann.rboard.ReversiBoard;

import java.util.Random;

public class RandomisedNPCPlayer extends NPCPlayer {
	
	public RandomisedNPCPlayer(String pID) {
		setStoneIdentifier(pID);
		setClassName("NPCPlayer");
	}

	@Override
	public void nextMove(ReversiBoard pCurrentBoard) {
		
		Random numGen = new Random();
		
		int countMin = 20; // a number that is always greater then the amount of valid moves
		int countMax = numGen.nextInt(30) + countMin; // the random number that validMoveCount must reach before the loop stops
		
		int validMoveCount = 0;
		
		while(validMoveCount <= countMax) {
			for(int i = 0; i < validMoves.length; i++) {
				for(int j = 0; j < validMoves[i].length; j++) {
					if(validMoves[i][j]) {
						validMoveCount++;
						if(validMoveCount == countMax) {
							createTimeDelay(3000);
							System.out.print((i+1) + " " + (j+1)); // adding 1 takes into account the board doesn't start at 0,0
							System.out.println("\n");
							pCurrentBoard.updateBoard(i, j, this);
							pCurrentBoard.clearValues();
							return;
						}
					}
				}
			}
		}
	}

	@Override
	public boolean isStillAValidMove() {
		
		boolean isAValidMove = false;
		
		for(int i = 0; i < validMoves.length; i++){
			for(int j = 0; j < validMoves[i].length; j++){
				if(validMoves[i][j]){
					isAValidMove = true;
				}
			}
		}
		
		return isAValidMove;
	}

}
