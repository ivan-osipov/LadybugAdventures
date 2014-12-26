package ladybugAdventures.util;

import java.util.ArrayList;
import java.util.List;

import ladybugAdventures.entities.CommandImpl;
import ladybugAdventures.entities.GameField;
import ladybugAdventures.entities.ManagementProgram;
import ladybugAdventures.entities.interfaces.Command;
import ladybugAdventures.enums.Behaviour;
import ladybugAdventures.enums.ErrorType;
import ladybugAdventures.enums.GameObject;

import org.eclipse.swt.graphics.Point;

public class Analizator {
	private ErrorType error;
	private Behaviour behaviour;
	private GameField fieldBeforeStep;
	private GameField fieldAfterStep;
	private List<StepTrack> trackList;
	private List<Command> commandList;
	private boolean ladybugOnOccupiedCell;
	private boolean endOfProgram;
	private int currentStep;
	
	public Analizator(GameField field, ManagementProgram program){
		fieldBeforeStep = field.clone();
		fieldAfterStep = field.clone();
		error = ErrorType.NONE_ERROR;
		ladybugOnOccupiedCell = false;
		commandList = program.getCommandListInLine();
		currentStep = 0;
		endOfProgram = false;
		trackList = new ArrayList<StepTrack>();
	}
	
	public GameField getFieldBeforeStep() {
		return fieldBeforeStep;
	}
	
	public GameField getFieldAfterStep() {
		return fieldAfterStep;
	}
	
	public List<StepTrack> getTrackList() {
		return trackList;
	}
	
	public boolean isEndOfProgram() {
		return endOfProgram;
	}
	
	public boolean isLadybugOnOccupiedCell() {
		return ladybugOnOccupiedCell;
	}
	
	public ErrorType getCurrentError() {
		return error;
	}
	
	public Behaviour getCurrentBehaviour() {
		return behaviour;
	}
	
	public CommandImpl getLastPerformedCommand() {
		return (CommandImpl)commandList.get(currentStep - 1); 
	}
	
	private Point getDirection(CommandImpl command) {
		Point direction = new Point(0, 0);
		switch (command.getDirection()) {
		case UP:
			direction.y--;
			if (fieldBeforeStep.getControlObjectCoordinates().y + direction.y < 0) {
				error = ErrorType.FIELD_BORDER;
			}
			break;
		case DOWN:
			direction.y++;
			if (fieldBeforeStep.getControlObjectCoordinates().y + direction.y >= fieldBeforeStep.getHeigh()) {
				error = ErrorType.FIELD_BORDER;
			}
			break;
		case LEFT:
			direction.x--;
			if (fieldBeforeStep.getControlObjectCoordinates().x + direction.x < 0) {
				error = ErrorType.FIELD_BORDER;
			}
			break;
		case RIGHT:
			direction.x++;
			if (fieldBeforeStep.getControlObjectCoordinates().x + direction.x >= fieldBeforeStep.getWidth()) {
				error = ErrorType.FIELD_BORDER;
			}
			break;
		}
		return direction;
	}
	
