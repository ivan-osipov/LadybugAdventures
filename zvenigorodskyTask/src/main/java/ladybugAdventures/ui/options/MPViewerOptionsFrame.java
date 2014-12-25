package ladybugAdventures.ui.options;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import ladybugAdventures.data.FileSystemManager;
import ladybugAdventures.data.StorageException;
import ladybugAdventures.entities.GameField;
import ladybugAdventures.entities.ManagementProgram;
import ladybugAdventures.util.AnimationRunner;
import ladybugAdventures.util.Dialogs;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class MPViewerOptionsFrame {
	private Text programText;
	private Text fieldText;

	private ManagementProgram program;
	private GameField field;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MPViewerOptionsFrame window = new MPViewerOptionsFrame();
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
		final Shell shell = new Shell(SWT.DIALOG_TRIM);
		shell.setSize(314, 210);
		shell.setText("Выполнение программы управления");
		org.eclipse.swt.graphics.Rectangle client = shell.getBounds();
		org.eclipse.swt.graphics.Rectangle screen = Display.getDefault().getBounds();
		client.x = screen.width/2 -client.width/2;
		client.y = screen.height/2 - client.height/2;
		shell.setLocation(client.x, client.y);
		
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(31, 10, 250, 30);
		label.setText("Необходимо указать программу управления \r\nи игровое поле");

		programText = new Text(shell, SWT.BORDER);
		programText.setEditable(false);
		programText.setBounds(31, 69, 200, 21);

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(shell, SWT.OPEN);
		        fd.setText("Выбор программы управления");
		        //fd.setFilterPath(File.pathSeparator);
		        String[] filterExt = { "*.xml"};
		        fd.setFilterExtensions(filterExt);
		        String selected = fd.open();
		        if(selected!=null){
		        	try {
						program = FileSystemManager.getManagementProgramm(selected);
						programText.setText(selected);
						try{
							field = FileSystemManager.getGameField(program.getMapAddress());
							fieldText.setText(program.getMapAddress());
						}
						catch(StorageException e2){
							MessageDialog.openInformation(shell, "Внимание", "Игровое поле, установленное по умолчанию, недоступно. Выберите другое.");
						}
					} catch (StorageException e1) {
						MessageDialog.openWarning(shell, "Внимание", "Программа управления не может быть корректно распознана. Выберите другую.");
					}

		        }
			}
		});
		button.setBounds(237, 67, 47, 25);
		button.setText("Обзор");

		fieldText = new Text(shell, SWT.BORDER);
		fieldText.setToolTipText("Если программа имеет игровое поле по умолчанию, тогда его можно не указывать явно");
		fieldText.setEditable(false);
		fieldText.setBounds(31, 119, 200, 21);

		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(shell, SWT.OPEN);
		        fd.setText("Выбор игрового поля");
		        //fd.setFilterPath(File.pathSeparator);
		        String[] filterExt = { "*.map"};
		        fd.setFilterExtensions(filterExt);
		        String selected = fd.open();
		        if(selected!=null){
		        	try {
						field = FileSystemManager.getGameField(selected);
						fieldText.setText(selected);
					} catch (StorageException e1) {
						MessageDialog.openWarning(shell, "Внимание", "Игровое поле не может быть корректно распознано. Выберите другое.");
					}
		        }
			}
		});
		button_1.setText("Обзор");
		button_1.setBounds(237, 117, 47, 25);

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(31, 48, 179, 15);
		label_1.setText("Программа управления");

		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(31, 98, 91, 15);
		label_2.setText("Игровое поле");

		Button doneButton = new Button(shell, SWT.NONE);
		doneButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(program!=null && field != null){
					if(!program.getMapAddress().equals(fieldText.getText())){
						if(Dialogs.showYesNoDialog(shell, "Игровое поле отличается от поля по умолчанию. "
								+ "Заменить поле по умолчанию на текущее?", "Игровое поле отличается")==SWT.YES){
							program.setMapAddress(fieldText.getText());
							try {
								FileSystemManager.saveManagementProgram(program, programText.getText());
							} catch (StorageException e1) {
								MessageDialog.openWarning(new Shell(), "Невозможно сохранить", "Новое игровое поле не будет сохранено в качестве поля по умолчанию!");
							}
						}
					}		
					Shell mainShell = ((Shell)shell.getParent());
//					mainShell.setVisible(false);
					shell.dispose();
					AnimationRunner.run(field, program);
					mainShell.dispose();
//					shell.getParent().dispose();
//					shell.dispose();
					
				}
			}
		});
		doneButton.setBounds(114, 147, 58, 25);
		doneButton.setText("Готово");

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
