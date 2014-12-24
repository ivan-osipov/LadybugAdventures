package ladybugAdventures.entities.interfaces;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import ladybugAdventures.entities.CommandImpl;
import ladybugAdventures.entities.Cycle;
import ladybugAdventures.enums.CommandType;

@XmlTransient
@XmlSeeAlso({Cycle.class, CommandImpl.class})
public interface Command extends Serializable {
	public CommandType getType();
}
