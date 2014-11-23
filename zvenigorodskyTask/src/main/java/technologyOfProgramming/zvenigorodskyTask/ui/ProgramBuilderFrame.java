package technologyOfProgramming.zvenigorodskyTask.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import technologyOfProgramming.zvenigorodskyTask.data.FileSystemManager;
import technologyOfProgramming.zvenigorodskyTask.entities.CommandImpl;
import technologyOfProgramming.zvenigorodskyTask.entities.Cycle;
import technologyOfProgramming.zvenigorodskyTask.entities.GameField;
import technologyOfProgramming.zvenigorodskyTask.entities.ManagementProgram;
import technologyOfProgramming.zvenigorodskyTask.entities.enums.CommandType;
import technologyOfProgramming.zvenigorodskyTask.entities.enums.Direction;
import technologyOfProgramming.zvenigorodskyTask.factories.FieldFactory;
import technologyOfProgramming.zvenigorodskyTask.interfaces.Command;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class ProgramBuilderFrame implements Observer {

	private enum WorkMode {
		ADD_COMMAND, ADD_CYCLE, DELETE
	}

	private ManagementProgram program;
	private org.eclipse.swt.widgets.List listComponent;

	// private CommandType currentType = null;
	// private Direction currentDirection = null;
	private CommandImpl currentCommand;
	private WorkMode currentWorkMode;

	/**
	 * Launch the application.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			public void run() {
				try {

					FileSystemManager.saveGameField(FieldFactory
							.createFieldAutomatically(4, 5));
					ManagementProgram mp = new ManagementProgram("NONAME",
							"C:/defaultMap.map");
					ProgramBuilderFrame window = new ProgramBuilderFrame();
					window.open(mp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void open(ManagementProgram program) {
		currentCommand = new CommandImpl();
		currentWorkMode = WorkMode.ADD_COMMAND;
		this.program = program;
		this.program.addObserver(this);

		open();
	}

	/**
	 * Open the window.
	 */
	private void open() {
		Display display = Display.getDefault();
		final Shell shell = new Shell(SWT.DIALOG_TRIM);
		shell.setSize(505, 510);
		shell.setText("Редактор программы управления");
		final List<Button> directionBts = new ArrayList<>();
		final List<Button> actionBts = new ArrayList<>();

		final Label modeLabel = new Label(shell, SWT.NONE);
		modeLabel.setBounds(419, 452, 75, 30);
		modeLabel.setText("добавления\r\nкоманды");

		Group cycleGroup = new Group(shell, SWT.NONE);
		final Button deleteButton = new Button(shell, SWT.TOGGLE);
		final Button addCycleButton = new Button(cycleGroup, SWT.TOGGLE);

		final Group directionGroup = new Group(shell, SWT.NONE);
		directionGroup.setText("Направление движения");
		directionGroup.setBounds(248, 10, 248, 119);

		Button upButton = new Button(directionGroup, SWT.TOGGLE);
		upButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				currentCommand.setDirection(Direction.UP);
				unselectExcept(e.getSource(), directionBts);
				currentWorkMode = WorkMode.ADD_COMMAND;
				modeLabel.setText("добавления\r\nкоманды");
				addCycleButton.setSelection(false);
				deleteButton.setSelection(false);
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
				currentCommand.setDirection(Direction.DOWN);
				unselectExcept(e.getSource(), directionBts);
				currentWorkMode = WorkMode.ADD_COMMAND;
				modeLabel.setText("добавления\r\nкоманды");
				addCycleButton.setSelection(false);
				deleteButton.setSelection(false);
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
				currentCommand.setDirection(Direction.RIGHT);
				unselectExcept(e.getSource(), directionBts);
				currentWorkMode = WorkMode.ADD_COMMAND;
				modeLabel.setText("добавления\r\nкоманды");
				addCycleButton.setSelection(false);
				deleteButton.setSelection(false);
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
				currentCommand.setDirection(Direction.LEFT);
				unselectExcept(e.getSource(), directionBts);
				currentWorkMode = WorkMode.ADD_COMMAND;
				modeLabel.setText("добавления\r\nкоманды");
				addCycleButton.setSelection(false);
				deleteButton.setSelection(false);
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
				currentCommand.setType(CommandType.MOVE);
				unselectExcept(e.getSource(), actionBts);
				currentWorkMode = WorkMode.ADD_COMMAND;
				modeLabel.setText("добавления\r\nкоманды");
				addCycleButton.setSelection(false);
				deleteButton.setSelection(false);
			}
		});
		stepButton.setBounds(10, 20, 60, 52);

		Button jumpButton = new Button(actionTypeGroup, SWT.TOGGLE);
		jumpButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				currentCommand.setType(CommandType.JUMP);
				unselectExcept(e.getSource(), actionBts);
				currentWorkMode = WorkMode.ADD_COMMAND;
				modeLabel.setText("добавления\r\nкоманды");
				addCycleButton.setSelection(false);
				deleteButton.setSelection(false);
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
				currentCommand.setType(CommandType.PUSH);
				unselectExcept(e.getSource(), actionBts);
				currentWorkMode = WorkMode.ADD_COMMAND;
				modeLabel.setText("добавления\r\nкоманды");
				addCycleButton.setSelection(false);
				deleteButton.setSelection(false);
			}
		});
		Image pushImg = SWTResourceManager.getImage(ProgramBuilderFrame.class
				.getResource("/img/icons/push.png").getPath());
		pushImg = new Image(pushImg.getDevice(), pushImg.getImageData()
				.scaledTo(52, 42));
		pushButton.setImage(pushImg);
		pushButton.setBounds(178, 20, 60, 52);

		final ListViewer listViewer = new ListViewer(shell, SWT.BORDER
				| SWT.V_SCROLL);
		listComponent = listViewer.getList();
		// if(program.getCommandList().isEmpty()){
		// listComponent.add("Список команд пуст");
		// listViewer.refresh();
		// }
		listComponent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				listComponent.redraw();
				switch (currentWorkMode) {
				case ADD_COMMAND:
					if (currentCommand.getType() != null
							&& currentCommand.getDirection() != null) {
						// unselectExcept(null, actionBts);
						// unselectExcept(null, directionBts);
						if (!program.insertCommand(listViewer.getList()
								.getSelectionIndex(),
								new CommandImpl(currentCommand.getDirection(),
										currentCommand.getType()))) {
							MessageDialog.openWarning(shell, "Внимание",
									"Команда не может быть добавлена");
						}
					}
					break;
				case DELETE:
					if(!program.removeCommand(listViewer.getList().getSelectionIndex()))
							MessageDialog.openWarning(shell, "Внимание",
									"Команда не может быть удалена");
					break;
				default:
					break;
				}
			}
		});
		listComponent.setLocation(10, 10);
		listComponent.setSize(232, 436);
		listComponent.setItems(new String[] {"Список команд пуст"});
		directionBts.add(upButton);
		directionBts.add(downButton);
		directionBts.add(leftButton);
		directionBts.add(rightButton);

		actionBts.add(stepButton);
		actionBts.add(jumpButton);
		actionBts.add(pushButton);

		cycleGroup.setText("Цикл");
		cycleGroup.setBounds(248, 221, 242, 225);

		Composite composite = new Composite(cycleGroup, SWT.BORDER);
		composite.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		composite.setBounds(38, 69, 45, 45);
		composite.setLayout(new GridLayout(1, false));

		Composite composite_1 = new Composite(cycleGroup, SWT.BORDER);
		composite_1.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		composite_1.setBounds(89, 69, 45, 45);
		composite_1.setLayout(new GridLayout(1, false));

		Composite composite_2 = new Composite(cycleGroup, SWT.BORDER);
		composite_2.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		composite_2.setBounds(140, 69, 45, 45);
		composite_2.setLayout(new GridLayout(1, false));

		Composite composite_3 = new Composite(cycleGroup, SWT.BORDER);
		composite_3.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		composite_3.setBounds(38, 121, 45, 45);
		composite_3.setLayout(new GridLayout(1, false));

		Composite composite_4 = new Composite(cycleGroup, SWT.BORDER);
		composite_4.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		composite_4.setBounds(89, 121, 45, 45);
		composite_4.setLayout(new GridLayout(1, false));

		Composite composite_5 = new Composite(cycleGroup, SWT.BORDER);
		composite_5.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		composite_5.setBounds(140, 121, 45, 45);
		composite_5.setLayout(new GridLayout(1, false));

		Composite composite_6 = new Composite(cycleGroup, SWT.BORDER);
		composite_6.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		composite_6.setBounds(38, 170, 45, 45);
		composite_6.setLayout(new GridLayout(1, false));

		Composite composite_7 = new Composite(cycleGroup, SWT.BORDER);
		composite_7.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		composite_7.setBounds(89, 170, 45, 45);
		composite_7.setLayout(new GridLayout(1, false));

		Composite composite_8 = new Composite(cycleGroup, SWT.BORDER);
		composite_8.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		composite_8.setBounds(140, 170, 45, 45);
		composite_8.setLayout(new GridLayout(1, false));

		Label lblNewLabel = new Label(cycleGroup, SWT.NONE);
		lblNewLabel.setBounds(142, 10, 65, 15);
		lblNewLabel.setText("Повторить");

		Spinner cycleSpinner = new Spinner(cycleGroup, SWT.BORDER);
		cycleSpinner.setMaximum(25);
		cycleSpinner.setMinimum(1);
		cycleSpinner.setBounds(142, 31, 47, 22);

		Label label = new Label(cycleGroup, SWT.NONE);
		label.setBounds(190, 34, 29, 15);
		label.setText("раз");

		addCycleButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				currentCommand.setDirection(null);
				currentCommand.setType(null);
				unselectExcept(null, actionBts);
				unselectExcept(null, directionBts);
				if (!((Button) e.getSource()).getSelection()
						&& !deleteButton.getSelection()) {
					currentWorkMode = WorkMode.ADD_COMMAND;
					modeLabel.setText("добавления\r\nкоманды");
				} else {
					deleteButton.setSelection(false);
					currentWorkMode = WorkMode.ADD_CYCLE;
					modeLabel.setText("добавления\r\nцикла");
				}
			}
		});
		addCycleButton.setBounds(28, 25, 96, 33);
		addCycleButton.setText("Добавить цикл");

		deleteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				currentCommand.setDirection(null);
				currentCommand.setType(null);
				unselectExcept(null, actionBts);
				unselectExcept(null, directionBts);
				if (!((Button) e.getSource()).getSelection()
						&& !addCycleButton.getSelection()) {
					currentWorkMode = WorkMode.ADD_COMMAND;
					modeLabel.setText("добавления\r\nкоманды");
				} else {

					addCycleButton.setSelection(false);
					currentWorkMode = WorkMode.DELETE;
					modeLabel.setText("удаления");
				}
			}
		});
		deleteButton.setBounds(10, 452, 56, 25);
		deleteButton.setText("Удалить");

		Button cleanButton = new Button(shell, SWT.NONE);
		cleanButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				program.cleanProgram();
				listViewer.refresh();
			}
		});
		cleanButton.setBounds(72, 452, 64, 25);
		cleanButton.setText("Очистить");

		Button runButton = new Button(shell, SWT.NONE);
		runButton.setBounds(142, 452, 75, 25);
		runButton.setText("Выполнить");

		Button showGameField = new Button(shell, SWT.NONE);
		showGameField.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final Display display = Display.getDefault();
				display.asyncExec(new Runnable() {

					@Override
					public void run() {
						// тут будет вызов компонента Димы для отрисовки поля
						Shell shell2 = new Shell(SWT.DIALOG_TRIM);
						shell2.setSize(505, 510);
						shell2.setBounds(
								shell.getBounds().x + shell.getBounds().width,
								shell.getBounds().y, -1, -1);
						shell2.setText("Редактор программы управления");
						shell2.open();
						shell2.setLayout(new FillLayout());
						while (!shell2.isDisposed()) {
							if (!display.readAndDispatch()) {
								display.sleep();
							}
						}

					}
				});

			}
		});
		showGameField.setBounds(223, 452, 146, 25);
		showGameField.setText("Показать игровую карту");

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(375, 452, 38, 15);
		label_1.setText("Режим");

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

	@Override
	public void update(Observable o, Object arg) {
		List<Command> updatedList = program.getCommandList();
		fillListComponent(0, 2, updatedList);
		if (updatedList.isEmpty()) {
			listComponent.add("Список команд пуст");
		}
	}

	/**
	 * Процедура заполнения компонента отображающего список
	 *
	 * @param tabAmount
	 *            Число табуляций
	 * @param spaceInTabAmount
	 *            Количество пробелов в одной табуляции
	 * @param comList
	 *            Список команд, которыми будет заполнен компонент
	 */
	private void fillListComponent(int tabAmount, int spaceInTabAmount,
			List<Command> comList) {
		int stringNum = 0;
		listComponent.removeAll();
		listComponent.redraw();
		for (int i = 0; i < comList.size(); i++) {
			StringBuilder currentString = new StringBuilder();
			// регулируем число пробелов
			currentString.append(stringNum).append(": ");
			for (int spaceCounter = 0; spaceCounter < tabAmount
					* spaceInTabAmount; spaceCounter++) {
				currentString.append(" ");
			}
			Command currentCommand = comList.get(i);
			currentString.append(currentCommand.toString());
			// если команда не цикл - записываем её с отрегулированным числом
			// пробелов
			if (currentCommand.getType() != CommandType.CYCLE) {
				listComponent.add(currentString.toString());
			} else {
				// если команда - цикл
				Cycle currentCycle = (Cycle) currentCommand;
				fillListComponent(++tabAmount, spaceInTabAmount,
						currentCycle.getCommandList());
			}
			stringNum++;

		}

	}
}
