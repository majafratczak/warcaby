package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import concreteStrategy.NormalMove;
import enums.PawnColor;


	public class BoardTest {
		
		private Board sut;
		
		@Before
		public void initializeSUT() {
			sut = new Board();
		}
		
		@Test
		public void shouldReturnNotGameOver(){
			Assert.assertEquals(false, sut.checkGameOver());
		}
		
		@Test
		public void shouldMovePawn() {
			sut.makeMove(sut.allFields[2][0], sut.allFields[3][1]);
			Assert.assertNotEquals(sut.allFields[3][1].getPawn().getColor(), PawnColor.INVISIBLE);
		}
		
		@Test
		public void shouldBeBeating() {
			sut.allFields[0][0] = new Field(0,0,PawnColor.BLACK, new Pawn(PawnColor.BLACK, new NormalMove(sut.allFields)));
			sut.allFields[1][1] = new Field(1,1,PawnColor.BLACK, new Pawn(PawnColor.WHITE, new NormalMove(sut.allFields)));
			sut.allFields[2][2] = new Field(2,2,PawnColor.BLACK, new Pawn(PawnColor.INVISIBLE, new NormalMove(sut.allFields)));
			sut.makeMove(sut.allFields[0][0], sut.allFields[2][2]);
			Assert.assertEquals(sut.getIsNextBeatingPossible(), true);
		}
}
