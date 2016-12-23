package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.*;
/**
 * Created by Stijn on 23-12-2016.
 */
public class GridPanel extends JPanel {

    int posx;
    int posy;

    public GridPanel() {
        super();
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                super.mousePressed(e);
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                super.mouseReleased(e);
//            }
//        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                posx = e.getX();
                posy = e.getY();

                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g)
    {
        //super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawOval(posx,posy,5,5);
    }
}
