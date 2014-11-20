package technologyOfProgramming.zvenigorodskyTask.factories;

import technologyOfProgramming.zvenigorodskyTask.entities.GameField;

public class FieldFactory {
	
	public static GameField createFieldManually(int fieldWidth, int fieldHeight) {
		return new GameField(fieldWidth, fieldHeight);
	}
	
	public static GameField createFieldAutomatically(int fieldWidth, int fieldHeight) {
		GameField gf = new GameField(fieldWidth, fieldHeight);
		gf.automaticCompositionField();
		return gf;
	}
}
