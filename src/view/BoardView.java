package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import enums.PawnColor;
import main.Game;
import model.Board;
import model.Field;

public class BoardView extends JFrame {

	FieldView[][] buttons = new FieldView[8][8];
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem menuItem1, menuItem2;
	JLabel nameFirstPlayer, nameSecondPlayer;
	
	public List<Field> highlightedFields;
	
	public boolean movesToggle;
	
	public int activeX;
	public int acitveY;
	
	private Game game;
	
	public BoardView(Game game){
		this.game=game;
		this.highlightedFields = new ArrayList<Field>();
		
		this.movesToggle = false;
		
		this.setBounds(50,50,650,700);
		this.setUpBoard();
		
		this.setLayout(null);
		createMenu();
		createLabelStats();
	}
	
	
	private void createLabelStats(){
		nameFirstPlayer = new JLabel(game.getUser1().getName(),
				JLabel.CENTER);
		nameSecondPlayer = new JLabel(game.getUser2().getName(), JLabel.CENTER);
		nameSecondPlayer.setBounds(150,75,120,30);
		nameFirstPlayer.setBounds(330,75,120,30);
		this.add(nameFirstPlayer);
		this.add(nameSecondPlayer);
	}
	
	
	private void createMenu(){
		menuBar = new JMenuBar();
		menu = new JMenu("Game");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");
		menuBar.add(menu);

		//a group of JMenuItems
		menuItem1 = new JMenuItem("Save Game");
		menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                game.saveGame();
            }
        });
		menu.add(menuItem1);
		menuItem2 = new JMenuItem("Load Game");
		menuItem2.addActionListener(new LoadButtonHandler(this));
		menu.add(menuItem2);
		this.setJMenuBar(menuBar);
	}
	
	
	private void setUpBoard(){
		for(int i=0; i<8; i++)
			for(int j=0; j<8; j++){
				buttons[i][j] = new FieldView(i+j+"");		
				buttons[i][j].setBounds(100+50*i, 100+50*j, 50, 50);
				if(game.getBoard().allFields[i][j].getColor()==PawnColor.BLACK){
					buttons[i][j].setBackground(Color.BLACK);
					buttons[i][j].setColor(Color.BLACK);
				}
				else if(game.getBoard().allFields[i][j].getColor()==PawnColor.WHITE){
					buttons[i][j].setBackground(Color.WHITE);
					buttons[i][j].setColor(Color.WHITE);
				}
				
				if(game.getBoard().allFields[i][j].getPawn().getColor()==PawnColor.BLACK)
					buttons[i][j].setBackground(Color.GREEN);
				else if(game.getBoard().allFields[i][j].getPawn().getColor()==PawnColor.WHITE)
					buttons[i][j].setBackground(Color.RED);
		
				buttons[i][j].addMouseListener(new mouseClickHandler(i, j, this));
				
				this.add(buttons[i][j]);
			}
	}
	
	public void repaintBoard(){
		
		for(int i=0; i<8; i++)
			for(int j=0; j<8; j++){
				if(game.getBoard().allFields[i][j].getColor()==PawnColor.BLACK){
					buttons[i][j].setBackground(Color.BLACK);
					buttons[i][j].setColor(Color.BLACK);
				}
				else if(game.getBoard().allFields[i][j].getColor()==PawnColor.WHITE){
					buttons[i][j].setBackground(Color.WHITE);
					buttons[i][j].setColor(Color.WHITE);
				}
				
				if(game.getBoard().allFields[i][j].getPawn().getColor()==PawnColor.BLACK)
					buttons[i][j].setBackground(Color.GREEN);
				else if(game.getBoard().allFields[i][j].getPawn().getColor()==PawnColor.WHITE)
					buttons[i][j].setBackground(Color.RED);
		
				buttons[i][j].repaint();
			}
		
	}
	
	public void highlightFields(){
		
		for (Field field : highlightedFields){
			this.buttons[field.getLength()][field.getWidth()].setBackground(Color.CYAN);
			this.buttons[field.getLength()][field.getWidth()].repaint();
		}
		
	}
	
	public void clearHighLightedFields(){
		
		for (Field field : highlightedFields){
			this.buttons[field.getLength()][field.getWidth()].setBackground(this.buttons[field.getLength()][field.getWidth()].getColor());
			this.buttons[field.getLength()][field.getWidth()].repaint();
		}
		repaintBoard();
		
	}
	
	public void markActiveField(int activeX, int activeY){
		this.activeX=activeX;
		this.acitveY=activeY;
	}
	
	
	private class LoadButtonHandler implements ActionListener{

		BoardView view;
		
		public LoadButtonHandler(BoardView view) {
			this.view=view;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			view.game=view.game.loadGame();
			view.game.setBoard(new Board(view.game.getBoard()));
			view.repaintBoard();
		}
		
		
		
	}
	
	
	private class mouseClickHandler extends MouseAdapter{
		
		int i;
		int j;
		BoardView view;
		
		public mouseClickHandler(int i, int j, BoardView view){
			this.i=i;
			this.j=j;
			this.view=view;
		}
		
		public void mouseClicked(MouseEvent arg0){
			
			if (SwingUtilities.isRightMouseButton(arg0)){
				if((movesToggle==false && game.getBoard().allFields[i][j].getPawn().getColor()==PawnColor.BLACK)
						||	(movesToggle==true && game.getBoard().allFields[i][j].getPawn().getColor()==PawnColor.WHITE)){
				view.markActiveField(i, j);
				view.clearHighLightedFields();
				if(view.game.getBoard().getIsNextBeatingPossible() == false)
					view.highlightedFields=game.getBoard().allFields[i][j].getPawn().showAvailableMoves(j,i,true);
				else{
					if(game.getBoard().allFields[i][j].getPawn().showAvailableMoves(j,i,false).isEmpty() == false)
						view.highlightedFields=game.getBoard().allFields[i][j].getPawn().showAvailableMoves(j,i,false);
					else{
						view.highlightedFields.clear();
						view.clearHighLightedFields();
					}
				}
				view.highlightFields();
				}
			}
			
			if (SwingUtilities.isLeftMouseButton(arg0)){
				for(Field field : view.highlightedFields){
					if(i==field.getLength()&&j==field.getWidth()){
						boolean nextMoves=view.game.getBoard().makeMove(view.game.getBoard().allFields[view.activeX][view.acitveY], view.game.getBoard().allFields[i][j]);
						view.repaintBoard();
						if(view.game.getBoard().getIsNextBeatingPossible() == false){
							view.movesToggle=!view.movesToggle;
							
						}
						else
							if(game.getBoard().allFields[i][j].getPawn().showAvailableMoves(j,i,false).isEmpty() == true){
								view.movesToggle=!view.movesToggle;
								view.game.getBoard().setIsNextBeatingPossible(false);
							}
					}
				}
			}
			
		}
	}
	
	
	
}
