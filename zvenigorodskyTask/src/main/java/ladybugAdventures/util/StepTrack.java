package ladybugAdventures.util;

import ladybugAdventures.enums.GameObject;

import org.eclipse.swt.graphics.Point;

public class StepTrack {

	private Point startPosition;
	private Point finishPosition;
	private GameObject gameObject;
	
	public StepTrack(Point startPosition, Point finishPosition, GameObject gameObject) {
		this.startPosition = startPosition;
		this.finishPosition = finishPosition;
		this.gameObject = gameObject;
	}

	public Point getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(Point startPosition) {
		this.startPosition = startPosition;
	}

	public Point getFinishPosition() {
		return finishPosition;
	}

	public void setFinishPosition(Point finishPosition) {
		this.finishPosition = finishPosition;
	}
	
	public GameObject getGameObject() {
		return gameObject;
	}
	
	public void setGameObject(GameObject gameObject) {
		this.gameObject = gameObject;
	}
}
