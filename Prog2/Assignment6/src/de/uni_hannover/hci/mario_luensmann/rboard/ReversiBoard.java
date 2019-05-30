package de.uni_hannover.hci.mario_luensmann.rboard;

import de.uni_hannover.hci.mario_luensmann.player.ReversiPlayer;
import javafx.util.Pair;
//import de.uni_hannover.hci.mario_luensmann.stones.ReversiStones;

public class ReversiBoard implements IBoard {
	
	private final int row = 8;
	private final int col = 8;
	private String[][] myBoard = new String[row][col];
	
	public ReversiBoard(){
		for(int i = 0; i < row; i++){
			for(int j = 0; j < col; j++){
				myBoard[i][j] = "|_|";
			}
		}
		myBoard[3][3] = "|O|";
		myBoard[3][4] = "|X|";
		myBoard[4][3] = "|X|";
		myBoard[4][4] = "|O|";
	}

	@Override
	public void printBoard() {
		for(int i = 0; i < row; i++) {
			if(i == 0) {
				for(int k = 0; k < row; k++ ) {
					if(k == 0) {
						System.out.print("   |" + ((char)65));
					} 
					else {
						System.out.print("||" + ((char) (65 + k)));
					} 
				} 
				System.out.println("|");
			}
			System.out.print("  " + (i + 1) + "");
			for(int j = 0; j < col; j++) {
				System.out.print(myBoard[i][j]);
			} 
			System.out.println();
		} 
		System.out.println();
	}
	
	private Pair<String, String> determinePlayer(ReversiPlayer pPlayer){
		String player2, player1;
		if(pPlayer.getStoneIdentifier().equals("|X|")){
			player2 = "|O|";
			player1 = "|X|";
		}
		else{
			player2 = "|X|";
			player1 = "|O|";
		}
		return new Pair<String, String>(player2, player1);
	}

