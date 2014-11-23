package technologyOfProgramming.zvenigorodskyTask.ui.components;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Canvas;

import technologyOfProgramming.zvenigorodskyTask.entities.GameField;
import technologyOfProgramming.zvenigorodskyTask.entities.enums.GameObject;

public class GameFieldViewer {
	public static final int CELL_WIDTH = 50;
	public static final int CELL_HEIGH = 50;
	
	public static void showField(final GameField field, final Canvas canvas) {
		canvas.addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				int currentX = CELL_WIDTH;
				int currentY = CELL_HEIGH;
				e.gc.drawRectangle(0, 0, canvas.getSize().x - 1, canvas.getSize().y - 1);
				for (int i = 0; i < field.getWidth(); i++) {
					e.gc.drawLine(currentX, 0, currentX, canvas.getSize().y);
					currentX += CELL_WIDTH;
				}
				for (int i = 0; i < field.getHeigh(); i++) {
					e.gc.drawLine(0, currentY, canvas.getSize().x, currentY);
					currentY += CELL_WIDTH;
				}
				for (int i = 0; i < field.getWidth(); i++) {
					for (int j = 0; j < field.getHeigh(); j++) {
						drawCell(i,j,field.getType(i, j),canvas);
					}
				}
			}
		});
	}
	
	public static void drawCell(final int x, final int y, final GameObject object, final Canvas canvas) {
		canvas.addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				switch (object) {
				case LADYBUG:
					e.gc.drawOval(CELL_WIDTH * x + 6, CELL_HEIGH * y + 2, CELL_WIDTH - 12, CELL_HEIGH - 4);
					e.gc.drawOval(CELL_WIDTH * x + 15, CELL_HEIGH * y + 15, 4, 4);
					e.gc.drawOval(CELL_WIDTH * x + 30, CELL_HEIGH * y + 15, 4, 4);
					e.gc.drawOval(CELL_WIDTH * x + 11, CELL_HEIGH * y + 25, 4, 4);
					e.gc.drawOval(CELL_WIDTH * x + 34, CELL_HEIGH * y + 25, 4, 4);
					e.gc.drawOval(CELL_WIDTH * x + 15, CELL_HEIGH * y + 35, 4, 4);
					e.gc.drawOval(CELL_WIDTH * x + 30, CELL_HEIGH * y + 35, 4, 4);
					e.gc.drawLine(CELL_WIDTH * x, CELL_HEIGH * y + CELL_HEIGH / 2 + 2, CELL_WIDTH * x + 6, CELL_HEIGH * y + CELL_HEIGH / 2 + 2);
					e.gc.drawLine(CELL_WIDTH * (x + 1) - 6, CELL_HEIGH * y + CELL_HEIGH / 2 + 2, CELL_WIDTH * (x + 1), CELL_HEIGH * y + CELL_HEIGH / 2 + 2);
					e.gc.drawLine(CELL_WIDTH * x + 2, CELL_HEIGH * y + CELL_HEIGH / 2 - 10, CELL_WIDTH * x + 8, CELL_HEIGH * y + CELL_HEIGH / 2 - 10);
					e.gc.drawLine(CELL_WIDTH * x + 4, CELL_HEIGH * y + CELL_HEIGH / 2 + 14, CELL_WIDTH * x + 10, CELL_HEIGH * y + CELL_HEIGH / 2 + 14);
					e.gc.drawLine(CELL_WIDTH * (x + 1) - 8, CELL_HEIGH * y + CELL_HEIGH / 2 - 10, CELL_WIDTH * (x + 1) - 2, CELL_HEIGH * y + CELL_HEIGH / 2 - 10);
					e.gc.drawLine(CELL_WIDTH * (x + 1) - 10, CELL_HEIGH * y + CELL_HEIGH / 2 + 14, CELL_WIDTH * (x + 1) - 4, CELL_HEIGH * y + CELL_HEIGH / 2 + 14);
					break;
				case BLOCK:
					e.gc.drawRectangle(CELL_WIDTH * x + 4, CELL_HEIGH * y + 4, CELL_WIDTH - 8, CELL_HEIGH - 8);
					break;
				case HOLE:
					e.gc.drawOval(CELL_WIDTH * x + 4, CELL_HEIGH * y + 4, CELL_WIDTH - 8, CELL_HEIGH - 8);
					break;
				case OCCUPIED_CELL:
					e.gc.drawLine(CELL_WIDTH * x, CELL_HEIGH * y, CELL_WIDTH * x + CELL_WIDTH, CELL_HEIGH * y + CELL_HEIGH);
					e.gc.drawLine(CELL_WIDTH * x + CELL_WIDTH, CELL_HEIGH * y, CELL_WIDTH * x, CELL_HEIGH * y + CELL_HEIGH);
					break;
				case EMPTY_CELL: break;
				}
			}
		});
	}
}
