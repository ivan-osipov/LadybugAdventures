package ladybugAdventures.util;

import ladybugAdventures.enums.Direction;
import ladybugAdventures.enums.GameObject;
import ladybugAdventures.ui.animation.components.GameFieldRenderer;

import org.eclipse.swt.graphics.Point;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MoveRenderElement {
	public Point source;
	public Point current;
	public Point result;
	public Animation sprite;
	private boolean animating;
	public MoveRenderElement(StepTrack data, GameFieldRenderer gameField) throws SlickException {

		this.source = new Point(gameField.getRenderPosX() + data.getStartPosition().x*gameField.getCellSize(),
				gameField.getRenderPosY() + data.getStartPosition().y*gameField.getCellSize());
		
		this.current = new Point(gameField.getRenderPosX() + data.getStartPosition().x*gameField.getCellSize(),
				gameField.getRenderPosY() + data.getStartPosition().y*gameField.getCellSize());
		
		this.result = new Point(gameField.getRenderPosX() + data.getFinishPosition().x*gameField.getCellSize(),
				gameField.getRenderPosY() + data.getFinishPosition().y*gameField.getCellSize());
		
		if(data.getGameObject()==GameObject.LADYBUG){
			int deltaX, deltaY;
			deltaX = data.getFinishPosition().x-data.getStartPosition().x;
			deltaY = data.getFinishPosition().y-data.getStartPosition().y;
			Direction direction = Direction.UP;
			if(deltaX>0){
				direction = Direction.RIGHT;
			}
			else{
				if(deltaX<0){
					direction = Direction.LEFT;
				}
				else
					if(deltaY>0)
						direction = Direction.DOWN;
			}
			this.sprite = new Animation(LazyRenderBuffer.getImages(GameObject.LADYBUG,direction), 70);
		}
		else{
			if(data.getGameObject()==GameObject.BLOCK)
				this.sprite = new Animation(new Image[]{LazyRenderBuffer.getImage(GameObject.BLOCK)}, 1000);//можно и ящичку анимацию дописать
		}
		setAnimating(false);
	}
	public boolean isAnimating() {
		return animating;
	}
	public void setAnimating(boolean animating) {
		this.animating = animating;
		if(animating)
			sprite.restart();
		else
			sprite.stop();
	}
	
}
