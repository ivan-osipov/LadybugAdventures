package technologyOfProgramming.zvenigorodskyTask;

import technologyOfProgramming.zvenigorodskyTask.ui.MainFrame;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
    	try {
			MainFrame window = new MainFrame();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}

//		sample: How get file from resources
//		File file = new File(App.class.getResource("/programs/managementProgram.xml").getPath());
//		System.out.println(file.exists());
    }
}
