package technologyOfProgramming.zvenigorodskyTask.ui;

import org.eclipse.swt.widgets.Display;
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

import technologyOfProgramming.zvenigorodskyTask.entities.GameField;

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
		Shell shell = new Shell();
		shell.setSize(356, 257);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		
		TabItem tbtmCreateNewField = new TabItem(tabFolder, SWT.NONE);
		tbtmCreateNewField.setToolTipText("");
		tbtmCreateNewField.setText("Создание игрового поля");
		
		Composite compositeCreateNewField = new Composite(tabFolder, SWT.NONE);
		tbtmCreateNewField.setControl(compositeCreateNewField);
		compositeCreateNewField.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		compositeCreateNewField.setLayout(null);
		
		Label lblNewLabel = new Label(compositeCreateNewField, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel.setBounds(10, 10, 161, 15);
		lblNewLabel.setText("Размерность игрового поля:");
		
		Label label = new Label(compositeCreateNewField, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setBounds(10, 34, 55, 15);
		label.setText("Ширина:");
		
		Label label_1 = new Label(compositeCreateNewField, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_1.setBounds(10, 62, 55, 15);
		label_1.setText("Высота:");
		
		final Spinner spinnerWidth = new Spinner(compositeCreateNewField, SWT.BORDER);
		spinnerWidth.setMaximum(20);
		spinnerWidth.setMinimum(2);
		spinnerWidth.setBounds(71, 31, 47, 22);
		
		final Spinner spinnerHeigh = new Spinner(compositeCreateNewField, SWT.BORDER);
		spinnerHeigh.setMaximum(20);
		spinnerHeigh.setMinimum(2);
		spinnerHeigh.setBounds(71, 59, 47, 22);
		
		Label lblNewLabel_1 = new Label(compositeCreateNewField, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel_1.setBounds(10, 90, 203, 15);
		lblNewLabel_1.setText("Режим составления игрового поля:");
		
		final Button radioButtonAutomatically = new Button(compositeCreateNewField, SWT.RADIO);
		radioButtonAutomatically.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		radioButtonAutomatically.setSelection(true);
		radioButtonAutomatically.setBounds(10, 111, 108, 16);
		radioButtonAutomatically.setText("Автоматически");
		
		Button radioButtonManually = new Button(compositeCreateNewField, SWT.RADIO);
		radioButtonManually.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		radioButtonManually.setBounds(10, 133, 108, 16);
		radioButtonManually.setText("Вручную");
		
		Button button_1 = new Button(compositeCreateNewField, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				GameField field = new GameField(Integer.parseInt(spinnerWidth.getText()), Integer.parseInt(spinnerHeigh.getText()));
				if (radioButtonAutomatically.getSelection()) {
					field.automaticCompositionField();
				}
				GameFieldBuilder fieldBuilder = new GameFieldBuilder();
				fieldBuilder.open(field);
			}
		});
		button_1.setBounds(10, 155, 108, 25);
		button_1.setText("Создать поле");
		
		TabItem tbtmUpdateField = new TabItem(tabFolder, SWT.NONE);
		tbtmUpdateField.setText("Редактирование игрового поля");
		
		Composite compositeUpdateField = new Composite(tabFolder, SWT.NONE);
		compositeUpdateField.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		tbtmUpdateField.setControl(compositeUpdateField);
		compositeUpdateField.setLayout(null);
		
		text = new Text(compositeUpdateField, SWT.BORDER);
		text.setEnabled(false);
		text.setBounds(100, 40, 135, 21);
		
		Button buttonOverview = new Button(compositeUpdateField, SWT.NONE);
		buttonOverview.setBounds(121, 67, 92, 25);
		buttonOverview.setText("Обзор");
		
		Button buttonEdit = new Button(compositeUpdateField, SWT.NONE);
		buttonEdit.setBounds(121, 98, 92, 25);
		buttonEdit.setText("Редактировать");

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