	private boolean canPerform(CommandImpl command){
		trackList.clear();
		Point controlObjectCoordinates = fieldBeforeStep.getControlObjectCoordinates();
		Point direction = getDirection(command);
		if (error != ErrorType.NONE_ERROR) return false;
		switch (command.getType()) {
		case MOVE:
			switch (fieldBeforeStep.getType(controlObjectCoordinates.y + direction.y,
					controlObjectCoordinates.x + direction.x)) {
				case BLOCK:
					error = ErrorType.MOVE_BLOCK;
					return false;
				case HOLE:
					error = ErrorType.MOVE_HOLE;
					return false;
				default:
					behaviour = Behaviour.MOVING;
					trackList.add(new StepTrack(
							new Point(controlObjectCoordinates.x, 
									controlObjectCoordinates.y), 
							new Point(
									controlObjectCoordinates.x + direction.x, 
									controlObjectCoordinates.y + direction.y),
							fieldBeforeStep.getType(controlObjectCoordinates.y,
									controlObjectCoordinates.x)));
					return true;
			}
		case JUMP:
			switch (fieldBeforeStep.getType(controlObjectCoordinates.y + direction.y,
					controlObjectCoordinates.x + direction.x)) {
			case BLOCK:
				error = ErrorType.JUMP_BLOCK;
				return false;
			case EMPTY_CELL:
				error = ErrorType.JUMP_EMPTY_CELL;
				return false;
			case OCCUPIED_CELL:
				error = ErrorType.JUMP_OCCUPIED_CELL;
				return false;
			default:
				if (controlObjectCoordinates.x + 2 * direction.x < fieldBeforeStep.getWidth() &&
						controlObjectCoordinates.x + 2 * direction.x >= 0 &&
						controlObjectCoordinates.y + 2 * direction.y < fieldBeforeStep.getHeigh() &&
						controlObjectCoordinates.y + 2 * direction.y >= 0) {
					switch (fieldBeforeStep.getType(controlObjectCoordinates.y + 2 * direction.y,
							controlObjectCoordinates.x + 2 * direction.x)) {
					case BLOCK:
						error = ErrorType.JUMP_BLOCK;
						return false;
					case HOLE:
						error = ErrorType.JUMP_ON_HOLE;
						return false;
					default:
						behaviour = Behaviour.JUMPING;
						trackList.add(new StepTrack(
								new Point(controlObjectCoordinates.x, 
										controlObjectCoordinates.y), 
								new Point(
										controlObjectCoordinates.x + 2 * direction.x, 
										controlObjectCoordinates.y + 2 * direction.y),
								fieldBeforeStep.getType(controlObjectCoordinates.y,
										controlObjectCoordinates.x)));
						return true;
					}
				}
				else {
					error = ErrorType.FIELD_BORDER;
					return false;
				}
			}
		case PUSH:
			switch (fieldBeforeStep.getType(controlObjectCoordinates.y + direction.y,
					controlObjectCoordinates.x + direction.x)) {
			case EMPTY_CELL:
				error = ErrorType.PUSH_EMPTY_CELL;
				return false;
			case HOLE:
				error = ErrorType.PUSH_HOLE;
				return false;
			case OCCUPIED_CELL:
				error = ErrorType.PUSH_OCCUPIED_CELL;
				return false;
			default:
				if (controlObjectCoordinates.x + 2 * direction.x < fieldBeforeStep.getWidth() &&
						controlObjectCoordinates.x + 2 * direction.x >= 0 &&
						controlObjectCoordinates.y + 2 * direction.y < fieldBeforeStep.getHeigh() &&
						controlObjectCoordinates.y + 2 * direction.y >= 0) {
					switch (fieldBeforeStep.getType(controlObjectCoordinates.y + 2 * direction.y,
							controlObjectCoordinates.x + 2 * direction.x)) {
					case BLOCK:
						error = ErrorType.PUSH_TWO_BLOCKS;
						return false;
					case OCCUPIED_CELL:
						error = ErrorType.PUSH_BLOCK_TO_OCCUPIED_CELL;
						return false;
					default:
						behaviour = Behaviour.PUSHING;
						trackList.add(new StepTrack(
								new Point(controlObjectCoordinates.x, 
										controlObjectCoordinates.y), 
								new Point(controlObjectCoordinates.x + direction.x, 
										controlObjectCoordinates.y + direction.y),
								fieldBeforeStep.getType(controlObjectCoordinates.y,
										controlObjectCoordinates.x)));
						trackList.add(new StepTrack(
								new Point(controlObjectCoordinates.x + direction.x, 
										controlObjectCoordinates.y + direction.y), 
								new Point(controlObjectCoordinates.x + 2 * direction.x, 
										controlObjectCoordinates.y + 2 * direction.y),
								fieldBeforeStep.getType(controlObjectCoordinates.y + direction.y,
										controlObjectCoordinates.x + direction.x)));
						return true;
					}
				}
				else {
					error = ErrorType.FIELD_BORDER;
					return false;
				}
			}
		default: break;
		}
		return true;
	}
	
