package technologyOfProgramming.zvenigorodskyTask.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import technologyOfProgramming.zvenigorodskyTask.data.FileSystemManager;
import technologyOfProgramming.zvenigorodskyTask.entities.GameField;
import technologyOfProgramming.zvenigorodskyTask.entities.ManagementProgram;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

public class ProgramBuilderFrame {
	private ManagementProgram program;

	/**
	 * Launch the application.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ProgramBuilderFrame window = new ProgramBuilderFrame();
			window.open();
			GameField field = new GameField(4, 5);
			field.automaticCompositionField();
			FileSystemManager.saveGameField(field);
			// ManagementProgram mp = new ManagementProgram("asd",
			// "C:/defaultMap.map")
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open(ManagementProgram program) {
		this.program = program;
		open();
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		Shell shell = new Shell(SWT.DIALOG_TRIM);
		shell.setSize(506, 509);
		shell.setText("Редактор программы управления");
		final List<Button> directionBts = new ArrayList<>();
		final List<Button> actionBts = new ArrayList<>();

		final Group directionGroup = new Group(shell, SWT.NONE);
		directionGroup.setText("Направление движения");
		directionGroup.setBounds(248, 10, 248, 119);

		Button upButton = new Button(directionGroup, SWT.TOGGLE);
		upButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				unselectExcept(e.getSource(), directionBts);
			}
		});

		upButton.setBounds(109, 20, 32, 32);
		Image upImg = SWTResourceManager.getImage(ProgramBuilderFrame.class
				.getResource("/img/icons/up.png").getPath());
		upImg = new Image(upImg.getDevice(), upImg.getImageData().scaledTo(32,
				32));
		upButton.setImage(upImg);

		Button downButton = new Button(directionGroup, SWT.TOGGLE);
		downButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				unselectExcept(e.getSource(), directionBts);
			}
		});
		downButton.setBounds(109, 77, 32, 32);
		Image downImg = SWTResourceManager.getImage(ProgramBuilderFrame.class
				.getResource("/img/icons/down.png").getPath());
		downImg = new Image(downImg.getDevice(), downImg.getImageData()
				.scaledTo(32, 32));
		downButton.setImage(downImg);

		Button rightButton = new Button(directionGroup, SWT.TOGGLE);
		rightButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				unselectExcept(e.getSource(), directionBts);
			}
		});
		rightButton.setLocation(140, 49);
		rightButton.setSize(32, 32);
		Image rightImg = SWTResourceManager.getImage(ProgramBuilderFrame.class
				.getResource("/img/icons/right.png").getPath());
		rightImg = new Image(rightImg.getDevice(), rightImg.getImageData()
				.scaledTo(32, 32));
		rightButton.setImage(rightImg);

		Button leftButton = new Button(directionGroup, SWT.TOGGLE);
		leftButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				unselectExcept(e.getSource(), directionBts);
			}
		});
		leftButton.setBounds(78, 49, 32, 32);
		Image leftImg = SWTResourceManager.getImage(ProgramBuilderFrame.class
				.getResource("/img/icons/left.png").getPath());
		leftImg = new Image(leftImg.getDevice(), leftImg.getImageData()
				.scaledTo(32, 32));
		leftButton.setImage(leftImg);

		Group actionTypeGroup = new Group(shell, SWT.NONE);
		actionTypeGroup.setText("Тип команды");
		actionTypeGroup.setBounds(248, 135, 248, 82);

		Button stepButton = new Button(actionTypeGroup, SWT.TOGGLE);
		Image stepImg = SWTResourceManager.getImage(ProgramBuilderFrame.class
				.getResource("/img/icons/step.png").getPath());
		leftImg = new Image(stepImg.getDevice(), stepImg.getImageData());
		stepButton.setImage(stepImg);
		stepButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				unselectExcept(e.getSource(), actionBts);
			}
		});
		stepButton.setBounds(10, 20, 60, 52);

		Button jumpButton = new Button(actionTypeGroup, SWT.TOGGLE);
		jumpButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				unselectExcept(e.getSource(), actionBts);
			}
		});
		Image jumpImg = SWTResourceManager.getImage(ProgramBuilderFrame.class
				.getResource("/img/icons/jump.png").getPath());
		jumpImg = new Image(jumpImg.getDevice(), jumpImg.getImageData()
				.scaledTo(32, 48));
		jumpButton.setImage(jumpImg);
		jumpButton.setBounds(92, 20, 60, 52);

		Button pushButton = new Button(actionTypeGroup, SWT.TOGGLE);

		pushButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				unselectExcept(e.getSource(), actionBts);
			}
		});
		Image pushImg = SWTResourceManager.getImage(ProgramBuilderFrame.class
				.getResource("/img/icons/push.png").getPath());
		pushImg = new Image(pushImg.getDevice(), pushImg.getImageData()
				.scaledTo(52, 42));
		pushButton.setImage(pushImg);
		pushButton.setBounds(178, 20, 60, 52);

		ListViewer listViewer = new ListViewer(shell, SWT.BORDER | SWT.V_SCROLL);
		org.eclipse.swt.widgets.List list = listViewer.getList();
		list.setLocation(10, 10);
		list.setSize(232, 436);
		list.setItems(new String[] { "..." });


		directionBts.add(upButton);
		directionBts.add(downButton);
		directionBts.add(leftButton);
		directionBts.add(rightButton);

		actionBts.add(stepButton);
		actionBts.add(jumpButton);
		actionBts.add(pushButton);

		Group cycleGroup = new Group(shell, SWT.NONE);
		cycleGroup.setText("Цикл");
		cycleGroup.setBounds(248, 221, 242, 225);

		Composite composite = new Composite(cycleGroup, SWT.BORDER);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		composite.setBounds(38, 69, 45, 45);
		composite.setLayout(new GridLayout(1, false));

		Composite composite_1 = new Composite(cycleGroup, SWT.BORDER);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		composite_1.setBounds(89, 69, 45, 45);
		composite_1.setLayout(new GridLayout(1, false));

		Composite composite_2 = new Composite(cycleGroup, SWT.BORDER);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		composite_2.setBounds(140, 69, 45, 45);
		composite_2.setLayout(new GridLayout(1, false));

		Composite composite_3 = new Composite(cycleGroup, SWT.BORDER);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		composite_3.setBounds(38, 121, 45, 45);
		composite_3.setLayout(new GridLayout(1, false));

		Composite composite_4 = new Composite(cycleGroup, SWT.BORDER);
		composite_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		composite_4.setBounds(89, 121, 45, 45);
		composite_4.setLayout(new GridLayout(1, false));

		Composite composite_5 = new Composite(cycleGroup, SWT.BORDER);
		composite_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		composite_5.setBounds(140, 121, 45, 45);
		composite_5.setLayout(new GridLayout(1, false));

		Composite composite_6 = new Composite(cycleGroup, SWT.BORDER);
		composite_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		composite_6.setBounds(38, 170, 45, 45);
		composite_6.setLayout(new GridLayout(1, false));

		Composite composite_7 = new Composite(cycleGroup, SWT.BORDER);
		composite_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		composite_7.setBounds(89, 170, 45, 45);
		composite_7.setLayout(new GridLayout(1, false));

		Composite composite_8 = new Composite(cycleGroup, SWT.BORDER);
		composite_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		composite_8.setBounds(140, 170, 45, 45);
		composite_8.setLayout(new GridLayout(1, false));

		Label lblNewLabel = new Label(cycleGroup, SWT.NONE);
		lblNewLabel.setBounds(142, 10, 65, 15);
		lblNewLabel.setText("Повторить");

		Spinner spinner = new Spinner(cycleGroup, SWT.BORDER);
		spinner.setMaximum(25);
		spinner.setMinimum(1);
		spinner.setBounds(142, 31, 47, 22);

		Label label = new Label(cycleGroup, SWT.NONE);
		label.setBounds(190, 34, 29, 15);
		label.setText("раз");

		Button button = new Button(cycleGroup, SWT.TOGGLE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		button.setBounds(28, 25, 96, 33);
		button.setText("Добавить цикл");

		Button button_1 = new Button(shell, SWT.TOGGLE);
		button_1.setBounds(10, 452, 56, 25);
		button_1.setText("Удалить");

		Button button_2 = new Button(shell, SWT.NONE);
		button_2.setBounds(85, 452, 64, 25);
		button_2.setText("Очистить");

		Button button_3 = new Button(shell, SWT.NONE);
		button_3.setBounds(167, 452, 75, 25);
		button_3.setText("Выполнить");

		Button button_4 = new Button(shell, SWT.NONE);
		button_4.setBounds(310, 452, 100, 25);
		button_4.setText("Показать карту");

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	private void unselectExcept(Object excepted, List<Button> buttonList) {
		for (Button dirBut : buttonList)
			if (excepted != dirBut)
				dirBut.setSelection(false);
	}
}
