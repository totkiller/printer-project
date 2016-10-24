/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prototype.advancedprinter.action;

import java.io.File;
import javax.swing.filechooser.FileView;

/**
 *
 * @author Ronaldo Totini
 */
public class ImageFileView extends FileView {
    
    
    
    public String getName(File f) {
        return null; //let the L&F FileView figure this out
    }

    @Override
    public String getDescription(File f) {
        return null; //let the L&F FileView figure this out
    }

    public Boolean isTraversable(File f) {
        return null; //let the L&F FileView figure this out
    }

    @Override
    public String getTypeDescription(File f) {
        String extension = FileChooserUtil.getExtension(f);
        String type = null;

        if (extension != null) {
            if (extension.equals(FileChooserUtil.jpeg) ||
                extension.equals(FileChooserUtil.jpg)) {
                type = "JPEG Image";
            } else if (extension.equals(FileChooserUtil.gif)){
                type = "GIF Image";
            } else if (extension.equals(FileChooserUtil.tiff) ||
                       extension.equals(FileChooserUtil.tif)) {
                type = "TIFF Image";
            } else if (extension.equals(FileChooserUtil.png)){
                type = "PNG Image";
            }
        }
        return type;
    }

   
}
