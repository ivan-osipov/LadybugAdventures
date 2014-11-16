package technologyOfProgramming.zvenigorodskyTask.entities;

import java.io.Serializable;
import java.util.Random;

public class GameField implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//предлагаю использовать следующую систему кодов объектов
	//0 - пустая клетка
	//1 - божья коровка
	//2 - кубик
	//3 - ямка
	//4 - занятая клетка
	private int[][] field;
	private Random random;
	
	public int[][] getField() {
		return field;
	}
	
	public int getWidth() {
		return field[0].length;
	}
	
	public int getHeigh() {
		return field.length;
	}
	
	public GameField(int width, int height) {
		field = new int[height][width];
		random = new Random();
		cleanField();
	}
	
	public boolean isControlObjectOnField() {
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeigh(); j++) {
				if (field[j][i] == 1) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void addObject(int objectCode, int x, int y) {
		//проверку на выходы за границы поля не делаю, ибо (как мне помнится) добавление
		//элемента на поле происходит по клику мыши на соответствующей ячейке поля
		if (objectCode == 1) {
			if (!isControlObjectOnField()) {	//Если на поле уже есть объект управления, то ничего не добавляется. Можно придумать код ошибки.
				field[y][x] = objectCode;
			}
		}
		else {
			field[y][x] = objectCode;
		}
	}
	
	public void removeObject(int x, int y) {
		field[y][x] = 0;
	}
	
	public void cleanField() {
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeigh(); j++) {
				field[j][i] = 0;
			}
		}
	}
	
	public void automaticCompositionField() {
		int currentObjectValue;
		int objectMaxValue = (int)(getWidth() * getHeigh() * 0.2); //константа, обеспечивающая заполнение поля объектом каждого типа не более, чем на 20%
		field[random.nextInt(getHeigh())][random.nextInt(getWidth())] = 1; //ставим одну божью коровку
		int currentX = random.nextInt(getWidth());
		int currentY = random.nextInt(getHeigh());
		for (int i = 2; i < 5; i++) {
			currentObjectValue = random.nextInt(objectMaxValue + 1);
			for (int j = 0; j < currentObjectValue; j++) {
				while (field[currentY][currentX] != 0) {
					currentX = random.nextInt(getWidth());
					currentY = random.nextInt(getHeigh());
				}
				field[currentY][currentX] = i;
			}
		}
	}
}
