package ladybugAdventures.util;


import ladybugAdventures.enums.Direction;
import ladybugAdventures.enums.GameObject;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class LazyRenderBuffer {
	private static Image emptyCell;
	private static Image box;
	private static Image hole;
	private static Image occupiedCell;
	private static Image ladybug1;
	private static Image ladybug2;//up default
	private static Image ladybug1l;
	private static Image ladybug2l;//left
	private static Image ladybug1r;
	private static Image ladybug2r;//right
	private static Image ladybug1d;
	private static Image ladybug2d;//down
	private static Image background;
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
				if(ladybug1==null){
					ladybug1 = new Image(ResourceProvider.getResInpStr(ResourceProvider.LADYBUG_FRAME1_ID),
							ResourceProvider.LADYBUG_FRAME1_ID,false);
				}
				recieve = ladybug1;
				break;
		}
		return recieve;
				
	}
	public static Image[] getImages(GameObject gameObject,Direction derection) throws SlickException{
		Image[] recieve = null;
		switch (gameObject) {
		case LADYBUG:
			recieve = new Image[2];
			switch (derection) {
			case UP:
				if(ladybug1==null){
					
					ladybug1 = new Image(ResourceProvider.getResInpStr(ResourceProvider.LADYBUG_FRAME1_ID),
							ResourceProvider.LADYBUG_FRAME1_ID,false);
				}
				if(ladybug2==null){
					ladybug2 = new Image(ResourceProvider.getResInpStr(ResourceProvider.LADYBUG_FRAME2_ID),
							ResourceProvider.LADYBUG_FRAME2_ID,false);
				}
				recieve[0] =ladybug1;
				recieve[1] =ladybug2;
				break;

			case DOWN:
				if(ladybug1d==null){
					
					ladybug1d = new Image(ResourceProvider.getResInpStr(ResourceProvider.LADYBUG_FRAME1_DOWN_ID),
							ResourceProvider.LADYBUG_FRAME1_DOWN_ID,false);
				}
				if(ladybug2d==null){
					ladybug2d = new Image(ResourceProvider.getResInpStr(ResourceProvider.LADYBUG_FRAME2_DOWN_ID),
							ResourceProvider.LADYBUG_FRAME2_DOWN_ID,false);
				}
				recieve[0] =ladybug1d;
				recieve[1] =ladybug2d;
				break;
			case LEFT:
				if(ladybug1l==null){
					
					ladybug1l = new Image(ResourceProvider.getResInpStr(ResourceProvider.LADYBUG_FRAME1_LEFT_ID),
							ResourceProvider.LADYBUG_FRAME1_LEFT_ID,false);
				}
				if(ladybug2l==null){
					ladybug2l = new Image(ResourceProvider.getResInpStr(ResourceProvider.LADYBUG_FRAME2_LEFT_ID),
							ResourceProvider.LADYBUG_FRAME2_LEFT_ID,false);
				}
				recieve[0] =ladybug1l;
				recieve[1] =ladybug2l;	
				break;
			case RIGHT:
				if(ladybug1r==null){
					
					ladybug1r = new Image(ResourceProvider.getResInpStr(ResourceProvider.LADYBUG_FRAME1_RIGHT_ID),
							ResourceProvider.LADYBUG_FRAME1_RIGHT_ID,false);
				}
				if(ladybug2r==null){
					ladybug2r = new Image(ResourceProvider.getResInpStr(ResourceProvider.LADYBUG_FRAME2_RIGHT_ID),
							ResourceProvider.LADYBUG_FRAME2_RIGHT_ID,false);
				}
				recieve[0] =ladybug1r;
				recieve[1] =ladybug2r;
				break;

			}
			
			
			break;
		}
		return recieve;
	}
}
