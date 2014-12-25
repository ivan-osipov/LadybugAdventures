package ladybugAdventures.ui;

import ladybugAdventures.ui.options.GameFieldOptionsFrame;
import ladybugAdventures.ui.options.MPViewerOptionsFrame;
import ladybugAdventures.ui.options.ProgramBuilderOptionsFrame;
import ladybugAdventures.util.Dialogs;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.wb.swt.SWTResourceManager;

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
		try{
			try {
				shell.open();
				shell.layout();
				while (!shell.isDisposed()) {
					if (!display.readAndDispatch()) {
						display.sleep();
					}
				}
			} finally {
				if (!shell.isDisposed()) {
					shell.dispose();
				}
			}
		}
		finally{
			display.dispose();
			
		}
//		System.exit(0);
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(SWT.DIALOG_TRIM);
		shell.setSize(446, 348);
		shell.setText("Приключения божьей коровки");
		
		org.eclipse.swt.graphics.Rectangle client = shell.getBounds();
		org.eclipse.swt.graphics.Rectangle screen = Display.getDefault().getBounds();
		client.x = screen.width/2 -client.width/2;
		client.y = screen.height/2 - client.height/2;
		shell.setLocation(client.x, client.y);
//		shell.addListener(SWT.Close, new Listener() { 
//			public void handleEvent(Event event) { 
//				if(Dialogs.showYesNoDialog(shell, "Вы уверены, что хотите завершить приключения "
//						+ "божьей коровки?\r\nВсе не сохраненные данные будут утеряны!", 
//						"Прервать приключения") == SWT.YES){
//					shell.dispose();
//				    System.exit(0);
//				}
//				else{
//					event.doit = false;
//				}
//			
//			} 
//			});
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				GameFieldOptionsFrame gameFieldFrame = new GameFieldOptionsFrame();
				gameFieldFrame.open();
			}
		});
		button.setBounds(51, 37, 298, 60);
		button.setText("Создать или редактировать поле приключений");

		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				ProgramBuilderOptionsFrame optionsFrame = new ProgramBuilderOptionsFrame();
				optionsFrame.open();
			}
		});
		button_1.setText("Создать или редактировать шаги");
		button_1.setBounds(51, 132, 298, 60);

		Button button_2 = new Button(shell, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				MPViewerOptionsFrame optionsFrame = new MPViewerOptionsFrame();
				optionsFrame.open();
			}
		});
		button_2.setText("Смотреть!");
		button_2.setBounds(51, 231, 298, 60);

		Button btnNewButton = new Button(shell, SWT.CENTER);
		btnNewButton.setToolTipText("Справка");
		Image img = new Image(shell.getDisplay(), new ImageData(MainFrame.class.getResourceAsStream("/img/icons/question.png")));
		btnNewButton.setImage(img);
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
