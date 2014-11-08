package technologyOfProgramming.zvenigorodskyTask.data;


import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import technologyOfProgramming.zvenigorodskyTask.entities.ManagementProgram;
import technologyOfProgramming.zvenigorodskyTask.exceptions.StorageException;

public class FileSystemManager{
	private static final String PROGRAM_ADDRESS = "/managementProgram.xml";
	private static String programAddress;
	private static String mapAddress;
	static{
		programAddress = PROGRAM_ADDRESS;
	}

	public static  ManagementProgram getManagementProgramm() throws StorageException {
		try {
            JAXBContext context = JAXBContext.newInstance(ManagementProgram.class);
            Unmarshaller un = context.createUnmarshaller();
            ManagementProgram mgp = (ManagementProgram) un.unmarshal(new File(programAddress));
            mapAddress = mgp.getMapAddress();
            return mgp;
        } catch (JAXBException e) {
            throw new StorageException();
        }
	}
	public static void saveManagementProgram(ManagementProgram program) throws StorageException{
		try {
            JAXBContext context = JAXBContext.newInstance(ManagementProgram.class);
            Marshaller m = context.createMarshaller();
//            for pretty-print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

//             Write to System.out for debugging
//             m.marshal(program, System.out);

//            Write to File
            m.marshal(program, new File(programAddress));
        } catch (JAXBException e) {
            throw new StorageException();
        }
	}
	public static void saveManagementProgram(ManagementProgram program, String programAddress) throws StorageException{
		FileSystemManager.programAddress = programAddress;
		saveManagementProgram(program);
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
