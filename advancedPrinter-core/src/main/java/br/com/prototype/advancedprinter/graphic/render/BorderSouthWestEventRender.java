/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prototype.advancedprinter.graphic.render;

import br.com.prototype.advancedprinter.event.EventTypeEnum;
import br.com.prototype.advancedprinter.graphic.document.AbstractComponentPage;
import br.com.prototype.advancedprinter.graphic.render.annotation.EventRenderType;

/**
 *
 * @author Ronaldo Totini Todos os direitos reservados ao autor
 */
@EventRenderType(event = EventTypeEnum.BORDER_SELECTION_SW)
public class BorderSouthWestEventRender implements ComponentPageRender {

    @Override
    public void render(AbstractComponentPage component, int x, int y, int initX, int initY) {

        if (x > initX) {
            component.setWidth(component.getWidth() + (initX - x));
            component.setX(component.getX() - (initX - x));
        }

        if (x < initX) {
            component.setWidth(component.getWidth() - (x - initX));
            component.setX(component.getX() + (x - initX));
        }

       if (y > initY) {
            component.setHeight(component.getHeight()+ (y - initY));
        } 
       
       if (y < initY) {
            component.setHeight(component.getHeight() - (initY - y));
        }
    }

}
