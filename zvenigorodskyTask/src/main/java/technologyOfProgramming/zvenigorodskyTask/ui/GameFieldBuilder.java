package technologyOfProgramming.zvenigorodskyTask.ui;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.TouchEvent;
import org.eclipse.swt.events.TouchListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.wb.swt.SWTResourceManager;

import technologyOfProgramming.zvenigorodskyTask.entities.GameField;
import technologyOfProgramming.zvenigorodskyTask.entities.enums.GameObject;
import technologyOfProgramming.zvenigorodskyTask.ui.components.GameFieldViewer;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.PaintEvent;

public class GameFieldBuilder {

	protected Shell shell;
	private GameField field;
	public static final int CELL_WIDTH = 50;
	public static final int CELL_HEIGH = 50;
	public static final int BORDER = 20;
	private GameObject object = GameObject.HOLE;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GameFieldBuilder window = new GameFieldBuilder();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	/**
	 * Open the window.
	 */
	public void open(GameField field) {
		this.field = field;
		open();
	}
	
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(165, 362);
		shell.setText("Редактор игрового поля");
		shell.setLayout(null);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(CELL_WIDTH * field.getWidth() + BORDER/2, 0, 145, 190);
		//composite.setBounds(150, 10, 100, 100);
		composite.setLayout(null);
		
		Composite composite_1 = new Composite(shell, SWT.NONE);
		//composite_1.setBounds(10, 10, 100, 100);
		composite_1.setBounds(10, 10, CELL_WIDTH * field.getWidth(), CELL_HEIGH * field.getHeigh());
		shell.setSize(composite_1.getSize().x + composite.getSize().x + BORDER, (composite_1.getSize().y > composite.getSize().y ? composite_1.getSize().y : composite.getSize().y) + BORDER * 3); //я хз, почему приходится *3, но только так работает как надо, без этого обрезается кусочек
		FillLayout fl_composite_1 = new FillLayout(SWT.HORIZONTAL);
		fl_composite_1.marginWidth = 10;
		fl_composite_1.marginHeight = 10;
		composite_1.setLayout(fl_composite_1);

		final Canvas canvas = new Canvas(composite_1,SWT.NO_REDRAW_RESIZE);
		canvas.setSize(composite_1.getSize().x, composite_1.getSize().y);
		canvas.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GameFieldViewer.showField(field, canvas);
		field.addObject(object, 0, 0);
		GameFieldViewer.showField(field, canvas);
		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent ev) {
				GameFieldViewer.showField(field, canvas);
				field.addObject(object, 1, 1);
				GameFieldViewer.showField(field, canvas);
				//super.mouseDown(e);
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				GameFieldViewer.showField(field, canvas);
				field.addObject(object, 1, 1);
				GameFieldViewer.showField(field, canvas);
				//super.mouseDoubleClick(e);
			}
		});
		
		Group group = new Group(composite, SWT.NONE);
		group.setBounds(10, 10, 120, 111);
		group.setText("Игровые объекты");

		Button button = new Button(group, SWT.RADIO);
		button.setSelection(true);
		button.setLocation(10, 22);
		button.setSize(100, 16);
		button.setText("Божья коровка");
		button.addTouchListener(new TouchListener() {
			@Override
			public void touch(TouchEvent e) {
				object = GameObject.LADYBUG;
			}
		});

		Button button_1 = new Button(group, SWT.RADIO);
		button_1.setLocation(10, 37);
		button_1.setSize(90, 16);
		button_1.setText("Кубик");
		button_1.addTouchListener(new TouchListener() {
			@Override
			public void touch(TouchEvent e) {
				object = GameObject.BLOCK;
			}
		});

		Button button_2 = new Button(group, SWT.RADIO);
		button_2.setLocation(10, 54);
		button_2.setSize(90, 16);
		button_2.setText("Яма");
		button_2.addTouchListener(new TouchListener() {
			@Override
			public void touch(TouchEvent e) {
				object = GameObject.HOLE;
			}
		});

		Button button_3 = new Button(group, SWT.RADIO);
		button_3.setLocation(10, 70);
		button_3.setSize(103, 16);
		button_3.setText("Занятая клетка");
		button_3.addTouchListener(new TouchListener() {
			@Override
			public void touch(TouchEvent e) {
				object = GameObject.OCCUPIED_CELL;
			}
		});

		Button button_4 = new Button(composite, SWT.NONE);
		button_4.setBounds(10, 127, 120, 25);
		button_4.setText("Сохранить");

		Button button_5 = new Button(composite, SWT.NONE);
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		button_5.setBounds(10, 158, 120, 25);
		button_5.setText("Закрыть");

	}
}
