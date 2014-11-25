package technologyOfProgramming.zvenigorodskyTask.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import technologyOfProgramming.zvenigorodskyTask.ui.graphic.MPViewerOptionsFrame;

public class MainFrame {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainFrame window = new MainFrame();
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
		shell = new Shell(SWT.DIALOG_TRIM);
		shell.setSize(446, 348);
		shell.setText("Путешествие божьей коровки");

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				GameFieldOptionsFrame gameFieldFrame = new GameFieldOptionsFrame();
				gameFieldFrame.open();
			}
		});
		button.setBounds(51, 37, 298, 60);
		button.setText("Создать/редактировать игровое поле");

		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				ProgramBuilderOptionsFrame optionsFrame = new ProgramBuilderOptionsFrame();
				optionsFrame.open();
			}
		});
		button_1.setText("Создать/редактировать программу управления");
		button_1.setBounds(51, 132, 298, 60);

		Button button_2 = new Button(shell, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				MPViewerOptionsFrame optionsFrame = new MPViewerOptionsFrame();
				optionsFrame.open();
			}
		});
		button_2.setText("Выполнить программу управления");
		button_2.setBounds(51, 231, 298, 60);

		Button btnNewButton = new Button(shell, SWT.CENTER);
		btnNewButton.setImage(SWTResourceManager.getImage(MainFrame.class.getResource("/img/icons/question.png").getPath()));
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					HelpFrame window = new HelpFrame();
					window.open();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(375, 247, 44, 44);
		//btnNewButton.setText("New Button");

	}
}
