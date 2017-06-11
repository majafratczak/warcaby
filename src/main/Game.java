package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import strategy.MoveBahaviour;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import concreteStrategy.NormalMove;
import concreteStrategy.QueenMove;
import model.Board;
import model.Field;

public class Game{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user1;
	private User user2;
	private String name1;
	private String name2;
	private Board board;
	
	public User getUser1(){
		return user1;
	}
	
	public User getUser2(){
		return user2;
	}
	
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Game(){
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("Podaj imie gracza o czarnych pionkach:");
			name1 = in.readLine();
			System.out.print("Podaj imie gracza o bialych pionkach:");
			name2 = in.readLine();
		} catch (IOException e) {
			System.out.println("error");
			e.printStackTrace();
		}
		user1 = new User(name1);
		user2 = new User(name2);
		board = new Board();
	}
	
	public Game(User user1, User user2, Board board){
		this.user1 = user1;
		this.user2 = user2;
		this.board = board;
	}
	
	public void saveGame(){
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("allFields", Board.class);
		xstream.alias("field", Field.class);
		
		xstream.addImplicitArray(Board.class, "allFields", Field[].class);
		
		ObjectOutputStream outputStream;
		try {
			
			outputStream = xstream.createObjectOutputStream(new FileOutputStream("test100.xml"));
			outputStream.writeObject(this);
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Game loadGame(){
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("allFields", Board.class);
		xstream.alias("field", Field.class);
		xstream.addImplicitArray(Board.class, "allFields", Field[].class);		
		ObjectInputStream inputStream;
		
		try {
			inputStream = xstream.createObjectInputStream(new FileInputStream("test100.xml"));
			Game game = (Game)inputStream.readObject();
			inputStream.close();
			return game;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}

}
