package ladybugAdventures.ui.animation.components;

import java.awt.Point;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

public class CommonInformationRenderer extends AbstractComponent {
	

	Point location;
	Point size;
	String info;
	public CommonInformationRenderer(GUIContext container, Point location, Point size, String info) {
		super(container);
		this.location = location;
		this.size = size;
		this.info = info;
	}
	
	@Override
	public void render(GUIContext container, Graphics g) throws SlickException {
		g.drawString(info, location.x, location.y);
	}
	public void setText(String text){
		info  = text;
	}
	@Override
	public void setLocation(int x, int y) {
		location = new Point(x, y);

	}

	@Override
	public int getX() {
		return location.x;
	}

	@Override
	public int getY() {
		return location.y;
	}

	@Override
	public int getWidth() {
		return size.x;
	}

	@Override
	public int getHeight() {
		return size.y;
	}

}