	@Override
	public void updateBoard(int pRow, int pCol, ReversiPlayer pPlayer) {

		/*
		 * Determines each time who the actual current player1 and player2 is!
		 * Means checks for the String representation of the current player which is
		 * determined via the Switch function of the generic type parameter and generic
		 * type Method in ReversiMain!
		 */
		Pair<String, String> myPair = determinePlayer(pPlayer);
		/*
		 * To check where the current player1s possible move position is!
		 * Means where is the current |*| position from which directions we 
		 * need to check for the enemy position and its stones!
		 */
		int locateStones = 0;
		if(myBoard[pRow][pCol].equals("|*|") || pPlayer.getValidMoves()[pRow][pCol]){
			myBoard[pRow][pCol] = myPair.getValue(); //player1;
			locateStones = 1;
		}
		
		if(locateStones == 1){
			//checks right horizontally
			/*
			 * The algorithm is as follows: First it checks in the direction mentioned above.
			 * In this case it is right horizontally which means it goes from he start position
			 * of the current valid move like for example pRow and pCol and iterates from one column
			 * of pCol + 1 to the next Col which is pCol + 2. In that time of course the Row is fixed
			 * because in that current direction we check horizontally! If in that direction it does not
			 * find a player2 stone it simply checkSkips it and that as long as it does not hit the col
			 * Number which is set as a final value at 8 s.a.! Iff however checkSkip is == 0 then it 
			 * has found another stone which is probably an enemy stone of the opposite player!
			 * At this point it would in here check whether at the position pRow at index i it equals
			 * player2 and checkSkip == 0 which it of course needs to be then it checks checkStop == 1
			 * as a value to stop for when it has done its job of iterating and updating the board!
			 * Then it goes here in particular through the columns now because it sets i to j in order
			 * to check for the rest of the columns. The first if reassures that at that pRow and j position
			 * is an enemy stone and sets the checkstones to true or 1 and sets endFlip up 1 value!
			 * It continues and checks with the second if are there more then one enemy stone or
			 * probably empty spaces or another |*| valid move and if thats true it also sets 
			 * otherStones flag true but sets endFlip to 0 again, so that it can iterate a second
			 * time over the board in order to change for example situations where it would eat not
			 * only in horizontal but probably also a diagonical way so that it does both at the same
			 * time, Reversi Rules FTW! And the last if within the innermost for is there that if there
			 * is ONLY one enemy stone that it puts the flipper on which means put the flag flipStones
			 * to 1 so that the follow up if-statement can take care of it! The last if then checks for
			 * it and also rechecks that otherstones is still not 1 so that it does not only change 1
			 * place but multiple as has been mentioned above, and finally leaves the entire iteration
			 * process of updating the board in this particular case!
			 * 
			 * The Rest of the cases is very similar just has another direction to start off from!
			 */
			int checkStones = 0, otherStones = 0, flipStones = 0, endFlip = 0, checkSkip = 0, checkStop = 1;
			for(int i = pCol + 1; i < col; i++){
				if(!myBoard[pRow][i].equals(myPair.getKey())){
					checkSkip = 1;
				}
				if(myBoard[pRow][i].equals(myPair.getKey()) && checkSkip == 0){
					if(checkStop == 1){
						for(int j = i; j < col; j++){
							if(myBoard[pRow][j].equals(myPair.getKey())){
								checkStones = 1;
								endFlip++;
								continue;
							}
							if(myBoard[pRow][j].equals("|_|") || myBoard[pRow][j].equals("|*|") || (j == col - 1 && myBoard[pRow][j].equals(myPair.getKey()))){
								otherStones = 1;
								endFlip = 0;
							}
							if(myBoard[pRow][j].equals(myPair.getValue()) && checkStones == 1 && otherStones != 1){
								flipStones = 1;
								checkStop = 0;
								break;
							}
						}
					}
					if(flipStones == 1 && myBoard[pRow][i].equals(myPair.getKey()) && endFlip > 0){
						myBoard[pRow][i] = (myPair.getValue());
						endFlip--;
						continue;
					}
					else if(myBoard[pRow][i].equals(myPair.getValue())){
						break;
					}
				}
			}
			//checks left horizontally
			int checkStones2 = 0, otherStones2 = 0, flipStones2 = 0, endFlip2 = 0, checkSkip2 = 0, checkStop2 = 1;
			for(int i = pCol - 1; i >= 0; i--){
				if(!myBoard[pRow][i].equals(myPair.getKey())){
					checkSkip2 = 1;
				}
				if(myBoard[pRow][i].equals(myPair.getKey()) && checkSkip2 == 0){
					if(checkStop2 == 1){
						for(int j = i; j >= 0; j--){
							if(myBoard[pRow][j].equals(myPair.getKey())){
								checkStones2 = 1;
								endFlip2++;
								continue;
							}
							if(myBoard[pRow][j].equals("|_|") || myBoard[pRow][j].equals("|*|") || (j == 0 && myBoard[pRow][j].equals(myPair.getKey()))){
								otherStones2 = 1;
								endFlip2 = 0;
								break;
							}
							if(myBoard[pRow][j].equals(myPair.getValue()) && checkStones2 == 1 && otherStones2 != 1){
								flipStones2 = 1;
								checkStop2 = 0;
								break;
							}
						}
					}
					if(flipStones2 == 1 && myBoard[pRow][i].equals(myPair.getKey()) && endFlip2 > 0){
						myBoard[pRow][i] = (myPair.getValue());
						endFlip2--;
						continue;
					}
					else if(myBoard[pRow][i].equals(myPair.getValue())){
						break;
					}
				}
			}
			//checks down vertically
			int checkStones3 = 0, otherStones3 = 0, flipStones3 = 0, endFlip3 = 0, checkSkip3 = 0, checkStop3 = 1;
			for(int i = pRow + 1; i < row; i++){
				if(!myBoard[i][pCol].equals(myPair.getKey())){
					checkSkip3 = 1;
				}
				if(myBoard[i][pCol].equals(myPair.getKey()) && checkSkip3 == 0){
					if(checkStop3 == 1){
						for(int j = i; j < row; j++){
							if(myBoard[j][pCol].equals(myPair.getKey())){
								checkStones3 = 1;
								endFlip3++;
								continue;
							}
							if(myBoard[j][pCol].equals("|_|") || myBoard[j][pCol].equals("|*|") || (j == row - 1 && myBoard[j][pCol].equals(myPair.getKey()))){
								otherStones3 = 1;
								endFlip = 0;
								break;
							}
							if(myBoard[j][pCol].equals(myPair.getValue()) && checkStones3 == 1 && otherStones3 != 1){
								flipStones3 = 1;
								checkStop3 = 0;
								break;
							}
						}
					}
					if(flipStones3 == 1 && myBoard[i][pCol].equals(myPair.getKey()) && endFlip3 > 0){
						myBoard[i][pCol] = (myPair.getValue());
						endFlip3--;
						continue;
					}
					else if(myBoard[i][pCol].equals(myPair.getValue())){
						break;
					}
				}
			}
			//checks up vertically
			int checkStones4 = 0, otherStones4 = 0, flipStones4 = 0, endFlip4 = 0, checkSkip4 = 0, checkStop4 = 1;
			for(int i = pRow - 1; i >= 0; i--){
				if(!myBoard[i][pCol].equals(myPair.getKey())){
					checkSkip4 = 1;
				}
				if(myBoard[i][pCol].equals(myPair.getKey()) && checkSkip4 == 0){
					if(checkStop4 == 1){
						for(int j = i; j >= 0; j--){
							if(myBoard[j][pCol].equals(myPair.getKey()) && otherStones4 == 0){
								checkStones4 = 1;
								endFlip4++;
								continue;
							}
							if(myBoard[j][pCol].equals("|_|") || myBoard[j][pCol].equals("|*|") || (j == 0 && myBoard[j][pCol].equals(myPair.getKey()))){
								otherStones4 = 1;
								endFlip4 = 0;
								break;
							}
							if(myBoard[j][pCol].equals(myPair.getValue()) && checkStones4 == 1 && otherStones4 != 1){
								flipStones4 = 1;
								checkStop4 = 0;
								break;
							}
						}
					}
					if(flipStones4 == 1 && myBoard[i][pCol].equals(myPair.getKey()) && endFlip4 > 0){
						myBoard[i][pCol] = (myPair.getValue());
						endFlip4--;
						continue;
					}
					else if(myBoard[i][pCol].equals(myPair.getValue())){
						break;
					}
				}
			}
			//checks upper left diagonally			
			int checkStones5 = 0, otherStones5 = 0, flipStones5 = 0, endFlip5 = 0, checkSkip5 = 0, checkStop5 = 1;
			for(int i = pRow - 1, j = pCol - 1; i >= 0 && j >= 0; i--, j--){
				if(!myBoard[i][j].equals(myPair.getKey())){
					checkSkip5 = 1;
				}
				if(myBoard[i][j].equals(myPair.getKey()) && checkSkip5 == 0){
					if(checkStop5 == 1){
						for(int k = i, l = j; l >= 0 && k >= 0; l--, k--){
							if(myBoard[k][l].equals(myPair.getKey())){
								checkStones5 = 1;
								endFlip5++;
								continue;
							}
							if(myBoard[k][l].equals("|_|") || myBoard[k][l].equals("|*|") || ((k == 0 && j == 0) && myBoard[k][l].equals(myPair.getKey()))){
								otherStones5 = 1;
								endFlip5 = 0;
								break;
							}
							if(myBoard[k][l].equals(myPair.getValue()) && checkStones5 == 1 && otherStones5 != 1){
								flipStones5 = 1;
								checkStop5 = 0;
								break;
							}
						}
					}
					if(flipStones5 == 1 && myBoard[i][j].equals(myPair.getKey()) && endFlip5 > 0){
						myBoard[i][j] = (myPair.getValue());
						endFlip5--;
						continue;
					}
					else if(myBoard[i][j].equals(myPair.getValue())){
						break;
					}
				}
			}
			//checks upper right diagonally			
			int checkStones6 = 0, otherStones6 = 0, flipStones6 = 0, endFlip6 = 0, checkSkip6 = 0, checkStop6 = 1;
			for(int i = pRow - 1, j = pCol + 1; i >= 0 && j < col; i--, j++){
				if(!myBoard[i][j].equals(myPair.getKey())){
					checkSkip6 = 1;
				}
				if(myBoard[i][j].equals(myPair.getKey()) && checkSkip6 == 0){
					if(checkStop6 == 1){
						for(int k = i, l = j; l < col && k >= 0; l++, k--){
							if(myBoard[k][l].equals(myPair.getKey())){
								checkStones6 = 1;
								endFlip6++;
								continue;
							}
							if(myBoard[k][l].equals("|_|") || myBoard[k][l].equals("|*|") || ((k == 0 && j == col - 1) && myBoard[k][l].equals(myPair.getKey()))){
								otherStones6 = 1;
								endFlip6++;
								break;
							}
							if(myBoard[k][l].equals(myPair.getValue()) && checkStones6 == 1 && otherStones6 != 1){
								flipStones6 = 1;
								checkStop6 = 0;
								break;
							}
						}
					}
					if(flipStones6 == 1 && myBoard[i][j].equals(myPair.getKey()) && endFlip6 > 0){
						myBoard[i][j] = (myPair.getValue());
						endFlip6--;
						continue;
					}
					else if(myBoard[i][j].equals(myPair.getValue())){
						break;
					}
				}
			}
			//checks lower right diagonally			
			int checkStones7 = 0, otherStones7 = 0, flipStones7 = 0, endFlip7 = 0, checkSkip7 = 0, checkStop7 = 1;
			for(int i = pRow + 1, j = pCol + 1; i < row && j < col; i++, j++){
				if(!myBoard[i][j].equals(myPair.getKey())){
					checkSkip7 = 1;
				}
				if(myBoard[i][j].equals(myPair.getKey()) && checkSkip7 == 0){
					if(checkStop7 == 1){
						for(int k = i, l = j; l < col && k < row; l++, k++){
							if(myBoard[k][l].equals(myPair.getKey())){
								checkStones7 = 1;
								endFlip7++;
								continue;
							}
							if(myBoard[k][l].equals("|_|") || myBoard[k][l].equals("|*|") || ((k == row - 1 && j == col - 1) && myBoard[k][l].equals(myPair.getKey()))){
								otherStones7 = 1;
								endFlip7 = 0;
								break;
							}
							if(myBoard[k][l].equals(myPair.getValue()) && checkStones7 == 1 && otherStones7 != 1){
								flipStones7 = 1;
								checkStop7 = 0;
								break;
							}
						}
					}
					if(flipStones7 == 1 && myBoard[i][j].equals(myPair.getKey()) && endFlip7 > 0){
						myBoard[i][j] = (myPair.getValue());
						endFlip7--;
						continue;
					}
					else if(myBoard[i][j].equals(myPair.getValue())){
						break;
					}
				}
			}
			//checks lower left diagonally			
			int checkStones8 = 0, otherStones8 = 0, flipStones8 = 0, endFlip8 = 0, checkSkip8 = 0, checkStop8 = 1;
			for(int i = pRow + 1, j = pCol - 1; i < row && j >= 0; i++, j--){
				if(!myBoard[i][j].equals(myPair.getKey())){
					checkSkip8 = 1;
				}
				if(myBoard[i][j].equals(myPair.getKey()) && checkSkip8 == 0){
					if(checkStop8 == 1){
						for(int k = i, l = j; l >= 0 && k < row; l--, k++){
							if(myBoard[k][l].equals(myPair.getKey())){
								checkStones8 = 1;
								endFlip8++;
								continue;
							}
							if(myBoard[k][l].equals("|_|") || myBoard[k][l].equals("|*|") || ((k == row - 1 && j == 0) && myBoard[k][l].equals(myPair.getKey()))){
								otherStones8 = 1;
								endFlip8 = 0;
								break;
							}
							if(myBoard[k][l].equals(myPair.getValue()) && checkStones8 == 1 && otherStones8 != 1){
								flipStones8 = 1;
								checkStop8 = 0;
								break;
							}
						}
					}
					if(flipStones8 == 1 && myBoard[i][j].equals(myPair.getKey()) && endFlip8 > 0){
						myBoard[i][j] = (myPair.getValue());
						endFlip8--;
						continue;
					}
					else if(myBoard[i][j].equals(myPair.getValue())){
						break;
					}
				}
			}
		}
	}
	
