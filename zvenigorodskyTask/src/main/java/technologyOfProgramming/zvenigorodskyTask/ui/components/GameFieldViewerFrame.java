package technologyOfProgramming.zvenigorodskyTask.ui.components;

import java.io.File;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.layout.FillLayout;

import technologyOfProgramming.zvenigorodskyTask.data.FileSystemManager;
import technologyOfProgramming.zvenigorodskyTask.exceptions.StorageException;

public class GameFieldViewerFrame {

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
//			GameFieldViewerFrame window = new GameFieldViewerFrame();
//			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open(String gameFileName,int x, int y) {
		Display display = Display.getDefault();
		Shell shell = new Shell(SWT.DIALOG_TRIM);
		//shell.setSize(450, 300);

		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));


		File file = new File(gameFileName);
		if(!file.exists()){
			MessageDialog.openWarning(shell, "Внимание", "Программа управления не соответствует никакому полю\r\n"+gameFileName);
			return;
		}
		try {
			FileSystemManager.setMapAddress(gameFileName);
			GameFieldViewer canvas = new GameFieldViewer(composite, SWT.NONE,FileSystemManager.getGameField());

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
}
