package ladybugAdventures.ui.animation.components;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import ladybugAdventures.entities.CommandImpl;
import ladybugAdventures.util.Analizator;

import org.eclipse.swt.graphics.Point;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

public class CommandLogRenderer extends AbstractComponent {
	private final int SPACE = 20;
	private int x,y;
	private Point size;
	private Queue<CommandImpl> log;
	private TextInformationRenderer[] logViewer;
	public CommandLogRenderer(GUIContext container) throws SlickException {
		
		super(container);
		x = container.getWidth()-200;
		y = container.getHeight() - 100;
		size = new Point(-1, -1);
		log = new ArrayBlockingQueue<>(4);
		logViewer = new TextInformationRenderer[4];
		for(int i = 0; i< logViewer.length;i++){
			logViewer[i] = new TextInformationRenderer(container, new Point(x+10,y+10 + i*SPACE), String.valueOf(i) + ": ----------");
			logViewer[i].init(container);
		}
	}

	@Override
	public void render(GUIContext container, Graphics g) throws SlickException {
		g.setColor(new Color(152,251,152,0.6f));
		g.fillRect(x, y, 200, 100);//фон
		for(int i = 0; i< logViewer.length;i++){
			logViewer[i].render(container, g,Color.black);
		}

	}
	public void update(){
		
		CommandImpl[] curLog =  log.toArray(new CommandImpl[log.size()]);
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
		this.x = x;
		this.y = y;

	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
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