	public boolean[][] getValidMovesPlayers(ReversiPlayer pPlayer){
		
		boolean[][] thePossibleMoves = new boolean[row][col];
		
		//final ReversiStones myStones = new ReversiStones();
		Pair<String, String> myPair = determinePlayer(pPlayer);
		
		for(int i = 0; i < row; i++){
			for(int j = 0; j < col; j++){
				
				int locateStones = 0;
				if(myBoard[i][j].equals(myPair.getValue())){
					locateStones = 1;
				}
				
				if(locateStones == 1){
					
					int continued = 1, foundOtherStone = 0;
					for(int k = j + 1; k < col; k++){
						if(myBoard[i][k].equals("|_|") && foundOtherStone == 0){
							continued = 0;
							break;
						}
						if(myBoard[i][k].equals(myPair.getKey()) && continued == 1){
							foundOtherStone = 1;
							continue;
						}
						else if(!myBoard[i][k].equals(myPair.getKey()) && !myBoard[i][k].equals("|_|") && continued == 1){
							break;
						}
						else if(myBoard[i][k].equals("|_|") && foundOtherStone == 1 && continued == 1){
							myBoard[i][k] = "|*|";
							thePossibleMoves[i][k] = true;
							break;
						}
					}
					
					int continued2 = 1, foundOtherStone2 = 0;
					for(int k = j - 1; k >= 0; k--){
						if(myBoard[i][k].equals("|_|") && foundOtherStone2 == 0){
							continued2 = 0;
							break;
						}
						if(myBoard[i][k].equals(myPair.getKey()) && continued2 == 1){
							foundOtherStone2 = 1;
							continue;
						}
						else if(!myBoard[i][k].equals(myPair.getKey()) && !myBoard[i][k].equals("|_|") && continued2 == 1){
							break;
						}
						else if(myBoard[i][k].equals("|_|") && foundOtherStone2 == 1 && continued2 == 1){
							myBoard[i][k] = "|*|";
							thePossibleMoves[i][k] = true;
							break;
						}
					}
					
					int continued3 = 1, foundOtherStone3 = 0;
					for(int k = i + 1; k < row; k++){
						if(myBoard[k][j].equals("|_|") && foundOtherStone3 == 0){
							continued3 = 0;
							break;
						}
						if(myBoard[k][j].equals(myPair.getKey()) && continued3 == 1){
							foundOtherStone3 = 1;
							continue;
						}
						else if(!myBoard[k][j].equals(myPair.getKey()) && !myBoard[k][j].equals("|_|") && continued3 == 1){
							break;
						}
						else if(myBoard[k][j].equals("|_|") && foundOtherStone3 == 1 && continued3 == 1){
							myBoard[k][j] = "|*|";
							thePossibleMoves[k][j] = true;
							break;
						}
					}
					
					int continued4 = 1, foundOtherStone4 = 0;
					for(int k = i - 1; k >= 0; k--){
						if(myBoard[k][j].equals("|_|") && foundOtherStone4 == 0){
							continued4 = 0;
							break;
						}
						if(myBoard[k][j].equals(myPair.getKey()) && continued4 == 1){
							foundOtherStone4 = 1;
							continue;
						}
						else if(!myBoard[k][j].equals(myPair.getKey()) && !myBoard[k][j].equals("|_|") && continued4 == 1){
							break;
						}
						else if(myBoard[k][j].equals("|_|") && foundOtherStone4 == 1 && continued4 == 1){
							myBoard[k][j] = "|*|";
							thePossibleMoves[k][j] = true;
							break;
						}
					}
					
					int continued5 = 1, foundOtherStone5 = 0;
					for(int k = i - 1, l = j - 1; k >= 0 && l >= 0; k--, l--){
						if(myBoard[k][l].equals("|_|") && foundOtherStone5 == 0){
							continued5 = 0;
							break;
						}
						if(myBoard[k][l].equals(myPair.getKey()) && continued5 == 1){
							foundOtherStone5 = 1;
							continue;
						}
						else if(!myBoard[k][l].equals(myPair.getKey()) && !myBoard[k][l].equals("|_|")){
							break;
						}
						else if(myBoard[k][l].equals("|_|") && foundOtherStone5 == 1){
							myBoard[k][l] = "|*|";
							thePossibleMoves[k][l] = true;
							break;
						}
					}
					
					int continued6 = 1, foundOtherStone6 = 0;
					for(int k = i - 1, l = j + 1; k >= 0 && l < col; k--, l++){
						if(myBoard[k][l].equals("|_|") && foundOtherStone6 == 0){
							continued6 = 0;
							break;
						}
						if(myBoard[k][l].equals(myPair.getKey()) && continued6 == 1){
							foundOtherStone6 = 1;
							continue;
						}
						else if(!myBoard[k][l].equals(myPair.getKey()) && !myBoard[k][l].equals("|_|")){
							break;
						}
						else if(myBoard[k][l].equals("|_|") && foundOtherStone6 == 1){
							myBoard[k][l] = "|*|";
							thePossibleMoves[k][l] = true;
							break;
						}
					}
					
					int continued7 = 1, foundOtherStone7 = 0;
					for(int k = i + 1, l = j + 1; k < row && l < col; k++, l++){
						if(myBoard[k][l].equals("|_|") && foundOtherStone7 == 0){
							continued7 = 0;
							break;
						}
						if(myBoard[k][l].equals(myPair.getKey()) && continued7 == 1){
							foundOtherStone7 = 1;
							continue;
						}
						else if(!myBoard[k][l].equals(myPair.getKey()) && !myBoard[k][l].equals("|_|")){
							break;
						}
						else if(myBoard[k][l].equals("|_|") && foundOtherStone7 == 1){
							myBoard[k][l] = "|*|";
							thePossibleMoves[k][l] = true;
							break;
						}
					}
					
					int continued8 = 1, foundOtherStone8 = 0;
					for(int k = i + 1, l = j - 1; k < row && l >= 0; k++, l--){
						if(myBoard[k][l].equals("|_|") && foundOtherStone8 == 0){
							continued8 = 0;
							break;
						}
						if(myBoard[k][l].equals(myPair.getKey()) && continued8 == 1){
							foundOtherStone8 = 1;
							continue;
						}
						else if(!myBoard[k][l].equals(myPair.getKey()) && !myBoard[k][l].equals("|_|")){
							break;
						}
						else if(myBoard[k][l].equals("|_|") && foundOtherStone8 == 1){
							myBoard[k][l] = "|*|";
							thePossibleMoves[k][l] = true;
							break;
						}
					}
				}
			}
		}
		
		return thePossibleMoves;
	}
	
	public void clearValues(){
		for(int i = 0; i < row; i++){
			for(int j = 0; j < col; j++){
				if(myBoard[i][j] == "|*|"){
					myBoard[i][j] = "|_|";
				}
			}
		}
	}
	
	public String[][] getBoard(){
		return this.myBoard;
	}
	
	public int countStonesP1(){
		int stonesP1 = 0;
		for(int i = 0; i < myBoard.length; i++){
			for(int j = 0; j < myBoard[i].length; j++){
				if(myBoard[i][j] == "|X|"){
					stonesP1++;
				}
			}
		}
		return stonesP1;
	}
	
	public int countStonesP2(){
		int stonesP2 = 0;
		for(int i = 0; i < myBoard.length; i++){
			for(int j = 0; j < myBoard[i].length; j++){
				if(myBoard[i][j] == "|O|"){
					stonesP2++;
				}
			}
		}
		return stonesP2;
	}

	@Override
	public boolean isFull() {
		return (countStonesP1() + countStonesP2()) == myBoard.length * myBoard.length;
	}

}
