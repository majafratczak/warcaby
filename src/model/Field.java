package model;

import java.io.Serializable;

import concreteStrategy.NormalMove;
import enums.PawnColor;


public class Field implements Comparable<Field>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width;
	private int length;
	private Pawn pawn;
	private PawnColor color;
	
	
	public Field(int width, int length, PawnColor color, Pawn pawn){
		this.width = width;
		this.length = length;
		this.color = color;
		this.pawn = pawn;
		if(color == PawnColor.WHITE)
			this.pawn = new Pawn(PawnColor.INVISIBLE, null);
	}
	
	public Field(int width, int length){
		this.width = width;
		this.length = length;
	}
	
	public int getWidth() {
		return width;
	}

	public int getLength() {
		return length;
	}

	public Pawn getPawn() {
		return pawn;
	}

	public void setPawn(Pawn pawn) {
		if(color != PawnColor.WHITE)
		this.pawn = pawn;
	}
	
	public PawnColor getColor() {
		return color;
	}

	
	public void deletePawn(){
		pawn = new Pawn(PawnColor.INVISIBLE, null);
	}

	@Override
	public int compareTo(Field otherField) {
		if(this.length == otherField.getLength() && this.width == otherField.getWidth())
			return 1;
		else
			return 0;
	}
}
