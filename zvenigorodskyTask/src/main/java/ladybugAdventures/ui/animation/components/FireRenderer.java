package ladybugAdventures.ui.animation.components;

import ladybugAdventures.util.ResourceProvider;

import org.eclipse.swt.graphics.Point;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

public class FireRenderer extends AbstractComponent {
	private int x,y,wigth,height;
	private Animation sprite;
	private boolean visible;
	public FireRenderer(GUIContext container, int size) throws SlickException {
		super(container);
		wigth = size;
		height = size;
		SpriteSheet fireFrames = new SpriteSheet(
				new Image(ResourceProvider.getResInpStr(ResourceProvider.FIRE_SPRITE_ID),
						ResourceProvider.FIRE_SPRITE_ID,false), 100, 108);
		sprite = new Animation(fireFrames, 50);
		sprite.setLooping(false);
		visible = false;
	}

	@Override
	public void render(GUIContext container, Graphics g) throws SlickException {
		if(visible){
			sprite.draw(x, y, wigth, height);
			if(sprite.isStopped()){
				setVisible(false);
			}
		}
	}
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
		if(visible)
			sprite.restart();
	}

	@Override
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;

	}
	public void restart(){
		sprite.restart();
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
		return wigth;
	}

	@Override
	public int getHeight() {
		return height;
	}

}
