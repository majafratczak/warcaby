package strategy;
import java.util.List;

import enums.PawnColor;
import model.Field;


public interface MoveBahaviour {
	
	public List<Field> showAvailableMoves(int width, int length, PawnColor color);
	public List<Field> possibleBeating(int width, int length, PawnColor color);
}
