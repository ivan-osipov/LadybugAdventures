package technologyOfProgramming.zvenigorodskyTask;

import java.util.List;

import org.eclipse.swt.graphics.Point;

import technologyOfProgramming.zvenigorodskyTask.entities.CommandImpl;
import technologyOfProgramming.zvenigorodskyTask.entities.GameField;
import technologyOfProgramming.zvenigorodskyTask.entities.ManagementProgram;
import technologyOfProgramming.zvenigorodskyTask.entities.enums.Direction;
import technologyOfProgramming.zvenigorodskyTask.entities.enums.ErrorType;
import technologyOfProgramming.zvenigorodskyTask.interfaces.Command;
import technologyOfProgramming.zvenigorodskyTask.util.StepTrack;

public class Analizator {
//	private String message;
	private ErrorType error;
	private GameField field;
	private ManagementProgram program;
	private List<StepTrack> trackList;
	
	public Analizator(GameField field, ManagementProgram program){
		this.field=field;
		this.program=program;
		error = ErrorType.NONE_ERROR;
	}

	public GameField clone() {
		GameField newField = new GameField(field.getWidth(), field.getHeigh());
		for (int i = 0; i < field.getWidth(); i++) {
			for (int j = 0; j < field.getHeigh(); j++) {
				newField.addObject(field.getType(i, j), i, j);
			}
		}
		return newField;
	}
	
	public GameField getCurrentField() {
		return clone();
	}
	
	public boolean canPerform(CommandImpl command){
		int x = 0;
		int y = 0;
		switch (command.getDirection()) {
		case UP:
			y++;
			if (field.getControlObjectCoordinates().y + y > field.getHeigh()) {
				error = ErrorType.FIELD_BORDER;
				return false;
			}
			break;
		case DOWN:
			y--;
			if (field.getControlObjectCoordinates().y + y > field.getHeigh()) {
				error = ErrorType.FIELD_BORDER;
				return false;
			}
			break;
		case LEFT:
			x--;
			if (field.getControlObjectCoordinates().x + x > field.getWidth()) {
				error = ErrorType.FIELD_BORDER;
				return false;
			}
			break;
		case RIGHT:
			x++;
			if (field.getControlObjectCoordinates().x + x > field.getWidth()) {
				error = ErrorType.FIELD_BORDER;
				return false;
			}
			break;
		}
		switch (command.getType()) {
		case MOVE:
			switch (field.getType(field.getControlObjectCoordinates().x + x, field.getControlObjectCoordinates().y + y)) {
				case BLOCK:
					error = ErrorType.MOVE_BLOCK;
					return false;
				case HOLE:
					error = ErrorType.MOVE_HOLE;
					return false;
				default:
					trackList.add(new StepTrack(new Point(field.getControlObjectCoordinates().x, field.getControlObjectCoordinates().y), new Point(field.getControlObjectCoordinates().x + x, field.getControlObjectCoordinates().y + y)));
					return true;
			}
		case JUMP:
			switch (field.getType(field.getControlObjectCoordinates().x + x, field.getControlObjectCoordinates().y + y)) {
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
				if (field.getControlObjectCoordinates().x + 2 * x < field.getWidth() && field.getControlObjectCoordinates().y + 2 * y < field.getHeigh()) {
					switch (field.getType(field.getControlObjectCoordinates().x + 2 * x, field.getControlObjectCoordinates().y + 2 * y)) {
					case BLOCK:
						error = ErrorType.JUMP_BLOCK;
						return false;
					case EMPTY_CELL:
						error = ErrorType.JUMP_EMPTY_CELL;
						return false;
					default:
						trackList.add(new StepTrack(new Point(field.getControlObjectCoordinates().x, field.getControlObjectCoordinates().y), new Point(field.getControlObjectCoordinates().x + 2 * x, field.getControlObjectCoordinates().y + 2 * y)));
						return true;
					}
				}
				else {
					error = ErrorType.FIELD_BORDER;
					return false;
				}
			}
		case PUSH:
			switch (field.getType(field.getControlObjectCoordinates().x + x, field.getControlObjectCoordinates().y + y)) {
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
				if (field.getControlObjectCoordinates().x + 2 * x < field.getWidth() && field.getControlObjectCoordinates().y + 2 * y < field.getHeigh()) {
					switch (field.getType(field.getControlObjectCoordinates().x + 2 * x, field.getControlObjectCoordinates().y + 2 * y)) {
					case BLOCK:
						error = ErrorType.PUSH_TWO_BLOCKS;
						return false;
					case OCCUPIED_CELL:
						error = ErrorType.PUSH_BLOCK_TO_OCCUPIED_CELL;
						return false;
					default:
						trackList.add(new StepTrack(new Point(field.getControlObjectCoordinates().x, field.getControlObjectCoordinates().y), new Point(field.getControlObjectCoordinates().x + x, field.getControlObjectCoordinates().y + y)));
						trackList.add(new StepTrack(new Point(field.getControlObjectCoordinates().x + x, field.getControlObjectCoordinates().y + y), new Point(field.getControlObjectCoordinates().x + 2 * x, field.getControlObjectCoordinates().y + 2 * y)));
						return true;
					}
				}
				else {
					error = ErrorType.FIELD_BORDER;
					return false;
				}
			}
		}
		return true;
	}
	
	public ErrorType stepPerform(CommandImpl command){
		if (canPerform(command)) {
			
		}
		return error;
	}
	
	public ErrorType Perform(){
		List<Command> commandList = program.getCommandList();
		for (int i = 0; i < commandList.size(); i++) {
			stepPerform((CommandImpl)commandList.get(i));
		}
		return error;
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
			return"Я не могу совершить прыжок-эта клетка уже занята не мной!";
		case MOVE_BLOCK:
			return"Я не могу беспрепятственно пройти...На моем пути кубик!";
		case MOVE_HOLE:
		    return"В яму можно упасть!Лучше бы мне перепрыгнуть её!";
		case PUSH_BLOCK_TO_OCCUPIED_CELL:
		    return"Кубик возможно толкать только на пустую клетку...Попробуй еще раз!";
		case PUSH_EMPTY_CELL:
		    return"К сожалению, мне нечего толкать...Давай встанем поближе к кубику.";
		case PUSH_HOLE:
			return"Я не могу толкать яму!Давай встанем поближе к кубику.";
		case PUSH_OCCUPIED_CELL:
			return"Я не могу толкать занятую клетку!Давай встанем поближе к кубику.";
		case PUSH_TWO_BLOCKS:
			return "Два кубика?!Это слишком тяжело для меня!";
		default:
			return "Ты всё правильно сделал!Молодец!";
		}
		
	}
	
	public static void main(String[] args) {


	}

}
