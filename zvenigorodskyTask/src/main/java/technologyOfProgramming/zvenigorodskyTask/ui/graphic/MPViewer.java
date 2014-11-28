package technologyOfProgramming.zvenigorodskyTask.ui.graphic;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.swt.widgets.Shell;
import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.TextureImpl;

import technologyOfProgramming.zvenigorodskyTask.data.FileSystemManager;
import technologyOfProgramming.zvenigorodskyTask.entities.GameField;
import technologyOfProgramming.zvenigorodskyTask.entities.ManagementProgram;
import technologyOfProgramming.zvenigorodskyTask.exceptions.StorageException;
import technologyOfProgramming.zvenigorodskyTask.ui.graphic.components.GameFieldRenderer;

public class MPViewer extends BasicGame {
	private Image background;
	private Image buglady;
	private GameFieldRenderer gameField;
	private GameField startField;
	private ManagementProgram program;

	private float x;
	private float y;
	public MPViewer(GameField field, ManagementProgram program){
		super("Путешествие божьей коровки");
		startField = field;
		this.program = program;
	}



	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		background.draw(0,0,container.getWidth(),container.getHeight());
		gameField.render(container, g);
		buglady.draw(x,y);

	}

	@Override
	public void init(GameContainer container) throws SlickException {
		background = new Image(getClass().getResource("/img/pictures/background.jpg").getPath());
		gameField = new GameFieldRenderer(5,5);
		gameField.init(container);
		buglady = new Image(getClass().getResource("/img/pictures/buglady.png").getPath());
		buglady.rotate(90);
		x=10;
		y=20;
	}

	@Override
	public void update(GameContainer container, int t)
			throws SlickException {
		//x+=0.1*delta;


	}

	/*
	 *
	 *
	 * 			MAIN
	 *
	 *
	 */
	public static void main(String[] args) {
		System.out.println(System.getProperty("java.library.path"));
		Path resourcePath = null;
		URL resourceUrl = MPViewer.class.
				getResource("/native/win32");
		try {
			resourcePath = Paths.get(resourceUrl.toURI());
		} catch (URISyntaxException e1) {
		}
		System.setProperty("org.lwjgl.librarypath", resourcePath.toString());
		System.setProperty("java.library.path", resourcePath.toString());

		try {
			AppGameContainer container = new AppGameContainer(new MPViewer(FileSystemManager.getDefaultGameField(),FileSystemManager.getDefaultManagementProgramm()));
			container.setDisplayMode(800, 600, false);
			container.setTargetFrameRate(60);
			container.start();
		} catch (SlickException | StorageException e) {
			e.printStackTrace();
		}

	}

}
