package ladybugAdventures.ui;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;

public class HelpFrame {

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HelpFrame window = new HelpFrame();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		Shell shell = new Shell(SWT.DIALOG_TRIM);
		//shell.setImage(SWTResourceManager
				//.getImage(getClass().getResource("/img/icons/help.png").getPath()));
		new Image(shell.getDisplay(), getClass()
				.getResourceAsStream("/img/icons/help.png"));
		shell.setSize(700, 650);
		shell.setText("Справочная информация");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		org.eclipse.swt.graphics.Rectangle client = shell.getBounds();
		org.eclipse.swt.graphics.Rectangle screen = Display.getDefault().getBounds();
		client.x = screen.width/2 -client.width/2;
		client.y = screen.height/2 - client.height/2;
		shell.setLocation(client.x, client.y);
		
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);

		TabItem tabItem_1 = new TabItem(tabFolder, SWT.NONE);
		tabItem_1.setText("Информация о разработчиках"); 	

		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tabItem_1.setControl(composite_1);
		
		Browser browser_1 = new Browser(composite_1, SWT.NONE);
		browser_1.setBounds(0, 0, 700, 650);

        browser_1.setTouchEnabled(true);
	    
        InputStream is = getClass().getResourceAsStream("/files/authors.html");
		if (is != null) {
			try {
				browser_1.setText(IOUtils.toString(is, "UTF-8"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
		StringBuilder builder=new StringBuilder();
		builder.append("<html><body bgcolor=#c0c0c0><center><br></br><h1>Файл информации об авторах отсутствует!</h1><h2><br></br>");
		builder.append("Пожалуйста,проверьте наличие файла и его корректность.</h2></center></body></html>");
		browser_1.setText(builder.toString());
	}

		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("Информация о системе");

		Composite composite = new Composite(tabFolder, SWT.NONE);
		tabItem.setControl(composite);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL)); 
		
		Browser browser = new Browser(composite, SWT.NONE);
		browser.setTouchEnabled(true);  
		
		is = getClass().getResourceAsStream("/files/help.html");
		if (is != null) {
			try {
				browser.setText(IOUtils.toString(is, "UTF-8"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
		StringBuilder builder=new StringBuilder();
		builder.append("<html><body bgcolor=#c0c0c0><center><br></br><h1>Файл справки отсутствует!</h1><h2><br></br>");
		builder.append("Пожалуйста,проверьте наличие файла и его корректность.</h2></center></body></html>");
		browser.setText(builder.toString());
	}
		shell.open();
		  shell.layout();
		  while (!shell.isDisposed()) {
		   if (!display.readAndDispatch()) {
		    display.sleep();
		   }
		  }
	}
}
