import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JComponent;

public class Drawing extends JComponent {

    // Image in which we're going to draw
    private Image image;
    // Graphics2D object ==> used to draw on
    private Graphics2D g2;
    // Mouse coordinates
    public int X, Y, oX, oY;
    public Color Dcolor;
    public int mode = 0;


    public Drawing() {
        setDoubleBuffered(false);
//        g2.setStroke(new BasicStroke(5));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                	int fx = Math.min(X, oX);
                	int fy = Math.min(Y, oY);
                	int fw = Math.abs(X- oX);
                	int fh = Math.abs(Y- oY);
                
                if (g2 != null && mode==2) {

                    g2.setPaint(Dcolor);
                    g2.drawLine(X, Y, oX, oY);
                    repaint();

                }
                if (g2 != null && mode==3) {
                	
                    g2.setPaint(Dcolor);
                    g2.drawRect(fx, fy, fw, fh);

                    repaint();

                }
                if (g2 != null && mode==4) {

                    g2.setPaint(Dcolor);
                    g2.drawOval(fx, fy, fw, fh);


                    repaint();

                }

            }
        });
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                oX = e.getX();
                oY = e.getY();
                if (mode != 5 && gameScreen.permission == true) {
                gameScreen.setCounter();
                }

            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                // coord x,y when drag mouse
                X = e.getX();
                Y = e.getY();

                if (g2 != null && mode==1) {
                    // draw line if g2 context not null
                    g2.setPaint(Dcolor);
                    g2.drawLine(X, Y, oX, oY);
                    send();
                    
                    // refresh draw area to repaint
                    repaint();
                    // store current coords x,y as olds x,y
                    oX = X;
                    oY = Y;
                }


            }
        });
    }

    protected void paintComponent(Graphics g) {
        if (image == null) {
            // image to draw null ==> we create
            image = createImage(getSize().width, getSize().height);
            g2 = (Graphics2D) image.getGraphics();
            // enable antialiasing
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // clear draw area
            clear();
        }

        g.drawImage(image, 0, 0, null);
    }

    // now we create exposed methods
    public void clear() {
        g2.setPaint(Color.white);
        // draw white on entire draw area to clear
        g2.fillRect(0, 0, getSize().width, getSize().height);
        g2.setPaint(Color.black);
        repaint();
    }
    public void send()
    {
        if (gameScreen.permission == true && Server.playerjoined==true)
        Server.sendpaintinfo(mode,X,Y,oX,oY);
    }





}