package technologyOfProgramming.zvenigorodskyTask.entities;

import java.util.LinkedList;
import java.util.List;

import technologyOfProgramming.zvenigorodskyTask.interfaces.Command;

public class ManagementProgram {
	private List<Command> commandList;
	private int cycleAmount;
	public ManagementProgram(){
		commandList = new LinkedList<>();
		cycleAmount = 0;
	}
	public List<Command> getCommandList() {
		return commandList;
	}
	public void setCommandList(List<Command> commandList) {
		this.commandList = commandList;
		cycleAmount = 0;
		for(Command command: commandList)
			if(command.getType().equals(Command.CommandType.CYCLE))
				cycleAmount++;
	}
	public int getCycleAmount(){
		return cycleAmount;
	}
	public int getCommandAmount(){
		return commandList.size();
	}
	public void insertCommand(int position, Command command){
		commandList.add(position, command);
	}
}
