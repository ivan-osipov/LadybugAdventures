package ladybugAdventures.ui.animation.components;

import java.awt.Font;

import org.eclipse.swt.graphics.Point;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

public class CommonInformationRenderer extends AbstractComponent {
	

	Point location;
	Point size;
	String info;
	UnicodeFont font2;
	public CommonInformationRenderer(GUIContext container, Point location, Point size, String info) {
		super(container);
		this.location = location;
		this.size = size;
		this.info = info;
	}
	
	@Override
	public void render(GUIContext container, Graphics g) throws SlickException {
		font2.drawString(location.x, location.y, info, Color.green);
	}
	@SuppressWarnings("unchecked")
	public void init(GameContainer container) throws SlickException{
		Font awtFont = new Font("Cambria", Font.ITALIC, 20);
		
		font2 = new UnicodeFont(awtFont);
		font2.addAsciiGlyphs();
		font2.addGlyphs(1024, 1279);//русские символы внутри unicode
		font2.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
		font2.loadGlyphs();
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
