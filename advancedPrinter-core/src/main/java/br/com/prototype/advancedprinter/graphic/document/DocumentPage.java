/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prototype.advancedprinter.graphic.document;

import java.util.List;

/**
 *
 * @author Ronaldo Totini
 */
public class DocumentPage {
    
    
    //formato da pagina
    //list de componentes de pagina 
   private List<AbstractComponentPage> listComponents;
   
   

    public List<AbstractComponentPage> getListComponents() {
        return listComponents;
    }

    public void setListComponents(List<AbstractComponentPage> listComponents) {
        this.listComponents = listComponents;
    }
    
    
}
