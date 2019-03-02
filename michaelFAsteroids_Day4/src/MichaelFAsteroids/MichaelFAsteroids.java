/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MichaelFAsteroids;


import java.applet.Applet;
import java.awt.Color; 
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.Timer;


/**|
 *
 * @author Tyler
 */
public class MichaelFAsteroids extends Applet implements KeyListener, ActionListener {
    
    private int width = 900, height = 600;
    private int x1 = 20, x2 = 200, y1 = 50, y2 = 300;
    private int[] x,y;
    Image offscreen;
    Graphics offg;
    Spacecraft ship;
    Asteroid rock;
    Timer timer;
    boolean upKey, leftKey, rightKey;
    
    public void init(){
        this.setSize(width, height);
        this.addKeyListener(this);
        timer = new Timer(20,this);
        offscreen = createImage(this.getWidth(),this.getHeight());
        offg = offscreen.getGraphics();
        
        //initialize variables

        ship = new Spacecraft();
        rock = new Asteroid();
        
        //start timer
        start();
    }
    
    
    
    public void paint(Graphics g) {
        //ship.updatePosition();
        offg.setColor(Color.black);
        offg.fillRect(0, 0, width, height);
        offg.setColor(Color.green);
        ship.paint(offg);
        rock.paint(offg);
        
        g.drawImage(offscreen, 0, 0, this);
        repaint();
        
    }
    
    public void update(Graphics g) {
        paint(g);
    }
    
    //key methods
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        //copy below
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT ) {
            rightKey = true;
            
        }
        //left under here
        if (ke.getKeyCode() == KeyEvent.VK_LEFT ) {
            leftKey = true;
        }
        //to here
        if (ke.getKeyCode() == KeyEvent.VK_UP ) {
             upKey = true;
        }
        //left under here
        
        
        
        //everything goes above this comment
        repaint();
    }
    public void keycheck(){
        if (upKey){
            ship.accelerate();
        }
        if (leftKey) {
            ship.rotateLeft();
      
        }
        if (rightKey) {
           ship.rotateRight();
                    
        }
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
         if (ke.getKeyCode() == KeyEvent.VK_RIGHT ) {
            rightKey = false ;
            
        }
        //left under here
        if (ke.getKeyCode() == KeyEvent.VK_LEFT ) {
            leftKey = false;
        }
        //to here
        if (ke.getKeyCode() == KeyEvent.VK_UP ) {
             upKey = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        keycheck();
        ship.updatePosition();
        rock.updatePosition();
        
        
    }

    public void start()
    {
        timer.start();
    }
    
    public void stop()
    {
        timer.stop();
    }
    
}
