package technologyOfProgramming.zvenigorodskyTask.data;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import technologyOfProgramming.zvenigorodskyTask.entities.ManagementProgram;
import technologyOfProgramming.zvenigorodskyTask.interfaces.Command;

public class FileSystemManager implements StorageManager{
	private static final String MAP_ADDRESS_DEFAULT = "default";
	private static final String PROGRAM_NAME_DEFAULT = "noname";
	private static final String PROGRAM_CREATOR = "anonym";

	private List<Command> comandList;

	private String mapAddress;
	private String programName;
	private String programCreator;
	private boolean validate;
	public FileSystemManager(){
		mapAddress = MAP_ADDRESS_DEFAULT;
		programName = PROGRAM_NAME_DEFAULT;
		programCreator = PROGRAM_CREATOR;
		comandList = new LinkedList<>();
		validate = false;
	}
	public boolean isValidate() {
		return validate;
	}
	public void setProgram(Document doc) {
		Node firstNode = doc.getFirstChild();
		if(!firstNode.getAttributes().getNamedItem("type").getTextContent().equals("zvenigorodskyTask"))
			return;
		mapAddress = firstNode.getAttributes().getNamedItem("mapAddress").getTextContent();
		programName = firstNode.getAttributes().getNamedItem("programName").getTextContent();
		programCreator = firstNode.getAttributes().getNamedItem("programCreator").getTextContent();
		//TODO Проход и соханение всех вложенных блоков в список соманд
//		System.out.println(MessageFormat.format("{0} - {1} - {2}", mapAddress, programName, programCreator));
//		for(int i =0; i< list.getLength();i++){
//			System.out.println(list.item(i).getLocalName());
//		}
//		System.out.println( list.item(0).getAttributes().getNamedItem(name));
		validate = true;
	}

	public Document getProgram() {
		return null;
	}
	public List<Command> getComandList() {
		return comandList;
	}
	@Override
	public ManagementProgram getManagementProgramm() {
		// TODO Auto-generated method stub
		return null;
	}

}
