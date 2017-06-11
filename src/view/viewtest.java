package view;

import java.awt.Button;

import main.Game;

public class viewtest {
	
	public static void main(String[] args) {
		
		Game game = new Game();
		BoardView view = new BoardView(game);
		view.setVisible(true);
		view = new BoardView(game);
	}

}
