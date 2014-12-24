package ladybugAdventures;

import ladybugAdventures.ui.MainFrame;

/**
 * Hello world!
 *
 */
public class Application
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
