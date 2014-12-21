package technologyOfProgramming.zvenigorodskyTask.util;

import java.util.List;

import org.eclipse.swt.graphics.Point;

import technologyOfProgramming.zvenigorodskyTask.entities.CommandImpl;
import technologyOfProgramming.zvenigorodskyTask.entities.GameField;
import technologyOfProgramming.zvenigorodskyTask.entities.ManagementProgram;
import technologyOfProgramming.zvenigorodskyTask.entities.interfaces.Command;
import technologyOfProgramming.zvenigorodskyTask.enums.ErrorType;
import technologyOfProgramming.zvenigorodskyTask.enums.GameObject;

public class Analizator {
	private ErrorType error;
	private GameField fieldBeforeStep;
	private GameField fieldAfterStep;
	private ManagementProgram program;
	private List<StepTrack> trackList;
	private boolean ladybugOnOccupiedCell;
	
	public Analizator(GameField field, ManagementProgram program){
		fieldBeforeStep = fieldClone(field);
		fieldAfterStep = fieldClone(field);
		this.program = program;
		error = ErrorType.NONE_ERROR;
		ladybugOnOccupiedCell = false;
	}

	public GameField fieldClone(GameField field) {
		GameField newField = new GameField(field.getWidth(), field.getHeigh());
		for (int i = 0; i < field.getWidth(); i++) {
			for (int j = 0; j < field.getHeigh(); j++) {
				newField.addObject(field.getType(i, j), i, j);
			}
		}
		return newField;
	}
	
	public GameField getFieldBeforeStep() {
		return fieldBeforeStep;
	}
	
	public GameField getFieldAfterStep() {
		return fieldAfterStep;
	}
	
