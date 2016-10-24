/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prototype.advancedprinter.event;

import br.com.prototype.advancedprinter.graphic.document.AbstractComponentPage;
import java.awt.Cursor;
import java.util.List;

/**
 *
 * @author Ronaldo Totini
 */
public class EventOut {
    
    
    private List<AbstractComponentPage> targetList;
    private EventTypeEnum eventTarget;
    private Cursor cursorOut;

    public List<AbstractComponentPage> getTargetList() {
        return targetList;
    }

    public void setTargetList(List<AbstractComponentPage> targetList) {
        this.targetList = targetList;
    }

    public EventTypeEnum getEventTarget() {
        return eventTarget;
    }

    public void setEventTarget(EventTypeEnum eventTarget) {
        this.eventTarget = eventTarget;
    }

    public Cursor getCursorOut() {
        return cursorOut;
    }

    public void setCursorOut(Cursor cursorOut) {
        this.cursorOut = cursorOut;
    }
    
    
}
