package technologyOfProgramming.zvenigorodskyTask.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;


import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;

public class ProgramBuilder extends Composite {
	private Text text;
	private Text text_1;

	/**
	 * Create the composite.
	 *
	 * @param parent
	 * @param style
	 */
	public ProgramBuilder(Composite parent, int style) {
		super(parent, style);
		setLayout(new RowLayout(SWT.HORIZONTAL));

		Composite composite_3 = new Composite(this, SWT.NONE);
		composite_3.setLayout(null);
		composite_3.setLayoutData(new RowData(512, 465));

		Composite programsPart = new Composite(composite_3, SWT.NONE);
		programsPart.setBounds(3, 3, 317, 423);
		programsPart.setLayout(new FillLayout(SWT.HORIZONTAL));

		List list = new List(programsPart, SWT.BORDER);
		// programsPart.setBounds(0, 0, this.getSize().x-100, this.getSize().y);

		Composite elementsPart = new Composite(composite_3, SWT.NONE);
		elementsPart.setBounds(323, 3, 179, 423);
		elementsPart.setLayout(null);

		Composite composite_2 = new Composite(elementsPart, SWT.NONE);
		composite_2.setBounds(0, 0, 179, 97);
				composite_2.setLayout(null);

				Button button = new Button(composite_2, SWT.TOGGLE | SWT.CENTER);
				button.setBounds(65, 10, 46, 25);
				button.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
					}
				});
				button.setText("ВВЕРХ");

				Button button_2 = new Button(composite_2, SWT.TOGGLE);
				button_2.setBounds(33, 41, 49, 25);
				button_2.setText("ВЛЕВО");

				Button button_3 = new Button(composite_2, SWT.TOGGLE);
				button_3.setBounds(88, 41, 59, 25);
				button_3.setText("ВПРАВО");

				Button button_1 = new Button(composite_2, SWT.TOGGLE);
				button_1.setBounds(65, 72, 44, 25);
				button_1.setText("ВНИЗ");

		Composite composite_5 = new Composite(elementsPart, SWT.NONE);
		composite_5.setBounds(0, 95, 179, 95);
								composite_5.setLayout(null);

								Button button_5 = new Button(composite_5, SWT.TOGGLE);
								button_5.setBounds(10, 70, 64, 25);
								button_5.setText("ТОЛКАТЬ");

				Button button_4 = new Button(composite_5, SWT.NONE);
				button_4.setBounds(10, 39, 64, 25);
				button_4.setText("ПРЫЖОК");

				Button btnNewButton = new Button(composite_5, SWT.NONE);
				btnNewButton.setBounds(10, 10, 64, 25);
				btnNewButton.setText("ШАГ");

				text_1 = new Text(composite_5, SWT.BORDER);
				text_1.setBounds(104, 46, 56, 21);

				Label label_1 = new Label(composite_5, SWT.CENTER);
				label_1.setBounds(94, 10, 75, 30);
				label_1.setText("Позиция\r\nдля вставки");

				Button button_10 = new Button(composite_5, SWT.NONE);
				button_10.setBounds(104, 70, 56, 25);
				button_10.setText("Вставить");

		Composite composite = new Composite(elementsPart, SWT.NONE);
		composite.setBounds(0, 196, 179, 182);
		composite.setLayout(null);

		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setBounds(111, 10, 51, 26);

		text = new Text(composite_1, SWT.BORDER);
		text.setText("1");
		text.setToolTipText("");

		Label label = new Label(composite_1, SWT.NONE);
		label.setText("раз");

				Button button_7 = new Button(composite, SWT.NONE);
				button_7.setLocation(10, 11);
				button_7.setSize(83, 25);
				button_7.setText("ПОВТОРИТЬ");

		Composite composite_4 = new Composite(composite, SWT.NONE);
		composite_4.setBounds(10, 41, 159, 131);

		Composite composite_6 = new Composite(composite_4, SWT.NONE);
		composite_6.setBounds(10, 10, 35, 35);

		Composite composite_7 = new Composite(composite_4, SWT.NONE);
		composite_7.setBounds(114, 10, 35, 35);

		Composite composite_8 = new Composite(composite_4, SWT.NONE);
		composite_8.setBounds(64, 10, 35, 35);

		Composite composite_9 = new Composite(composite_4, SWT.NONE);
		composite_9.setBounds(10, 52, 35, 35);

		Composite composite_10 = new Composite(composite_4, SWT.NONE);
		composite_10.setBounds(10, 93, 35, 35);

		Composite composite_11 = new Composite(composite_4, SWT.NONE);
		composite_11.setBounds(64, 51, 35, 35);

		Composite composite_12 = new Composite(composite_4, SWT.NONE);
		composite_12.setBounds(114, 51, 35, 35);

		Composite composite_13 = new Composite(composite_4, SWT.NONE);
		composite_13.setBounds(64, 93, 35, 35);

		Composite composite_14 = new Composite(composite_4, SWT.NONE);
		composite_14.setBounds(114, 93, 35, 35);

				Button button_8 = new Button(elementsPart, SWT.NONE);
				button_8.setBounds(27, 388, 128, 25);
				button_8.setText("ПОКАЗАТЬ КАРТУ");

				Button button_6 = new Button(composite_3, SWT.NONE);
				button_6.setEnabled(false);
				button_6.setBounds(3, 430, 56, 25);
				button_6.setText("Удалить");

				Button button_9 = new Button(composite_3, SWT.NONE);
				button_9.setBounds(333, 432, 75, 25);
				button_9.setText("Сохранить");

				Button btnNewButton_1 = new Button(composite_3, SWT.NONE);
				btnNewButton_1.setBounds(414, 432, 75, 25);
				btnNewButton_1.setText("Закрыть");

				Button button_11 = new Button(composite_3, SWT.NONE);
				button_11.setBounds(238, 432, 75, 25);
				button_11.setText("Выполнить");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
