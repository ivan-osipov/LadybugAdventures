package ladybugAdventures.ui;

import java.io.File;

import ladybugAdventures.data.FileSystemManager;
import ladybugAdventures.data.StorageException;
import ladybugAdventures.ui.components.GameFieldViewerComponent;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.layout.FillLayout;

public class GameFieldViewer {
	private Shell shell = null;

	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open(String gameFileName,int x, int y) {
		Display display = Display.getDefault();
		shell = new Shell(SWT.DIALOG_TRIM);
		//shell.setSize(450, 300);
		shell.setLocation(x, y);
		shell.setText("Игровая карта");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));


		File file = new File(gameFileName);
		if(!file.exists()){
			MessageDialog.openWarning(shell, "Внимание", "Программа управления не соответствует никакому полю\r\n"+gameFileName);
			return;
		}
		try {
//			FileSystemManager.setDefaultMapAddress(gameFileName);
			GameFieldViewerComponent canvas = new GameFieldViewerComponent(composite, SWT.NONE,FileSystemManager.getGameField(gameFileName));

			canvas.initField();
			composite.setSize(canvas.getBounds().width, canvas.getBounds().height);
			//shell.setBounds(x, y,-1, -1);
			shell.setSize(canvas.getBounds().width+8,canvas.getBounds().height+28);
		} catch (StorageException e) {
			MessageDialog.openWarning(shell, "Внимание", "Ошибка загрузки поля");
		}
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	public Shell getShell(){
		return shell;
	}
}
