package ladybugAdventures.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlEnum
@XmlType(name = "direction")
public enum Direction {
	UP, DOWN, LEFT, RIGHT
}
