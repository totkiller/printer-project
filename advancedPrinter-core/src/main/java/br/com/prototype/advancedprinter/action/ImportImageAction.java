/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prototype.advancedprinter.action;

import br.com.prototype.advancedprinter.event.EventNotifier;
import br.com.prototype.advancedprinter.event.EventTypeEnum;
import br.com.prototype.advancedprinter.graphic.document.ImageComponentPage;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;


@ActionID(
        category = "Tools",
        id = "br.com.prototype.advancedprinter.action.ImportImageAction"
)
@ActionRegistration(
        iconBase = "br/com/prototype/advancedprinter/action/imageImport.png",
        displayName = "#CTL_ImportImageAction"
)
@ActionReference(path = "Toolbars/MainToolBar", position = 3333)
@Messages("CTL_ImportImageAction=Importar Imagem")
public final class ImportImageAction implements ActionListener {

    //Create a file chooser
    private final JFileChooser fc = new JFileChooser();
    private ImageComponentPage image = null;

    public ImportImageAction() {

    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        
        BufferedImage img;
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(new ImageFilter());
        fc.setFileView(new ImageFileView());
        int returnVal = fc.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            
            try {
                img = ImageIO.read(file);
                image = new ImageComponentPage();
                image.setTipo(ImageComponentPage.class);
                image.setX(0);
                image.setY(0);
                image.setWidth(img.getWidth());
                image.setHeight(img.getHeight());
                image.setBufferedImage(img);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }

            EventNotifier.getInstance().setSource(image);
            EventNotifier.getInstance().setCurrentEvent(EventTypeEnum.IMPORT_IMAGE);
            EventNotifier.getInstance().performNotification();

        }

    }

}
