/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MichaelFAsteroids;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 * |
 *
 * @author Tyler
 */
public class MichaelFAsteroids extends Applet implements KeyListener, ActionListener {

    private int width = 900, height = 600;
    private int x1 = 20, x2 = 200, y1 = 50, y2 = 300;
    private int[] x, y;
    Image offscreen;
    Graphics offg;
    Spacecraft ship;
    Timer timer;
    boolean upKey, leftKey, rightKey, spacekey;
    ArrayList<Asteroid> asteroidList;
    ArrayList<Bullet> BulletList;
    AudioClip shipHit;
    int score;
    AudioClip shipShot;
    ArrayList<Debris> DebrisList;
    boolean Bossspawned;
    

    public void init() {
        shipHit = getAudioClip(getCodeBase(),"explosion.wav");
        shipShot = getAudioClip(getCodeBase(),"alienshot.wav");
        this.setSize(width, height);
        this.addKeyListener(this);
        timer = new Timer(20, this);
        offscreen = createImage(this.getWidth(), this.getHeight());
        offg = offscreen.getGraphics();

        //initialize variables
        ship = new Spacecraft();
        asteroidList = new ArrayList();
        BulletList = new ArrayList();
        DebrisList = new ArrayList();
        for (int i = 0; i < 6; i++) {
            asteroidList.add(new Asteroid());
            

        }
        score = 0;
        
        start();
    }

    public boolean isrespawnsafe() {
        int x, y, h;
        for (int i = 0; i < asteroidList.size(); i++) {
            x = (int) asteroidList.get(i).xposition - 450;
            y = (int) asteroidList.get(i).yposition - 300;
            h = (int) Math.sqrt(x * x + y * y);
            if (h < 100) {
                return false;
            }
        }
        return true;
    }
    

    public void paint(Graphics g) {
        //ship.updatePosition();
        offg.setColor(Color.black);
        offg.fillRect(0, 0, width, height);
        offg.setColor(Color.red);
        offg.drawString("SCORE: " + score ,200,300);
        
        
        offg.setColor(Color.blue);
        if (ship.active == true) {
            ship.paint(offg);
        }
        Font myFont = new Font("Courier New",1,50);
        if(asteroidList.isEmpty()) {
            offg.setFont(myFont);
        offg.drawString("You Win", 400,400);
        
    }
        offg.setColor(Color.orange);

        for (int i = 0; i < asteroidList.size(); i++) {
            asteroidList.get(i).paint(offg);
        }

        for (int i = 0; i < BulletList.size(); i++) {
            BulletList.get(i).paint(offg);
        }
         for (int i = 0; i < DebrisList.size(); i++) {
            DebrisList.get(i).paint(offg);
        }

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
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightKey = true;

        }
        //left under here
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            leftKey = true;
        }
        //to here
        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            upKey = true;
        }
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            spacekey = true;
        }
        //left under here

        //everything goes above this comment
        repaint();
    }

    public void keycheck() {
        if (upKey) {
            ship.accelerate();
        }
        if (leftKey) {
            ship.rotateLeft();

        }
        if (rightKey) {
            ship.rotateRight();

        }
        if (spacekey) {
            shoot();
        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightKey = false;

        }
        //left under here
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            leftKey = false;
        }
        //to here
        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            upKey = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            spacekey = false;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        keycheck();
        ship.updatePosition();
        checkCollision();
        checkasteroiddestruction();
        respawnShip();
        checkbosslevel();
        for (int i = 0; i < asteroidList.size(); i++) {
            asteroidList.get(i).updatePosition();
           
        }
        for (int i = 0; i < BulletList.size(); i++) {
            BulletList.get(i).updatePosition();
            if (BulletList.get(i).counter == 50 || BulletList.get(i).active == false){
            BulletList.remove(i);
            }
        }
        for (int i = 0; i < DebrisList.size(); i++) {
                    DebrisList.get(i).updatePosition();
                    if (DebrisList.get(i).counter == 50 || DebrisList.get(i).active == false){
                        DebrisList.remove(i);
                    }
        }
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public boolean collision(VectorSprite thing1, VectorSprite thing2) {
        int x, y;
        for (int i = 0; i < ship.drawShape.npoints; i++) {
            x = thing1.drawShape.xpoints[i];
            y = thing1.drawShape.ypoints[i];
            if (thing2.drawShape.contains(x, y)) {
                return true;
            }
        }
        for (int i = 0; i < ship.drawShape.npoints; i++) {
            x = thing2.drawShape.xpoints[i];
            y = thing2.drawShape.ypoints[i];
            if (thing1.drawShape.contains(x, y)) {
                return true;
            }
        }
        return false;

    }

    public void checkCollision() {
        for (int i = 0; i < asteroidList.size(); i++) {
            if (collision(ship, asteroidList.get(i)) == true) {
                ship.hit();
                
            }
            
            for(int b =0; b < BulletList.size(); b++) { 
                if ( collision(BulletList.get(b), asteroidList.get(i))){
                    BulletList.get(b).active = false;
                    asteroidList.get(i).active = false;
                    shipHit.play();
                    score += 1;
                    for (int Z = 0;Z<15;Z++){
                        DebrisList.add(new Debris(asteroidList.get(i).xposition,asteroidList.get(i).yposition));
                    }
                    
                }
              
            }
        }

    }

    public void respawnShip() {
        if (ship.active == false && ship.counter > 50 && isrespawnsafe()) {
            ship.reset();
        }
    }
    
    public void shoot() {
        if(ship.counter >5 && ship.active == true){
            BulletList.add(new Bullet(ship.xposition, ship.yposition, ship.angle));
            ship.counter = 0;
        }
        shipShot.play();
              
    }
    public void checkbosslevel(){
        if (score > 2 && Bossspawned == false)
            asteroidList.clear();
                    if(asteroidList.size() < 1){
                    asteroidList.add(new Asteroid(10,
                            10, 10));
                    Bossspawned = true;
    }

    }
    public void checkasteroiddestruction() {
        for(int i = 0; i < asteroidList.size();i++) {
            asteroidList.get(i).updatePosition();
            if( asteroidList.get(i).active == false){
                if(asteroidList.get(i).size > 1){
                    asteroidList.add(new Asteroid(asteroidList.get(i).xposition,
                            asteroidList.get(i).yposition, asteroidList.get(i).size-1));
                    asteroidList.add(new Asteroid(asteroidList.get(i).xposition,
                            asteroidList.get(i).yposition, asteroidList.get(i).size-1));
                
                
                }
            asteroidList.remove(i);
            }
        }
    }
}


