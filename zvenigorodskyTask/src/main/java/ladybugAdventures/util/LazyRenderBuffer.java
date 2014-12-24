package ladybugAdventures.util;

import java.util.LinkedList;
import java.util.List;

import ladybugAdventures.enums.GameObject;

import org.eclipse.swt.internal.win32.BP_PAINTPARAMS;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class LazyRenderBuffer {
	private static Image emptyCell;
	private static Image box;
	private static Image hole;
	private static Image occupiedCell;
	private static Image ladybug1;
	private static Image ladybug2;
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
	public static Image[] getImages(GameObject gameObject) throws SlickException{
		Image[] recieve = null;
		switch (gameObject) {
		case LADYBUG:
			recieve = new Image[2];
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
		}
		return recieve;
	}
}
