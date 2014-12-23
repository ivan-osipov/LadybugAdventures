package ladybugAdventures.util;

import java.io.InputStream;

public class ResourceProvider {
	//IDs
	public final static String BACKGROUND_ID 			= 		"/main/background";
	public final static String LADYBUG_ID 				= 		"/gameobject/ladybug";
	public final static String START_BUTTON_ID 			= 		"/main/startButton";
	public final static String START_BUTTON_PRESSED_ID 	= 		"/main/startButtonPressed";
	public final static String EMPTY_CELL_ID 			=		"/img/pictures/cell";
	public final static String LOAD_ICON_ID 			= 		"/img/icons/load";
	public final static String BLOCK_ID					=		"/img/pictures/block";
	public final static String HOLE_ID					=		"/img/pictures/hole";
	public final static String OCCUPIED_CELL_ID			=		"/img/pictures/occupiedCell";
	//links
	private final static String BACKGROUND_LINK 			= 		"/img/pictures/background.jpg";
	private final static String LADYBUG_LINK 				= 		"/img/pictures/buglady.png";
	private final static String START_BUTTON_LINK 			= 		"/img/pictures/startButton.png";
	private final static String START_BUTTON_PRESSED_LINK 	= 		"/img/pictures/startButtonPressed.png";
	private final static String EMPTY_CELL_LINK 			= 		"/img/pictures/cell.png";
	private final static String LOAD_ICON_LINK 				= 		"/img/icons/load.jpg";
	private final static String BLOCK_LINK					=		"/img/pictures/box.png";
	private final static String HOLE_LINK					=		"/img/pictures/hole.png";
	private final static String OCCUPIED_CELL_LINK			=		"/img/pictures/occupied_cell.png";
	
	
	public static InputStream getResInpStr(String RESOURCE_ID){
		String resultLink = null;
		switch (RESOURCE_ID) {
		case BACKGROUND_ID:
			resultLink = BACKGROUND_LINK;
			break;
		case LADYBUG_ID:
			resultLink = LADYBUG_LINK;
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
		}
		return ResourceProvider.class.getResourceAsStream(resultLink);
	}
}
