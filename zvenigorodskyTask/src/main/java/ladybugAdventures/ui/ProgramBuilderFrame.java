package ladybugAdventures.ui;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ladybugAdventures.data.FileSystemManager;
import ladybugAdventures.data.StorageException;
import ladybugAdventures.entities.CommandImpl;
import ladybugAdventures.entities.Cycle;
import ladybugAdventures.entities.FieldFactory;
import ladybugAdventures.entities.GameField;
import ladybugAdventures.entities.ManagementProgram;
import ladybugAdventures.entities.interfaces.Command;
import ladybugAdventures.enums.CommandType;
import ladybugAdventures.enums.Direction;
import ladybugAdventures.util.AnimationRunner;
import ladybugAdventures.util.CommandConverter;
import ladybugAdventures.util.Dialogs;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
//import org.eclipse.core.databinding.observable.Realm;
//import org.eclipse.jface.databinding.swt.SWTObservables;
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
	private Command[] currentCycle;
	GameFieldViewer gameFieldFrame;
	private int stringNum = 0;
	private boolean changeSaved = true;
	private boolean opened = false;
	public ProgramBuilderFrame(ManagementProgram program){
		currentCommand = new CommandImpl();
		currentWorkMode = WorkMode.ADD_COMMAND;
		this.program = program;
		this.program.addObserver(this);
		currentCycle = new Command[9];
		for(int i = 0; i<currentCycle.length;i++){
			currentCycle[i] = null;
		}
	}
	/**
	 * Launch the application.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
//		Display display = Display.getDefault();
//		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
//			public void run() {
				try {

					FileSystemManager.saveDefaultGameField(FieldFactory
							.createFieldAutomatically(13, 16));
					ManagementProgram mp = new ManagementProgram("NONAME",
							"C:/defaultMap.map");
					ProgramBuilderFrame window = new ProgramBuilderFrame(mp);
					window.open();
				} catch (Exception e) {
					e.printStackTrace();
				}
//			}
//		});
	}


	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		final Shell shell = new Shell(SWT.DIALOG_TRIM);
		shell.setSize(593, 510);
		shell.setText("Редактор программы управления");
		org.eclipse.swt.graphics.Rectangle client = shell.getBounds();
		org.eclipse.swt.graphics.Rectangle screen = Display.getDefault().getBounds();
		client.x = screen.width/2 -client.width/2;
		client.y = screen.height/2 - client.height/2;
		shell.setLocation(client.x, client.y);
		
		shell.addListener(SWT.Close, new Listener() { 
			public void handleEvent(Event event) { 
				if(!changeSaved){
					if(Dialogs.showYesNoDialog(shell, "Перед выходом вы хотите сохранить проделанный путь?"
							+ "\r\nИначе, все не сохраненные изменения будут утеряны!", 
							"Прервать редактирование пути") == SWT.YES){
						Dialogs.showSaveDialog(shell, "Файл программы управления", "*.xml", "managementProgram.xml", program);
					}
				}
				if(gameFieldFrame!=null)
					gameFieldFrame.getShell().dispose();
				shell.dispose();
			
			} 
			});
		
		final List<Button> directionBts = new ArrayList<>();
		final List<Button> actionBts = new ArrayList<>();

		final Group cycleGroup = new Group(shell, SWT.NONE);
		final Spinner cycleSpinner = new Spinner(cycleGroup, SWT.BORDER);

		final Label modeLabel = new Label(shell, SWT.NONE);
		modeLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		modeLabel.setBounds(453, 457, 118, 15);
		modeLabel.setText("добавления команды");
		final Button deleteButton = new Button(shell, SWT.TOGGLE);
		final Button addCycleButton = new Button(cycleGroup, SWT.TOGGLE);

		final Group directionGroup = new Group(shell, SWT.NONE);
		directionGroup.setText("Направление движения");
		directionGroup.setBounds(329, 10, 248, 119);

		Button upButton = new Button(directionGroup, SWT.TOGGLE);
		upButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modeLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
				currentCommand.setDirection(Direction.UP);
				unselectExcept(e.getSource(), directionBts);
				currentWorkMode = WorkMode.ADD_COMMAND;
				modeLabel.setText("добавления команды");
				addCycleButton.setSelection(false);
				deleteButton.setSelection(false);
			}
		});

		upButton.setBounds(109, 20, 32, 32);
		Image upImg = new Image(shell.getDisplay(), ProgramBuilderFrame.class
				.getResourceAsStream("/img/icons/up.png"));
		upImg = new Image(upImg.getDevice(), upImg.getImageData().scaledTo(32,
				32));
		upButton.setImage(upImg);

		Button downButton = new Button(directionGroup, SWT.TOGGLE);
		downButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modeLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
				currentCommand.setDirection(Direction.DOWN);
				unselectExcept(e.getSource(), directionBts);
				currentWorkMode = WorkMode.ADD_COMMAND;
				modeLabel.setText("добавления команды");
				addCycleButton.setSelection(false);
				deleteButton.setSelection(false);
			}
		});
		downButton.setBounds(109, 77, 32, 32);
		Image downImg = new Image(shell.getDisplay(), ProgramBuilderFrame.class
				.getResourceAsStream("/img/icons/down.png"));
		downImg = new Image(downImg.getDevice(), downImg.getImageData()
				.scaledTo(32, 32));
		downButton.setImage(downImg);

		Button rightButton = new Button(directionGroup, SWT.TOGGLE);
		rightButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modeLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
				currentCommand.setDirection(Direction.RIGHT);
				unselectExcept(e.getSource(), directionBts);
				currentWorkMode = WorkMode.ADD_COMMAND;
				modeLabel.setText("добавления команды");
				addCycleButton.setSelection(false);
				deleteButton.setSelection(false);
			}
		});
		rightButton.setLocation(140, 49);
		rightButton.setSize(32, 32);
		Image rightImg = new Image(shell.getDisplay(), ProgramBuilderFrame.class
				.getResourceAsStream("/img/icons/right.png"));
		rightImg = new Image(rightImg.getDevice(), rightImg.getImageData()
				.scaledTo(32, 32));
		rightButton.setImage(rightImg);

		Button leftButton = new Button(directionGroup, SWT.TOGGLE);
		leftButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modeLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
				currentCommand.setDirection(Direction.LEFT);
				unselectExcept(e.getSource(), directionBts);
				currentWorkMode = WorkMode.ADD_COMMAND;
				modeLabel.setText("добавления команды");
				addCycleButton.setSelection(false);
				deleteButton.setSelection(false);
			}
		});
		leftButton.setBounds(78, 49, 32, 32);
		Image leftImg = new Image(shell.getDisplay(), ProgramBuilderFrame.class
				.getResourceAsStream("/img/icons/left.png"));
		leftImg = new Image(leftImg.getDevice(), leftImg.getImageData()
				.scaledTo(32, 32));
		leftButton.setImage(leftImg);

		Group actionTypeGroup = new Group(shell, SWT.NONE);
		actionTypeGroup.setText("Тип команды");
		actionTypeGroup.setBounds(329, 135, 248, 82);

		Button stepButton = new Button(actionTypeGroup, SWT.TOGGLE);
		stepButton.setToolTipText("Тип команды: ШАГ");
		Image stepImg = new Image(shell.getDisplay(), ProgramBuilderFrame.class
				.getResourceAsStream("/img/icons/step.png"));
		leftImg = new Image(stepImg.getDevice(), stepImg.getImageData());
		stepButton.setImage(stepImg);
		stepButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modeLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
				currentCommand.setType(CommandType.MOVE);
				unselectExcept(e.getSource(), actionBts);
				currentWorkMode = WorkMode.ADD_COMMAND;
				modeLabel.setText("добавления команды");
				addCycleButton.setSelection(false);
				deleteButton.setSelection(false);
			}
		});
		stepButton.setBounds(10, 20, 60, 52);

		Button jumpButton = new Button(actionTypeGroup, SWT.TOGGLE);
		jumpButton.setToolTipText("Тип команды: ПРЫЖОК");
		jumpButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modeLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
				currentCommand.setType(CommandType.JUMP);
				unselectExcept(e.getSource(), actionBts);
				currentWorkMode = WorkMode.ADD_COMMAND;
				modeLabel.setText("добавления команды");
				addCycleButton.setSelection(false);
				deleteButton.setSelection(false);
			}
		});
		Image jumpImg = new Image(shell.getDisplay(), ProgramBuilderFrame.class
				.getResourceAsStream("/img/icons/jump.png"));
		jumpImg = new Image(jumpImg.getDevice(), jumpImg.getImageData()
				.scaledTo(32, 48));
		jumpButton.setImage(jumpImg);
		jumpButton.setBounds(92, 20, 60, 52);

		Button pushButton = new Button(actionTypeGroup, SWT.TOGGLE);
		pushButton.setToolTipText("Тип команды: ТОЛКАТЬ");

		pushButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modeLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
				currentCommand.setType(CommandType.PUSH);
				unselectExcept(e.getSource(), actionBts);
				currentWorkMode = WorkMode.ADD_COMMAND;
				modeLabel.setText("добавления команды");
				addCycleButton.setSelection(false);
				deleteButton.setSelection(false);
			}
		});
		Image pushImg = new Image(shell.getDisplay(), ProgramBuilderFrame.class
				.getResourceAsStream("/img/icons/push.png"));
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
				changeSaved = false;
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
				case ADD_CYCLE:
					List<Command> cycleCommandList = new LinkedList<>();
					for(int i=0;i<currentCycle.length;i++){
						if(currentCycle[i]==null)
							continue;
						cycleCommandList.add(currentCycle[i]);
						currentCycle[i] = null;
					}
					if(cycleCommandList.isEmpty()){
						MessageDialog.openWarning(shell, "Внимание",
								"Нельзя добавлять пустой цикл!");
						break;
					}
					Cycle cycle = new Cycle(Integer.parseInt(cycleSpinner.getText()), cycleCommandList);
					if (!program.insertCommand(listViewer.getList()
							.getSelectionIndex(),cycle)) {
						MessageDialog.openWarning(shell, "Внимание",
								"Цикл не может быть добавлен");
					}
					cycleGroup.redraw(0,0,250,250,true);
					break;
				default:
					break;
				}
				
			}
		});
		listComponent.setLocation(10, 10);
		listComponent.setSize(313, 404);
		listComponent.setItems(new String[] {"Список команд пуст"});
		directionBts.add(upButton);
		directionBts.add(downButton);
		directionBts.add(leftButton);
		directionBts.add(rightButton);

		actionBts.add(stepButton);
		actionBts.add(jumpButton);
		actionBts.add(pushButton);

		cycleGroup.setText("Цикл");
		cycleGroup.setBounds(329, 221, 242, 225);
		/*
		 * Инициализация ячеек цикла
		 */
		for(int i = 0; i<3;i++)
			for(int j = 0; j<3; j++){
				final int numberInArray = j*3+i;
				final Canvas canvas = new Canvas(cycleGroup, SWT.BORDER);
				canvas.setBackground(SWTResourceManager
						.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
				canvas.setBounds(38+i*54, 65 + j*54, 45, 45);
//				gc.di
				//canvas.redraw();
				canvas.addPaintListener(new PaintListener() {

					@Override
					public void paintControl(PaintEvent e) {
						if(currentCycle[numberInArray]!=null){
							e.gc.drawImage(CommandConverter.fromTypeToImage(currentCycle[numberInArray].getType(),25, 25), 0, 0);
							e.gc.drawImage(CommandConverter.fromDirectionToImage(((CommandImpl)currentCycle[numberInArray]).getDirection(), 15, 15),20,20);

						}
					}
				});
				canvas.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {

						switch (currentWorkMode) {
							case ADD_COMMAND:
								if(currentCommand.getDirection()!=null &&currentCommand.getType()!=null){
									currentCycle[numberInArray] = new CommandImpl(currentCommand.getDirection(),currentCommand.getType());
								}

								break;
							case DELETE:
								currentCycle[numberInArray] = null;
								break;
							default:
								break;
								}
						canvas.redraw(0, 0, 45, 45, true);
					}
				});
			}

		Label lblNewLabel = new Label(cycleGroup, SWT.NONE);
		lblNewLabel.setBounds(142, 10, 65, 15);
		lblNewLabel.setText("Повторить");


		cycleSpinner.setMaximum(25);
		cycleSpinner.setMinimum(1);
		cycleSpinner.setBounds(142, 31, 47, 22);

		Label label = new Label(cycleGroup, SWT.NONE);
		label.setBounds(190, 34, 29, 15);
		label.setText("раз");

		addCycleButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modeLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
				currentCommand.setDirection(null);
				currentCommand.setType(null);
				unselectExcept(null, actionBts);
				unselectExcept(null, directionBts);
				if (!((Button) e.getSource()).getSelection()
						&& !deleteButton.getSelection()) {
					currentWorkMode = WorkMode.ADD_COMMAND;
					modeLabel.setText("добавления команды");
				} else {
					deleteButton.setSelection(false);
					currentWorkMode = WorkMode.ADD_CYCLE;
					modeLabel.setText("добавления цикла");
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
					modeLabel.setText("добавления команды");
					modeLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
				} else {

					addCycleButton.setSelection(false);
					currentWorkMode = WorkMode.DELETE;
					modeLabel.setText("удаления");
					modeLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
				}
			}
			
		});
		deleteButton.setBounds(10, 420, 75, 25);
		deleteButton.setText("Удалить");

		Button cleanButton = new Button(shell, SWT.NONE);
		cleanButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(Dialogs.showYesNoDialog(shell, "Очистка сотрет все команды! Вы уверены?", "Очистка") == SWT.YES){
					changeSaved = false;
					program.cleanProgram();
				}
			}
		});
		cleanButton.setBounds(98, 421, 73, 25);
		cleanButton.setText("Очистить");

		Button runButton = new Button(shell, SWT.NONE);
		runButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if(!changeSaved && SWT.YES == Dialogs.showYesNoDialog(shell, "Перед выполнением прогрумму рекомендуется сохранить, "
							+ "иначе могут быть потеряны последние изменения.\r\nСохранить?", "Рекомендуемое сохранение"))
						Dialogs.showSaveDialog(shell, "Файл программы управления", "*.xml", "managementProgram.xml", program);
					AnimationRunner.run(FileSystemManager.getGameField(program.getMapAddress()), program);
				} catch (StorageException e1) {
					if(Dialogs.showYesNoDialog(shell, "Невозможно загрузить игровую карту. Выбрать другую?", "Карта отсутствует!")==SWT.YES){
						FileDialog fd = new FileDialog(shell, SWT.OPEN);
				        fd.setText("Выбор игрового поля");
				        //fd.setFilterPath(File.pathSeparator);
				        String[] filterExt = { "*.map"};
				        fd.setFilterExtensions(filterExt);
				        String selected = fd.open();
				        if(selected!=null){
				        	program.setMapAddress(selected);
				        	widgetSelected(e);
				        }
					}
				}
			}
		});
		runButton.setBounds(98, 452, 73, 25);
		runButton.setText("Выполнить");

		Button showGameField = new Button(shell, SWT.NONE);
		showGameField.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final Display display = Display.getDefault();
				display.asyncExec(new Runnable() {

					@Override
					public void run() {
						if(gameFieldFrame==null){
							gameFieldFrame = new GameFieldViewer();
						}
						if(!gameFieldFrame.isOpened()){
							
							if(!gameFieldFrame.open(program.getMapAddress(),shell.getBounds().x+shell.getBounds().width,shell.getBounds().y)){
								if(SWT.YES == Dialogs.showYesNoDialog(shell, "Карта не определена, либо не существует. Выбрать другую?", "Внимание")){
									FileDialog fd = new FileDialog(shell, SWT.OPEN);
							        fd.setText("Открыть игровое поле");
							        //fd.setFilterPath(File.pathSeparator);
							        String[] filterExt = { "*.map"};
							        fd.setFilterExtensions(filterExt);
							        String selected = fd.open();
							        if(selected!=null){
							        	program.setMapAddress(selected);
										changeSaved = false;
										run();
							        }
								}
							}
//							else
								
						}
						else{
							Shell gameFieldFrameShell = gameFieldFrame.getShell();
							if(gameFieldFrameShell.isDisposed()){
								gameFieldFrame.open(program.getMapAddress(),shell.getBounds().x+shell.getBounds().width,shell.getBounds().y);
							}
							else{
								gameFieldFrameShell.setFocus();
							}
						}
					}
				});

			}
		});
		showGameField.setBounds(177, 420, 146, 25);
		showGameField.setText("Показать игровую карту");

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(409, 457, 38, 15);
		label_1.setText("Режим");

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(Dialogs.showSaveDialog(shell, "Файл программы управления", "*.xml", "managementProgram.xml", program)){
					changeSaved = true;
				}
			}
		});
		button.setBounds(10, 452, 75, 25);
		button.setText("Сохранить");

		
		
		program.notifyObservers();

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
		stringNum=0;
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
		if(tabAmount==0){
			listComponent.removeAll();
			listComponent.redraw();
		}
		for (int i = 0; i < comList.size(); i++) {
			StringBuilder currentString = new StringBuilder();
			// регулируем число пробелов
			currentString.append(stringNum);
			if(stringNum<10)
				currentString.append("  ");
			currentString.append(": ");
			for (int spaceCounter = 0; spaceCounter < tabAmount
					* spaceInTabAmount; spaceCounter++) {
				currentString.append("-");
			}
			Command currentCommand = comList.get(i);
			currentString.append(currentCommand.toString());
			listComponent.add(currentString.toString());
				
			// если команда не цикл - записываем её с отрегулированным числом
			// пробелов
			if (currentCommand.getType() == CommandType.CYCLE) {
				Cycle currentCycle = (Cycle) currentCommand;
				stringNum++;
				fillListComponent(tabAmount+1, spaceInTabAmount,
							currentCycle.getCommandList());
			}
			else
				stringNum++;
		}
		
	}
}