	private Point getDirection(CommandImpl command) {
		Point direction = new Point(0, 0);
		switch (command.getDirection()) {
		case UP:
			direction.y++;
			if (fieldBeforeStep.getControlObjectCoordinates().y + direction.y > fieldBeforeStep.getHeigh()) {
				error = ErrorType.FIELD_BORDER;
			}
			break;
		case DOWN:
			direction.y--;
			if (fieldBeforeStep.getControlObjectCoordinates().y + direction.y < 0) {
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
			if (fieldBeforeStep.getControlObjectCoordinates().x + direction.x > fieldBeforeStep.getWidth()) {
				error = ErrorType.FIELD_BORDER;
			}
			break;
		}
		return direction;
	}
	
	public boolean canPerform(CommandImpl command){
		java.awt.Point controlObjectCoordinates = fieldBeforeStep.getControlObjectCoordinates();
		Point direction = getDirection(command);
		if (error != ErrorType.NONE_ERROR) return false;
		switch (command.getType()) {
		case MOVE:
			switch (fieldBeforeStep.getType(controlObjectCoordinates.x + direction.x, 
						controlObjectCoordinates.y + direction.y)) {
				case BLOCK:
					error = ErrorType.MOVE_BLOCK;
					return false;
				case HOLE:
					error = ErrorType.MOVE_HOLE;
					return false;
				default:
					trackList.add(new StepTrack(new Point(controlObjectCoordinates.x, 
							controlObjectCoordinates.y), new Point(
								controlObjectCoordinates.x + direction.x, 
								controlObjectCoordinates.y + direction.y)));
					return true;
			}
		case JUMP:
			switch (fieldBeforeStep.getType(controlObjectCoordinates.x + direction.x, 
						controlObjectCoordinates.y + direction.y)) {
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
						controlObjectCoordinates.x + 2 * direction.x > 0 &&
						controlObjectCoordinates.y + 2 * direction.y < fieldBeforeStep.getHeigh() &&
						controlObjectCoordinates.y + 2 * direction.y > 0) {
					switch (fieldBeforeStep.getType(controlObjectCoordinates.x + 2 * direction.x, 
								controlObjectCoordinates.y + 2 * direction.y)) {
					case BLOCK:
						error = ErrorType.JUMP_BLOCK;
						return false;
					case HOLE:
						error = ErrorType.JUMP_ON_HOLE;
						return false;
					default:
						trackList.add(new StepTrack(new Point(controlObjectCoordinates.x, 
							controlObjectCoordinates.y), new Point(
								controlObjectCoordinates.x + 2 * direction.x, 
								controlObjectCoordinates.y + 2 * direction.y)));
						return true;
					}
				}
				else {
					error = ErrorType.FIELD_BORDER;
					return false;
				}
			}
		case PUSH:
			switch (fieldBeforeStep.getType(controlObjectCoordinates.x + direction.x, 
						controlObjectCoordinates.y + direction.y)) {
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
						controlObjectCoordinates.x + 2 * direction.x > 0 &&
						controlObjectCoordinates.y + 2 * direction.y < fieldBeforeStep.getHeigh() &&
						controlObjectCoordinates.y + 2 * direction.y > 0) {
					switch (fieldBeforeStep.getType(controlObjectCoordinates.x + 2 * direction.x, 
								controlObjectCoordinates.y + 2 * direction.y)) {
					case BLOCK:
						error = ErrorType.PUSH_TWO_BLOCKS;
						return false;
					case OCCUPIED_CELL:
						error = ErrorType.PUSH_BLOCK_TO_OCCUPIED_CELL;
						return false;
					default:
						trackList.add(new StepTrack(new Point(controlObjectCoordinates.x, 
								controlObjectCoordinates.y), new Point(
									controlObjectCoordinates.x + direction.x, 
									controlObjectCoordinates.y + direction.y)));
						trackList.add(new StepTrack(
								new Point(controlObjectCoordinates.x + direction.x, 
										controlObjectCoordinates.y + direction.y), 
								new Point(controlObjectCoordinates.x + 2 * direction.x, 
										controlObjectCoordinates.y + 2 * direction.y)));
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
	
	public boolean stepPerform(CommandImpl command){
		if (canPerform(command)) {
			fieldBeforeStep = fieldClone(fieldAfterStep);
			switch(command.getType()) {
			case PUSH:
				fieldAfterStep.addObject(GameObject.EMPTY_CELL, 
						trackList.get(trackList.size() - 2).getStartPosition().x, 
						trackList.get(trackList.size() - 2).getStartPosition().y);
				if (fieldBeforeStep.getType(trackList.get(trackList.size() - 1).getFinishPosition().x,
						trackList.get(trackList.size() - 1).getFinishPosition().y) == GameObject.HOLE) {
					fieldAfterStep.addObject(GameObject.EMPTY_CELL, 
							trackList.get(trackList.size() - 1).getFinishPosition().x,
							trackList.get(trackList.size() - 1).getFinishPosition().y);
				}
				else {
					fieldAfterStep.addObject(GameObject.BLOCK, 
							trackList.get(trackList.size() - 1).getFinishPosition().x,
							trackList.get(trackList.size() - 1).getFinishPosition().y);
				}
				fieldAfterStep.addObject(GameObject.LADYBUG, 
						trackList.get(trackList.size() - 1).getStartPosition().x, 
						trackList.get(trackList.size() - 1).getStartPosition().y);
				break;
			default:
				if (ladybugOnOccupiedCell) {
					fieldAfterStep.addObject(GameObject.OCCUPIED_CELL, 
							trackList.get(trackList.size() - 1).getStartPosition().x, 
							trackList.get(trackList.size() - 1).getStartPosition().y);
				}
				else {
					fieldAfterStep.addObject(GameObject.EMPTY_CELL, 
							trackList.get(trackList.size() - 1).getStartPosition().x, 
							trackList.get(trackList.size() - 1).getStartPosition().y);
				}
				if (fieldBeforeStep.getType(trackList.get(trackList.size() - 1).getFinishPosition().x,
						trackList.get(trackList.size() - 1).getFinishPosition().y) == GameObject.EMPTY_CELL) {
					ladybugOnOccupiedCell = false;
				}
				fieldAfterStep.addObject(GameObject.LADYBUG, 
						trackList.get(trackList.size() - 1).getFinishPosition().x, 
						trackList.get(trackList.size() - 1).getFinishPosition().y);
				break;
			}
			return true;
		}
		return false;
	}
	
	public ErrorType Perform() {
		List<Command> commandList = program.getCommandList();
		for (int i = 0; i < commandList.size(); i++) {
			if (!stepPerform((CommandImpl)commandList.get(i)))
				return error;
		}
		return ErrorType.NONE_ERROR;
	}
	
	public String getDefininitionError(){
		switch(error){
		case FIELD_BORDER:
			return "Я не могу уйти с поля!";
		case JUMP_BLOCK:
			return "К сожалению,я не могу прыгнуть на кубик!";
		case JUMP_EMPTY_CELL:
			return"Я могу прыгать только через яму...Увы!";
		case JUMP_OCCUPIED_CELL:
			return"Я не могу совершить прыжок - эта клетка уже занята не мной!";
		case JUMP_ON_HOLE:
			return "Если я прыгну, то попаду в яму. Давай придумаем что-нибудь другое.";
		case MOVE_BLOCK:
			return"Я не могу беспрепятственно пройти...На моем пути кубик!";
		case MOVE_HOLE:
		    return"В яму можно упасть! Лучше бы мне перепрыгнуть её!";
		case PUSH_BLOCK_TO_OCCUPIED_CELL:
		    return"Кубик нельзя поставить на занятую клетку... Попробуй еще раз!";
		case PUSH_EMPTY_CELL:
		    return"К сожалению, мне нечего толкать... Давай встанем поближе к кубику.";
		case PUSH_HOLE:
			return"Я не могу толкать яму! Давай встанем поближе к кубику.";
		case PUSH_OCCUPIED_CELL:
			return"Я не могу толкать занятую клетку! Давай встанем поближе к кубику.";
		case PUSH_TWO_BLOCKS:
			return "Два кубика?! Это слишком тяжело для меня!";
		default:
			return "Ты всё правильно сделал! Молодец!";
		}
	}
}
