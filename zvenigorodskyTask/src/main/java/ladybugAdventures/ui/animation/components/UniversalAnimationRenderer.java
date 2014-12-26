package ladybugAdventures.ui.animation.components;

import ladybugAdventures.util.ResourceProvider;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

public class UniversalAnimationRenderer extends AbstractComponent {
	protected int x,y,wigth,height;
	protected Animation sprite;
	protected boolean visible;
	public UniversalAnimationRenderer(GUIContext container, int size) throws SlickException {
		super(container);
		wigth = size;
		height = size;
		x = 0;
		y = 0;
//		SpriteSheet sheet = new SpriteSheet(
//				new Image(ResourceProvider.getResInpStr(resourceProviderID),
//						resourceProviderID,false), tilesWidth, tilesHeight);
//		sprite = new Animation(sheet, duration);
//		sprite.setLooping(true);
		visible = false;
	}
	public void setFrameSpeed(float speed){
		sprite.setSpeed(speed);
	}
	public void setRepeated(boolean repeated){
		sprite.setLooping(repeated);
	}
	@Override
	public void render(GUIContext container, Graphics g) throws SlickException {
		if(!visible)
			return;
	}

	@Override
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;

	}
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
		if(visible)
			sprite.restart();
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
