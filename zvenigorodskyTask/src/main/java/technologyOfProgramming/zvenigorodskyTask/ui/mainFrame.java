package technologyOfProgramming.zvenigorodskyTask.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;

public class mainFrame {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			mainFrame window = new mainFrame();
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
		shell.setSize(495, 358);
		shell.setText("Путешествие божьей коровки");

		Button button = new Button(shell, SWT.NONE);
		button.setBounds(106, 10, 298, 78);
		button.setText("Создать/редактировать игровое поле");

		Button button_1 = new Button(shell, SWT.NONE);
		button_1.setText("Создать/редактировать программу управления");
		button_1.setBounds(106, 103, 298, 88);

		Button button_2 = new Button(shell, SWT.NONE);
		button_2.setText("Выполнить программу управления");
		button_2.setBounds(106, 218, 298, 73);

	}
}
