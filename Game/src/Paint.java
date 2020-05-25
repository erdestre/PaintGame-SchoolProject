import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JComponent;

public class Paint extends JComponent implements MouseListener{
    private Image image;
    private Graphics2D g2d;
    private int x,y,ox,oy;

    public Paint(){
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                ox=e.getX();
                oy=.e.getY();

                if (g2 !=null){
                    g2d.drawLine(ox,oy,x,y);
                    repaint();
                    ox=x;
                    oy=y;
                }
            }
        });
    }
    protected void paintComponent(Graphics g){
        if (image == null){
            image = createImage(getSize().width,getSize().height);
            g2d = (Graphics2D) image.getGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        g.drawImage(image,0,0,null);
    }
public void clear(){
    g2d.setPaint();
}
}