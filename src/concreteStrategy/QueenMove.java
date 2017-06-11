package concreteStrategy;
import java.util.ArrayList;
import java.util.List;

import enums.PawnColor;
import strategy.MoveBahaviour;
import model.Board;
import model.Field;
import model.Pawn;


public class QueenMove implements MoveBahaviour{

	private transient Field[][] allFields;
	
	public QueenMove(Field[][] allFields){
		this.allFields = allFields;
	}
	
	public void setAllField(Field[][] allFields){
		this.allFields = allFields;
	}
	
	@Override
	public List<Field> showAvailableMoves(int width, int length, PawnColor color) {
		int w = width;
		int l = length;
		List<Field> movesList = new ArrayList<Field>();
		while(w-1 >= 0 && l+1 < 8 && allFields[l +1][w -1].getPawn().getColor() == PawnColor.INVISIBLE){
			movesList.add(new Field(w-1, l+1));
			System.out.println("dodaje jako move1"+ w +"  "+ l);
			w = w -1;
			l = l +1;
		}
		w = width;
		l = length;
		while(w+1 < 8 && l+1 < 8 && allFields[l +1][w +1].getPawn().getColor() == PawnColor.INVISIBLE){
			movesList.add(new Field(w+1, l+1));

			System.out.println("dodaje jako move2"+ w +"  "+ l);
			w = w +1;
			l = l +1;
		}
		w = width;
		l = length;
		while(w-1 >= 0 && l-1 >= 0 && allFields[l -1][w -1].getPawn().getColor() == PawnColor.INVISIBLE){
			movesList.add(new Field(w-1, l-1));

			System.out.println("dodaje jako move3"+ w +"  "+ l);
			w = w -1;
			l = l -1;
		}
		w = width;
		l = length;
		while(w+1 < 8 && l-1 >= 0 && allFields[l -1][w +1].getPawn().getColor() == PawnColor.INVISIBLE){
			movesList.add(new Field(w+1, l-1));
			System.out.println("dodaje jako move4"+ w +"  "+ l);
			w = w +1;
			l = l -1;
		}
		return movesList;
	}

	
	@Override
	public List<Field> possibleBeating(int width, int length, PawnColor color) {
		List<Field> movesList = new ArrayList<Field>();
		int w = width;
		int l = length;
		while(w-1 >= 0 && l+1 < 8 && allFields[l +1][w -1].getPawn().getColor() != color){
			if(allFields[l +1][w -1].getPawn().getColor() == swapColor(color)){
				w = w -1;
				l = l +1;
				while(w-1 >= 0 && l+1 < 8 && allFields[l +1][w -1].getPawn().getColor() == PawnColor.INVISIBLE){
					movesList.add(new Field(w-1, l+1));
					w = w -1;
					l = l +1;
				}
			}
			else{
				w = w -1;
				l = l +1;
			}
		}
		w = width;
		l = length;
		while(w+1 < 8 && l+1 < 8 && allFields[l +1][w +1].getPawn().getColor() != color){
			if(allFields[l +1][w +1].getPawn().getColor() == swapColor(color)){
				w = w +1;
				l = l +1;
				while(w+1 < 8 && l+1 < 8 && allFields[l +1][w +1].getPawn().getColor() == PawnColor.INVISIBLE){
					movesList.add(new Field(w+1, l+1));
					System.out.println("dodaje jako zbijka");
					w = w +1;
					l = l +1;
				}
			}
			else{
				w = w +1;
				l = l +1;
			}
		}
		w = width;
		l = length;
		while(w-1 >= 0 && l-1 >= 0 && allFields[l -1][w -1].getPawn().getColor() != color){
			if(allFields[l -1][w -1].getPawn().getColor() == swapColor(color)){
				w = w -1;
				l = l -1;
				while(w-1 >= 0 && l-1 >= 0 && allFields[l -1][w -1].getPawn().getColor() == PawnColor.INVISIBLE){
					movesList.add(new Field(w-1, l-1));
					w = w -1;
					l = l -1;
				}
			}
			else{
				w = w -1;
				l = l -1;
			}
		}
		w = width;
		l = length;
		while(w+1 < 8 && l-1 >= 0 && allFields[l -1][w +1].getPawn().getColor() != color){
			if(allFields[l -1][w +1].getPawn().getColor() == swapColor(color)){
				w = w +1;
				l = l -1;
				while(w+1 < 8 && l-1 >= 0 && allFields[l -1][w +1].getPawn().getColor() == PawnColor.INVISIBLE){
					movesList.add(new Field(w+1, l-1));
					w = w +1;
					l = l -1;
				}
			}
			else{
				w = w +1;
				l = l -1;
			}
		}
		return movesList;
	}


	private PawnColor swapColor(PawnColor color){
		if(color == PawnColor.WHITE)
			color = PawnColor.BLACK;
		else 
			color = PawnColor.WHITE;
		return color;
	}
	
}
