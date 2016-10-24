/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prototype.advancedprinter.graphic.render;

import br.com.prototype.advancedprinter.graphic.document.AbstractComponentPage;

/**
 *
 * @author Ronaldo Totini
 */
public interface ComponentPageRender {
    
    /**
     * 
     * @param component 
     */
    public void render(AbstractComponentPage component, int x,int y,int initX, int initY);
    
}
