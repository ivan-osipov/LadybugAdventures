package technologyOfProgramming.zvenigorodskyTask.entities;

import java.util.LinkedList;
import java.util.List;

import technologyOfProgramming.zvenigorodskyTask.interfaces.Command;

public class Cycle implements Command {
	private final int ITERATIONS_DEFAULT = 1;
	private int iterations;
	private List<Command> commandList;
	public Cycle(){
		iterations = ITERATIONS_DEFAULT;
		commandList = new LinkedList<>();
	}
	public Cycle(int iterations, List<Command> commandList){
		this.iterations = iterations;
		this.commandList = commandList;
	}
	@Override
	public CommandType getType() {
		return CommandType.CYCLE;
	}
	public int getIterations() {
		return iterations;
	}
	public void setIterations(int iterations) {
		this.iterations = iterations;
	}
	public List<Command> getCommandList() {
		return commandList;
	}
	public void setCommandList(List<Command> commandList) {
		this.commandList = commandList;
	}

}
