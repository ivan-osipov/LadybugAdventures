package ladybugAdventures.ui.animation.components;

import ladybugAdventures.enums.GameObject;
import ladybugAdventures.util.ResourceProvider;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

/**
 * Ячейка поля
 * @author Осипов Иван
 *
 */
public class BaseCellRenderer extends AbstractComponent {
	
	private int x,y,width,height;
	private Image image;
	public BaseCellRenderer(GUIContext container, int cellSize) throws SlickException {
		super(container);
		image = new Image(ResourceProvider.getResInpStr(ResourceProvider.EMPTY_CELL_ID),
				ResourceProvider.EMPTY_CELL_ID,false);
		this.width = cellSize;
		this.height = cellSize;
	}
	
	public BaseCellRenderer(GUIContext container,int cellSize, GameObject objectType) throws SlickException {
		super(container);
//		image = new Image(ResourceProvider.getResInpStr(ResourceProvider.EMPTY_CELL_ID),
//				ResourceProvider.EMPTY_CELL_ID,false);
		switch (objectType) {
			case EMPTY_CELL:
				image = new Image(ResourceProvider.getResInpStr(ResourceProvider.EMPTY_CELL_ID),
						ResourceProvider.EMPTY_CELL_ID,false);
				break;
			case BLOCK:
				image = new Image(ResourceProvider.getResInpStr(ResourceProvider.BLOCK_ID),
						ResourceProvider.BLOCK_ID,false);		
				break;
			case HOLE:
				image = new Image(ResourceProvider.getResInpStr(ResourceProvider.HOLE_ID),
						ResourceProvider.HOLE_ID,false);
				break;
			case LADYBUG:
				image = new Image(ResourceProvider.getResInpStr(ResourceProvider.LADYBUG_ID),
						ResourceProvider.LADYBUG_ID,false);
				break;
			case OCCUPIED_CELL:
				image = new Image(ResourceProvider.getResInpStr(ResourceProvider.OCCUPIED_CELL_ID),
						ResourceProvider.OCCUPIED_CELL_ID,false);
				break;
		}
		this.width = cellSize;
		this.height = cellSize;
	}
	
	@Override
	public void render(GUIContext container, Graphics g) throws SlickException {
		image.draw(x, y, width, height);

	}

	@Override
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

}
