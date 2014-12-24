package ladybugAdventures.ui.animation.components;

import ladybugAdventures.entities.GameField;
import ladybugAdventures.enums.GameObject;
import ladybugAdventures.util.Analizator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

public class GameFieldRenderer extends GameField{
	private int maxWidth;
	private int maxHeigth;
	private int cellSize;//длина/ширина рисованой клетки
	private int fieldWidth;
	private int fieldHeight;
	private int renderPosX;
	private int renderPosY;
	
	private GameContainer container;
	private BaseCellRenderer[][] emptyCellCollection;
	private BaseCellRenderer[][] contentCellCollection;
	
	public GameFieldRenderer(GameField field){
		super(field.getWidth(), field.getHeigh());
		for(int i = 0; i<super.getWidth();i++){
			for(int j = 0; j<super.getHeigh();j++){
				super.field[j][i] = field.getType(i, j);
			}
		}
//		super.field = this.field;
	}
	public GameFieldRenderer(int width, int height) {
		super(width, height);
	}
	public void render(GameContainer container, Graphics g) throws SlickException{
		g.setColor(new Color(152,251,152,0.4f));
		g.fillRect(renderPosX, renderPosY, fieldWidth, fieldHeight);
		for(int i = 0; i<getWidth(); i++){
			for(int j = 0; j<getHeigh(); j++){
				emptyCellCollection[i][j].render(container, g);
				if(getType(i, j)!=GameObject.EMPTY_CELL)
					contentCellCollection[i][j].render(container, g);
			}
		}
		
	}
	public void init(GameContainer container) throws SlickException{
		this.container = container;
		maxWidth = container.getWidth()-50;
		maxHeigth = container.getHeight()-150;
		int cellWidth =  maxWidth / getWidth();
		int cellHeigth = maxHeigth / getHeigh();
		cellSize = cellHeigth>cellWidth? cellWidth : cellHeigth;
		fieldWidth = cellSize*getWidth();
		fieldHeight = cellSize*getHeigh();
		renderPosX = (container.getWidth()-fieldWidth)/2;
		renderPosY = (container.getHeight()-100-getFieldHeight())/2;
		
		emptyCellCollection = new BaseCellRenderer[getWidth()][getHeigh()];
		contentCellCollection = new BaseCellRenderer[getWidth()][getHeigh()];
		for(int i = 0; i<getWidth(); i++){
			for(int j = 0; j<getHeigh(); j++){
				emptyCellCollection[i][j] = new BaseCellRenderer(container, cellSize);
				emptyCellCollection[i][j].setLocation(renderPosX+i*cellSize, renderPosY + j*cellSize);
				
				contentCellCollection[i][j] = new BaseCellRenderer(container, cellSize,getType(i, j));
				contentCellCollection[i][j].setLocation(renderPosX+i*cellSize, renderPosY + j*cellSize);
			}
		}
		
	}
	
	public int getFieldWidth() {
		return fieldWidth;
	}
	public void setFieldWidth(int fieldWidth) {
		this.fieldWidth = fieldWidth;
	}
	public int getFieldHeight() {
		return fieldHeight;
	}
	public void setFieldHeight(int fieldHeight) {
		this.fieldHeight = fieldHeight;
	}
	

}
