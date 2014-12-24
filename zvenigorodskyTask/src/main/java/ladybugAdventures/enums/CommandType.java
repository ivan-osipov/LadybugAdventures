package ladybugAdventures.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlEnum
@XmlType(name = "commandType")
public enum CommandType {
	MOVE, JUMP, PUSH, CYCLE
}
