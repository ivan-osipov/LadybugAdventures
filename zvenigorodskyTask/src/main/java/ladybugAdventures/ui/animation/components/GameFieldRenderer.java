package ladybugAdventures.ui.animation.components;

import java.util.ArrayList;
import java.util.List;

import ladybugAdventures.entities.GameField;
import ladybugAdventures.enums.GameObject;
import ladybugAdventures.util.Analizator;
import ladybugAdventures.util.LazyRenderBuffer;
import ladybugAdventures.util.StepTrack;

import org.eclipse.swt.graphics.Point;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class GameFieldRenderer extends GameField{
	private int maxWidth;
	private int maxHeigth;
	private int cellSize;//длина/ширина рисованой клетки
	private int fieldWidth;
	private int fieldHeight;
	private int renderPosX;
	private int renderPosY;
	
	private BaseCellRenderer[][] emptyCellCollection;
	private Image emptyCell;
	private BaseCellRenderer[][] contentCellCollection;
	private List<Point> notRenderingList = new ArrayList<Point>();
	
	
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
	public void setGameField(GameField field){
		for(int i = 0; i<super.getWidth();i++){
			for(int j = 0; j<super.getHeigh();j++){
				super.field[j][i] = field.getType(i, j);
			}
		}
	}
	
	public void init(GameContainer container) throws SlickException{
		maxWidth = container.getWidth()-50;
		maxHeigth = container.getHeight()-150;
		int cellWidth =  maxWidth / getWidth();
		int cellHeigth = maxHeigth / getHeigh();
		cellSize = cellHeigth>cellWidth? cellWidth : cellHeigth;
		fieldWidth = cellSize*getWidth();
		fieldHeight = cellSize*getHeigh();
		renderPosX = (container.getWidth()-fieldWidth)/2;
		renderPosY = (container.getHeight()-100-getFieldHeight())/2;
		emptyCell = LazyRenderBuffer.getImage(GameObject.EMPTY_CELL);
//		emptyCellCollection = new BaseCellRenderer[getWidth()][getHeigh()];
		contentCellCollection = new BaseCellRenderer[getWidth()][getHeigh()];
		for(int i = 0; i<getWidth(); i++){
			for(int j = 0; j<getHeigh(); j++){
//				emptyCellCollection[i][j] = new BaseCellRenderer(container, cellSize);
//				emptyCellCollection[i][j].setLocation(renderPosX+i*cellSize, renderPosY + j*cellSize);
				
				contentCellCollection[i][j] = new BaseCellRenderer(container, cellSize,getType(i, j));
				contentCellCollection[i][j].setLocation(renderPosX+i*cellSize, renderPosY + j*cellSize);
			}
		}
		
	}
	public void render(GameContainer container, Graphics g) throws SlickException{
		g.setColor(new Color(152,251,152,0.4f));
		g.fillRect(renderPosX, renderPosY, fieldWidth, fieldHeight);//фон для поля
		boolean render;//флаг того, что контент ячейки будет отрисован, не должны отрисовываться только текущие анимируемые ячейки
		for(int i = 0; i<getWidth(); i++){
			for(int j = 0; j<getHeigh(); j++){
//				emptyCellCollection[i][j].render(container, g);
				emptyCell.draw(renderPosX+i*getCellSize(), renderPosY + j*getCellSize(),getCellSize(),getCellSize());//ячейка задднего фона будет отрисована в любом случае
//				if(getType(i, j)==GameObject.HOLE || getType(i, j)==GameObject.OCCUPIED_CELL)
				render = true;
				for(Point notPaint: notRenderingList){
					if(notPaint.x==j && notPaint.y == i)
						render = false;
				}
				if(render)
					contentCellCollection[i][j].render(container, g);
			}
		}
		
	}
	
	
	public int getRenderPosX() {
		return renderPosX;
	}
	public int getRenderPosY() {
		return renderPosY;
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
	public int getCellSize() {
		return cellSize;
	}
	public void setNotRenderList(List<StepTrack> notRenderList) {
		this.notRenderingList.clear();
		for(StepTrack step: notRenderList){
			this.notRenderingList.add(step.getStartPosition());
		}
		
	}
	

}
