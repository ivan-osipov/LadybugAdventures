package ladybugAdventures.ui.animation.components;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import ladybugAdventures.entities.CommandImpl;
import ladybugAdventures.util.Analizator;

import org.eclipse.swt.graphics.Point;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

public class CommandLogRenderer extends AbstractComponent {
	private final int SPACE = 10;
	private Point location;
	private Point size;
	private Queue<CommandImpl> log;
	private TextInformationRenderer[] logViewer;
	public CommandLogRenderer(GUIContext container) {
		super(container);
		this.location = new Point(container.getWidth()-400, container.getHeight() - 200);
		size = new Point(-1, -1);
		log = new ArrayBlockingQueue<>(4);
		logViewer = new TextInformationRenderer[log.size()];
		for(int i = 0; i< logViewer.length;i++){
			logViewer[i] = new TextInformationRenderer(container, new Point(location.x,location.y + i*SPACE), String.valueOf(i) + ": ");
		}
	}

	@Override
	public void render(GUIContext container, Graphics g) throws SlickException {
		for(int i = 0; i< logViewer.length;i++){
			logViewer[i].render(container, g);
		}

	}
	public void update(){
		
		CommandImpl[] curLog =  log.toArray(new CommandImpl[0]);
		for(int i =0; i<log.size(); i++){
			if(curLog[i]!=null)
				logViewer[i].setText(String.valueOf(i) + ": " + curLog[i].toString());
			else
				logViewer[i].setText(String.valueOf(i) + ": ----------");
		}
	}
	public void addToLog(CommandImpl command){
		if(log.size() == 4)
			log.poll();
		log.add(command);
		update();
	}
	@Override
	public void setLocation(int x, int y) {
		this.location.x = x;
		this.location.y = y;

	}

	@Override
	public int getX() {
		return location.x;
	}

	@Override
	public int getY() {
		return location.y;
	}

	@Override
	public int getWidth() {
		return size.x;
	}

	@Override
	public int getHeight() {
		return size.y;
	}

}
