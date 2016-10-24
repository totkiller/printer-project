/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prototype.advancedprinter.action;

import br.com.prototype.advancedprinter.event.EventNotifier;
import br.com.prototype.advancedprinter.event.EventTypeEnum;
import br.com.prototype.advancedprinter.graphic.PageGraphicalPanel;
import br.com.prototype.advancedprinter.graphic.document.AbstractComponentPage;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;


/**
 *
 * @author Ronaldo Totini
 */
public class DeleteSelectionAction extends AbstractAction {

    private  PageGraphicalPanel gPanel;

    public DeleteSelectionAction(PageGraphicalPanel gPanel) {

        this.gPanel = gPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        int i = 0;
        AbstractComponentPage removeComponent ;
        //boolean delete = false;
        List<AbstractComponentPage> list=gPanel.getListComponents();

        if(list!=null&&!list.isEmpty()){
       
        while (i < list.size()) {
            removeComponent = list.get(i);
            System.out.println(removeComponent.isSelected());
            if (removeComponent.isSelected()) {
              System.out.println("status remove "+list.remove(removeComponent));
             
                             
            }
            i++;
        }
        gPanel.setListComponents(list);
        gPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
       // if (delete) {
            // listComponents.remove(removeComponent);            
            EventNotifier.getInstance().setCurrentEvent(EventTypeEnum.DELETE);
            EventNotifier.getInstance().performNotification();
            
        //}
    }
    }

}
