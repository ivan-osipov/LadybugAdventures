package technologyOfProgramming.zvenigorodskyTask.entities;

import technologyOfProgramming.zvenigorodskyTask.interfaces.Command;

public class CommandImpl implements Command {
	private Direction direction;
	private CommandType type;
	public void setDirection(Direction direction){
		this.direction = direction;
	}
	public Direction getDirection(){
		return direction;
	}
	public CommandType getType() {
		return type;
	}
	public void setType(CommandType type) {
		this.type = type;
	}

}
