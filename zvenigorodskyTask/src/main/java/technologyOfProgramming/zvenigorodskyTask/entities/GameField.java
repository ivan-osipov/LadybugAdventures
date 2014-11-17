package technologyOfProgramming.zvenigorodskyTask.entities;

import java.io.Serializable;
import java.util.Random;

public class GameField implements Serializable {
	/**
	 * 
	 */
	public final float MAX_OBJECT_PERCENT = (float)0.2;
	private static final long serialVersionUID = 1L;
	//предлагаю использовать следующую систему кодов объектов
	//0 - пустая клетка
	//1 - божья коровка
	//2 - кубик
	//3 - ямка
	//4 - занятая клетка
	private objects[][] field;
	private enum objects {LADYBUG, EMPTY_CELL, BLOCK, HOLE, OCCUPIED_CELL}
	
	public objects[][] getField() {
		return field;
	}
	
	public objects getType (int x, int y) {
		return field[y][x];
	}
	
	public int getWidth() {
		return field[0].length;
	}
	
	public int getHeigh() {
		return field.length;
	}
	
	public GameField(int width, int height) {
		field = new objects[height][width];
		cleanField();
	}
	
	public boolean isControlObjectOnField() {
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeigh(); j++) {
				if (field[j][i] == objects.LADYBUG) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void addObject(objects object, int x, int y) {
		//проверку на выходы за границы поля не делаю, ибо (как мне помнится) добавление
		//элемента на поле происходит по клику мыши на соответствующей ячейке поля
		if (object == objects.LADYBUG) {
			if (!isControlObjectOnField()) {	//Если на поле уже есть объект управления, то ничего не добавляется. Можно придумать код ошибки.
				field[y][x] = objects.LADYBUG;
			}
		}
		else {
			field[y][x] = object;
		}
	}
	
	public void removeObject(int x, int y) {
		field[y][x] = objects.EMPTY_CELL;
	}
	
	public void cleanField() {
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeigh(); j++) {
				field[j][i] = objects.EMPTY_CELL;
			}
		}
	}
	
	public void automaticCompositionField() {
		Random random = new Random();
		int currentObjectAmount;
		int objectMaxAmount = (int)(getWidth() * getHeigh() * MAX_OBJECT_PERCENT); //константа, обеспечивающая заполнение поля объектом каждого типа не более, чем на 20%
		field[random.nextInt(getHeigh())][random.nextInt(getWidth())] = objects.LADYBUG; //ставим одну божью коровку
		int currentX = random.nextInt(getWidth());
		int currentY = random.nextInt(getHeigh());
		for (int i = 2; i < 5; i++) {
			currentObjectAmount = random.nextInt(objectMaxAmount + 1);
			for (int j = 0; j < currentObjectAmount; j++) {
				while (field[currentY][currentX] != objects.EMPTY_CELL) {
					currentX = random.nextInt(getWidth());
					currentY = random.nextInt(getHeigh());
				}
				field[currentY][currentX] = objects.values()[i];
			}
		}
	}
}
