package ladybugAdventures.util;

import ladybugAdventures.enums.CommandType;
import ladybugAdventures.enums.Direction;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

public class CommandConverter {//FIXME all "getResource" replace getResourceAsStream//move methods to ResourceProvider
	public static Image fromTypeToImage(CommandType type,int width, int height){
		ImageData imgData = null;
		switch (type) {
			case JUMP:
				imgData =  new Image(Display.getDefault(), CommandConverter.class.getResourceAsStream("/img/icons/jump.png")).getImageData();
				break;
			case MOVE:
				imgData = new Image(Display.getDefault(), CommandConverter.class.getResourceAsStream("/img/icons/step.png")).getImageData();
				break;
			case PUSH:
				imgData = new Image(Display.getDefault(), CommandConverter.class.getResourceAsStream("/img/icons/push.png")).getImageData();
				break;
			case CYCLE:
				break;
		}
		Image img = new Image(Display.getDefault(), imgData);
		return (new Image(img.getDevice(), img.getImageData().scaledTo(width, height)));
	}
	public static Image fromDirectionToImage(Direction direction,int width, int height){
		Image img = null;
		switch (direction) {
			case UP:
				img = new Image(Display.getDefault(), CommandConverter.class.getResourceAsStream("/img/icons/up.png"));
				break;
			case DOWN:
				img = new Image(Display.getDefault(), CommandConverter.class.getResourceAsStream("/img/icons/down.png"));
				break;
			case LEFT:
				img = new Image(Display.getDefault(), CommandConverter.class.getResourceAsStream("/img/icons/left.png"));
				break;
			case RIGHT:
				img = new Image(Display.getDefault(), CommandConverter.class.getResourceAsStream("/img/icons/right.png"));
				break;
		}
		return (new Image(img.getDevice(), img.getImageData().scaledTo(width, height)));
	}
}
