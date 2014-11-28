package technologyOfProgramming.zvenigorodskyTask.ui.graphic.components;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import technologyOfProgramming.zvenigorodskyTask.entities.GameField;

public class GameFieldRenderer extends GameField{
	private int maxWidth;
	private int maxHeigth;
	private int cellSize;
	private int fieldWidth;
	private int fieldHeight;

	public GameFieldRenderer(GameField field){
		super(field.getWidth(), field.getHeigh());
		super.field = this.field;
	}
	public GameFieldRenderer(int width, int height) {
		super(width, height);
	}
	public void render(GameContainer container, Graphics g){
		g.fillRect(25, 50, fieldWidth, fieldHeight);
	}
	public void init(GameContainer container){
		maxWidth = container.getWidth()-50;
		maxHeigth = container.getHeight()-150;
		int cellWidth =  maxWidth / getWidth();
		int cellHeigth = maxHeigth / getHeigh();
		cellSize = cellHeigth>cellWidth? cellWidth : cellHeigth;
		fieldWidth = cellSize*getWidth();
		fieldHeight = cellSize*getHeigh();
	}

}
