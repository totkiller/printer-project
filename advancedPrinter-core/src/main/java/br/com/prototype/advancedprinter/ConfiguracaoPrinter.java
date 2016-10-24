/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prototype.advancedprinter;


import br.com.prototype.advancedprinter.event.EventNotifier;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Tools",
        id = "br.com.prototype.advancedprinter.ConfiguracaoPrinter"
)
@ActionRegistration(
        displayName = "#CTL_ConfiguracaoPrinter"
)
@ActionReference(path = "Menu/Configuração", position = 3333)
@Messages("CTL_ConfiguracaoPrinter=Nova Página")
public final class ConfiguracaoPrinter implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
                      
        NovaPaginaTopComponent component=new NovaPaginaTopComponent();
        component.open();      
        EventNotifier.getInstance().addEventNotificationListener(component.getTargetPanel());
        EventNotifier.getInstance().reset();    
        
    }
}
