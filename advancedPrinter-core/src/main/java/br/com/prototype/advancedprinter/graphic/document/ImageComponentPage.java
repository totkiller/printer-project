/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prototype.advancedprinter.graphic.document;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author Ronaldo Totini
 */
public class ImageComponentPage extends AbstractComponentPage implements Cloneable{
 
    
    //buffered image?
    //?? 
    private BufferedImage buffer;
    
    

    public BufferedImage getBufferedImage() {
        return buffer;
    }

    public void setBufferedImage(BufferedImage buffer) {
        this.buffer = buffer;
    }

    @Override
    public void drawSelectionBorder(Graphics2D g) {
        
      if(!isSelected())//senao estiver selecionado nao exibe borda de seleçao
          return;
        
        
      float dashPhase = 0f;
      float dash[] = {10.0f,getWidth()/4};
     

       dashPhase += 20.0f;
       BasicStroke dashedStroke = new BasicStroke(
                                10.5f,
                                BasicStroke.CAP_BUTT,
                                BasicStroke.CAP_SQUARE,
                                10.5f, 
                                dash,
                                dashPhase
                                );       
      
       g.setStroke(dashedStroke);
       g.drawLine(getX(),getY(), getX()+getWidth(),getY());
       g.drawLine(getX()+getWidth(),getY(), getX()+getWidth(),getY()+getHeight());
       g.drawLine(getX(),getY(), getX(),getY()+getHeight());
       g.drawLine(getX(), getY()+getHeight(), getX()+getWidth(),getY()+getHeight());
      
           
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        ImageComponentPage component;

        component = (ImageComponentPage) super.clone();
        component.setBufferedImage(buffer);
        

        return component;
    }
    
}
