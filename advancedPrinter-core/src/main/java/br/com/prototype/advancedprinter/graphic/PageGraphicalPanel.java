/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prototype.advancedprinter.graphic;

import br.com.prototype.advancedprinter.action.DeleteSelectionAction;
import br.com.prototype.advancedprinter.event.EventHandler;
import br.com.prototype.advancedprinter.event.EventNotificationListener;
import br.com.prototype.advancedprinter.event.EventNotifier;
import br.com.prototype.advancedprinter.event.EventOut;
import br.com.prototype.advancedprinter.event.EventTypeEnum;
import br.com.prototype.advancedprinter.event.Source;
import br.com.prototype.advancedprinter.graphic.document.AbstractComponentPage;
import br.com.prototype.advancedprinter.graphic.document.ImageComponentPage;
import br.com.prototype.advancedprinter.graphic.render.ComponentPageRender;
import br.com.prototype.advancedprinter.graphic.render.RenderManager;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author Ronaldo Totini
 */
public final class PageGraphicalPanel extends JPanel
        implements MouseListener, EventNotificationListener, MouseMotionListener {

    private EventTypeEnum event = EventTypeEnum.NONE;
    private BufferedImage importedImage;
    private Source source;
    private int sectorClickedX = -1;
    private int sectorClickedY = -1;
    private int initDragX = 0;
    private int initDragY = 0;
    private final RenderManager render = new RenderManager();

    private List<AbstractComponentPage> listComponents;

    public List<AbstractComponentPage> getListComponents() {
        return listComponents;
    }

    public void setListComponents(List<AbstractComponentPage> listComponents) {
        this.listComponents = listComponents;
    }
    private List<AbstractComponentPage> undoList;

    public PageGraphicalPanel() {
        super();        
    }

    public void init() {
        InputMap in =this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap ac=this.getActionMap();
        in.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0),"delete" );
        
        ac.put("delete", new DeleteSelectionAction(this));
        addMouseListener(this);
        addMouseMotionListener(this);
        this.setFocusable(true);
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);

    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    private void doDrawing(Graphics g) {
        //formato a4
        //1344 altura 35.56
        //816 largura 21.59 cm          
        render.renderComponents(listComponents, g, sectorClickedX, sectorClickedY);
        g.dispose();
    }

    public EventTypeEnum getEvent() {
        return event;
    }

    public void setEvent(EventTypeEnum event) {
        this.event = event;
    }

    public BufferedImage getImportedImage() {
        return importedImage;
    }

    public void setImportedImage(BufferedImage importedImage) {
        this.importedImage = importedImage;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    private AbstractComponentPage checkMouseOver(Integer x, Integer y) {
        AbstractComponentPage componentOut = null;
        boolean hasSelected = false;

        if (listComponents != null) {
            for (AbstractComponentPage component : listComponents) {
                ImageComponentPage img = (ImageComponentPage) component;
                if (img.isSelected() && x >= img.getX() && x <= img.getX() + img.getWidth()
                        && y >= img.getY() && y <= img.getY() + img.getHeight()) {
                    componentOut = component;

                }
            }
        }
        if (!hasSelected) {
            setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        }

        return componentOut;

    }

    @Override
    public void mousePressed(MouseEvent e) {
        e.consume();
       
        initDragX = e.getX();
        initDragY = e.getY();

        if (event.equals(EventTypeEnum.NONE)) {
            return;
        }

        if (listComponents == null) {
            listComponents = new LinkedList<>();
        }

        if (event.equals(EventTypeEnum.IMPORT_IMAGE) && source != null) {

            ImageComponentPage imageComp = (ImageComponentPage) source;
            imageComp.setX(e.getX());
            imageComp.setY(e.getY());
            imageComp.setTipo(ImageComponentPage.class);
            listComponents.add(imageComp);
            repaint();
           

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        e.consume();
        if (event.equals(EventTypeEnum.NONE)||event.equals(EventTypeEnum.DELETE)) {
            sectorClickedX = e.getX();
            sectorClickedY = e.getY();
            //selecionar componente para drag n drop, resizing , exclusao e copia
            if (listComponents != null && !listComponents.isEmpty()) {
                repaint();
            }
        }
        if (event.equals(EventTypeEnum.IMPORT_IMAGE)) {
            event = EventTypeEnum.NONE;
            setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            this.source = null;
            EventNotifier.getInstance().reset();

        }

        if (event.equals(EventTypeEnum.DRAG_N_DROP) || EventTypeEnum.isBorderSelection(event)) {
            EventNotifier.getInstance().reset();
            initDragX = 0;
            initDragY = 0;
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public int eventTriggered(Source source, EventTypeEnum event) {
        this.event = event;
        
        if (event.equals(EventTypeEnum.IMPORT_IMAGE)) {            
            this.source = source;            
            setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        } else if (event.equals(EventTypeEnum.NONE)) {
            this.source = null;
            setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        } else if (event.equals(EventTypeEnum.DELETE)) {
            this.source = null;
            setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            repaint();
        }
        return 0;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        e.consume();
        AbstractComponentPage component;
        // checkMouseOver(e.getX(), e.getY());
        
       EventOut ev= EventHandler.checkMouseOver(listComponents,e.getX(), e.getY());

       setCursor(ev.getCursorOut());
       
       if(ev.getTargetList()!=null&&!ev.getTargetList().isEmpty()){
           
           if(ev.getEventTarget().equals(EventTypeEnum.SELECTION)){
               event = EventTypeEnum.DRAG_N_DROP;
           }else{
               event=ev.getEventTarget();
           }
            component=(AbstractComponentPage)ev.getTargetList().get(0);
            if (component != null) {
               ComponentPageRender pageRender = render.create(event);
               pageRender.render(component, e.getX(), e.getY(), initDragX, initDragY);
               sectorClickedX = e.getX();
               sectorClickedY = e.getY();
               initDragX = e.getX();
               initDragY = e.getY();
               repaint();
           }
       }
        //verificar se evento é para dragg ou resizing        
       /*if(ev.getEventTarget().equals(EventTypeEnum.SELECTION)
               &&!ev.getTargetList().isEmpty()){
            component=(AbstractComponentPage)ev.getTargetList().get(0);           
            event = EventTypeEnum.DRAG_N_DROP;
            ComponentPageRender pageRender=render.create(event);
            pageRender.render(component,e.getX(), e.getY(), initDragX, initDragY);
            component.setX(component.getX() + (e.getX() - initDragX));
            component.setY(component.getY() + (e.getY() - initDragY)); 
            sectorClickedX = e.getX();
            sectorClickedY = e.getY();
            initDragX = e.getX();
            initDragY = e.getY();
            repaint();
        }else if(ev.getEventTarget().equals(EventTypeEnum.BORDER_SELECTION_R)
               &&!ev.getTargetList().isEmpty()){
            component=(AbstractComponentPage)ev.getTargetList().get(0);           
            event = EventTypeEnum.BORDER_SELECTION_R;   
            ComponentPageRender pageRender=render.create(event);
            pageRender.render(component, e.getX(), e.getY(), initDragX, initDragY);
            // setCursor(new java.awt.Cursor(java.awt.Cursor.E_RESIZE_CURSOR));
            //se posiçao do cursor positivo entao soma se o tamanho da largura da imagem
            // caso contrario a mesma é retraida             
            if(e.getX()>initDragX){
             component.setWidth(component.getWidth()+(e.getX() - initDragX));             
            }else if(e.getX()<initDragX){
             component.setWidth(component.getWidth()-(initDragX-e.getX()));             
            }            
            sectorClickedX = e.getX();
            sectorClickedY = e.getY();
            initDragX = e.getX();
            initDragY = e.getY();
            repaint();
        }*/
              
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        e.consume();
       if (event.equals(EventTypeEnum.NONE)) {
            //verificar se eixo se localiza nas arestas 
            //criar evento para resizing  

           // AbstractComponentPage component ;
            
            EventOut ev= EventHandler.checkMouseOver(listComponents,e.getX(), e.getY());
            //= checkMouseOver(e.getX(), e.getY());
            setCursor(ev.getCursorOut());
            /*if(ev!=null&&ev.getEventTarget().equals(EventTypeEnum.SELECTION)
               &&!ev.getTargetList().isEmpty()){*/
            //if (component != null) {
               /* setCursor(ev.getCursorOut());
            }*/

        }
    }

   

}
