package technologyOfProgramming.zvenigorodskyTask.data;

import org.w3c.dom.Document;

import technologyOfProgramming.zvenigorodskyTask.entities.ManagementProgram;

public interface StorageManager {
	void setProgram(Document doc);
	Document getProgram();
	ManagementProgram getManagementProgramm();
}
