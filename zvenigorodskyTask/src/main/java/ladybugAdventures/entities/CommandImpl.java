package ladybugAdventures.entities;

import java.text.MessageFormat;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import ladybugAdventures.entities.interfaces.Command;
import ladybugAdventures.enums.CommandType;
import ladybugAdventures.enums.Direction;

@XmlRootElement(name = "Command")
@XmlType(propOrder = { "direction", "type" })
public class CommandImpl implements Command {

	private Direction direction;
	private CommandType type;

	public CommandImpl() {
	}

	public CommandImpl(CommandType type) {
		this.type = type;
	}

	public CommandImpl(Direction direction, CommandType type) {
		this.direction = direction;
		this.type = type;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	@XmlElement(name = "direction")
	public Direction getDirection() {
		return direction;
	}

	@XmlElement(name = "commandType")
	public CommandType getType() {
		return type;
	}

	public void setType(CommandType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		String formatDirection = "";
		String formatType = "";
		switch (direction) {
		case UP:
			formatDirection = "ВВЕРХ";
			break;

		case DOWN:
			formatDirection = "ВНИЗ";
			break;
		case LEFT:
			formatDirection = "ВЛЕВО";
			break;
		case RIGHT:
			formatDirection = "ВПРАВО";
			break;
		}

		switch (type) {
		case MOVE:
			formatType = "ИДТИ";
			break;

		case JUMP:
			formatType = "ПРЫГАТЬ";
			break;
		case PUSH:
			formatType = "ТОЛКАТЬ";
			break;
		}
		return MessageFormat.format("{0} {1}\r\n",formatType, formatDirection);
	}

}
