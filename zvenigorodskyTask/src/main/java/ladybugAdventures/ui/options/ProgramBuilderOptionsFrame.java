package ladybugAdventures.ui.options;

import java.io.File;

import ladybugAdventures.data.FileSystemManager;
import ladybugAdventures.data.StorageException;
import ladybugAdventures.entities.ManagementProgram;
import ladybugAdventures.ui.ProgramBuilderFrame;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.win32.MEASUREITEMSTRUCT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.ui.internal.dnd.SwtUtil;

public class ProgramBuilderOptionsFrame {
	private Text authorName;
	private Text gameFieldPath;
	private Text text;
	private ManagementProgram mp;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ProgramBuilderOptionsFrame window = new ProgramBuilderOptionsFrame();
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
		shell.setSize(218, 176);
		shell.setText("Редактор программы управления");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		org.eclipse.swt.graphics.Rectangle client = shell.getBounds();
		org.eclipse.swt.graphics.Rectangle screen = Display.getDefault().getBounds();
		client.x = screen.width/2 -client.width/2;
		client.y = screen.height/2 - client.height/2;
		shell.setLocation(client.x, client.y);
		
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

		TabItem creatingTab = new TabItem(tabFolder, SWT.NONE);
		creatingTab.setToolTipText("Создание программы управления");
		creatingTab.setText("Cоздание");

		Composite creatingComposite = new Composite(tabFolder, SWT.BORDER);
		creatingComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		creatingTab.setControl(creatingComposite);
		creatingComposite.setLayout(null);

		Label label = new Label(creatingComposite, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		label.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		label.setBounds(10, 13, 45, 25);
		label.setText("Автор:");

		authorName = new Text(creatingComposite, SWT.BORDER);
		authorName.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		authorName.setBounds(74, 12, 112, 25);

		Label label_1 = new Label(creatingComposite, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		label_1.setBounds(10, 44, 59, 46);
		label_1.setText("Игровая\nкарта:");
		label_1.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));

		gameFieldPath = new Text(creatingComposite, SWT.BORDER);
		gameFieldPath.setEditable(false);
		gameFieldPath.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		gameFieldPath.setBounds(75, 44, 111, 25);

		Button explorerBut = new Button(creatingComposite, SWT.NONE);
		explorerBut.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog fd = new FileDialog(shell, SWT.OPEN);
		        fd.setText("Открыть игровое поле");
		        //fd.setFilterPath(File.pathSeparator);
		        String[] filterExt = { "*.map"};
		        fd.setFilterExtensions(filterExt);
		        String selected = fd.open();
		        if(selected!=null)
		        	gameFieldPath.setText(selected);
			}
		});
		explorerBut.setBounds(111, 87, 75, 25);
		explorerBut.setText("Обзор");

		Button createBut = new Button(creatingComposite, SWT.NONE);
		createBut.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(authorName.getText().isEmpty())
					MessageDialog.openWarning(shell, "Внимание!", "Укажите имя автора!");
				else{
					if(gameFieldPath.getText().isEmpty() || !new File(gameFieldPath.getText()).exists())
						MessageDialog.openWarning(shell, "Внимание!", "Укажите игровую карту на которой\nбудет выполняться программа!");
					else{
						ManagementProgram mp = new ManagementProgram(authorName.getText(), gameFieldPath.getText());
						ProgramBuilderFrame programBuilder = new ProgramBuilderFrame(mp);
						shell.dispose();
						programBuilder.open();

					}

				}


			}
		});
		createBut.setBounds(10, 87, 74, 25);
		createBut.setText("Создать");

		Composite composite = new Composite(tabFolder, SWT.NONE);

		TabItem editingTab = new TabItem(tabFolder, SWT.NONE);
		editingTab.setToolTipText("Редактирование программы управления");
		editingTab.setText("Редактирование");

		Composite editingComposite = new Composite(tabFolder, SWT.NONE);
		editingComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		editingTab.setControl(editingComposite);

		Button btnJpjh = new Button(editingComposite, SWT.NONE);
		btnJpjh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(shell, SWT.OPEN);
				dialog.setFilterNames (new String [] {"Файл программы управления *.xml"});
				dialog.setFilterExtensions (new String [] {"*.xml"}); //Windows wild cards
//				dialog.setFilterPath ("c:\\user\\"); //Windows path
				//dialog.setFileName ("managementProgram.xml");
				String fileName = dialog.open();
				if(fileName != null)
					text.setText(fileName);
			}
		});
		btnJpjh.setBounds(47, 58, 105, 25);
		btnJpjh.setText("Обзор");

		text = new Text(editingComposite, SWT.BORDER);
		text.setEditable(false);
		text.setBounds(10, 31, 184, 21);

		Label label_2 = new Label(editingComposite, SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		label_2.setBounds(10, 10, 191, 15);
		label_2.setText("Путь до программы управления:");

		Button button = new Button(editingComposite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if(text.getText().isEmpty()){
					MessageDialog.openWarning(shell, "Внимание", "Файл не указан!");
					return;
				}
				try {
					mp = new ManagementProgram();
					ManagementProgram downloadedMP = FileSystemManager.getManagementProgramm(text.getText());
					if(!mp.setManagementProgram(downloadedMP)){
						MessageDialog.openWarning(shell, "Внимание", "Программа управления не соответствует требованиям");
						return;
					}
				} catch (StorageException e1) {
					MessageDialog.openWarning(shell, "Внимание", "Невозможно открыть файл, файл поврежден");
					return;
				}
				ProgramBuilderFrame programBuilder = new ProgramBuilderFrame(mp);
				shell.dispose();
				programBuilder.open();
			}
		});
		button.setBounds(47, 89, 105, 25);
		button.setText("Редактировать");

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
