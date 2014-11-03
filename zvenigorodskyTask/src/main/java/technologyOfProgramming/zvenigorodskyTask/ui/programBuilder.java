package technologyOfProgramming.zvenigorodskyTask.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;

import swing2swt.layout.BoxLayout;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.layout.RowData;
import swing2swt.layout.FlowLayout;

public class programBuilder extends Composite {
	private Text text;
	private Text log;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public programBuilder(Composite parent, int style) {
		super(parent, style);
		setLayout(new RowLayout(SWT.HORIZONTAL));

		Composite composite_3 = new Composite(this, SWT.NONE);
		composite_3.setLayoutData(new RowData(443, 261));
						composite_3.setLayout(new RowLayout(SWT.HORIZONTAL));

								Composite programsPart = new Composite(composite_3, SWT.NONE);
								programsPart.setLayoutData(new RowData(322, 256));
				//		programsPart.setBounds(0, 0, this.getSize().x-100, this.getSize().y);

						Composite elementsPart = new Composite(composite_3, SWT.NONE);
						elementsPart.setLayoutData(new RowData(SWT.DEFAULT, 257));
						elementsPart.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

								Button button = new Button(elementsPart, SWT.NONE);
								button.setText("ШАГ ВВЕРХ");

										Button button_1 = new Button(elementsPart, SWT.NONE);
										button_1.setText("ШАГ ВНИЗ");

												Button button_2 = new Button(elementsPart, SWT.NONE);
												button_2.setText("ШАГ ВЛЕВО");

														Button button_3 = new Button(elementsPart, SWT.NONE);
														button_3.setText("ШАГ ВПРАВО");

																Button button_4 = new Button(elementsPart, SWT.NONE);
																button_4.setText("ПРЫЖОК");

																		Button button_5 = new Button(elementsPart, SWT.NONE);
																		button_5.setText("ТОЛКАТЬ");

																				Button button_6 = new Button(elementsPart, SWT.NONE);
																				button_6.setText("ЗАНЯТЬ КЛЕТКУ");

																						Composite composite = new Composite(elementsPart, SWT.NONE);
																								composite.setLayout(new FillLayout(SWT.VERTICAL));

																								Button button_7 = new Button(composite, SWT.NONE);
																								button_7.setText("ПОВТОРИТЬ");

																										Composite composite_1 = new Composite(composite, SWT.NONE);
																												composite_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

																												text = new Text(composite_1, SWT.BORDER);
																												text.setText("1");
																												text.setToolTipText("");

																														Label label = new Label(composite_1, SWT.NONE);
																														label.setText("раз");

																														Button button_8 = new Button(elementsPart, SWT.NONE);
																														button_8.setText("ПОКАЗАТЬ КАРТУ");

																														Composite composite_4 = new Composite(composite_3, SWT.NONE);

		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setLayoutData(new RowData(440, 32));
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));

		log = new Text(composite_2, SWT.BORDER);
		log.setEditable(false);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
