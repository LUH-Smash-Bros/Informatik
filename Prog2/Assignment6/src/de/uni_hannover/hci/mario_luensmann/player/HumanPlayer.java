package de.uni_hannover.hci.mario_luensmann.player;

import de.uni_hannover.hci.mario_luensmann.rboard.ReversiBoard;
//import de.uni_hannover.hci.mario_luensmann.stones.ReversiStones;
import java.util.Scanner;

public class HumanPlayer extends ReversiPlayer {

	//public ReversiStones myStoneIdentifiers;
	
	public HumanPlayer(String pID){
		//myStoneIdentifiers.setStoneIdentifier(pID);
		setStoneIdentifier(pID);
		setClassName("HumanPlayer");
	}
	
	@Override
	public boolean isStillAValidMove() {
		// TODO Auto-generated method stub
		
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
	
	@Override
	public void nextMove(ReversiBoard pCurrentBoard) {
		// TODO Auto-generated method stub
		Scanner myKeyboardScanner = new Scanner(System.in);
		boolean checkInput = false;
		String input;
		do{
			System.out.println(" Player " + getStoneIdentifier() +"'s Turn: ");
			input = myKeyboardScanner.nextLine().trim();
			System.out.println();
			if(input.length() == 3 && input.charAt(1) == ' '){
				Scanner myKeyboardScanner2 = new Scanner(input);
				if(myKeyboardScanner2.hasNextInt()){
					int row = myKeyboardScanner2.nextInt() - 1;
					if(myKeyboardScanner2.hasNextInt()){
						int col = myKeyboardScanner2.nextInt() - 1;
						if(row < pCurrentBoard.getBoard().length && col < pCurrentBoard.getBoard()[0].length && row >= 0 && col >= 0){
							if(validMoves[row][col]){
								pCurrentBoard.updateBoard(row, col, this);
								pCurrentBoard.clearValues();
								checkInput = true;
							}
						}
					}
				}
				//myKeyboardScanner2.close();
			}
			//myKeyboardScanner.close();
			if(!checkInput){
				System.out.println();
				System.out.println("Invalid Turn");
				System.out.println();
				pCurrentBoard.printBoard();
			}
		}
		while(!checkInput);
	}
	
}
