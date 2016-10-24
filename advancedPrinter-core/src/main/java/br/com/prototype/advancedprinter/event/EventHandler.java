/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prototype.advancedprinter.event;

import br.com.prototype.advancedprinter.graphic.document.AbstractComponentPage;
import br.com.prototype.advancedprinter.graphic.document.ImageComponentPage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ronaldo Totini
 */
public class EventHandler {

    public static final EventOut checkMouseOver(List<AbstractComponentPage> listComponents, Integer x, Integer y) {
        AbstractComponentPage componentOut = null;
        List<AbstractComponentPage> targetList;
        EventOut evOut = new EventOut();;
        EventTypeEnum evTypeOut = EventTypeEnum.NONE;
        evOut.setCursorOut(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        if (listComponents != null) {

            for (AbstractComponentPage component : listComponents) {
                ImageComponentPage img = (ImageComponentPage) component;
                evTypeOut = checkEventType(x, y, evOut, img);
                if (evTypeOut.equals(EventTypeEnum.SELECTION)
                        || EventTypeEnum.isBorderSelection(evTypeOut)) {
                    componentOut = component;
                }
            }
            //single selection
            if (componentOut != null) {
                targetList = new ArrayList<>();
                targetList.add(componentOut);
                evOut.setTargetList(targetList);
            }
            evOut.setEventTarget(evTypeOut);
        }

        return evOut;

    }

    private static EventTypeEnum checkEventType(int x, int y, EventOut out, ImageComponentPage img) {
       
        //borda lateral esquerda
        if (img.isSelected() && x >= img.getX()  && x <= img.getX()+20
                && y > img.getY()+20
                && y < img.getY() + img.getHeight()-20) {
            out.setCursorOut(new java.awt.Cursor(java.awt.Cursor.W_RESIZE_CURSOR));
            return EventTypeEnum.BORDER_SELECTION_L;
        }else  if (img.isSelected() && x >= img.getX() + img.getWidth()-20
                && x <= img.getX() + img.getWidth()  && y > img.getY()+20
                && y <= img.getY() + img.getHeight()-20) {
            out.setCursorOut(new java.awt.Cursor(java.awt.Cursor.E_RESIZE_CURSOR));
            return EventTypeEnum.BORDER_SELECTION_R;
        }else  if (img.isSelected() && y >= img.getY() + img.getHeight()-10
                && y <= img.getY() + img.getHeight() 
                && x > img.getX()+21 && x < img.getX() + img.getWidth()-21) {
            out.setCursorOut(new java.awt.Cursor(java.awt.Cursor.S_RESIZE_CURSOR));
            return EventTypeEnum.BORDER_SELECTION_S;
        }else  if (img.isSelected() && y >= img.getY()
                && y <= img.getY()+20 
                && x > img.getX()+21 && x < img.getX() + img.getWidth()-21) {
            out.setCursorOut(new java.awt.Cursor(java.awt.Cursor.N_RESIZE_CURSOR));
            return EventTypeEnum.BORDER_SELECTION_N;
        }else  if (img.isSelected() && y >= img.getY() && y <=img.getY()+19
                && x >= img.getX()-20&&x <=img.getX()+20) {
            out.setCursorOut(new java.awt.Cursor(java.awt.Cursor.NW_RESIZE_CURSOR));
            return EventTypeEnum.BORDER_SELECTION_NW;
        }else  if (img.isSelected() && y >= img.getY()+ img.getHeight()-20 
                && y <=img.getY()+ img.getHeight()+20
                && x >= img.getX()-20&&x <=img.getX()+20) {
            out.setCursorOut(new java.awt.Cursor(java.awt.Cursor.SW_RESIZE_CURSOR));
            return EventTypeEnum.BORDER_SELECTION_SW;
        }else  if (img.isSelected() && y >= img.getY() && y <=img.getY()+20
                && x >= img.getX()+img.getWidth()-20&&x <=img.getX()+img.getWidth()+20) {
            out.setCursorOut(new java.awt.Cursor(java.awt.Cursor.NE_RESIZE_CURSOR));
            return EventTypeEnum.BORDER_SELECTION_NE;
        }else  if (img.isSelected() && y >= img.getY()+img.getHeight()-20 && y <=img.getY()+img.getHeight()+5
                && x >= img.getX()+img.getWidth()-20&&x <=img.getX()+img.getWidth()) {
            out.setCursorOut(new java.awt.Cursor(java.awt.Cursor.SE_RESIZE_CURSOR));
            return EventTypeEnum.BORDER_SELECTION_SE;
        }else  if (img.isSelected() && x > img.getX() && x < img.getX() + img.getWidth()
                && y > img.getY() && y < img.getY() + img.getHeight()) {
            out.setCursorOut(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
            return EventTypeEnum.SELECTION;
        }else{
            out.setCursorOut(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
        return EventTypeEnum.NONE;
    }

}
