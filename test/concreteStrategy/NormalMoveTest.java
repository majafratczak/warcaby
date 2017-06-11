package concreteStrategy;

import static org.mockito.Mockito.*;

import java.util.List;

import model.Board;
import model.Field;
import model.Pawn;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import enums.PawnColor;


public class NormalMoveTest {
	
	private NormalMove sut;
	
	Board mockedBoard = mock(Board.class);
	
	@Before
	public void initializeSUT() {
		mockedBoard.allFields = new Field[8][8];
		mockedBoard.allFields[0][0]=new Field(0,0,PawnColor.BLACK,new Pawn(PawnColor.BLACK, new NormalMove(mockedBoard.allFields)));
		mockedBoard.allFields[1][0]=new Field(1,0,PawnColor.WHITE,new Pawn(PawnColor.INVISIBLE, new NormalMove(mockedBoard.allFields)));
		mockedBoard.allFields[0][1]=new Field(0,1,PawnColor.WHITE,new Pawn(PawnColor.INVISIBLE, new NormalMove(mockedBoard.allFields)));
		mockedBoard.allFields[1][1]=new Field(1,1,PawnColor.BLACK,new Pawn(PawnColor.WHITE, new NormalMove(mockedBoard.allFields)));
		mockedBoard.allFields[2][2]=new Field(2,2,PawnColor.BLACK,new Pawn(PawnColor.INVISIBLE, new NormalMove(mockedBoard.allFields)));	

		sut = new NormalMove(mockedBoard.allFields);
	}
	
	@Test
	public void shouldReturnOneBeating() {
		List<Field> list = sut.possibleBeating(0, 0, PawnColor.BLACK);
		
		Assert.assertEquals(list.size(), 1);
	}
	
	@Test
	public void shouldntReturnAnyMoves() {
		List<Field> list = sut.showAvailableMoves(0, 0, PawnColor.BLACK);
		
		Assert.assertEquals(list.size(), 0);
	}
	
	
	
	
}
