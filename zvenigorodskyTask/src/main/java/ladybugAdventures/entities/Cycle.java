package ladybugAdventures.entities;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import ladybugAdventures.entities.interfaces.Command;
import ladybugAdventures.enums.CommandType;

@XmlRootElement(name = "Cycle")
@XmlType(propOrder = {"commandList"})
public class Cycle implements Command {
	private final int ITERATIONS_DEFAULT = 1;
	private int iterations;
	private List<Command> commandList;
	public Cycle(){
		iterations = ITERATIONS_DEFAULT;
		this.commandList = new LinkedList<>();
	}
	public Cycle(int iterations, List<Command> commandList){
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
	public List<Command> getCommandListInLine() {
		List<Command> commandList = new LinkedList<>();
		for(Command command: this.commandList){
			if(command instanceof Cycle){
				for(int i=0;i<((Cycle)command).getIterations();i++){
					commandList.addAll(((Cycle)command).getCommandListInLine());
				}
			}
			else
				commandList.add(command);
		}
		return commandList;
	}
	@XmlElement(name = "command", required = true)
	public List<Command> getCommandList() {
		return commandList;
	}
	public void setCommandList(List<Command> commandList) {
		this.commandList = commandList;
	}
	/**
	 * Число выполняющихся команд с учетом числа итераций
	 * @return
	 */
	public int getAllCommandAmountWithIterations(){
		int commandAmount = 0;
		for(Command command: commandList)
		{
			if(command instanceof Cycle){
				int cycleComandAmount = ((Cycle)command).getAllCommandAmountWithIterations();
				commandAmount += cycleComandAmount*((Cycle)command).getIterations();
			}
			else
				commandAmount++;
		}
		return commandAmount;
	}
	public int getAllCommandAmount(){
		int commandAmount = 0;
		for(Command command: commandList)
		{
			if(command instanceof Cycle){
				int cycleComandAmount = ((Cycle)command).getAllCommandAmount();
				commandAmount += cycleComandAmount;
			}
			commandAmount++;//команда рассматривается в любом случае - цикл тоже команда
		}
		return commandAmount;
	}
	public int getCommandAmount(){
		return commandList.size();
	}
	@Override
	public String toString() {
		return MessageFormat.format("Цикл. Повторится {0} раз(а). Команд внутри {1}", iterations, commandList.size());
	}

}
