/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prototype.advancedprinter.action;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Ronaldo Totini
 */
public class ImageFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = FileChooserUtil.getExtension(f);
        if (extension != null) {
            if (extension.equals(FileChooserUtil.tiff) ||
                extension.equals(FileChooserUtil.tif) ||
                extension.equals(FileChooserUtil.gif) ||
                extension.equals(FileChooserUtil.jpeg) ||
                extension.equals(FileChooserUtil.jpg) ||
                extension.equals(FileChooserUtil.png)) {
                    return true;
            } else {
                return false;
            }
        }

        return false;

    }

    @Override
    public String getDescription() {
        return "somente imagens";

    }
    
}
