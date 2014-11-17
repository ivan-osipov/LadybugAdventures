package technologyOfProgramming.zvenigorodskyTask.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.wb.swt.SWTResourceManager;

public class GameFieldBuilder {

	protected Shell shell;

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
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(432, 276);
		shell.setText("Редактор игрового поля");

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(10, 10, 257, 218);

		Canvas canvas = new Canvas(composite, SWT.NONE);
		canvas.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		canvas.setBounds(0, 0, 257, 220);

		Group group = new Group(shell, SWT.NONE);
		group.setText("Игровые объекты");
		group.setBounds(279, 10, 133, 95);

		Button button = new Button(group, SWT.RADIO);
		button.setSelection(true);
		button.setLocation(10, 22);
		button.setSize(113, 16);
		button.setText("Божья коровка");

		Button button_1 = new Button(group, SWT.RADIO);
		button_1.setLocation(10, 37);
		button_1.setSize(90, 16);
		button_1.setText("Кубик");

		Button button_2 = new Button(group, SWT.RADIO);
		button_2.setLocation(10, 54);
		button_2.setSize(90, 16);
		button_2.setText("Яма");

		Button button_3 = new Button(group, SWT.RADIO);
		button_3.setLocation(10, 70);
		button_3.setSize(103, 16);
		button_3.setText("Занятая клетка");

		Button button_4 = new Button(shell, SWT.NONE);
		button_4.setBounds(278, 203, 70, 25);
		button_4.setText("Сохранить");

		Button button_5 = new Button(shell, SWT.NONE);
		button_5.setBounds(354, 203, 58, 25);
		button_5.setText("Закрыть");

	}
}
