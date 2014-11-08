package technologyOfProgramming.zvenigorodskyTask.entities;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import technologyOfProgramming.zvenigorodskyTask.entities.enums.CommandType;
import technologyOfProgramming.zvenigorodskyTask.interfaces.Command;

@XmlRootElement(name = "Cycle")
@XmlType(propOrder = {"commandList"})
public class Cycle implements Command {
	private final int ITERATIONS_DEFAULT = 1;
	private int iterations;
	private List<CommandImpl> commandList;
	public Cycle(){
		iterations = ITERATIONS_DEFAULT;
		this.commandList = new LinkedList<>();
	}
	public Cycle(int iterations, List<CommandImpl> commandList){
		this.iterations = iterations;
		this.commandList = commandList;
	}
	@Override
	@XmlTransient
	public CommandType getType() {
		return CommandType.CYCLE;
	}
	@XmlAttribute
	public int getIterations() {
		return iterations;
	}
	public void setIterations(int iterations) {
		this.iterations = iterations;
	}
	@XmlElement(name = "command", required = true)
	public List<CommandImpl> getCommandList() {
		return commandList;
	}
	public void setCommandList(List<CommandImpl> commandList) {
		this.commandList = commandList;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("cycle: elemAmount = ").append(commandList.size()).append("; iterations = ").append(iterations).append("\r\n");
		for(int i = 0; i < commandList.size();i++)
			builder.append(commandList.get(i).toString());
		return builder.toString();
	}

}
