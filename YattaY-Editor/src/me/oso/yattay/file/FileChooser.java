package me.oso.yattay.file;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Created by Thomas on 3 oct. 2016
 */
public class FileChooser {

	
	public static String choose(String fileFilter) {
		JFileChooser fileopen = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("YattaY files", fileFilter);
	    fileopen.addChoosableFileFilter(filter);

	    int ret = fileopen.showDialog(null, "Open file");

	    if (ret == JFileChooser.APPROVE_OPTION) {
	      File file = fileopen.getSelectedFile();
	      return file.getAbsolutePath().toString();
	    }
	    return null;
	}
	
	
	
	
}
