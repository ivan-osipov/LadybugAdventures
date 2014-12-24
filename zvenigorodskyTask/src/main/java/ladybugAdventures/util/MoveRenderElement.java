package ladybugAdventures.util;

import java.awt.Point;

import ladybugAdventures.entities.GameField;
import ladybugAdventures.enums.GameObject;
import ladybugAdventures.ui.animation.components.GameFieldRenderer;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MoveRenderElement {
	public Point source;
	public Point current;
	public Point result;
	public Animation sprite;
	public MoveRenderElement(StepTrack data, GameFieldRenderer gameField) throws SlickException {
		super();
		this.source = new Point(gameField.getRenderPosX() + data.getStartPosition().x*gameField.getCellSize(),
				gameField.getRenderPosY() + data.getStartPosition().y*gameField.getCellSize());
		
		this.current = new Point(gameField.getRenderPosX() + data.getStartPosition().x*gameField.getCellSize(),
				gameField.getRenderPosY() + data.getStartPosition().y*gameField.getCellSize());
		
		this.result = new Point(gameField.getRenderPosX() + data.getFinishPosition().x*gameField.getCellSize(),
				gameField.getRenderPosY() + data.getFinishPosition().y*gameField.getCellSize());
		
		if(data.getGameObject()==GameObject.LADYBUG)
			this.sprite = new Animation(LazyRenderBuffer.getImages(GameObject.LADYBUG), 100);
		else
			if(data.getGameObject()==GameObject.BLOCK)
				this.sprite = new Animation(new Image[]{LazyRenderBuffer.getImage(GameObject.BLOCK)}, 1000);//можно и ящичку анимацию дописать
	}
}
