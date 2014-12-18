package technologyOfProgramming.zvenigorodskyTask.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class YesNoDialog {
	/**
	 * Сконфигурированный диалог Да/Нет. 
	 * 
	 * @param shell оболочка родитель
	 * @param message сообщение
	 * @param frameTitle заголовок окна
	 * @see SWT
	 * @return Варианты ответов: константы SWT.YES и SWT.NO 
	 * 
	 */
	public static int showDialog(Shell shell, String message, String frameTitle ){
		MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION
		            | SWT.YES | SWT.NO);
        messageBox.setMessage(message);
        messageBox.setText(frameTitle);
        return messageBox.open();
    }
}
