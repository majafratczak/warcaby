package model;
import java.io.Serializable;
import java.util.List;

import strategy.MoveBahaviour;
import concreteStrategy.NormalMove;
import concreteStrategy.QueenMove;
import enums.PawnColor;


public class Pawn implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PawnColor color;
	private MoveBahaviour moveBahaviour;

	public Pawn(PawnColor color, MoveBahaviour beh){
		this.color = color; 
		moveBahaviour = beh;
	}
	
	public PawnColor getColor() {
		return color;
	}
	
	public Boolean isQueen(){
		if(moveBahaviour instanceof QueenMove)
			return true;
		else
			return false;
	}
	
	public List<Field> showAvailableMoves(int width, int length, Boolean isFirstMove){
		if(moveBahaviour.possibleBeating(width, length, color).isEmpty() == true && isFirstMove == true){
			System.out.println("nie ma mozliwych bic");
			return moveBahaviour.showAvailableMoves(width, length, color);
		}
		else{
			return moveBahaviour.possibleBeating(width, length, color);
		}
	}
	
	public void changeBehaviour(MoveBahaviour beh){
		moveBahaviour = beh;
	}
}
