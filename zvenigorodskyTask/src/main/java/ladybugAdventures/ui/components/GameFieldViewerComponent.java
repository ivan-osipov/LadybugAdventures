package ladybugAdventures.ui.components;

import ladybugAdventures.entities.GameField;
import ladybugAdventures.enums.GameObject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

public class GameFieldViewerComponent extends Canvas {
	public static final int CELL_WIDTH = 30;
	public static final int CELL_HEIGH = 30;
	private GameField field;
	private GC gc;

	public GameFieldViewerComponent(Composite parent, int style) {
		super(parent, style);
		gc = new GC(this);
	}

	public GameFieldViewerComponent(Composite parent, int style, GameField field) {
		this(parent, style);
		this.field = field;
		setSize(field.getWidth() * CELL_WIDTH, field.getHeigh() * CELL_HEIGH);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	}

	public void initField() {
		addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				int currentX = CELL_WIDTH;
				int currentY = CELL_HEIGH;
				e.gc.drawRectangle(0, 0, getSize().x - 1, getSize().y - 1);
				for (int i = 0; i < field.getWidth(); i++) {
					e.gc.drawLine(currentX, 0, currentX, getSize().y);
					currentX += CELL_WIDTH;
				}
				for (int i = 0; i < field.getHeigh(); i++) {
					e.gc.drawLine(0, currentY, getSize().x, currentY);
					currentY += CELL_WIDTH;
				}
				for (int row = 0; row < field.getHeigh(); row++) {
					for (int column = 0; column < field.getWidth(); column++) {
						drawCell(row, column, field.getType(row, column));
					}
				}
			}
		});
	}

	public void drawCell(final int row, final int column, final GameObject object) {
		switch (object) {
		case LADYBUG:
			gc.drawOval(CELL_WIDTH * row + 6, CELL_HEIGH * column + 2,
					CELL_WIDTH - 12, CELL_HEIGH - 4);
//			gc.drawOval(CELL_WIDTH * x + CELL_WIDTH / 2, CELL_HEIGH * y + CELL_HEIGH / 2, 4, 4);
//			gc.drawOval(CELL_WIDTH * x + CELL_WIDTH, CELL_HEIGH * y + CELL_HEIGH / 2, 4, 4);
//			gc.drawOval(CELL_WIDTH * x + CELL_WIDTH/ 3, CELL_HEIGH * y + CELL_HEIGH, 4, 4);
//			gc.drawOval(CELL_WIDTH * x + CELL_WIDTH, CELL_HEIGH * y + CELL_HEIGH, 4, 4);
//			gc.drawOval(CELL_WIDTH * x + CELL_WIDTH / 2, CELL_HEIGH * y + CELL_HEIGH, 4, 4);
//			gc.drawOval(CELL_WIDTH * x + CELL_WIDTH, CELL_HEIGH * y + CELL_HEIGH, 4, 4);
			gc.drawLine(CELL_WIDTH * row, CELL_HEIGH * column + CELL_HEIGH / 2 + 2,
					CELL_WIDTH * row + 6, CELL_HEIGH * column + CELL_HEIGH / 2 + 2);
			gc.drawLine(CELL_WIDTH * (row + 1) - 6, CELL_HEIGH * column + CELL_HEIGH
					/ 2 + 2, CELL_WIDTH * (row + 1), CELL_HEIGH * column + CELL_HEIGH
					/ 2 + 2);
			gc.drawLine(CELL_WIDTH * row + 2, CELL_HEIGH * column + CELL_HEIGH / 2
					- 10, CELL_WIDTH * row + 8, CELL_HEIGH * column + CELL_HEIGH / 2
					- 10);
			gc.drawLine(CELL_WIDTH * row + 4, CELL_HEIGH * column + CELL_HEIGH / 2
					+ 10, CELL_WIDTH * row + 10, CELL_HEIGH * column + CELL_HEIGH / 2
					+ 10);
			gc.drawLine(CELL_WIDTH * (row + 1) - 8, CELL_HEIGH * column + CELL_HEIGH
					/ 2 - 10, CELL_WIDTH * (row + 1) - 2, CELL_HEIGH * column
					+ CELL_HEIGH / 2 - 10);
			gc.drawLine(CELL_WIDTH * (row + 1) - 10, CELL_HEIGH * column + CELL_HEIGH
					/ 2 + 10, CELL_WIDTH * (row + 1) - 4, CELL_HEIGH * column
					+ CELL_HEIGH / 2 + 10);
			break;
		case BLOCK:
			gc.drawRectangle(CELL_WIDTH * row + 4, CELL_HEIGH * column + 4,
					CELL_WIDTH - 8, CELL_HEIGH - 8);
			break;
		case HOLE:
			gc.drawOval(CELL_WIDTH * row + 4, CELL_HEIGH * column + 4, CELL_WIDTH - 8,
					CELL_HEIGH - 8);
			break;
		case OCCUPIED_CELL:
			gc.drawLine(CELL_WIDTH * row, CELL_HEIGH * column, CELL_WIDTH * row
					+ CELL_WIDTH, CELL_HEIGH * column + CELL_HEIGH);
			gc.drawLine(CELL_WIDTH * row + CELL_WIDTH, CELL_HEIGH * column, CELL_WIDTH
					* row, CELL_HEIGH * column + CELL_HEIGH);
			break;
		case EMPTY_CELL:
			break;
		}
	}
}
