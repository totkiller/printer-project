/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prototype.advancedprinter.graphic.document;

import br.com.prototype.advancedprinter.event.Source;
import java.awt.Graphics2D;

/**
 *
 * @author Ronaldo Totini
 */
public abstract class AbstractComponentPage implements Source, Cloneable {

    //id
    //class tipo
    private Class tipo;
    //positon x 
    private Integer x;
    //position y
    private Integer y;
    //height
    private Integer height;
    //width
    private Integer width;
    //z-index ?    
    //is selected by mouse clicking
    private boolean selected;

    @Override
    public Class getTipo() {
        return tipo;
    }

    @Override
    public void setTipo(Class tipo) {
        this.tipo = tipo;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    abstract void drawSelectionBorder(Graphics2D g);

    @Override
    public Object clone() throws CloneNotSupportedException {
        AbstractComponentPage component;

        component = (AbstractComponentPage) super.clone();
        component.setHeight(height);
        component.setWidth(width);
        component.setTipo(tipo);
        component.setSelected(selected);
        component.setX(x);
        component.setY(y);

        return component;
    }
}
