package model;

import concreteStrategy.NormalMove;
import concreteStrategy.QueenMove;
import enums.PawnColor;

public class Board{
	
	public Field[][] allFields; 
	private Boolean isNextBeatingPossible;
	private int numberOfMovementsWithoutBeating;
	private NormalMove normalMove;
	private QueenMove queenMove;
	
	public Boolean getIsNextBeatingPossible(){
		return isNextBeatingPossible;
	}
	
	public void setIsNextBeatingPossible(Boolean value){
		this.isNextBeatingPossible = value;
	}
	
	public int getNumberOfMovementsWithoutBeating(){
		return numberOfMovementsWithoutBeating;
	}

	public Board(){
		allFields = new Field[8][8];
		normalMove = new NormalMove(allFields);
		queenMove = new QueenMove(allFields);
		createCheckerboard();
		isNextBeatingPossible = false;
		numberOfMovementsWithoutBeating = 0;
	}
	
	public Board (Board board){
		allFields = board.allFields;
		normalMove = new NormalMove(allFields);
		for(int i=0; i<8; i++)
			for(int j=0; j<8; j++){
				if(allFields[i][j].getPawn().getColor()!=PawnColor.INVISIBLE)
					allFields[i][j].getPawn().changeBehaviour(normalMove);
			}
		isNextBeatingPossible = board.isNextBeatingPossible;
		numberOfMovementsWithoutBeating = board.numberOfMovementsWithoutBeating;
	}
	
	private void createCheckerboard(){
		PawnColor color = PawnColor.BLACK;
		PawnColor pawnColor = PawnColor.WHITE;
		for(int i = 0; i < 8; i++){
			if(i>2 && i<5)
				pawnColor = PawnColor.INVISIBLE;
			if(i>4)
				pawnColor = PawnColor.BLACK;
			for(int j  = 0; j < 8; j++){
				if(color != PawnColor.INVISIBLE)
					allFields[i][j] = new Field(j,i,color, new Pawn(pawnColor, normalMove));
				color = changeColor(color);
				}
			color = changeColor(color);
			}
		}
	
//	private void createCheckerboardTEST(){
//		PawnColor color = PawnColor.BLACK;
//		PawnColor pawnColor = PawnColor.WHITE;
//		for(int i = 0; i < 8; i++){
//			if(i>2 && i<5)
//				pawnColor = PawnColor.INVISIBLE;
//			if(i>4)
//				pawnColor = PawnColor.BLACK;
//			for(int j  = 0; j < 8; j++){
//				if(color != PawnColor.INVISIBLE)
//					allFields[i][j] = new Field(j,i,color, new Pawn(PawnColor.INVISIBLE));
//				else if(color == PawnColor.INVISIBLE)
//					allFields[i][j] = new Field(j,i,color, new Pawn(PawnColor.INVISIBLE));
//				color = changeColor(color);
//				}
//			color = changeColor(color);
//			}
//		allFields[0][2] = new Field(2,0,PawnColor.BLACK, new Pawn(PawnColor.WHITE));
//		allFields[2][4] = new Field(1,3,PawnColor.BLACK, new Pawn(PawnColor.BLACK));
//	}
	
	private PawnColor changeColor(PawnColor col){
		if(col == PawnColor.WHITE)
			return PawnColor.BLACK;
		else
			return PawnColor.WHITE;
	}
	
//	public void printBoard2(){
//		for(int i = 0; i < 8; i++){
//			for(int j = 0; j < 8; j++){
//				System.out.print(allFields[i][j].getPawn().getColor() + "   ");
//			}
//			System.out.println();
//		}
//	}
//		
//	
//	public void printBoard(){
//		for(int i = 0; i < 8; i++){
//			for(int j = 0; j < 8; j++){
//				if(allFields[i][j].getPawn().getColor()==PawnColor.BLACK)
//					System.out.print("#");
//				else if(allFields[i][j].getPawn().getColor()==PawnColor.WHITE)
//					System.out.print("O");
//				else
//					System.out.print(".");
//			}
//			System.out.println();
//		}
//	}
//	

	public Boolean makeMove(Field beginningField, Field endField){
		Boolean isPossible = false;
		for(Field f : beginningField.getPawn().showAvailableMoves(beginningField.getWidth(), beginningField.getLength(), true)){
			if(endField.compareTo(f) == 1)
				isPossible = true;
		}
		if(isPossible == true){
			int smallerLength = beginningField.getLength();
			int smallerWidth = beginningField.getWidth();
			if(endField.getLength() < beginningField.getLength())
				smallerLength = endField.getLength();
			if(endField.getWidth() < beginningField.getWidth())
				smallerWidth = endField.getWidth();
			isNextBeatingPossible = false;
			for(int i =1; i < Math.abs(beginningField.getLength()-endField.getLength()); i++){
				if(allFields[smallerLength+i][smallerWidth+i].getPawn().getColor() == changeColor(beginningField.getPawn().getColor())){
					allFields[smallerLength+i][smallerWidth+i].deletePawn();
					System.out.println("Zbito pionka o kolorze " + changeColor(beginningField.getPawn().getColor()));
					isNextBeatingPossible = true;
					numberOfMovementsWithoutBeating = 0;
				}
				else
				{
					numberOfMovementsWithoutBeating++;
				}
			}
			endField.setPawn(beginningField.getPawn());
			beginningField.deletePawn();
			if(checkChangedNormalToQueen() == true)
				isNextBeatingPossible = false;
			return true;
		}
		else{
			isNextBeatingPossible = false;
			System.out.println("This move is not permitted!");
			return false;
		}
	}
	

	public Boolean checkGameOver(){
		if(getNumberOfPawns(PawnColor.BLACK) == 0){
			System.out.println("Wygral gracz o bialych pionkach");
			return true;
		}
		if(getNumberOfPawns(PawnColor.WHITE) == 0){
			System.out.println("Wygral gracz o czarnych pionkach");
			return true;
		}
		if(checkIsLocked(PawnColor.WHITE) == true){
			System.out.println("Wygral gracz o czarnych pionkach");
			return true;
		}
		if(checkIsLocked(PawnColor.BLACK) == true){
			System.out.println("Wygral gracz o bialych pionkach");
			return true;
		}
		if(getNumberOfMovementsWithoutBeating() > 20){
			for(Field[] row : allFields){
				for(Field f : row){
					if(f.getPawn().isQueen() == false)
						return false;
				}
			}
		}
		return false;
	}
	
	private int getNumberOfPawns(PawnColor color){
		int counter = 0;
		for(Field[] row : allFields){
			for(Field f : row){
				if(f.getPawn().getColor() == color)
					counter++;
			}
		}
		return counter;
	}
	
	private Boolean checkIsLocked(PawnColor color){
		for(Field[] row : allFields){
			for(Field f : row){
				if(f.getPawn().getColor() == color && f.getPawn().showAvailableMoves(f.getWidth(), f.getLength(), true).isEmpty() == false)
					return false;
			}
		}
		return true;
	}
	
	private Boolean checkChangedNormalToQueen(){
		for(int i =0; i< 8; i++){
			if(allFields[0][i].getPawn().isQueen() == false  && allFields[0][i].getPawn().getColor() == PawnColor.BLACK){
				allFields[0][i].getPawn().changeBehaviour(queenMove);
				return true;
			}
			if(allFields[7][i].getPawn().isQueen() == false  && allFields[7][i].getPawn().getColor() == PawnColor.WHITE){
				allFields[7][i].getPawn().changeBehaviour(queenMove);
				return true;
			}
		}
		return false;
	}
	
}
