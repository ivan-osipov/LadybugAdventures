package ladybugAdventures.ui.components;

import ladybugAdventures.entities.GameField;
import ladybugAdventures.enums.GameObject;
import ladybugAdventures.util.CommandConverter;
import ladybugAdventures.util.ResourceProvider;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.wb.swt.SWTResourceManager;

public class GameFieldViewerComponent extends Canvas {
	public static final int CELL_WIDTH = 30;
	public static final int CELL_HEIGH = 30;
	private GameField field;
	private GC gc;

	public GameFieldViewerComponent(Composite parent, int style) {
		super(parent, style | SWT.BORDER);
		gc = new GC(this);
		addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				int currentX = CELL_WIDTH;
				int currentY = CELL_HEIGH;
				for (int column = 0; column < field.getWidth(); column++) {
					e.gc.drawLine(currentX, 0, currentX, getSize().y);
					currentX += CELL_WIDTH;
				}
				for (int row = 0; row < field.getHeigh(); row++) {
					e.gc.drawLine(0, currentY, getSize().x, currentY);
					currentY += CELL_WIDTH;
				}
				for (int row = 0; row < field.getHeigh(); row++) {
					for (int column = 0; column < field.getWidth(); column++) {
							drawCell(column, row, field.getType(row, column));
					}
				}
			}
		});
	}

	public GameFieldViewerComponent(Composite parent, int style, GameField field) {
		this(parent, style);
		this.field = field;
		setSize(field.getWidth() * CELL_WIDTH, field.getHeigh() * CELL_HEIGH);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	}

	public void drawCell(final int column, final int row, final GameObject object) {
		gc.fillRectangle(CELL_WIDTH * column + 1, CELL_HEIGH * row + 1, CELL_WIDTH - 1, CELL_HEIGH - 1);
		switch (object) {
		case LADYBUG:
			gc.drawImage(CommandConverter.fromGameObjectToImage(GameObject.LADYBUG, CELL_WIDTH, CELL_HEIGH, this.getDisplay()), 
					CELL_WIDTH * column, CELL_HEIGH * row);
			break;
		case BLOCK:
			gc.drawImage(CommandConverter.fromGameObjectToImage(GameObject.BLOCK, CELL_WIDTH, CELL_HEIGH, this.getDisplay()), 
					CELL_WIDTH * column, CELL_HEIGH * row);
			break;
		case HOLE:
			gc.drawImage(CommandConverter.fromGameObjectToImage(GameObject.HOLE, CELL_WIDTH, CELL_HEIGH, this.getDisplay()), 
					CELL_WIDTH * column, CELL_HEIGH * row);
			break;
		case OCCUPIED_CELL:
			gc.drawImage(CommandConverter.fromGameObjectToImage(GameObject.OCCUPIED_CELL, CELL_WIDTH, CELL_HEIGH, this.getDisplay()), 
					CELL_WIDTH * column, CELL_HEIGH * row);
			break;
		default: break;
		}
	}
}
