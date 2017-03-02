
public class MiniMaxValue {
	
	BasicTicTacToe game;
	
	public MiniMaxValue(BasicTicTacToe game) {
		
		this.game = game;
	}
	
	//getters
	public BasicTicTacToe getGame() {
		
		return game;
	}
	
	//setters
	public void setGame(BasicTicTacToe game) {
		
		this.game = game;
	}
	
	//this method is going to be later used for determining when
	//to exactly stop the tree search for minimax
	//number of unavailable positions is equivalent to the depth of the tree
	//so essentially with this function, we'll be able to control how deep the search goes
	public int numberOfUnavailablePositions () {
		
		int counter = 0;
		
		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 3; j++) {
				
				if (this.game.states[i][j].toString().equals("X") || this.game.states[i][j].toString().equals("O")) {
					
					counter++;
				}
			}
		}
		
		return counter;
	}
	
	//100 points for 3 in a row/column/diagonal
	//10 points for 2 in the same row/column/diagonal
	//1 point for 1 in a row
	//this is essentially the heuristic function
	public int utilityValuesOfLine(String[][] states,int value1, int value2, int value3, int value4, int value5, int value6) {
		
		String myPiece = "";
		String compPiece = "";
				
		int score = 0;
		
		if (game.player == true) {
			//the player is X
			myPiece = "X";
			compPiece = "O";
		
		} else {
			//the player is O
			myPiece = "O";
			compPiece = "X";
		}
		

		if (states[value1][value2].toString().equals(myPiece)) {
			
			score =  score + 1;

			
		} else if (states[value1][value2].toString().equals(compPiece)){
			
			score = score + -1;
		
		} else {
			
			score = score + 10;
		}


		//System.out.println("AFTER FIRST POINT: " + score);

		
		if (states[value3][value4].toString().equals(myPiece)) {
			
	         if (score == 1) { 
	        	 // cell1 is myPiece
	        	 score = score - 20;
	        	 
	          } else if (score == -1) { 
	        	 // cell1 is copmPiece
	        	  score = score + 1;
	        	  
	          } else {  
	        	  // cell1 is empty
	        	  score = score + 1;
	          }
		} else if (states[value3][value4].toString().equals(compPiece)) {
			
			if (score == -1) { 
				// cell1 is compPIece
				score = score - 20;
				
	         } else if (score == 1) { 
	        	 // cell1 is myPiece
	        	 score = score + 0;
	            
	         } else if(score == 10){  
	        	 // cell1 is empty
	        	 score = score + -13;
	         }	

		}else{

			if (score == -1) { 
				// cell1 is compPIece
				score = score - 2;
				
	         } else if (score == 1) { 
	        	 // cell1 is myPiece
	        	 score = score + 1;
	            
	         } else {  
	        	 // cell1 is empty
	        	 score = score + 5;
	         }	
		}

		//System.out.println("AFTER SECOND POINT: " + score);
		
		if (states[value5][value6].toString().equals(myPiece)) {
			
			//POSSIBILITIES
			// 2 = first is mypiece second is empty
			//-19 = first and second are mypiece
			// 11 = first is empty second is mypiece
			// 0 = first is comp second is mypeice
			// 1 = first is mypeice second is comp
			// -3 = first is comp second is empty
			// -3 = first is empty second is comp
			// -21 = both are comp

	         if (score == 1 ) {  
	        	
	             score = score - 45;
	          
	         }else if(score == 0){

	         	score = score - 44;

	         } else {

	         	score = score + 0;

	         }
			
		} else if (states[value5][value6].toString().equals(compPiece)) {
			
	         if (score == -19) {  
	        	 // first and second are mypiece
	             score = score - 25;
	    
	          } else if( score == -21) { 

	             score = score - 100;

	          }else{
	          	score = score + 0;
	          }
		}

		//System.out.println("AFTER THIRD POINT: " + score);
		return score;
	}
	
	public int totalUtility (String[][] states, int row, int column) {
		
		int totalUtilityScore = 0;

		if(row == 0){
			//Top row
			int scoreOfTopRow = utilityValuesOfLine(states, 0, 0, 0, 1, 0, 2);
			totalUtilityScore += scoreOfTopRow;
			//System.out.println("TOP: " + scoreOfTopRow);

		}else if(row == 1){
			//Middle Row
			int scoreOfMiddleRow = utilityValuesOfLine(states, 1, 0, 1, 1, 1, 2);
			totalUtilityScore += scoreOfMiddleRow;
			//System.out.println("MIDDLE ROW: " + scoreOfMiddleRow);
		
		}else if(row == 2){
			//Bottom Row
			int scoreOfBottomRow = utilityValuesOfLine(states, 2, 0, 2, 1, 2, 2);
			totalUtilityScore += scoreOfBottomRow;
			//System.out.println("BOTTOM: " + scoreOfBottomRow);
		}

		if(column == 0){
			//Left column
			int scoreOfLeftColumn = utilityValuesOfLine(states, 0, 0, 1, 0, 2, 0);
			totalUtilityScore += scoreOfLeftColumn;
			//System.out.println("LEFT: " + scoreOfLeftColumn);
		
		}else if(column == 1){
			//Middle column
			int scoreOfMiddleColumn = utilityValuesOfLine(states, 0, 1, 1, 1, 2, 1);
			totalUtilityScore += scoreOfMiddleColumn; 
			//System.out.println("MIDDLE COLUMN: " + scoreOfMiddleColumn);

		}else if(column == 2){
			//Right column
			int scoreOfRightColumn = utilityValuesOfLine(states, 0, 2, 1, 2, 2, 2);
			totalUtilityScore += scoreOfRightColumn;
			//System.out.println("RIGHT: " + scoreOfRightColumn);
		}

		if( row == 1 && column == 1){
			//Diagonal left to right
			int scoreOfDiagonalLeftToRight = utilityValuesOfLine(states, 0, 0, 1, 1, 2, 2);
		
			//Diagonal right to left
			int scoreOfDiagonalRightToLeft = utilityValuesOfLine(states, 0, 2, 1, 1, 2, 0);

			//System.out.println("DIAGONAL RIGHT: " + scoreOfDiagonalRightToLeft);
			//System.out.println("DIAGONAL LEFT: " + scoreOfDiagonalLeftToRight);

			totalUtilityScore = scoreOfDiagonalRightToLeft + scoreOfDiagonalLeftToRight + totalUtilityScore;
		}

		if( (row == 0 && column == 2) || (row == 2 && column == 0) ){
		
			//Diagonal right to left
			int scoreOfDiagonalRightToLeft = utilityValuesOfLine(states, 0, 2, 1, 1, 2, 0);

			//System.out.println("DIAGONAL RIGHT: " + scoreOfDiagonalRightToLeft);

			totalUtilityScore = scoreOfDiagonalRightToLeft + totalUtilityScore;
		}

		if( (row == 0 && column == 0) || (row == 2 && column == 2) ){
		
			//Diagonal left to right
			int scoreOfDiagonalLeftToRight = utilityValuesOfLine(states, 0, 0, 1, 1, 2, 2);

			//System.out.println("DIAGONAL LEFT: " + scoreOfDiagonalLeftToRight);

			totalUtilityScore = scoreOfDiagonalLeftToRight + totalUtilityScore;
		}
		
		return totalUtilityScore;
	}	
	
	public int[] nextMoves(String[][] states) {
		
		//an array of the possible moves (aka all the empty spots)
		//the spots come in pairs
		//so the first of the pair will be the index in the first box
		//the second of the pair will be the index in the second box
		//total size is 18 because initially, there are 9 possible moves
		int[] possibleMoves = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
		
		for(int i = 0; i < 3; i++) {
			
			for (int j = 0; j < 3; j++) {
				
				//if it's empty, then it's a possible move
				if (states[i][j].toString().equals(" ")) {
					
					int k = 0;
					
					//checking whether or not it's empty
					if (possibleMoves[k] == -1) {
						
						possibleMoves[k] = i;
						possibleMoves[k+1] = j;
						
					} else {
						
						while(possibleMoves[k] != -1) {
							//looping through to find an open spot in possibleMoves array
							k++;
						}
						
						possibleMoves[k] = i;
						possibleMoves[k+1] = j;
					}
				}
			}
		}

		//returns an array with coordinates with open moves, and "-1"s in the rest of the array
		return possibleMoves;
	}
	
	//this method is honestly unncessary
	//just have this to make sure all the possible moves were being correcty generated
	public void printArray(int[] array) {
		
		for (int i = 0; i < array.length; i++) {
			
			System.out.println(array[i]);
		}
	}
	
	public int[] bestMove(String[][] states) {
		
		int[] bestMove = new int[2];
		
		int[] possibleMoves = nextMoves(states);
		
		int firstIndex = 0;
		int secondIndex = 1;
		int bestFirstIndex = -1;
		int bestSecondIndex = -1;
		int previousTotalUtility = 0;
		
		//will run until it reaches the end of the possibleMoves array
		while(firstIndex < possibleMoves.length && possibleMoves[firstIndex] != -1) {
			
			String[][] copyOfStates = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};
			
			for (int i = 0; i < 3; i++) {
				
				for (int j = 0; j < 3; j++) {
					
					//making a copy of the states
					copyOfStates[i][j] = states[i][j];
				}
			}
			
			//testing out every possible move
			if (game.compSymbol.equals("O")) {
				
				copyOfStates[possibleMoves[firstIndex]][possibleMoves[secondIndex]] = "O";
				
			} else {
				
				copyOfStates[possibleMoves[firstIndex]][possibleMoves[secondIndex]] = "X";	
			}
			
			int currentTotalUtility = totalUtility(copyOfStates, possibleMoves[firstIndex], possibleMoves[secondIndex]);
			
			//this is where i should add code
			
			
			//System.out.println("First Possible Index: " + possibleMoves[firstIndex]);
			//System.out.println("Second Possible Index: " + possibleMoves[secondIndex]);
			//System.out.println("Current Total Utility: " + currentTotalUtility);
			
			if (currentTotalUtility <= previousTotalUtility) {
				
				previousTotalUtility = currentTotalUtility;
				bestFirstIndex = firstIndex;
				bestSecondIndex = secondIndex;
			}
			
			firstIndex += 2;
			secondIndex += 2;
		}
		
		bestMove[0] = possibleMoves[bestFirstIndex];
		bestMove[1] = possibleMoves[bestSecondIndex];
		
		return bestMove;
	}
	
	public int[] bestMove2(String[][] states) {
		
		int[] bestMove = new int[2];
		
		int[] possibleMoves = nextMoves(states);
		
		int firstIndex = 0;
		int secondIndex = 1;
		int bestFirstIndex = -1;
		int bestSecondIndex = -1;
		int previousTotalUtility = 0;
		
		//will run until it reaches the end of the possibleMoves array
		while(firstIndex < possibleMoves.length && possibleMoves[firstIndex] != -1) {
			
			String[][] copyOfStates = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};
			
			for (int i = 0; i < 3; i++) {
				
				for (int j = 0; j < 3; j++) {
					
					//making a copy of the states
					copyOfStates[i][j] = states[i][j];
				}
			}
			
			//testing out every possible move
			if (game.compSymbol.equals("O")) {
				
				copyOfStates[possibleMoves[firstIndex]][possibleMoves[secondIndex]] = "O";
				
			} else {
				
				copyOfStates[possibleMoves[firstIndex]][possibleMoves[secondIndex]] = "X";	
			}
			
			int currentTotalUtility = totalUtility(copyOfStates, possibleMoves[firstIndex], possibleMoves[secondIndex]);
			
			int biggerIndex = 0;
			
			if (possibleMoves[firstIndex] == 0 && possibleMoves[secondIndex] == 0) {
				
				biggerIndex = 1;
				
			} else if (possibleMoves[firstIndex] == 0 && possibleMoves[secondIndex] == 1) {
				
				biggerIndex = 2;
				
			} else if (possibleMoves[firstIndex] == 0 && possibleMoves[secondIndex] == 2) {
				
				biggerIndex = 3;
				
			}else if (possibleMoves[firstIndex] == 1 && possibleMoves[secondIndex] == 0) {
				
				biggerIndex = 4;
				
			}else if (possibleMoves[firstIndex] == 1 && possibleMoves[secondIndex] == 1) {
				
				biggerIndex = 5;
				
			}else if (possibleMoves[firstIndex] == 1 && possibleMoves[secondIndex] == 2) {
				
				biggerIndex = 6;
				
			}else if (possibleMoves[firstIndex] == 2 && possibleMoves[secondIndex] == 0) {
				
				biggerIndex = 7;
				
			}else if (possibleMoves[firstIndex] == 2 && possibleMoves[secondIndex] == 1) {
				
				biggerIndex = 8;
				
			}else if (possibleMoves[firstIndex] == 2 && possibleMoves[secondIndex] == 2) {
				
				biggerIndex = 9;
			}
			
			if (biggerIndex == 1) {
				
				
				
			} else if (biggerIndex == 2) {
				
				
				
			} else if (biggerIndex == 3) {
				
				
				
			} else if (biggerIndex == 4) {
				
				
				
			} else if (biggerIndex == 5) {
				
				
				
			} else if (biggerIndex == 6) {
				
				
				
			} else if (biggerIndex == 7) {
				
				
				
			} else if (biggerIndex == 8) {
				
				
				
			} else if (biggerIndex == 9) {
				
				
				
			}
			
			//this is where i should add code
			
			int playerUtility =0;
			
			int finalTotalUtility = currentTotalUtility + playerUtility;
			
			//System.out.println("First Possible Index: " + possibleMoves[firstIndex]);
			//System.out.println("Second Possible Index: " + possibleMoves[secondIndex]);
			//System.out.println("Current Total Utility: " + currentTotalUtility);
			
			if (finalTotalUtility <= previousTotalUtility) {
				
				previousTotalUtility = finalTotalUtility;
				bestFirstIndex = firstIndex;
				bestSecondIndex = secondIndex;
			}
			
			firstIndex += 2;
			secondIndex += 2;
		}
		
		bestMove[0] = possibleMoves[bestFirstIndex];
		bestMove[1] = possibleMoves[bestSecondIndex];
		
		return bestMove;
	}
}
