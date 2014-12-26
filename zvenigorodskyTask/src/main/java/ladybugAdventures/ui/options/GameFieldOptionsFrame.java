package ladybugAdventures.ui.options;

import ladybugAdventures.data.FileSystemManager;
import ladybugAdventures.data.StorageException;
import ladybugAdventures.entities.GameField;
import ladybugAdventures.ui.GameFieldBuilder;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;

public class GameFieldOptionsFrame {
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GameFieldOptionsFrame window = new GameFieldOptionsFrame();
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
		shell.setSize(250, 213);
		shell.setText("Редактор игрового поля");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		org.eclipse.swt.graphics.Rectangle client = shell.getBounds();
		org.eclipse.swt.graphics.Rectangle screen = Display.getDefault().getBounds();
		client.x = screen.width/2 -client.width/2;
		client.y = screen.height/2 - client.height/2;
		shell.setLocation(client.x, client.y);
		
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		
		TabItem tbtmCreateNewField = new TabItem(tabFolder, SWT.NONE);
		tbtmCreateNewField.setToolTipText("");
		tbtmCreateNewField.setText("Создание");
		
		Composite compositeCreateNewField = new Composite(tabFolder, SWT.NONE);
		tbtmCreateNewField.setControl(compositeCreateNewField);
		compositeCreateNewField.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		compositeCreateNewField.setLayout(null);
		
		Label lblNewLabel = new Label(compositeCreateNewField, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		lblNewLabel.setBounds(10, 10, 216, 19);
		lblNewLabel.setText("Размерность игрового поля:");
		
		Label label = new Label(compositeCreateNewField, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		label.setBounds(50, 34, 68, 16);
		label.setText("Ширина:");
		
		Label label_1 = new Label(compositeCreateNewField, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		label_1.setBounds(50, 59, 68, 16);
		label_1.setText("Высота:");
		
		final Spinner spinnerWidth = new Spinner(compositeCreateNewField, SWT.BORDER);
		spinnerWidth.setTextLimit(10);
		spinnerWidth.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				controlSpinnerValue(spinnerWidth);
			}
		});
		spinnerWidth.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				controlSpinnerValue(spinnerWidth);
			}
		});
		
		spinnerWidth.setMaximum(20);
		spinnerWidth.setMinimum(2);
		spinnerWidth.setSelection(5);
		spinnerWidth.setBounds(147, 31, 47, 22);
		
		final Spinner spinnerHeigh = new Spinner(compositeCreateNewField, SWT.BORDER);
		spinnerHeigh.setTextLimit(10);
		spinnerHeigh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				controlSpinnerValue(spinnerHeigh);
			}
		});
		spinnerHeigh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				controlSpinnerValue(spinnerHeigh);
			}
		});
		spinnerHeigh.setMaximum(20);
		spinnerHeigh.setMinimum(2);
		spinnerHeigh.setSelection(5);
		spinnerHeigh.setBounds(147, 56, 47, 22);
		
		Label lblNewLabel_1 = new Label(compositeCreateNewField, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		lblNewLabel_1.setBounds(10, 81, 216, 16);
		lblNewLabel_1.setText("Режим составления игрового поля:");
		
		final Button radioButtonAutomatically = new Button(compositeCreateNewField, SWT.RADIO);
		radioButtonAutomatically.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		radioButtonAutomatically.setSelection(true);
		radioButtonAutomatically.setBounds(10, 103, 108, 16);
		radioButtonAutomatically.setText("Автоматически");
		
		Button radioButtonManually = new Button(compositeCreateNewField, SWT.RADIO);
		radioButtonManually.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		radioButtonManually.setBounds(137, 103, 78, 16);
		radioButtonManually.setText("Вручную");
		
		Button button_1 = new Button(compositeCreateNewField, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				boolean automaticComposition = false;
				GameField field = new GameField(Integer.parseInt(spinnerWidth.getText()), Integer.parseInt(spinnerHeigh.getText()));
				if (radioButtonAutomatically.getSelection()) {
					field.automaticCompositionField();
					automaticComposition = true;
				}
				GameFieldBuilder fieldBuilder = new GameFieldBuilder(automaticComposition);
				shell.dispose();
				fieldBuilder.open(field);
			}
		});
		button_1.setBounds(64, 125, 108, 25);
		button_1.setText("Создать поле");
		
		TabItem tbtmUpdateField = new TabItem(tabFolder, SWT.NONE);
		tbtmUpdateField.setText("Редактирование");
		
		Composite compositeUpdateField = new Composite(tabFolder, SWT.NONE);
		compositeUpdateField.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		tbtmUpdateField.setControl(compositeUpdateField);
		compositeUpdateField.setLayout(null);
		
		text = new Text(compositeUpdateField, SWT.BORDER);
		text.setEnabled(false);
		text.setBounds(46, 46, 135, 21);
		
		Button buttonOverview = new Button(compositeUpdateField, SWT.NONE);
		buttonOverview.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(shell, SWT.OPEN);
				dialog.setText("Open Game Field");
		        String[] filterExt = { "*.map"};
		        dialog.setFilterExtensions(filterExt);
		        String selected = dialog.open();
		        if (selected != null)
		        	text.setText(selected);
			}
		});
		buttonOverview.setBounds(62, 73, 102, 25);
		buttonOverview.setText("Обзор");
		
		Button buttonEdit = new Button(compositeUpdateField, SWT.NONE);
		buttonEdit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (text.getText().isEmpty()) {
					MessageDialog.openWarning(shell, "Внимание", "Файл не указан!");
				}
				else {
					try {
						GameField openedField = FileSystemManager.getGameField(text.getText());
						GameFieldBuilder fieldBuilder = new GameFieldBuilder(false);
						shell.dispose();
						fieldBuilder.open(openedField);
					} catch (StorageException e1) {
						MessageDialog.openWarning(shell, "Внимание", "Невозможно открыть файл, файл поврежден");
					}
				}
			}
		});
		buttonEdit.setBounds(62, 104, 102, 25);
		buttonEdit.setText("Редактировать");
		
		Label label_2 = new Label(compositeUpdateField, SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		label_2.setBounds(46, 25, 135, 15);
		label_2.setText("Путь до игрового поля:");

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	private void controlSpinnerValue(Spinner spinner) {
		if (Integer.parseInt(spinner.getText()) > 20) {
			spinner.setSelection(20);
		}
		if (Integer.parseInt(spinner.getText()) < 2) {
			spinner.setSelection(2);
		}
	}
}
