package ladybugAdventures.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLEditorKit;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.widgets.*;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.internal.Platform;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.ui.internal.EditorPane;

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
		shell.setImage(SWTResourceManager
				.getImage(getClass().getResource("/img/icons/help.png").getPath()));
		shell.setSize(649, 418);
		shell.setText("Справочная информация");

		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBounds(0, 0, 633, 379);

		TabItem tabItem_1 = new TabItem(tabFolder, SWT.NONE);
		tabItem_1.setText("Информация о разработчиках"); 	

		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tabItem_1.setControl(composite_1);
		
		Browser browser_1 = new Browser(composite_1, SWT.NONE);
		browser_1.setBounds(0, 0, 618, 343);
		StringBuilder builder1=new StringBuilder();
		builder1.append("<html><body bgcolor=#Ff0e5f5><center><h2><ins>Студенты группы 6403:</h2></ins><br></br></center>");
		builder1.append("<h3><ul><li>Осипов Иван</li><br></br><br></br>");
		builder1.append("<li>Бочаров Дмитрий</li><br></br><br></br>");
		builder1.append("<li>Мухтулова Евгения</li>");
		builder1.append("</ul></h3></body></html>");
		browser_1.setText(builder1.toString());

		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("Информация о системе");

		Composite composite = new Composite(tabFolder, SWT.NONE);
		tabItem.setControl(composite);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL)); 

		Browser browser = new Browser(composite, SWT.NONE);
		browser.setTouchEnabled(true);
	    
		File file = new File(getClass().getResource("/files/help.html").getPath());
		if (file.exists()) {
			browser.setUrl(file.getAbsolutePath());
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