	private boolean performStep(CommandImpl command){
		fieldBeforeStep = fieldAfterStep.clone();
		if (canPerform(command)) {
			switch(command.getType()) {
			case PUSH:
				if (ladybugOnOccupiedCell) {
					fieldAfterStep.addObject(GameObject.OCCUPIED_CELL, 
							trackList.get(0).getStartPosition().y, 
							trackList.get(0).getStartPosition().x);
					ladybugOnOccupiedCell = false;
				}
				else {
					fieldAfterStep.addObject(GameObject.EMPTY_CELL, 
							trackList.get(0).getStartPosition().y, 
							trackList.get(0).getStartPosition().x);
				}
				if (fieldBeforeStep.getType(trackList.get(1).getFinishPosition().y,
						trackList.get(1).getFinishPosition().x) == GameObject.HOLE) {
					fieldAfterStep.addObject(GameObject.EMPTY_CELL,
							trackList.get(1).getFinishPosition().y,
							trackList.get(1).getFinishPosition().x);
				}
				else {
					fieldAfterStep.addObject(GameObject.BLOCK,
							trackList.get(1).getFinishPosition().y,
							trackList.get(1).getFinishPosition().x);
				}
				fieldAfterStep.addObject(GameObject.LADYBUG, 
						trackList.get(0).getFinishPosition().y, 
						trackList.get(0).getFinishPosition().x);
				break;
			default:
				if (ladybugOnOccupiedCell) {
					fieldAfterStep.addObject(GameObject.OCCUPIED_CELL, 
							trackList.get(0).getStartPosition().y, 
							trackList.get(0).getStartPosition().x);
				}
				else {
					fieldAfterStep.addObject(GameObject.EMPTY_CELL, 
							trackList.get(0).getStartPosition().y, 
							trackList.get(0).getStartPosition().x);
				}
				if (fieldBeforeStep.getType(trackList.get(0).getFinishPosition().y,
						trackList.get(0).getFinishPosition().x) == GameObject.EMPTY_CELL) {
					ladybugOnOccupiedCell = false;
				}
				else ladybugOnOccupiedCell = true;
				fieldAfterStep.addObject(GameObject.LADYBUG, 
						trackList.get(0).getFinishPosition().y, 
						trackList.get(0).getFinishPosition().x);
				break;
			}
			return true;
		}
		return false;
	}
	
	public boolean perform() {
		endOfProgram = false;
		for (int i = 0; i < commandList.size(); i++) {
			if (!performStep((CommandImpl)commandList.get(i)))
				return false;
		}
		endOfProgram = true;
		return endOfProgram;
	}
	
	public boolean nextStep() {
		if (currentStep < commandList.size()) {
			if (performStep((CommandImpl)commandList.get(currentStep))) {
				currentStep++;
				return true;
			}
		}
		else endOfProgram = true;
		return false;
	}
	
	public String getCurrentBehaviourDefinition() {
		switch (behaviour) {
		case MOVING:
			return "Пурум-пум-пум\r\nчем бы заняться?";
		case JUMPING:
			return "Йихуу! Полетели!";
		case PUSHING:
			return "Ну почему у ящиков нет колесиков?";
		default: return "";
		}
	}
	
	public String getCurrentErrorDefinition() {
		return getDefininitionError(error);
	}
	
	public String getDefininitionError(ErrorType error){
		switch(error){
		case FIELD_BORDER:
			return "Ой, туда нельзя!";
		case JUMP_BLOCK:
			return "Там же кубик! \r\nЯ не могу прыгнуть.";
		case JUMP_EMPTY_CELL:
			return"Я могу прыгать только через яму...Увы!";
		case JUMP_OCCUPIED_CELL:
			return"Это же не яма! \r\nПочему я должна прыгать?";
		case JUMP_ON_HOLE:
			return "Если я прыгну, то упаду в яму. \r\nДавай придумаем что-нибудь другое.";
		case MOVE_BLOCK:
			return"Я не могу беспрепятственно\r\nпройти...На моем пути кубик!";
		case MOVE_HOLE:
		    return"В яму можно упасть! \r\nЛучше бы мне перепрыгнуть её!";
		case PUSH_BLOCK_TO_OCCUPIED_CELL:
		    return"Я не могу поставить кубик на занятую клетку.";
		case PUSH_EMPTY_CELL:
		    return"К сожалению, мне нечего толкать. \r\nДавай встанем поближе к кубику.";
		case PUSH_HOLE:
			return"Я не могу толкать яму! \r\nДа и не очень-то и хотелось.";
		case PUSH_OCCUPIED_CELL:
			return"Я не могу толкать занятую клетку! \r\nДавай встанем поближе к кубику.";
		case PUSH_TWO_BLOCKS:
			return "Два кубика?! \r\nЭто слишком тяжело для меня!";
		default:
			return "Ты всё правильно сделал! \r\nМолодец!";
		}
	}
}
