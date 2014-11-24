package technologyOfProgramming.zvenigorodskyTask.interfaces;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import technologyOfProgramming.zvenigorodskyTask.entities.CommandImpl;
import technologyOfProgramming.zvenigorodskyTask.entities.Cycle;
import technologyOfProgramming.zvenigorodskyTask.entities.enums.CommandType;

@XmlTransient
@XmlSeeAlso({Cycle.class, CommandImpl.class})
public interface Command extends Serializable {
	public CommandType getType();
}
