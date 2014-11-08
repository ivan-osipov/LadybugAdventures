package technologyOfProgramming.zvenigorodskyTask.interfaces;

public interface Command {
	public enum Direction{UP, DOWN, LEFT, RIGHT}
	public enum CommandType{MOVE, JUMP, PUSH, CYCLE}
	public CommandType getType();
}
