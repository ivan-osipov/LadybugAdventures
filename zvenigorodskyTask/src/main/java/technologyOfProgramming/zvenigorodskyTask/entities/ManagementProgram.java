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


@XmlRootElement(name = "ManagementProgram")
@XmlType(propOrder = {"commandList"})
public class ManagementProgram {
	private final String AUTHOR_DEFAULT = "ANONYM";
	private final String MAP_ADDRESS = "NOT FOUND";
	private String author;
	private String mapAddress;
	private List<Command> commandList;
	private int cycleAmount;
	public ManagementProgram(){
		commandList = new LinkedList<>();
		cycleAmount = 0;
		author = AUTHOR_DEFAULT;
		mapAddress = MAP_ADDRESS;
	}
	@XmlElement(name = "command")
	public List<Command> getCommandList() {
		return commandList;
	}
	public boolean setCommandList(List<Command> commandList) {
		cycleAmount = 0;
		for(Command command: commandList)
			if(command.getType().equals(CommandType.CYCLE))
				cycleAmount++;
		if(cycleAmount > 4)
			return false;
		this.commandList = commandList;
		return true;
	}
	@XmlAttribute
	public int getCycleAmount(){
		return cycleAmount;
	}
	public int getCommandAmount(){
		return commandList.size();
	}
	public boolean insertCommand(int position, Command command){
		if(command.getType().equals(CommandType.CYCLE)
				&& cycleAmount == 4)
			return false;
		commandList.add(position, command);
		if(command.getType().equals(CommandType.CYCLE))
			cycleAmount++;
		return true;
	}
	public boolean addCommand(Command command){
		if(command.getType().equals(CommandType.CYCLE)
				&& cycleAmount == 4)
			return false;
		commandList.add(command);
		if(command.getType().equals(CommandType.CYCLE))
			cycleAmount++;
		return true;
	}
	@XmlAttribute
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@XmlAttribute
	public String getMapAddress() {
		return mapAddress;
	}
	public void setMapAddress(String mapAddress) {
		this.mapAddress = mapAddress;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(Command command: commandList){
			builder.append(command.toString()).append("\r\n");
		}
		return builder.toString();
	}
}
