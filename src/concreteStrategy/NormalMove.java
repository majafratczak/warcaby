package concreteStrategy;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import enums.PawnColor;
import model.Board;
import model.Field;
import strategy.MoveBahaviour;

public class NormalMove implements MoveBahaviour{
	
	private transient Field[][] allFields;
	
	public NormalMove(Field[][] allFields){
		this.allFields = allFields;
	}
	
	public void setAllField(Field[][] allFields){
		this.allFields = allFields;
	}
	
	public List<Field> showAvailableMoves(int width, int length, PawnColor color) {
		List<Field> movesList = new ArrayList<Field>();
//		if(length+1 > 7)
//			return movesList;
//		if(width-1 < 0 && allFields[length +1][width +1].getPawn().getColor() == Color.INVISIBLE){
//			movesList.add(new Field(width+1, length+1));
//			return movesList;
//		}
//		else if(width+1 > 7 && allFields[length +1][width -1].getPawn().getColor() == Color.INVISIBLE){
//			movesList.add(new Field(width-1, length+1));
//			return movesList;
//		}
//		if(width-1 >= 0 && width+1 <= 7 && allFields[length +1][width -1].getPawn().getColor() == Color.INVISIBLE)
//			movesList.add(new Field(width-1, length+1));
//		if(width-1 >= 0 && width+1 <= 7 && allFields[length +1][width +1].getPawn().getColor() == Color.INVISIBLE)
//			movesList.add(new Field(width+1, length+1));
		
		if(width-1 >= 0 && length+1 < 8 && allFields[length +1][width -1].getPawn().getColor() == PawnColor.INVISIBLE && color == PawnColor.WHITE)
			movesList.add(new Field(width-1, length+1));
		if(width+1 < 8 && length+1 < 8 && allFields[length +1][width +1].getPawn().getColor() == PawnColor.INVISIBLE && color == PawnColor.WHITE)
			movesList.add(new Field(width+1, length+1));
		if(width-1 >= 0 && length-1 >= 0 && allFields[length -1][width -1].getPawn().getColor() == PawnColor.INVISIBLE && color == PawnColor.BLACK)
			movesList.add(new Field(width-1, length-1));
		if(width+1 < 8 && length-1 >= 0 && allFields[length -1][width +1].getPawn().getColor() == PawnColor.INVISIBLE && color == PawnColor.BLACK)
			movesList.add(new Field(width+1, length-1));
		return movesList;
	}

	
	public List<Field> possibleBeating(int width, int length, PawnColor color) {
		List<Field> movesList = new ArrayList<Field>();
		if(width-2 >= 0 && length+2 < 8 && allFields[length +2][width -2].getPawn().getColor() == PawnColor.INVISIBLE && allFields[length +1][width -1].getPawn().getColor() == swapColor(color))
			movesList.add(new Field(width-2, length+2));
		if(width+2 < 8 && length+2 < 8 && allFields[length +2][width +2].getPawn().getColor() == PawnColor.INVISIBLE && allFields[length +1][width +1].getPawn().getColor() == swapColor(color))
			movesList.add(new Field(width+2, length+2));
		if(width-2 >= 0 && length-2 >= 0 && allFields[length -2][width -2].getPawn().getColor() == PawnColor.INVISIBLE && allFields[length -1][width -1].getPawn().getColor() == swapColor(color))
			movesList.add(new Field(width-2, length-2));
		if(width+2 < 8 && length-2 >= 0 && allFields[length -2][width +2].getPawn().getColor() == PawnColor.INVISIBLE && allFields[length -1][width +1].getPawn().getColor() == swapColor(color))
			movesList.add(new Field(width+2, length-2));
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
