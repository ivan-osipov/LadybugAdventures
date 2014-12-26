package ladybugAdventures.ui.animation.components;

import java.util.EnumMap;

import ladybugAdventures.enums.Behaviour;
import ladybugAdventures.enums.Direction;
import ladybugAdventures.enums.GameObject;
import ladybugAdventures.util.LazyRenderBuffer;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

public class LadybugRenderer extends UniversalAnimationRenderer{
	private Behaviour behaviour;

	private EnumMap<Direction, Animation> moving;
	private EnumMap<Direction, Animation> pushing;
	private EnumMap<Direction, Animation> jumping;
	
	private Animation  state;
	public LadybugRenderer(GUIContext container, int size) throws SlickException {
		super(container, size);
		//Инициализация анимаций
		moving = new EnumMap<>(Direction.class);
		pushing = new EnumMap<>(Direction.class);
		jumping = new EnumMap<>(Direction.class);
		
		moving.put(Direction.UP, LazyRenderBuffer.getAnimation(GameObject.LADYBUG, Behaviour.MOVING, Direction.UP));
		moving.put(Direction.DOWN, LazyRenderBuffer.getAnimation(GameObject.LADYBUG, Behaviour.MOVING, Direction.DOWN));
		moving.put(Direction.LEFT, LazyRenderBuffer.getAnimation(GameObject.LADYBUG, Behaviour.MOVING, Direction.LEFT));
		moving.put(Direction.RIGHT, LazyRenderBuffer.getAnimation(GameObject.LADYBUG, Behaviour.MOVING, Direction.RIGHT));
		
//		pushing.put(Direction.UP, LazyRenderBuffer.getAnimation(GameObject.LADYBUG, Behaviour.MOVING, Direction.UP));
//		pushing.put(Direction.DOWN, LazyRenderBuffer.getAnimation(GameObject.LADYBUG, Behaviour.MOVING, Direction.DOWN));
//		pushing.put(Direction.LEFT, LazyRenderBuffer.getAnimation(GameObject.LADYBUG, Behaviour.MOVING, Direction.LEFT));
//		pushing.put(Direction.RIGHT, LazyRenderBuffer.getAnimation(GameObject.LADYBUG, Behaviour.MOVING, Direction.RIGHT));
//		
//		jumping.put(Direction.UP, LazyRenderBuffer.getAnimation(GameObject.LADYBUG, Behaviour.MOVING, Direction.UP));
//		jumping.put(Direction.DOWN, LazyRenderBuffer.getAnimation(GameObject.LADYBUG, Behaviour.MOVING, Direction.DOWN));
//		jumping.put(Direction.LEFT, LazyRenderBuffer.getAnimation(GameObject.LADYBUG, Behaviour.MOVING, Direction.LEFT));
//		jumping.put(Direction.RIGHT, LazyRenderBuffer.getAnimation(GameObject.LADYBUG, Behaviour.MOVING, Direction.RIGHT));
	}
	public void setBehaviour(Behaviour behaviour){
		this.behaviour = behaviour;
	}
	public void setDirection(Direction direction){

		switch (behaviour) {
		case MOVING:
			state = moving.get(direction);
			break;
		case PUSHING:
			state = pushing.get(direction);	
			break;
				
		case JUMPING:
			state = jumping.get(direction);
			break;
		}
	}
}
