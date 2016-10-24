/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prototype.advancedprinter.event;

/**
 *
 * @author Ronaldo Totini
 */
public enum EventTypeEnum {
    
    NONE, IMPORT_IMAGE, INSERT_TEXTBOX, INSERT_VAR , BAR_CODE,
    QR_CODE , DRAG_N_DROP ,RESIZE,SELECTION,
    BORDER_SELECTION_L,BORDER_SELECTION_R,BORDER_SELECTION_S,BORDER_SELECTION_N,
    BORDER_SELECTION_NE,BORDER_SELECTION_SE,BORDER_SELECTION_NW,BORDER_SELECTION_SW,
    DELETE;
    
    public static boolean isBorderSelection(EventTypeEnum event){
        
        boolean isBorderSideSelected=event.equals(EventTypeEnum.BORDER_SELECTION_L)||
               event.equals(EventTypeEnum.BORDER_SELECTION_N)||
                event.equals(EventTypeEnum.BORDER_SELECTION_S)||
                event.equals(EventTypeEnum.BORDER_SELECTION_R);
        boolean isBorderDiagonalSelected=event.equals(EventTypeEnum.BORDER_SELECTION_NE)||
               event.equals(EventTypeEnum.BORDER_SELECTION_NW)||
                event.equals(EventTypeEnum.BORDER_SELECTION_SE)||
                event.equals(EventTypeEnum.BORDER_SELECTION_SW);
        
        return isBorderSideSelected||isBorderDiagonalSelected;
        
    }
    
}
