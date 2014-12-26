package ladybugAdventures.util;


import ladybugAdventures.enums.Behaviour;
import ladybugAdventures.enums.Direction;
import ladybugAdventures.enums.GameObject;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class LazyRenderBuffer {
	public static int duration = 200;
	private static Image emptyCell;
	private static Image box;
	private static Image hole;
	private static Image occupiedCell;
	private static Image ladybug;
	
	private static Animation ladybug_up,ladybug_down,ladybug_right,ladybug_left;
	public static Image getImage(GameObject gameObject) throws SlickException{
		Image recieve = null;
		switch (gameObject) {
			case EMPTY_CELL:
				if(emptyCell==null){
					emptyCell = new Image(ResourceProvider.getResInpStr(ResourceProvider.EMPTY_CELL_ID),
							ResourceProvider.EMPTY_CELL_ID,false);
				}
				recieve = emptyCell;
				break;
				
			case BLOCK:
				if(box==null){
					box = new Image(ResourceProvider.getResInpStr(ResourceProvider.BLOCK_ID),
							ResourceProvider.BLOCK_ID,false);
				}
				recieve = box;
				break;
			
			case HOLE:
				if(hole==null){
					hole = new Image(ResourceProvider.getResInpStr(ResourceProvider.HOLE_ID),
							ResourceProvider.HOLE_ID,false);
				}
				recieve = hole;
				break;
				
			case OCCUPIED_CELL:
				if(occupiedCell==null){
					occupiedCell = new Image(ResourceProvider.getResInpStr(ResourceProvider.OCCUPIED_CELL_ID),
							ResourceProvider.OCCUPIED_CELL_ID,false);
				}		
				recieve = occupiedCell;
				break;
				
			case LADYBUG:
				if(ladybug==null){
					ladybug = new Image(ResourceProvider.getResInpStr(ResourceProvider.LADYBUG_ID),
							ResourceProvider.LADYBUG_ID,false);
				}
				recieve = ladybug;
				break;
		}
		return recieve;
				
	}
	public static Animation getAnimation(GameObject gameObject, Behaviour behaviour ,Direction derection) throws SlickException{
		Animation animation = null;
		switch (gameObject) {
			case LADYBUG:
				switch (derection) {
					case UP:
						if(ladybug_up==null){
							SpriteSheet frames = new SpriteSheet(
									new Image(ResourceProvider.getResInpStr(ResourceProvider.LADYBUG_UP_ID),
											ResourceProvider.LADYBUG_UP_ID,false), 256, 256);
							ladybug_up = new Animation(frames, duration);
							
						}
						animation = ladybug_up;
						break;
		
					case DOWN:
						if(ladybug_down==null){
							SpriteSheet frames = new SpriteSheet(
									new Image(ResourceProvider.getResInpStr(ResourceProvider.LADYBUG_DOWN_ID),
											ResourceProvider.LADYBUG_DOWN_ID,false), 256, 256);
							ladybug_down = new Animation(frames, duration);
							
						}
						animation = ladybug_down;
						break;
					case LEFT:
						if(ladybug_left==null){
							SpriteSheet frames = new SpriteSheet(
									new Image(ResourceProvider.getResInpStr(ResourceProvider.LADYBUG_LEFT_ID),
											ResourceProvider.LADYBUG_LEFT_ID,false), 256, 256);
							ladybug_left = new Animation(frames, duration);
							
						}	
						animation = ladybug_left;
						break;
					case RIGHT:
						if(ladybug_right==null){
							SpriteSheet frames = new SpriteSheet(
									new Image(ResourceProvider.getResInpStr(ResourceProvider.LADYBUG_RIGHT_ID),
											ResourceProvider.LADYBUG_RIGHT_ID,false), 256, 256);
							ladybug_right = new Animation(frames, duration);
							
						}
						animation = ladybug_right;
						break;
	
				}
			}
		return animation;
	}
}
