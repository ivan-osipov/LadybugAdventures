package ladybugAdventures.entities;

import java.io.Serializable;
import java.util.Random;

import org.eclipse.swt.graphics.Point;

import ladybugAdventures.enums.GameObject;

public class GameField implements Serializable {
	/**
	 * 
	 */
	private final float MAX_OBJECT_PERCENT = 0.2f;
	private static final long serialVersionUID = 1L;
	
	protected GameObject[][] field;
	
	public GameObject[][] getField() {
		return field;
	}
	
	public GameObject getType (int row, int column) {
		return field[row][column];
	}
	
	public int getWidth() {
		return field[0].length;
	}
	
	public int getHeigh() {
		return field.length;
	}
	
	public Point getControlObjectCoordinates() {
		for (int row = 0; row < getHeigh(); row++) {
			for (int column = 0; column < getWidth(); column++) {
				if (field[row][column] == GameObject.LADYBUG) {
					return new Point(column, row);
				}
			}
		}
		return new Point (-1,-1);
	}
	
	protected GameField() {
		field = null;
	}
	
	public GameField(int width, int height) {
		field = new GameObject[height][width];
		cleanField();
	}
	
	public GameField clone() {
		GameField newField = new GameField(getWidth(), getHeigh());
		for (int row = 0; row < getHeigh(); row++) {
			for (int column = 0; column < getWidth(); column++) {
				newField.addObject(getType(row, column), row, column);
			}
		}
		return newField;
	}
	
	public boolean isControlObjectOnField() {
		for (int row = 0; row < getHeigh(); row++) {
			for (int column = 0; column < getWidth(); column++) {
				if (field[row][column] == GameObject.LADYBUG) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void addObject(GameObject object, int row, int column) {
		//проверку на выходы за границы поля не делаю, ибо (как мне помнится) добавление
		//элемента на поле происходит по клику мыши на соответствующей ячейке поля
		if (object == GameObject.LADYBUG) {
			if (!isControlObjectOnField()) {	//Если на поле уже есть объект управления, то ничего не добавляется. Можно придумать код ошибки.
				field[row][column] = GameObject.LADYBUG;
			}
		}
		else {
			field[row][column] = object;
		}
	}
	
	public void removeObject(int row, int column) {
		field[row][column] = GameObject.EMPTY_CELL;
	}
	
	public void cleanField() {
		for (int row = 0; row < getHeigh(); row++) {
			for (int column = 0; column < getWidth(); column++) {
				field[row][column] = GameObject.EMPTY_CELL;
			}
		}
	}
	
	public void automaticCompositionField() {
		cleanField();
		Random random = new Random();
		int currentObjectAmount;
		int objectMaxAmount = (int)(getWidth() * getHeigh() * MAX_OBJECT_PERCENT); //константа, обеспечивающая заполнение поля объектом каждого типа не более, чем на 20%
		field[random.nextInt(getHeigh())][random.nextInt(getWidth())] = GameObject.LADYBUG; //ставим одну божью коровку
		int currentColumn = random.nextInt(getWidth());
		int currentRow = random.nextInt(getHeigh());
		for (int i = 2; i < 5; i++) {
			currentObjectAmount = random.nextInt(objectMaxAmount + 1);
			for (int j = 0; j < currentObjectAmount; j++) {
				while (field[currentRow][currentColumn] != GameObject.EMPTY_CELL) {
					currentColumn = random.nextInt(getWidth());
					currentRow = random.nextInt(getHeigh());
				}
				field[currentRow][currentColumn] = GameObject.values()[i];
			}
		}
	}
}
