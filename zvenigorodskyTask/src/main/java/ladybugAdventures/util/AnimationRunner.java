package ladybugAdventures.util;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import ladybugAdventures.Application;
import ladybugAdventures.entities.GameField;
import ladybugAdventures.entities.ManagementProgram;
import ladybugAdventures.ui.animation.MPViewer;
import ladybugAdventures.ui.components.LoadAnimationFrame;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class AnimationRunner {
	public static void run(final GameField field, final ManagementProgram program){
		Executor executor = Executors.newSingleThreadExecutor();
		executor.execute(new Runnable() {

			@Override
			public void run() {
				Path resourcePath = null;
				
//				URL resourceUrl = AnimationRunner.class.
//						getResource("/native/win32");
//				try {
					System.out.println(CommonUtils.getJarPath());
					resourcePath = Paths.get(CommonUtils.getJarPath()+"/lib/native/");
//					resourcePath = Paths.get(resourceUrl.toURI());
					System.out.println(resourcePath);
//				} catch (URISyntaxException e1) {
//				}
				System.setProperty("org.lwjgl.librarypath", resourcePath.toString());
				System.setProperty("java.library.path", resourcePath.toString());
				try {
					AppGameContainer container = new AppGameContainer(new MPViewer(field,program));
//					org.eclipse.swt.graphics.Rectangle bounds = Display.getDefault().getBounds();
//					container.setDisplayMode(bounds.width, bounds.height, false);
					container.setDisplayMode(1366, 768, false);
					container.setShowFPS(false);
					container.setTitle("Приключения божьей коровки");
//					container.setAlwaysRender(false);
//					container.setClearEachFrame(false);
//					container.setIcon("src/main/resources/img/icons/logoIcon.ico");
					container.setTargetFrameRate(30);
//					container.setFullscreen(true);
					container.start();
					
				} catch (SlickException e3) {
					Shell shell = new Shell(Display.getDefault());
					MessageDialog.openError(shell, "Ошибка!", "Невозможно отобразить визуализацию. Переустановите игру!");
					shell.dispose();
					System.exit(0);
				}
				catch(UnsatisfiedLinkError e){
					Shell shell = new Shell(Display.getDefault());
					MessageDialog.openError(shell, "Ошибка!", "Ваша операционная система скорее всего не совместима\n"
							+ " со средством визуализации. \nОбновите библиотеки jinput и lwjgl(/lib/native/) в соотсветствии с вашей ОС");
					shell.dispose();
					System.exit(0);
				}
			}
		});
	}
}
