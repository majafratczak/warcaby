package view;

import java.awt.Color;

import javax.swing.JButton;

import enums.PawnColor;

public class FieldView extends JButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int widthx;
	private int lengthy;
	private Color color;
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getWidthx() {
		return widthx;
	}

	public void setWidthx(int widthx) {
		this.widthx = widthx;
	}

	public int getLengthy() {
		return lengthy;
	}

	public void setLengthy(int lengthy) {
		this.lengthy = lengthy;
	}

	public FieldView(String name){
		super(name);
		this.widthx=0;
		this.lengthy=0;
	}

}
