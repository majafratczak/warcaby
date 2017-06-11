package concreteStrategy;

import static org.mockito.Mockito.mock;

import java.util.List;

import model.Board;
import model.Field;
import model.Pawn;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import enums.PawnColor;


public class QueenMoveTest {

private QueenMove sut;
	
	Board mockedBoard = mock(Board.class);
	
	@Before
	public void initializeSUT() {
		mockedBoard.allFields = new Field[8][8];
		mockedBoard.allFields[0][0]=new Field(0,0,PawnColor.BLACK,new Pawn(PawnColor.BLACK, new QueenMove(mockedBoard.allFields)));
		mockedBoard.allFields[1][0]=new Field(1,0,PawnColor.WHITE,new Pawn(PawnColor.INVISIBLE, new NormalMove(mockedBoard.allFields)));
		mockedBoard.allFields[0][1]=new Field(0,1,PawnColor.WHITE,new Pawn(PawnColor.INVISIBLE, new NormalMove(mockedBoard.allFields)));
		mockedBoard.allFields[1][1]=new Field(1,1,PawnColor.BLACK,new Pawn(PawnColor.WHITE, new NormalMove(mockedBoard.allFields)));
		mockedBoard.allFields[2][2]=new Field(2,2,PawnColor.BLACK,new Pawn(PawnColor.INVISIBLE, new NormalMove(mockedBoard.allFields)));	
		mockedBoard.allFields[3][3]=new Field(3,3,PawnColor.BLACK,new Pawn(PawnColor.INVISIBLE, new NormalMove(mockedBoard.allFields)));
		mockedBoard.allFields[4][4]=new Field(4,4,PawnColor.BLACK,new Pawn(PawnColor.INVISIBLE, new NormalMove(mockedBoard.allFields)));
		mockedBoard.allFields[5][5]=new Field(5,5,PawnColor.BLACK,new Pawn(PawnColor.INVISIBLE, new NormalMove(mockedBoard.allFields)));
		mockedBoard.allFields[6][6]=new Field(6,6,PawnColor.BLACK,new Pawn(PawnColor.INVISIBLE, new NormalMove(mockedBoard.allFields)));
		mockedBoard.allFields[7][7]=new Field(7,7,PawnColor.BLACK,new Pawn(PawnColor.INVISIBLE, new NormalMove(mockedBoard.allFields)));
		mockedBoard.allFields[0][7]=new Field(0,7,PawnColor.BLACK,new Pawn(PawnColor.WHITE, new QueenMove(mockedBoard.allFields)));
		mockedBoard.allFields[0][6]=new Field(2,0,PawnColor.WHITE,new Pawn(PawnColor.INVISIBLE, new NormalMove(mockedBoard.allFields)));
		mockedBoard.allFields[1][7]=new Field(3,1,PawnColor.WHITE,new Pawn(PawnColor.INVISIBLE, new NormalMove(mockedBoard.allFields)));
		mockedBoard.allFields[7][0]=new Field(7,0,PawnColor.BLACK,new Pawn(PawnColor.INVISIBLE, new NormalMove(mockedBoard.allFields)));
		mockedBoard.allFields[6][1]=new Field(6,1,PawnColor.BLACK,new Pawn(PawnColor.INVISIBLE, new NormalMove(mockedBoard.allFields)));
		mockedBoard.allFields[5][2]=new Field(5,2,PawnColor.BLACK,new Pawn(PawnColor.INVISIBLE, new NormalMove(mockedBoard.allFields)));
		mockedBoard.allFields[4][3]=new Field(4,3,PawnColor.BLACK,new Pawn(PawnColor.INVISIBLE, new NormalMove(mockedBoard.allFields)));
		mockedBoard.allFields[3][4]=new Field(3,4,PawnColor.BLACK,new Pawn(PawnColor.INVISIBLE, new NormalMove(mockedBoard.allFields)));
		mockedBoard.allFields[2][5]=new Field(2,5,PawnColor.BLACK,new Pawn(PawnColor.INVISIBLE, new NormalMove(mockedBoard.allFields)));
		mockedBoard.allFields[1][6]=new Field(1,6,PawnColor.BLACK,new Pawn(PawnColor.INVISIBLE, new NormalMove(mockedBoard.allFields)));
		mockedBoard.allFields[0][7]=new Field(0,7,PawnColor.BLACK,new Pawn(PawnColor.INVISIBLE, new NormalMove(mockedBoard.allFields)));
		sut = new QueenMove(mockedBoard.allFields);
	}
	
	@Test
	public void shouldReturnSixBeating() {
		List<Field> list = sut.possibleBeating(0, 0, PawnColor.BLACK);
		
		Assert.assertEquals(list.size(), 6);
	}
	
	@Test
	public void shouldntReturnAnyMoves() {
		List<Field> list = sut.showAvailableMoves(0, 0, PawnColor.BLACK);
		
		Assert.assertEquals(list.size(), 0);
	}
	
	@Test
	public void shouldReturnSevenMoves() {
		List<Field> list = sut.showAvailableMoves(0, 7, PawnColor.BLACK);
		
		Assert.assertEquals(list.size(), 7);
	}
	
}
