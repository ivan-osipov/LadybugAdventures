package ladybugAdventures.ui.animation;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import ladybugAdventures.entities.GameField;
import ladybugAdventures.entities.ManagementProgram;
import ladybugAdventures.enums.GameObject;
import ladybugAdventures.ui.animation.components.CommonInformationRenderer;
import ladybugAdventures.ui.animation.components.GameFieldRenderer;
import ladybugAdventures.ui.animation.components.StartButtonRenderer;
import ladybugAdventures.util.Analizator;
import ladybugAdventures.util.LazyRenderBuffer;
import ladybugAdventures.util.MoveRenderElement;
import ladybugAdventures.util.ResourceProvider;
import ladybugAdventures.util.StepTrack;

import org.eclipse.swt.graphics.Point;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MPViewer extends BasicGame {
	
	
	private Image background;
	private GameFieldRenderer gameField;
	private StartButtonRenderer startButton;
	private Analizator analizator;
	private String info;
	private List<MoveRenderElement> renderTrackList;
	private CommonInformationRenderer infoRenderer;
	private boolean animating;
	int printedObjects = 0;
	int oneStep = 0;
	public MPViewer(GameField field, ManagementProgram program){
		super("Приключения божьей коровки");
		info = MessageFormat.format("Автор: {0}\r\nКоличество команд: {1}\r\nРазмер поля: {2}x{3}", program.getAuthor(),program.getAllCommandAmountWithIterations(),
				field.getWidth(),field.getHeigh());
		
		analizator = new Analizator(field, program);
	}
	@Override
	public void init(GameContainer container) throws SlickException {
		background = new Image(ResourceProvider.getResInpStr(ResourceProvider.BACKGROUND_ID),ResourceProvider.BACKGROUND_ID,false);
		startButton = new StartButtonRenderer(container);
		gameField = new GameFieldRenderer(analizator.getFieldBeforeStep());
		gameField.init(container);
		infoRenderer = new CommonInformationRenderer(container, new Point(10, container.getHeight()-200), new Point(300,300), info);
		infoRenderer.init(container);
		//СПИСОК ОТРИСУЕМЫХ
		renderTrackList = new ArrayList<MoveRenderElement>();
		if(!updateRenderTrackList()){
			startButton.setVisible(false);
		}
	}


	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		background.draw(0,0,container.getWidth(),container.getHeight());
		gameField.render(container, g);
		startButton.render(container, g);
		infoRenderer.render(container, g);
//		g.drawString(info, 30, container.getHeight()-150);
		for(MoveRenderElement renderElement: renderTrackList){
			renderElement.sprite.draw(renderElement.current.x, 
					renderElement.current.y, 
					gameField.getCellSize(), gameField.getCellSize());
		}

	}

	@Override
	public void update(GameContainer container, int t)
			throws SlickException {
		if(!animating){
			if(startButton.update(container, t)){
				animating = true;
				oneStep = gameField.getCellSize()/20;//размер 1 шага
			}
		}
		else{
			for(int i = 0; i<renderTrackList.size(); i++){
				MoveRenderElement currentElement = renderTrackList.get(i);
				if(!currentElement.isAnimating()){
					currentElement.setAnimating(true);
				}
				int deltaX = currentElement.result.x-currentElement.current.x;
				int deltaY = currentElement.result.y-currentElement.current.y;
				if(!(Math.abs(deltaX)<oneStep && Math.abs(deltaY)<oneStep)){
					if(deltaX>0){
						currentElement.current.x+=oneStep;
						continue;
					}
					if(deltaX<0){
						currentElement.current.x-=oneStep;
						continue;
					}
					if(deltaY>0){
						currentElement.current.y+=oneStep;
						continue;
					}
					if(deltaY<0){
						currentElement.current.y-=oneStep;
						continue;
					}
				}
//				if(i < renderTrackList.size()){
				if(!updateRenderTrackList())
					animating = false;
//				}
			}
				
		}


	}
	private boolean updateRenderTrackList() throws SlickException{
		renderTrackList.clear();
		
		if(analizator.nextStep())
		{
			List<StepTrack> tracks = analizator.getTrackList();
			gameField.setNotRenderList(tracks);
			for(int i = 0;i<tracks.size();i++){
				renderTrackList.add(new MoveRenderElement(tracks.get(i),gameField));
			}

			gameField.setGameField(analizator.getFieldBeforeStep());
			
			return true;
		}
		if(analizator.isEndOfProgram()){
			startButton.setVisible(true);
		}

		gameField.setNotRenderList(new ArrayList<StepTrack>());
		gameField.setGameField(analizator.getFieldAfterStep());
		return false;
	}
	/*
	 *
	 *
	 * 			MAIN
	 *
	 *
	 */
//	public static void main(String[] args) {
//		Path resourcePath = null;
//		
////		URL resourceUrl = AnimationRunner.class.
////				getResource("/native/win32");
////		try {
//			System.out.println(CommonUtils.getJarPath());
//			resourcePath = Paths.get(CommonUtils.getJarPath()+"/lib/native/");
////			resourcePath = Paths.get(resourceUrl.toURI());
//			System.out.println(resourcePath);
////		} catch (URISyntaxException e1) {
////		}
//		System.setProperty("org.lwjgl.librarypath", resourcePath.toString());
//		System.setProperty("java.library.path", resourcePath.toString());
//
//		try {
//			ManagementProgram mp = FileSystemManager.getManagementProgramm("C:/Users/Ivan/Desktop/managementProgram.xml");
//			AppGameContainer container = new AppGameContainer(new MPViewer(FileSystemManager.getGameField(mp.getMapAddress()),mp));
//			container.setDisplayMode(800, 600, false);
//			
//			container.setTargetFrameRate(60);
//			container.start();
//		} catch (SlickException | StorageException e) {
//			e.printStackTrace();
//		}
//
//	}

}
