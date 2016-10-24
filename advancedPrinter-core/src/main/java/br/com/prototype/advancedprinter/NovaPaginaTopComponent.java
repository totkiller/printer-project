    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prototype.advancedprinter;

import br.com.prototype.advancedprinter.event.EventNotifier;
import br.com.prototype.advancedprinter.graphic.PageGraphicalPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Line2D;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;


/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//br.com.prototype.advancedprinter//NovaPagina//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "NovaPaginaTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "output", openAtStartup = false)
@ActionID(category = "Window", id = "br.com.prototype.advancedprinter.NovaPaginaTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_NovaPaginaAction",
        preferredID = "NovaPaginaTopComponent"
)
@Messages({
    "CTL_NovaPaginaAction=NovaPagina",
    "CTL_NovaPaginaTopComponent=Nova Página",
    "HINT_NovaPaginaTopComponent=Nova Página"
})
public final class NovaPaginaTopComponent extends TopComponent {

    private final PageGraphicalPanel pagePanel;
    private JLabel columnheader;
    private JLabel rowheader;
    private final JScrollPane jsp;

//    private final Double centimeter=37.795275591;
    private final Double milimiter = 3.7795275591;

    public NovaPaginaTopComponent() {
        initComponents();
        setName(Bundle.CTL_NovaPaginaTopComponent());
        setToolTipText(Bundle.HINT_NovaPaginaTopComponent());
        pagePanel = new PageGraphicalPanel();
        EventNotifier.getInstance().addEventNotificationListener(pagePanel);
        Dimension screenSize =  Toolkit.getDefaultToolkit().getScreenSize();

        //793.700787402 largura,altura 1122.519685039
        Dimension dimPage=new Dimension();
        dimPage.setSize(screenSize.getWidth(), 1500.0);
        pagePanel.setLocation(30, 30);
        pagePanel.setLayout(new java.awt.BorderLayout());
        pagePanel.setBackground(Color.white);
        pagePanel.setPreferredSize(dimPage);
        pagePanel.setMaximumSize(dimPage);
        pagePanel.setMinimumSize(dimPage);

        pagePanel.setAutoscrolls(true);
        pagePanel.init();
        jsp = new JScrollPane(pagePanel);
        
        Dimension dimScroll=new Dimension();
        dimScroll.setSize(screenSize.getWidth()-100.0, screenSize.getHeight()-200.0);
        jsp.setVisible(true);
        jsp.setPreferredSize(dimScroll);
        jsp.setMaximumSize(dimScroll);
        jsp.setMinimumSize(dimScroll);
        jsp.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setPanelRuler();
        jsp.setRowHeaderView(rowheader);
        jsp.setColumnHeaderView(columnheader);
        jsp.setCorner(JScrollPane.UPPER_LEFT_CORNER, new JLabel("cm"));
        editorMainPanel.setPreferredSize(screenSize);
        editorMainPanel.setMaximumSize(screenSize);
        editorMainPanel.setMinimumSize(screenSize);
        
        editorMainPanel.add(jsp);
        // setPreferredSize(new Dimension(795,1130));
        // setMinimumSize(new Dimension(800,1300));
        // add(jsp);

    }

    private void setPanelRuler() {

        rowheader = new JLabel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Rectangle rect = g.getClipBounds();
                Graphics2D g2d = (Graphics2D) g;
                int intCent = 10;
                int intMil = 0;
                for (double i = milimiter; i < pagePanel.getHeight(); i += milimiter) {
                    intMil++;

                    if (intCent == intMil) {
                        g2d.draw(new Line2D.Double(0, i, 4, i));
                        g2d.drawString("" + intCent / 10, 6, Float.parseFloat(String.valueOf(i)));
                        intCent = intCent + 10;
                    } else {
                        g2d.draw(new Line2D.Double(0, i, 2, i));
                    }
                }
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(25, (int) pagePanel.getPreferredSize()
                        .getHeight());
            }
        };

        columnheader = new JLabel() {

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Rectangle r = g.getClipBounds();
                Graphics2D g2d = (Graphics2D) g;
                int intCent = 10;
                int intMil = 0;
                for (double i = milimiter; i < r.width; i += milimiter) {
                    intMil++;
                    if (intCent == intMil) {
                        g2d.draw(new Line2D.Double(i, 0, i, 4));
                        g2d.drawString("" + intCent / 10, Float.parseFloat(String.valueOf(i)), 16);
                        intCent = intCent + 10;
                    } else {
                        g2d.draw(new Line2D.Double(i, 0, i, 2));
                    }
                }
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension((int) pagePanel.getPreferredSize().getWidth(),
                        25);
            }
        };

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        editorMainPanel = new javax.swing.JPanel();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setFocusable(true);
        setMaximumSize(new java.awt.Dimension(794, 1350));
        setMinimumSize(new java.awt.Dimension(794, 1350));
        setName(""); // NOI18N
        setLayout(new java.awt.BorderLayout());

        editorMainPanel.setBackground(new java.awt.Color(204, 204, 204));
        editorMainPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(51, 51, 0)));
        editorMainPanel.setAutoscrolls(true);
        editorMainPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        editorMainPanel.setPreferredSize(new java.awt.Dimension(500, 1000));
        add(editorMainPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel editorMainPanel;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {

    }

    @Override
    public void componentShowing() {
        

    }

    @Override
    public void componentClosed() {
       EventNotifier.getInstance().removeEventNotificationListener(getTargetPanel());
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    public PageGraphicalPanel getTargetPanel() {
        return pagePanel;
    }

   

}
