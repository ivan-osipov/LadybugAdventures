package technologyOfProgramming.zvenigorodskyTask.ui;

import java.io.File;

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

import technologyOfProgramming.zvenigorodskyTask.entities.ManagementProgram;

public class ProgramBuilderOptionsFrame {
	private Text authorName;
	private Text gameFieldPath;

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
		final Shell shell = new Shell();
		shell.setSize(349, 214);
		shell.setText("Создание/редактирование программы управления");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

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
		label.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		label.setBounds(10, 10, 74, 25);
		label.setText("Автор:");

		authorName = new Text(creatingComposite, SWT.BORDER);
		authorName.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		authorName.setBounds(119, 10, 112, 26);

		Label label_1 = new Label(creatingComposite, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		label_1.setBounds(10, 48, 96, 50);
		label_1.setText("Игровая\nкарта:");
		label_1.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));

		gameFieldPath = new Text(creatingComposite, SWT.BORDER);
		gameFieldPath.setEditable(false);
		gameFieldPath.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		gameFieldPath.setBounds(119, 73, 111, 25);

		Button explorerBut = new Button(creatingComposite, SWT.NONE);
		explorerBut.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog fd = new FileDialog(shell, SWT.SAVE);
		        fd.setText("Open Game Field");
		        //fd.setFilterPath(File.pathSeparator);
		        String[] filterExt = { "*.map"};
		        fd.setFilterExtensions(filterExt);
		        String selected = fd.open();
		        if(selected!=null)
		        	gameFieldPath.setText(selected);
			}
		});
		explorerBut.setBounds(242, 73, 75, 25);
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

						programBuilder.open();

					}

				}


			}
		});
		createBut.setBounds(243, 116, 74, 25);
		createBut.setText("Создать");

		Composite composite = new Composite(tabFolder, SWT.NONE);

		TabItem editingTab = new TabItem(tabFolder, SWT.NONE);
		editingTab.setToolTipText("Редактирование программы управления");
		editingTab.setText("Редактирование");

		Composite editingComposite = new Composite(tabFolder, SWT.NONE);
		editingTab.setControl(editingComposite);

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
