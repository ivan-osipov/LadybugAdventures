package ladybugAdventures.entities;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import ladybugAdventures.entities.interfaces.Command;
import ladybugAdventures.enums.CommandType;

@XmlRootElement(name = "ManagementProgram")
@XmlType(propOrder = { "commandList" })
public class ManagementProgram extends Observable implements Iterable<Command> {
	@XmlTransient
	public final String AUTHOR_DEFAULT = "ANONYM";
	@XmlTransient
	public final String GAME_FIELD_ADDRESS = "NOT FOUND";

	private String author;
	private String gameFieldAddress;
	private List<Command> commandList;
	private int cycleAmount;

	public ManagementProgram() {
		commandList = new LinkedList<>();
		cycleAmount = 0;
		author = AUTHOR_DEFAULT;
		gameFieldAddress = GAME_FIELD_ADDRESS;
	}

	public ManagementProgram(String author, String gameFieldAddress) {
		this();
		this.author = author;
		this.gameFieldAddress = gameFieldAddress;
	}
	/**
	 * Получение полного списка команд с учетом итераций и вложенных циклов
	 * @return список команд
	 */
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
	@XmlElement(name = "command")
	public List<Command> getCommandList() {
		return commandList;
	}
	public boolean setManagementProgram(ManagementProgram program) {
		if(!setCommandList(program.commandList))
			return false;
		this.author = program.author;
		this.gameFieldAddress = program.gameFieldAddress;
		return true;
	}

	public boolean setCommandList(List<Command> commandList) {
		cycleAmount = 0;
		cycleAmount = getLargestCycleDepth(commandList);
		if (cycleAmount > 4)
			return false;
		this.commandList = commandList;
		setChanged();
		return true;
	}

	@XmlAttribute
	public int getCycleAmount() {
		return cycleAmount;
	}
	public int getAllCommandAmountWithIterations() {
		int commandAmount = 0;
		for(Command command: commandList){
			if(command instanceof Cycle){
				int cycleComandAmount = ((Cycle)command).getAllCommandAmountWithIterations();
				commandAmount += cycleComandAmount*((Cycle)command).getIterations();
			}
			else
				commandAmount++;
		}
		return commandAmount;
	}
	public int getAllCommandAmount() {
		int commandAmount = 0;
		for(Command command: commandList){
			if(command instanceof Cycle){
				int cycleComandAmount = ((Cycle)command).getAllCommandAmount();
				commandAmount += cycleComandAmount;
			}
			commandAmount++;
		}
		return commandAmount;
	}
	public int getCommandAmount() {
		return commandList.size();
	}

	public boolean insertCommand(int position, Command command) {
		if (insertToList(0, 0, position, command, commandList)) {
			cycleAmount = getLargestCycleDepth(commandList);
			setChanged();
			notifyObservers();
			return true;
		}
		return false;
	}

	public boolean removeCommand(int position) {
		if (removeFromList(0, position, commandList)) {
			cycleAmount = getLargestCycleDepth(commandList);
			setChanged();
			notifyObservers();
			return true;
		}
		return false;
	}

	private boolean removeFromList(int startPos, int position,
			List<Command> commandList) {
		int currentPos = startPos;
		for (int i = 0; i < commandList.size(); i++) {
			if (currentPos == position) {
				commandList.remove(i);
				return true;
			}

			if (commandList.get(i).getType() == CommandType.CYCLE) {
				if(removeFromList(currentPos+1, position,
						((Cycle) commandList.get(i)).getCommandList())){
					return true;
				}
				else{
					currentPos += ((Cycle)commandList.get(i)).getAllCommandAmount() + 1;
					continue;
				}

			}
			currentPos++;
		}
		return false;

	}

	/**
	 *
	 * Метод добавления команды в список команд с учетом заданной позиции, есть
	 * контроль глубины добавления (не >4).
	 *
	 * @param depth
	 *            текущая глубина, изначально должна быть равна 0
	 * @param startPos
	 *            начальная позиция, которой соответствует
	 * @param position
	 *            Позиция в списке с учетом всех вложений (заголовок цикла тоже
	 *            занимает позицию)
	 * @param command
	 *            Добавляемая команда
	 * @param commandList
	 *            Список команд в который добавляется команда
	 * @return true - добавление прошло успешно, false - была попытка добавить
	 *         цикл >4 уровня вложенности
	 */
	private boolean insertToList(int depth, int startPos, int position,//ИСПРАВИТЬ!!!
			Command command, List<Command> commandList) {
		if(position == -1){
			commandList.add(command);
			return true;
		}
		int currentPos = startPos;
		for (int i = 0; i < commandList.size(); i++) {
			if (currentPos == position) {
				commandList.add(i, command);
				return true;
			}
			if (commandList.get(i).getType() == CommandType.CYCLE) {
				if (depth == 3 && command.getType() == CommandType.CYCLE)
					return false;
				if( insertToList(depth+1, currentPos+1, position, command,
						((Cycle) commandList.get(i)).getCommandList()))
					return true;
				else{
					currentPos += ((Cycle)commandList.get(i)).getAllCommandAmount() + 1;
					continue;
				}

			}
			currentPos++;
		}
//		commandList.add(command);
		return false;
	}

	public void addCommand(Command command) {
		commandList.add(command);
		setChanged();
		notifyObservers();
	}

	public void cleanProgram() {
		commandList.clear();
		setChanged();
		notifyObservers();
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
		return gameFieldAddress;
	}

	public void setMapAddress(String mapAddress) {
		this.gameFieldAddress = mapAddress;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Command command : commandList) {
			builder.append(command.toString()).append("\r\n");
		}
		return builder.toString();
	}

	@Override
	public Iterator<Command> iterator() {
		return commandList.iterator();
	}

	/**
	 * Метод получения максимального колличества вложенных циклов
	 *
	 * @param commandList
	 *            Список команд, среди которым могут быть циклы
	 * @return 0, если нет ни одного цикла
	 */
	private int getLargestCycleDepth(List<Command> commandList) {
		int largestCycleDepth = 0;
		for (Command cycle : commandList) {
			if (cycle.getType() != CommandType.CYCLE)
				continue;
			Cycle currectCycle = (Cycle) cycle;
			int currentCycleDepth = 1;
			currentCycleDepth += getLargestCycleDepth(currectCycle
					.getCommandList());
			if (currentCycleDepth > largestCycleDepth)
				largestCycleDepth = currentCycleDepth;
		}
		return largestCycleDepth;
	}
}
