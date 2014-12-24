package ladybugAdventures.util;

import ladybugAdventures.enums.CommandType;
import ladybugAdventures.enums.Direction;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.wb.swt.SWTResourceManager;

public class CommandConverter {//FIXME all "getResource" replace getResourceAsStream//move methods to ResourceProvider
	public static Image fromTypeToImage(CommandType type,int width, int height){
		ImageData imgData = null;
		switch (type) {
			case JUMP:
				imgData = SWTResourceManager.getImage(CommandConverter.class.getResource("/img/icons/jump.png").getPath()).getImageData();
				break;
			case MOVE:
				imgData = SWTResourceManager.getImage(CommandConverter.class.getResource("/img/icons/step.png").getPath()).getImageData();
				break;
			case PUSH:
				imgData = SWTResourceManager.getImage(CommandConverter.class.getResource("/img/icons/push.png").getPath()).getImageData();
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
				img = SWTResourceManager.getImage(CommandConverter.class.getResource("/img/icons/up.png").getPath());
				break;
			case DOWN:
				img = SWTResourceManager.getImage(CommandConverter.class.getResource("/img/icons/down.png").getPath());
				break;
			case LEFT:
				img = SWTResourceManager.getImage(CommandConverter.class.getResource("/img/icons/left.png").getPath());
				break;
			case RIGHT:
				img = SWTResourceManager.getImage(CommandConverter.class.getResource("/img/icons/right.png").getPath());
				break;
		}
		return (new Image(img.getDevice(), img.getImageData().scaledTo(width, height)));
	}
}
