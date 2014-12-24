package ladybugAdventures.entities;

import java.awt.Point;
import java.io.Serializable;
import java.util.Random;

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
	
	public GameObject getType (int x, int y) {
		return field[y][x];
	}
	
	public int getWidth() {
		return field[0].length;
	}
	
	public int getHeigh() {
		return field.length;
	}
	
	public Point getControlObjectCoordinates() {
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeigh(); j++) {
				if (field[j][i] == GameObject.LADYBUG) {
					return new Point(j, i);
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
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeigh(); j++) {
				newField.addObject(getType(i, j), i, j);
			}
		}
		return newField;
	}
	
	public boolean isControlObjectOnField() {
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeigh(); j++) {
				if (field[j][i] == GameObject.LADYBUG) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void addObject(GameObject object, int x, int y) {
		//проверку на выходы за границы поля не делаю, ибо (как мне помнится) добавление
		//элемента на поле происходит по клику мыши на соответствующей ячейке поля
		if (object == GameObject.LADYBUG) {
			if (!isControlObjectOnField()) {	//Если на поле уже есть объект управления, то ничего не добавляется. Можно придумать код ошибки.
				field[y][x] = GameObject.LADYBUG;
			}
		}
		else {
			field[y][x] = object;
		}
	}
	
	public void removeObject(int x, int y) {
		field[y][x] = GameObject.EMPTY_CELL;
	}
	
	public void cleanField() {
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeigh(); j++) {
				field[j][i] = GameObject.EMPTY_CELL;
			}
		}
	}
	
	public void automaticCompositionField() {
		cleanField();
		Random random = new Random();
		int currentObjectAmount;
		int objectMaxAmount = (int)(getWidth() * getHeigh() * MAX_OBJECT_PERCENT); //константа, обеспечивающая заполнение поля объектом каждого типа не более, чем на 20%
		field[random.nextInt(getHeigh())][random.nextInt(getWidth())] = GameObject.LADYBUG; //ставим одну божью коровку
		int currentX = random.nextInt(getWidth());
		int currentY = random.nextInt(getHeigh());
		for (int i = 2; i < 5; i++) {
			currentObjectAmount = random.nextInt(objectMaxAmount + 1);
			for (int j = 0; j < currentObjectAmount; j++) {
				while (field[currentY][currentX] != GameObject.EMPTY_CELL) {
					currentX = random.nextInt(getWidth());
					currentY = random.nextInt(getHeigh());
				}
				field[currentY][currentX] = GameObject.values()[i];
			}
		}
	}
}
