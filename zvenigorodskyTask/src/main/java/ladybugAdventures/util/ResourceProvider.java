package ladybugAdventures.util;

import java.io.InputStream;

public class ResourceProvider {
	public final static String BACKGROUND_ID = "/main/background";
	private final static String BACKGROUND_LINK = "/img/pictures/background.jpg";
	public final static String LADYBUG_ID = "/gameobject/ladybug";
	private final static String LADYBUG_LINK = "/img/pictures/buglady.png";
	public final static String START_BUTTON_ID = "/main/startButton";
	private final static String START_BUTTON_LINK = "/img/pictures/startButton.png";
	public final static String START_BUTTON_PRESSED_ID = "/main/startButtonPressed";
	private final static String START_BUTTON_PRESSED_LINK = "/img/pictures/startButtonPressed.png";
	public final static String EMPTY_CELL_ID = "/img/pictures/cell";
	private final static String EMPTY_CELL_LINK = "/img/pictures/cell.png";
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
		}
		return ResourceProvider.class.getResourceAsStream(resultLink);
	}
}
