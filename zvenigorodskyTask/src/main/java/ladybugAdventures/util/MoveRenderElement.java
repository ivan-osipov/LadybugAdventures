package ladybugAdventures.util;

import java.awt.Point;

import ladybugAdventures.enums.GameObject;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MoveRenderElement {
	public Point source;
	public Point current;
	public Point result;
	public Animation sprite;
	public MoveRenderElement(StepTrack data, int cellSize) throws SlickException {
		super();
		this.source = new Point(data.getStartPosition().x*cellSize,data.getStartPosition().y*cellSize);
		this.current = new Point(data.getStartPosition().x*cellSize,data.getStartPosition().y*cellSize);
		this.result = new Point(data.getFinishPosition().x*cellSize,data.getFinishPosition().y*cellSize);
		if(data.getGameObject()==GameObject.LADYBUG)
			this.sprite = new Animation((Image[]) LazyRenderBuffer.getImages(GameObject.LADYBUG).toArray(), 10);
		else
			if(data.getGameObject()==GameObject.BLOCK)
				this.sprite = new Animation(new Image[]{LazyRenderBuffer.getImage(GameObject.BLOCK)}, 1000);//можно и ящичку анимацию дописать
	}
}
