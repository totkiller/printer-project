/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prototype.advancedprinter.graphic.render;

import br.com.prototype.advancedprinter.event.EventTypeEnum;
import br.com.prototype.advancedprinter.graphic.document.AbstractComponentPage;
import br.com.prototype.advancedprinter.graphic.document.ImageComponentPage;
import br.com.prototype.advancedprinter.graphic.render.annotation.EventRenderType;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openide.util.Exceptions;

/**
 *
 * @author Ronaldo Totini
 */
public class RenderManager {

    public final EventTypeEnum renderComponents(List<AbstractComponentPage> listComponents,
            Graphics g, int sectorClickedX, int sectorClickedY) {
        int index = 0;
        int lastIndex = 0;
        EventTypeEnum selectionRendered = EventTypeEnum.NONE;
        List<Integer> selectedList = new ArrayList<>();
        Graphics2D g2 = (Graphics2D) g;

        if (listComponents != null && !listComponents.isEmpty()) {

            AbstractComponentPage component;
            while (index < listComponents.size()) {

                component = listComponents.get(index);
                if (component.getTipo().equals(ImageComponentPage.class)) {
                    ImageComponentPage img = (ImageComponentPage) component;

                    if (sectorClickedX >= img.getX() && sectorClickedX <= img.getX() + img.getWidth()
                            && sectorClickedY >= img.getY() && sectorClickedY <= img.getY() + img.getHeight()) {
                        img.setSelected(true);
                        selectionRendered = EventTypeEnum.SELECTION;
                        selectedList.add(index);
                        lastIndex = index;

                    } else {
                        img.setSelected(false);
                        // g2.drawImage(img.getBufferedImage(), img.getX(),
                        //       img.getY(),img.getWidth(),img.getHeight(),null);
                        renderImage(g2, img.getBufferedImage(), img.getX(), img.getY(), img.getWidth(), img.getHeight());
                    }
                }

                index++;
            }

            AbstractComponentPage swapComponent;

            for (Integer sel : selectedList) {

                if (sel < lastIndex) {

                    if (listComponents.get(sel).getTipo().equals(ImageComponentPage.class)) {
                        ImageComponentPage img = (ImageComponentPage) listComponents.get(sel);
                        //g2.drawImage(img.getBufferedImage(), img.getX(),
                        //      img.getY(),img.getWidth(),img.getHeight(),null);
                        renderImage(g2, img.getBufferedImage(), img.getX(), img.getY(), img.getWidth(), img.getHeight());
                    }
                    listComponents.get(sel).setSelected(false);
                } else if (sel == lastIndex) {
                    if (listComponents.get(sel).getTipo().equals(ImageComponentPage.class)) {

                        ImageComponentPage img = (ImageComponentPage) listComponents.get(sel);
                        // g2.drawImage(img.getBufferedImage(), img.getX(), img.getY(), null);
                        renderImage(g2, img.getBufferedImage(), img.getX(), img.getY(), img.getWidth(), img.getHeight());
                        img.setSelected(true);
                        img.drawSelectionBorder((Graphics2D) g);

                        if (listComponents.size() > 1 && lastIndex < listComponents.size() - 1) {
                            swapComponent = (AbstractComponentPage) listComponents.get(listComponents.size() - 1);
                            ImageComponentPage imgSwp = (ImageComponentPage) listComponents.get(sel);
                            imgSwp.setSelected(true);
                            swapComponent.setSelected(false);
                            listComponents.set(sel, swapComponent);
                            listComponents.set(listComponents.size() - 1, imgSwp);
                        }

                    }
                }

            }
            selectedList.clear();

        }
        return selectionRendered;

    }

    private void renderImage(Graphics2D g2, BufferedImage img, int x, int y, int w, int h) {
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img, x, y, w, h, null);
    }

    public final ComponentPageRender create(EventTypeEnum event) {
        EventTypeEnum res;
        Map<Annotation, Class> map = new HashMap();     
        map.put(DragNDropEventRender.class.getAnnotation(EventRenderType.class), DragNDropEventRender.class);
        map.put(BorderRightEventRender.class.getAnnotation(EventRenderType.class), BorderRightEventRender.class);
        map.put(BorderLeftEventRender.class.getAnnotation(EventRenderType.class), BorderLeftEventRender.class);
        map.put(BorderSouthEventRender.class.getAnnotation(EventRenderType.class), BorderSouthEventRender.class);
        map.put(BorderNorthEventRender.class.getAnnotation(EventRenderType.class), BorderNorthEventRender.class);
        map.put(BorderNorthEastEventRender.class.getAnnotation(EventRenderType.class), BorderNorthEastEventRender.class);
        map.put(BorderNorthWestEventRender.class.getAnnotation(EventRenderType.class), BorderNorthWestEventRender.class);
        map.put(BorderSouthEastEventRender.class.getAnnotation(EventRenderType.class), BorderSouthEastEventRender.class);
        map.put(BorderSouthWestEventRender.class.getAnnotation(EventRenderType.class), BorderSouthWestEventRender.class);
      
        for (Annotation a : map.keySet()) {
            try {
                Class<? extends Annotation> type = a.annotationType();
                for (Method method : type.getDeclaredMethods()) {
                    res = (EventTypeEnum) method.invoke(a, (Object[]) null);

                    if (event.equals(res)) {
                        return (ComponentPageRender) map.get(a).newInstance();
                    }
                }

            } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException ex) {
                Exceptions.printStackTrace(ex);
            }
        }

        return null;
    }

}
