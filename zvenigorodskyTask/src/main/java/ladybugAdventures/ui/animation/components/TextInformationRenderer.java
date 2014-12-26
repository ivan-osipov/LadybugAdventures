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

public class TextInformationRenderer extends AbstractComponent {
	

	private Point location;
	private Point size;
	private String info;
	private UnicodeFont font2;
	public TextInformationRenderer(GUIContext container, Point location, String info) {
		super(container);
		this.location = location;
		this.size = new Point(0,0);//для текста не нужен
		this.info = info;
	}
	
	@Override
	public void render(GUIContext container, Graphics g) throws SlickException {
		font2.drawString(location.x, location.y, info, Color.green);
	}
	public void render(GUIContext container, Graphics g, Color color) throws SlickException {
		font2.drawString(location.x, location.y, info, color);
	}
	@SuppressWarnings("unchecked")
	public void init(GUIContext container) throws SlickException{
		Font awtFont = new Font("Cambria", Font.ITALIC, 20);
		
		font2 = new UnicodeFont(awtFont);
		font2.addAsciiGlyphs();
		font2.addGlyphs(1024, 1279);//русские символы внутри unicode
		font2.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
		font2.loadGlyphs();
	}
	public void init(GUIContext container,Font font) throws SlickException{
		
		font2 = new UnicodeFont(font);
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
