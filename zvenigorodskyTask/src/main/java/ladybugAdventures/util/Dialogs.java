package ladybugAdventures.util;

import java.io.File;

import ladybugAdventures.data.FileSystemManager;
import ladybugAdventures.data.StorageException;
import ladybugAdventures.entities.GameField;
import ladybugAdventures.entities.ManagementProgram;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class Dialogs {
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
	public static int showYesNoDialog(Shell shell, String message, String frameTitle ){
		MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION
		            | SWT.YES | SWT.NO);
        messageBox.setMessage(message);
        messageBox.setText(frameTitle);
        return messageBox.open();
    }
	public static boolean showSaveDialog(Shell shell, String fileTypeName, String extension, String defaultFileName, Object saveObj){
		FileDialog dialog = new FileDialog(shell, SWT.SAVE);
		dialog.setFilterNames (new String [] {fileTypeName});
		dialog.setFilterExtensions (new String [] {extension}); 
		dialog.setFileName (defaultFileName);
		String fileName = dialog.open();
		if(fileName != null)
			try {
				if (!(new File(fileName).exists() && Dialogs.showYesNoDialog(shell,"Перезаписать?", 
						"Файл с таким именем существует") == SWT.NO)) {
					if(saveObj instanceof ManagementProgram){
						if(((ManagementProgram)saveObj).getCommandAmount()==0 
								&& Dialogs.showYesNoDialog(shell,"Вы уверены, что хотите "
										+ "сохранить пустую программу управления?", "Внимание")==SWT.YES){
							FileSystemManager.saveManagementProgram((ManagementProgram)saveObj, fileName);
							return true;
						}
						else{
							if(((ManagementProgram)saveObj).getCommandAmount()!=0){
								FileSystemManager.saveManagementProgram((ManagementProgram)saveObj, fileName);
								return true;
							}
						}
						return false;
					}
					else
						if(saveObj instanceof GameField){
							FileSystemManager.saveGameField((GameField)saveObj, fileName);
							return true;
						}
				}
			} catch (StorageException e1) {
				MessageDialog.openWarning(shell, "Внимание", "Невозможно сохранить файл, выберите другую директорию");
				return false;
			}
		return false;
	}
}
