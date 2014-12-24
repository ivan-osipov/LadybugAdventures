package ladybugAdventures.data;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ladybugAdventures.entities.GameField;
import ladybugAdventures.entities.ManagementProgram;

public class FileSystemManager{
	private static final String PROGRAM_ADDRESS = "/defaultManagementProgram.xml";
	private static final String MAP_ADDRESS = "/defaultMap.map";
	private static String programAddress;
	private static String mapAddress;
	static{
		programAddress = PROGRAM_ADDRESS;
		mapAddress = MAP_ADDRESS;
	}
	public static void setDefaultProgramAddress(String programAddress) {
		FileSystemManager.programAddress = programAddress;
	}
	public static void setDefaultMapAddress(String mapAddress) {
		FileSystemManager.mapAddress = mapAddress;
	}
	public static void saveManagementProgram(ManagementProgram program, String programAddress) throws StorageException{
		try {
            JAXBContext context = JAXBContext.newInstance(ManagementProgram.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(program, new File(programAddress));
        } catch (JAXBException e) {
        	e.printStackTrace();
            throw new StorageException();
        }
	}
	public static void saveGameField(Serializable gameField, String mapAddress) throws StorageException{
		File gameFieldFile = new File(mapAddress);
		if(!gameFieldFile.exists())
			try {
				gameFieldFile.createNewFile();
			} catch (IOException ignore) {
				throw new StorageException();
			}
		try(FileOutputStream fileOutStream = new FileOutputStream(gameFieldFile);
			ObjectOutputStream serialOutputStream = new ObjectOutputStream(fileOutStream);){
				serialOutputStream.writeObject(gameField);
			} catch (IOException e) {
				throw new StorageException();
			}
	}
	public static void saveDefaultManagementProgram(ManagementProgram program) throws StorageException{
		saveManagementProgram(program, programAddress);
	}
	public static void saveDefaultGameField(Serializable gameField) throws StorageException{
		saveGameField(gameField, mapAddress);
	}
	public static GameField getGameField(String mapAddress) throws StorageException{
		File gameFieldFile = new File(mapAddress);
		if(!gameFieldFile.exists())
			throw new StorageException();
		try(FileInputStream fileInStream = new FileInputStream(mapAddress);
			ObjectInputStream serialInputStream = new ObjectInputStream(fileInStream);){
			return (GameField) serialInputStream.readObject();
		} catch (Exception e) {
			throw new StorageException();
		}
	}
	public static GameField getDefaultGameField() throws StorageException{
		return getGameField(mapAddress);
	}
	public static  ManagementProgram getManagementProgramm(String programAddress) throws StorageException{
		try {
            JAXBContext context = JAXBContext.newInstance(ManagementProgram.class);
            Unmarshaller un = context.createUnmarshaller();
            ManagementProgram mgp = (ManagementProgram) un.unmarshal(new File(programAddress));
            //mapAddress = mgp.getMapAddress();
            return mgp;
        } catch (Exception e) {
            throw new StorageException();
        }
	}
	public static  ManagementProgram getDefaultManagementProgramm() throws StorageException {
		return getManagementProgramm(programAddress);
	}
//	public static void saveCommand(Command command){
//		try {
//            JAXBContext context = JAXBContext.newInstance(Cycle.class);
//            Marshaller m = context.createMarshaller();
//            //for pretty-print XML in JAXB
//            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//
////             Write to System.out for debugging
//             m.marshal(command, System.out);
//
//            // Write to File
////            m.marshal(program, new File("/program.xml"));
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//	}

}
