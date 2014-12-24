package ladybugAdventures.ui;

import java.io.File;

import ladybugAdventures.data.FileSystemManager;
import ladybugAdventures.data.StorageException;
import ladybugAdventures.entities.GameField;
import ladybugAdventures.enums.GameObject;
import ladybugAdventures.ui.components.GameFieldViewerComponent;
import ladybugAdventures.util.Dialogs;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.TouchEvent;
import org.eclipse.swt.events.TouchListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.PaintEvent;

public class GameFieldBuilder {

	protected Shell shell;
	private GameField field;
	private GameObject object;
	private boolean changesSaved;
	public static final int BORDER = 20;
	
	public GameFieldBuilder() {
		object = GameObject.LADYBUG;
		changesSaved = false;
	}
	
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
		shell = new Shell(SWT.DIALOG_TRIM);
		shell.setSize(165, 362);
		shell.setText("Редактор игрового поля");
		shell.setLayout(null);
		org.eclipse.swt.graphics.Rectangle client = shell.getBounds();
		org.eclipse.swt.graphics.Rectangle screen = Display.getDefault().getBounds();
		client.x = screen.width/2 -client.width/2;
		client.y = screen.height/2 - client.height/2;
		shell.setLocation(client.x, client.y);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(GameFieldViewerComponent.CELL_WIDTH * field.getWidth() + BORDER/2, 0, 145, 190);
		//composite.setBounds(150, 10, 100, 100);
		composite.setLayout(null);
		
		Composite composite_1 = new Composite(shell, SWT.NONE);
		//composite_1.setBounds(10, 10, 100, 100);
		composite_1.setBounds(10, 10, GameFieldViewerComponent.CELL_WIDTH * field.getWidth(), GameFieldViewerComponent.CELL_HEIGH * field.getHeigh());
		shell.setSize(composite_1.getSize().x + composite.getSize().x + BORDER, (composite_1.getSize().y > composite.getSize().y ? composite_1.getSize().y : composite.getSize().y) + BORDER * 3); //TODO я хз, почему приходится *3, но только так работает как надо, без этого обрезается кусочек
		FillLayout fl_composite_1 = new FillLayout(SWT.HORIZONTAL);
		fl_composite_1.marginWidth = 10;
		fl_composite_1.marginHeight = 10;
		composite_1.setLayout(fl_composite_1);

		final GameFieldViewerComponent canvas = new GameFieldViewerComponent(composite_1,SWT.NO_REDRAW_RESIZE, field);
		canvas.initField();
		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				field.addObject(object, e.x / GameFieldViewerComponent.CELL_WIDTH, e.y / GameFieldViewerComponent.CELL_HEIGH);
				canvas.redraw();
				changesSaved = false;
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				field.removeObject(e.x / GameFieldViewerComponent.CELL_WIDTH, e.y / GameFieldViewerComponent.CELL_HEIGH);
				changesSaved = false;
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
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				object = GameObject.LADYBUG;
			}
		});

		Button button_1 = new Button(group, SWT.RADIO);
		button_1.setLocation(10, 37);
		button_1.setSize(90, 16);
		button_1.setText("Кубик");
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				object = GameObject.BLOCK;
			}
		});

		Button button_2 = new Button(group, SWT.RADIO);
		button_2.setLocation(10, 54);
		button_2.setSize(90, 16);
		button_2.setText("Яма");
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				object = GameObject.HOLE;
			}
		});

		Button button_3 = new Button(group, SWT.RADIO);
		button_3.setLocation(10, 70);
		button_3.setSize(103, 16);
		button_3.setText("Занятая клетка");
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				object = GameObject.OCCUPIED_CELL;
			}
		});

		Button button_4 = new Button(composite, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Dialogs.showSaveDialog(shell, "Файл игрового поля (*.map)", 
						"*.map", "gameField.map", field);
			}
		});
		button_4.setBounds(10, 127, 120, 25);
		button_4.setText("Сохранить");

		Button button_5 = new Button(composite, SWT.NONE);
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!changesSaved) {
					if(Dialogs.showYesNoDialog(shell,"Сохранить изменения перед закрытием?", 
							"Сохранить изменения?") == SWT.YES){
						Dialogs.showSaveDialog(shell, "Файл игрового поля (*.map)", 
								"*.map", "gameField.map", field);
					}
				}
				shell.dispose();
			}
		});
		button_5.setBounds(10, 158, 120, 25);
		button_5.setText("Закрыть");

		shell.addListener(SWT.Close, new Listener() { 
			public void handleEvent(Event event) { 
				if (!changesSaved) {
					if(Dialogs.showYesNoDialog(shell,"Сохранить изменения перед закрытием?", 
							"Сохранить изменения?") == SWT.YES){
						Dialogs.showSaveDialog(shell, "Файл игрового поля (*.map)", 
								"*.map", "gameField.map", field);
					}
				}
				shell.dispose();
			} 
			});
	}
}
