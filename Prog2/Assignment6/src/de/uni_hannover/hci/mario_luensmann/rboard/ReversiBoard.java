package de.uni_hannover.hci.mario_luensmann.rboard;

import de.uni_hannover.hci.mario_luensmann.player.ReversiPlayer;
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
		myBoard[3][3] = "|X|";
		myBoard[3][4] = "|O|";
		myBoard[4][3] = "|O|";
		myBoard[4][4] = "|X|";
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

	@Override
	public void updateBoard(int pRow, int pCol, ReversiPlayer pPlayer) {
		// TODO Auto-generated method stub
		//final ReversiStones myStones = new ReversiStones();
		String player2, player1;
		if(pPlayer.getStoneIdentifier().equals("|X|")){
			player2 = "|O|";
			player1 = "|X|";
		}
		else{
			player2 = "|X|";
			player1 = "|O|";
		}
		
		int locateStones = 0;
		if(myBoard[pRow][pCol].equals("|*|") || pPlayer.getValidMoves()[pRow][pCol]){
			myBoard[pRow][pCol] = player1;
			locateStones = 1;
		}
		
		if(locateStones == 1){
			//checks right horizontally
			int checkStones = 0, otherStones = 0, flipStones = 0, endFlip = 0, checkSkip = 0, checkStop = 1;
			for(int i = pCol + 1; i < pCol; i++){
				if(!myBoard[pRow][i].equals(player2)){
					checkSkip = 1;
				}
				if(myBoard[pRow][i].equals(player2) && checkSkip == 0){
					if(checkStop == 1){
						for(int j = i; j < col; j++){
							if(myBoard[pRow][j].equals(player2)){
								checkStones = 1;
								endFlip++;
								continue;
							}
							if(myBoard[pRow][j].equals("|_|") || myBoard[pRow][j].equals("|*|") || (j == col - 1 && myBoard[pRow][j].equals(player2))){
								otherStones = 1;
								endFlip = 0;
							}
							if(myBoard[pRow][j].equals(player1) && checkStones == 1 && otherStones != 1){
								flipStones = 1;
								checkStop = 0;
								break;
							}
						}
					}
					if(flipStones == 1 && myBoard[pRow][i].equals(player2) && endFlip > 0){
						myBoard[pRow][i] = (player1);
						endFlip--;
						continue;
					}
					else if(myBoard[pRow][i].equals(player1)){
						break;
					}
				}
			}
			//checks left horizontally
			int checkStones2 = 0, otherStones2 = 0, flipStones2 = 0, endFlip2 = 0, checkSkip2 = 0, checkStop2 = 1;
			for(int i = pCol - 1; i >= 0; i--){
				if(!myBoard[pRow][i].equals(player2)){
					checkSkip2 = 1;
				}
				if(myBoard[pRow][i].equals(player2) && checkSkip2 == 0){
					if(checkStop2 == 1){
						for(int j = i; j >= 0; j--){
							if(myBoard[pRow][j].equals(player2)){
								checkStones2 = 1;
								endFlip2++;
								continue;
							}
							if(myBoard[pRow][j].equals("|_|") || myBoard[pRow][j].equals("|*|") || (j == 0 && myBoard[pRow][j].equals(player2))){
								otherStones2 = 1;
								endFlip2 = 0;
								break;
							}
							if(myBoard[pRow][j].equals(player1) && checkStones2 == 1 && otherStones2 != 1){
								flipStones2 = 1;
								checkStop2 = 0;
								break;
							}
						}
					}
					if(flipStones2 == 1 && myBoard[pRow][i].equals(player2) && endFlip2 > 0){
						myBoard[pRow][i] = (player1);
						endFlip2--;
						continue;
					}
					else if(myBoard[pRow][i].equals(player1)){
						break;
					}
				}
			}
			//checks down vertically
			int checkStones3 = 0, otherStones3 = 0, flipStones3 = 0, endFlip3 = 0, checkSkip3 = 0, checkStop3 = 1;
			for(int i = pRow + 1; i < row; i++){
				if(!myBoard[i][pCol].equals(player2)){
					checkSkip3 = 1;
				}
				if(myBoard[i][pCol].equals(player2) && checkSkip3 == 0){
					if(checkStop3 == 1){
						for(int j = i; j < row; j++){
							if(myBoard[j][pCol].equals(player2)){
								checkStones3 = 1;
								endFlip3++;
								continue;
							}
							if(myBoard[j][pCol].equals("|_|") || myBoard[j][pCol].equals("|*|") || (j == row - 1 && myBoard[j][pCol].equals(player2))){
								otherStones3 = 1;
								endFlip = 0;
								break;
							}
							if(myBoard[j][pCol].equals(player1) && checkStones3 == 1 && otherStones3 != 1){
								flipStones3 = 1;
								checkStop3 = 0;
								break;
							}
						}
					}
					if(flipStones3 == 1 && myBoard[i][pCol].equals(player2) && endFlip3 > 0){
						myBoard[i][pCol] = (player1);
						endFlip3--;
						continue;
					}
					else if(myBoard[i][pCol].equals(player1)){
						break;
					}
				}
			}
			//checks up vertically
			int checkStones4 = 0, otherStones4 = 0, flipStones4 = 0, endFlip4 = 0, checkSkip4 = 0, checkStop4 = 1;
			for(int i = pRow - 1; i >= 0; i--){
				if(!myBoard[i][pCol].equals(player2)){
					checkSkip4 = 1;
				}
				if(myBoard[i][pCol].equals(player2) && checkSkip4 == 0){
					if(checkStop4 == 1){
						for(int j = i; j >= 0; j--){
							if(myBoard[j][pCol].equals(player2) && otherStones4 == 0){
								checkStones4 = 1;
								endFlip4++;
								continue;
							}
							if(myBoard[j][pCol].equals("|_|") || myBoard[j][pCol].equals("|*|") || (j == 0 && myBoard[j][pCol].equals(player2))){
								otherStones4 = 1;
								endFlip4 = 0;
								break;
							}
							if(myBoard[j][pCol].equals(player1) && checkStones4 == 1 && otherStones4 != 1){
								flipStones4 = 1;
								checkStop4 = 0;
								break;
							}
						}
					}
					if(flipStones4 == 1 && myBoard[i][pCol].equals(player2) && endFlip4 > 0){
						myBoard[i][pCol] = (player1);
						endFlip4--;
						continue;
					}
					else if(myBoard[i][pCol].equals(player1)){
						break;
					}
				}
			}
			//checks upper left diagonally			
			int checkStones5 = 0, otherStones5 = 0, flipStones5 = 0, endFlip5 = 0, checkSkip5 = 0, checkStop5 = 1;
			for(int i = pRow - 1, j = pCol - 1; i >= 0 && j >= 0; i--, j--){
				if(!myBoard[i][j].equals(player2)){
					checkSkip5 = 1;
				}
				if(myBoard[i][j].equals(player2) && checkSkip5 == 0){
					if(checkStop5 == 1){
						for(int k = i, l = j; l >= 0 && k >= 0; l--, k--){
							if(myBoard[k][l].equals(player2)){
								checkStones5 = 1;
								endFlip5++;
								continue;
							}
							if(myBoard[k][l].equals("|_|") || myBoard[k][l].equals("|*|") || ((k == 0 && j == 0) && myBoard[k][l].equals(player2))){
								otherStones5 = 1;
								endFlip5 = 0;
								break;
							}
							if(myBoard[k][l].equals(player1) && checkStones5 == 1 && otherStones5 != 1){
								flipStones5 = 1;
								checkStop5 = 0;
								break;
							}
						}
					}
					if(flipStones5 == 1 && myBoard[i][j].equals(player2) && endFlip5 > 0){
						myBoard[i][j] = (player1);
						endFlip5--;
						continue;
					}
					else if(myBoard[i][j].equals(player1)){
						break;
					}
				}
			}
			//checks upper right diagonally			
			int checkStones6 = 0, otherStones6 = 0, flipStones6 = 0, endFlip6 = 0, checkSkip6 = 0, checkStop6 = 1;
			for(int i = pRow - 1, j = pCol + 1; i >= 0 && j < col; i--, j++){
				if(!myBoard[i][j].equals(player2)){
					checkSkip6 = 1;
				}
				if(myBoard[i][j].equals(player2) && checkSkip6 == 0){
					if(checkStop6 == 1){
						for(int k = i, l = j; l < col && k >= 0; l++, k--){
							if(myBoard[k][l].equals(player2)){
								checkStones6 = 1;
								endFlip6++;
								continue;
							}
							if(myBoard[k][l].equals("|_|") || myBoard[k][l].equals("|*|") || ((k == 0 && j == col - 1) && myBoard[k][l].equals(player2))){
								otherStones6 = 1;
								endFlip6++;
								break;
							}
							if(myBoard[k][l].equals(player1) && checkStones6 == 1 && otherStones6 != 1){
								flipStones6 = 1;
								checkStop6 = 0;
								break;
							}
						}
					}
					if(flipStones6 == 1 && myBoard[i][j].equals(player2) && endFlip6 > 0){
						myBoard[i][j] = (player1);
						endFlip6--;
						continue;
					}
					else if(myBoard[i][j].equals(player1)){
						break;
					}
				}
			}
			//checks lower right diagonally			
			int checkStones7 = 0, otherStones7 = 0, flipStones7 = 0, endFlip7 = 0, checkSkip7 = 0, checkStop7 = 1;
			for(int i = pRow + 1, j = pCol + 1; i < row && j < col; i++, j++){
				if(!myBoard[i][j].equals(player2)){
					checkSkip7 = 1;
				}
				if(myBoard[i][j].equals(player2) && checkSkip7 == 0){
					if(checkStop7 == 1){
						for(int k = i, l = j; l < col && k < row; l++, k++){
							if(myBoard[k][l].equals(player2)){
								checkStones7 = 1;
								endFlip7++;
								continue;
							}
							if(myBoard[k][l].equals("|_|") || myBoard[k][l].equals("|*|") || ((k == row - 1 && j == col - 1) && myBoard[k][l].equals(player2))){
								otherStones7 = 1;
								endFlip7 = 0;
								break;
							}
							if(myBoard[k][l].equals(player1) && checkStones7 == 1 && otherStones7 != 1){
								flipStones7 = 1;
								checkStop7 = 0;
								break;
							}
						}
					}
					if(flipStones7 == 1 && myBoard[i][j].equals(player2) && endFlip7 > 0){
						myBoard[i][j] = (player1);
						endFlip7--;
						continue;
					}
					else if(myBoard[i][j].equals(player1)){
						break;
					}
				}
			}
			//checks lower left diagonally			
			int checkStones8 = 0, otherStones8 = 0, flipStones8 = 0, endFlip8 = 0, checkSkip8 = 0, checkStop8 = 1;
			for(int i = pRow + 1, j = pCol - 1; i < row && j >= 0; i++, j--){
				if(!myBoard[i][j].equals(player2)){
					checkSkip8 = 1;
				}
				if(myBoard[i][j].equals(player2) && checkSkip8 == 0){
					if(checkStop8 == 1){
						for(int k = i, l = j; l >= 0 && k < row; l--, k++){
							if(myBoard[k][l].equals(player2)){
								checkStones8 = 1;
								endFlip8++;
								continue;
							}
							if(myBoard[k][l].equals("|_|") || myBoard[k][l].equals("|*|") || ((k == row - 1 && j == 0) && myBoard[k][l].equals(player2))){
								otherStones8 = 1;
								endFlip8 = 0;
								break;
							}
							if(myBoard[k][l].equals(player1) && checkStones8 == 1 && otherStones8 != 1){
								flipStones8 = 1;
								checkStop8 = 0;
								break;
							}
						}
					}
					if(flipStones8 == 1 && myBoard[i][j].equals(player2) && endFlip8 > 0){
						myBoard[i][j] = (player1);
						endFlip8--;
						continue;
					}
					else if(myBoard[i][j].equals(player1)){
						break;
					}
				}
			}	
		}		
	}
	
	public boolean[][] getValidMovesPlayers(ReversiPlayer pPlayer){
		
		boolean[][] thePossibleMoves = new boolean[row][col];
		
		//final ReversiStones myStones = new ReversiStones();
		String player2, player1;
		if(pPlayer.getStoneIdentifier().equals("|X|")){
			player2 = "|O|";
			player1 = "|X|";
		}
		else{
			player2 = "|X|";
			player1 = "|O|";
		}
		
		for(int i = 0; i < row; i++){
			for(int j = 0; j < col; j++){
				
				int locateStones = 0;
				if(myBoard[i][j].equals(player1)){
					locateStones = 1;
				}
				
				if(locateStones == 1){
					
					int continued = 1, foundOtherStone = 0;
					for(int k = j + 1; k < col; k++){
						if(myBoard[i][k].equals("|_|") && foundOtherStone == 0){
							continued = 0;
							break;
						}
						if(myBoard[i][k].equals(player2) && continued == 1){
							foundOtherStone = 1;
							continue;
						}
						else if(!myBoard[i][k].equals(player2) && !myBoard[i][k].equals("|_|") && continued == 1){
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
						if(myBoard[i][k].equals(player2) && continued2 == 1){
							foundOtherStone2 = 1;
							continue;
						}
						else if(!myBoard[i][k].equals(player2) && !myBoard[i][k].equals("|_|") && continued2 == 1){
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
						if(myBoard[k][j].equals(player2) && continued3 == 1){
							foundOtherStone3 = 1;
							continue;
						}
						else if(!myBoard[k][j].equals(player2) && !myBoard[k][j].equals("|_|") && continued3 == 1){
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
						if(myBoard[k][j].equals(player2) && continued4 == 1){
							foundOtherStone4 = 1;
							continue;
						}
						else if(!myBoard[k][j].equals(player2) && !myBoard[k][j].equals("|_|") && continued4 == 1){
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
						if(myBoard[k][l].equals(player2) && continued5 == 1){
							foundOtherStone5 = 1;
							continue;
						}
						else if(!myBoard[k][l].equals(player2) && !myBoard[k][l].equals("_")){
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
						if(myBoard[k][l].equals(player2) && continued6 == 1){
							foundOtherStone6 = 1;
							continue;
						}
						else if(!myBoard[k][l].equals(player2) && !myBoard[k][l].equals("|_|")){
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
						if(myBoard[k][l].equals(player2) && continued7 == 1){
							foundOtherStone7 = 1;
							continue;
						}
						else if(!myBoard[k][l].equals(player2) && !myBoard[k][l].equals("|_|")){
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
						if(myBoard[k][l].equals(player2) && continued8 == 1){
							foundOtherStone8 = 1;
							continue;
						}
						else if(!myBoard[k][l].equals(player2) && !myBoard[k][l].equals("|_|")){
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
				/*else if(myBoard[i][j] == "|O|"){
					myBoard[i][j] = "|_|";
				}*/
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
		// TODO Auto-generated method stub
		return (countStonesP1() + countStonesP2()) == myBoard.length * myBoard.length;
	}

}
