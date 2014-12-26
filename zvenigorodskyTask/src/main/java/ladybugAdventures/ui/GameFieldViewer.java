package ladybugAdventures.ui;

import java.io.File;

import ladybugAdventures.data.FileSystemManager;
import ladybugAdventures.data.StorageException;
import ladybugAdventures.entities.GameField;
import ladybugAdventures.ui.components.GameFieldViewerComponent;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;

public class GameFieldViewer {
	private Shell shell = null;
	private boolean opened = false;
	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public boolean open(String gameFileName,int x, int y) {
		Display display = Display.getDefault();
		shell = new Shell(display, SWT.DIALOG_TRIM);
		//shell.setSize(450, 300);
		shell.setLocation(x, y);
		shell.setText("Игровая карта");
		shell.setLayout(new FillLayout(SWT.VERTICAL));
		
		shell.addListener(SWT.Close, new Listener() { 
			public void handleEvent(Event event) { 
				opened = false;
				shell.dispose();
			
			} 
			});
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.VERTICAL));


		File file = new File(gameFileName);
		if(!file.exists()){
			return false;
		}
		try {
			GameField field = FileSystemManager.getGameField(gameFileName);
			GameFieldViewerComponent canvas = new GameFieldViewerComponent(composite, SWT.NONE, field);

			canvas.initField();
			canvas.pack();
			canvas.setLayout(new FillLayout(SWT.HORIZONTAL));
			shell.setSize((field.getWidth()+1)*GameFieldViewerComponent.CELL_WIDTH-GameFieldViewerComponent.CELL_WIDTH/2-10, (field.getHeigh()+1)*GameFieldViewerComponent.CELL_HEIGH);
			//непонятные особенности SWT размер shell непоколебим, вероятно из-за предустановленного минимального размера формы
		} catch (StorageException e) {
			return false;
		}
		opened = true;
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		opened = true;
		return true;
	}
	
	public boolean isOpened() {
		return opened;
	}

	public Shell getShell(){
		return shell;
	}
}
