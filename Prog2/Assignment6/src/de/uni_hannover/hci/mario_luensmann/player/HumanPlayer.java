package de.uni_hannover.hci.mario_luensmann.player;

import de.uni_hannover.hci.mario_luensmann.rboard.ReversiBoard;
import java.util.Scanner;

public class HumanPlayer extends ReversiPlayer {

	public HumanPlayer(String pID){
		setStoneIdentifier(pID);
		setClassName("HumanPlayer");
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
	
	private String changeString(String pString){
		char replacementChar = pString.charAt(2);			
		
		String myActualString = pString;
		
		char[] myCharArr = new char[8];
		
		for(int i = 0; i < myCharArr.length; i++){
			myCharArr[i] = (char) (i + 65);
		}
		
		if(!(replacementChar < (char) 65 || replacementChar > (char) 72)){
			
			String myStringReplacement = "";
			
			int counter = 1;
			
			for(char aChar : myCharArr){
				if(aChar == replacementChar){
					myActualString = myActualString.substring(0, 2);
					myStringReplacement = String.valueOf(counter);
					myActualString = myActualString + myStringReplacement;
					break;
				}
				else{
					counter++;
				}
			}
		}
		else{
			myActualString = myActualString.substring(0, 2) + "8";
		}
		
		return myActualString;
	}
	
	@Override
	public void nextMove(ReversiBoard pCurrentBoard) {
		// TODO Auto-generated method stub
		Scanner myKeyboardScanner = new Scanner(System.in);
		boolean checkInput = false;
		String input, stoneScorePrint;
		do{
			if(getStoneIdentifier() == "|X|"){
				stoneScorePrint = "X";
			}
			else{
				stoneScorePrint = "O";
			}
			System.out.println(" Player " + stoneScorePrint +"'s Turn: ");
			input = myKeyboardScanner.nextLine().trim();
			System.out.println();
			if(input.length() == 3 && input.charAt(1) == ' '){
				input = changeString(input);
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
						//myKeyboardScanner2.close();
					}
				}
			}
			if(!checkInput){
				pCurrentBoard.printBoard();
				System.out.println();
				System.out.println(" Invalid Turn");
				System.out.println();
				System.out.println(" Score: O " + pCurrentBoard.countStonesP2() + ":" + pCurrentBoard.countStonesP1() + " X");
			}
		}
		while(!checkInput);
		//myKeyboardScanner.close();
	}
	
}
