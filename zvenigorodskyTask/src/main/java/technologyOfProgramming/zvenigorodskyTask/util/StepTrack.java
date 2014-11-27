package technologyOfProgramming.zvenigorodskyTask.util;

import org.eclipse.swt.graphics.Point;

public class StepTrack {

	private Point startPosition;
	private Point finishPosition;
	
	public StepTrack(Point startPosition, Point finishPosition) {
		this.startPosition = startPosition;
		this.finishPosition = finishPosition;
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
	
	
}
