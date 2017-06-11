package model;

import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import model.Board;
import concreteStrategy.NormalMove;
import concreteStrategy.QueenMove;
import enums.PawnColor;


//@RunWith(Parameterized.class)
public class PawnTest {

	private Pawn sut;
	Board mockedBoard = mock(Board.class);
	
//	 List<Field> givenList;
//	 List<Field> properList;

//	    public PawnTest(String password, String passwordHash)
//	    {
//	        this.password = password;
//	        this.passwordHash = passwordHash;
//	    }
		
	@Before
	public void initializeSUT() {
		mockedBoard.allFields = new Field[8][8];
		createCheckerboard();
		sut = new Pawn(PawnColor.WHITE, new NormalMove(mockedBoard.allFields));
	}
	
	@Test
	 public void shouldNotBeQueen() {
	     // given
	     // when
		Boolean result = sut.isQueen();
	     // then
	    Assert.assertEquals(false, result);
	 }
	
	@Test
	public void shouldChangeToQueen(){
		//given
		//when
		sut.changeBehaviour(new QueenMove(mockedBoard.allFields));
		Boolean result = sut.isQueen();
		//then
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void shouldReturnProperColor(){
		Assert.assertEquals(sut.getColor(), PawnColor.WHITE);
	}
	
	@Test
	public void shouldReturnPossibleMovesWhenItsFirstMove(){
		
		Assert.assertNotNull(sut.showAvailableMoves(1, 1, true));
		
	}
	
	@Test
	public void shouldReturnPossibleBeatingsWhenItsNotFirstMove(){

		Assert.assertNotNull(sut.showAvailableMoves(1, 1, false));
		
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
					mockedBoard.allFields[i][j] = new Field(j,i,color, new Pawn(pawnColor, new NormalMove(mockedBoard.allFields)));
				color = changeColor(color);
				}
			color = changeColor(color);
			}
		}
	
	private PawnColor changeColor(PawnColor col){
		if(col == PawnColor.WHITE)
			return PawnColor.BLACK;
		else
			return PawnColor.WHITE;
	}

	
}

