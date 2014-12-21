package ladybugAdventures.util;

import java.io.File;
import java.net.URISyntaxException;
import java.security.CodeSource;

import ladybugAdventures.Application;

public class CommonUtils {
	public static String getJarPath(){
		CodeSource codeSource = Application.class.getProtectionDomain().getCodeSource();
		File jarFile = null;
		try {
			jarFile = new File(codeSource.getLocation().toURI().getPath());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return jarFile.getParentFile().getPath();
	}
}
