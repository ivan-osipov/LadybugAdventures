package ladybugAdventures.util;

import java.io.InputStream;

public class ResourceProvider {
	//IDs
	public final static String BACKGROUND_ID 			= 		"/main/background";
	public final static String START_BUTTON_ID 			= 		"/main/startButton";
	public final static String START_BUTTON_PRESSED_ID 	= 		"/main/startButtonPressed";
	public final static String EMPTY_CELL_ID 			=		"/img/pictures/cell";
	public final static String LOAD_ICON_ID 			= 		"/img/icons/load";
	public final static String BLOCK_ID					=		"/img/pictures/block";
	public final static String HOLE_ID					=		"/img/pictures/hole";
	public final static String OCCUPIED_CELL_ID			=		"/img/pictures/occupiedCell";
	public final static String SAY_ID 					= 		"/img/pictures/say";

	public final static String LADYBUG_FRAME1_ID 		= 		"/gameobject/ladybug1";
	public final static String LADYBUG_FRAME2_ID 		= 		"/gameobject/ladybug2";
	
	public final static String LADYBUG_FRAME1_LEFT_ID	= 		"/gameobject/ladybug1/left";
	public final static String LADYBUG_FRAME2_LEFT_ID 	= 		"/gameobject/ladybug2/left";

	public final static String LADYBUG_FRAME1_RIGHT_ID 	= 		"/gameobject/ladybug1/right";
	public final static String LADYBUG_FRAME2_RIGHT_ID 	= 		"/gameobject/ladybug2/right";

	public final static String LADYBUG_FRAME1_DOWN_ID 		= 		"/gameobject/ladybug1/down";
	public final static String LADYBUG_FRAME2_DOWN_ID 		= 		"/gameobject/ladybug2/down";
	
	public final static String CLOUD_SPRITE_ID 				= 		"/sprites/cloud_sprite";
	public final static String FIRE_SPRITE_ID 				= 		"/sprites/FIRE_sprite";
	//links
	private final static String BACKGROUND_LINK 			= 		"/img/pictures/background.jpg";
	private final static String START_BUTTON_LINK 			= 		"/img/pictures/startButton.png";
	private final static String START_BUTTON_PRESSED_LINK 	= 		"/img/pictures/startButtonPressed.png";
	private final static String EMPTY_CELL_LINK 			= 		"/img/pictures/cell.png";
	private final static String LOAD_ICON_LINK 				= 		"/img/icons/load.jpg";
	private final static String BLOCK_LINK					=		"/img/pictures/box.png";
	private final static String HOLE_LINK					=		"/img/pictures/hole.png";
	private final static String OCCUPIED_CELL_LINK			=		"/img/pictures/occupied_cell.png";
	private final static String SAY_LINK			=		"/img/pictures/say.png";
	
	private final static String LADYBUG_FRAME1_LINK 		= 		"/img/pictures/ladybug_frame1.png";
	private final static String LADYBUG_FRAME2_LINK 		= 		"/img/pictures/ladybug_frame2.png";

	private final static String LADYBUG_FRAME1_LEFT_LINK 	= 		"/img/pictures/ladybug_frame1_left.png";
	private final static String LADYBUG_FRAME2_LEFT_LINK 	= 		"/img/pictures/ladybug_frame2_left.png";

	private final static String LADYBUG_FRAME1_RIGHT_LINK 	= 		"/img/pictures/ladybug_frame1_right.png";
	private final static String LADYBUG_FRAME2_RIGHT_LINK 	= 		"/img/pictures/ladybug_frame2_right.png";

	private final static String LADYBUG_FRAME1_DOWN_LINK 	= 		"/img/pictures/ladybug_frame1_down.png";
	private final static String LADYBUG_FRAME2_DOWN_LINK 	= 		"/img/pictures/ladybug_frame2_down.png";
	
	private final static String CLOUD_SPRITE_LINK 			= 		"/img/sprites/clouds.jpg";
	private final static String FIRE_SPRITE_LINK 			= 		"/img/sprites/fire1.png";
	
	
	public static InputStream getResInpStr(String RESOURCE_ID){
		String resultLink = null;
		switch (RESOURCE_ID) {
		case BACKGROUND_ID:
			resultLink = BACKGROUND_LINK;
			break;
		case LADYBUG_FRAME1_ID:
			resultLink = LADYBUG_FRAME1_LINK;
			break;
		case LADYBUG_FRAME2_ID:
			resultLink = LADYBUG_FRAME2_LINK;
			break;
		case START_BUTTON_ID:
			resultLink = START_BUTTON_LINK;
			break;
		case START_BUTTON_PRESSED_ID:
			resultLink = START_BUTTON_PRESSED_LINK;
			break;
		case EMPTY_CELL_ID:
			resultLink = EMPTY_CELL_LINK;
			break;
		case BLOCK_ID:
			resultLink = BLOCK_LINK;
			break;
		case HOLE_ID:
			resultLink = HOLE_LINK;
			break;
		case OCCUPIED_CELL_ID:
			resultLink = OCCUPIED_CELL_LINK;
			break;
		case LOAD_ICON_ID:
			resultLink = LOAD_ICON_LINK;
			break;
			
		case LADYBUG_FRAME1_LEFT_ID:
			resultLink = LADYBUG_FRAME1_LEFT_LINK;
			break;
		case LADYBUG_FRAME2_LEFT_ID:
			resultLink = LADYBUG_FRAME2_LEFT_LINK;
			break;
			
		case LADYBUG_FRAME1_RIGHT_ID:
			resultLink = LADYBUG_FRAME1_RIGHT_LINK;
			break;
		case LADYBUG_FRAME2_RIGHT_ID:
			resultLink = LADYBUG_FRAME2_RIGHT_LINK;
			break;
			
		case LADYBUG_FRAME1_DOWN_ID:
			resultLink = LADYBUG_FRAME1_DOWN_LINK;
			break;
		case LADYBUG_FRAME2_DOWN_ID:
			resultLink = LADYBUG_FRAME2_DOWN_LINK;
			break;
		case CLOUD_SPRITE_ID:
			resultLink = CLOUD_SPRITE_LINK;
			break;
		case FIRE_SPRITE_ID:
			resultLink = FIRE_SPRITE_LINK;
			break;
		case SAY_ID:
			resultLink = SAY_LINK;
			break;
		}
		return ResourceProvider.class.getResourceAsStream(resultLink);
	}
}
