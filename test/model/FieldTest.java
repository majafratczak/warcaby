package model;

import static org.mockito.Mockito.mock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import concreteStrategy.NormalMove;
import enums.PawnColor;


public class FieldTest {

	private Field sut;
	Board mockedBoard = mock(Board.class);
	
	@Before
	public void initializeSUT() {
		mockedBoard.allFields = new Field[8][8];
		createCheckerboard();
		sut = new Field(0,0, PawnColor.BLACK, new Pawn(PawnColor.BLACK, new NormalMove(mockedBoard.allFields)));
	}
	
	@Test
	public void shouldReplacePawnForInvisible(){
		sut.deletePawn();
		Assert.assertEquals(PawnColor.INVISIBLE, sut.getPawn().getColor());
	}
	
	@Test
	public void shouldBeTheSame(){
		Field value = new Field(0,0, PawnColor.BLACK, new Pawn(PawnColor.BLACK, new NormalMove(mockedBoard.allFields)));
		Assert.assertEquals(1, sut.compareTo(value));
	}
	
	@Test
	public void shouldBeNotTheSame(){
		Field value = new Field(1,1, PawnColor.BLACK, new Pawn(PawnColor.BLACK, new NormalMove(mockedBoard.allFields)));
		Assert.assertEquals(0, sut.compareTo(value));
	}
	
	@Test
	public void shouldSetPawn(){
		Pawn expectedPawn = new Pawn(PawnColor.WHITE, new NormalMove(mockedBoard.allFields));
		sut.setPawn(expectedPawn);
		Assert.assertEquals(expectedPawn, sut.getPawn());
	}
	
	@Test
	public void shouldReturnFieldColor(){
		Assert.assertEquals(PawnColor.BLACK, sut.getColor());
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
