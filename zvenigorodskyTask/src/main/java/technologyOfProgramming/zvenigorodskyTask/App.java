package technologyOfProgramming.zvenigorodskyTask;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import technologyOfProgramming.zvenigorodskyTask.data.FileSystemManager;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
    	System.out.println("Hello!");
    	System.out.println("Bye!");

//		sample: How get file from resources
//		File file = new File(App.class.getResource("/programs/managementProgram.xml").getPath());
//		System.out.println(file.exists());
    }
}
