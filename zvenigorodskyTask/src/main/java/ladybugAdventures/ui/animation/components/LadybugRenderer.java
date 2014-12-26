package ladybugAdventures.ui.animation.components;

import ladybugAdventures.enums.Behaviour;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

public class LadybugRenderer extends UniversalAnimationRenderer{
	private Behaviour behaviour;
	private Animation moving;
	private Animation pushing;
	private Animation jumping;
	public LadybugRenderer(GUIContext container, int size) throws SlickException {
		super(container, size);
//		moving
	}
	public void setBehaviour(Behaviour behaviour){
		this.behaviour = behaviour;
		
	}

}
