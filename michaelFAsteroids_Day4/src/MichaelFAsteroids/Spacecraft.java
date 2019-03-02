/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MichaelFAsteroids;

import java.awt.Polygon;

/**
 *
 * @author RealProgramming4Kids
 */
public class Spacecraft extends VectorSprite {
    public Spacecraft() {
        shape = new Polygon();
        shape.addPoint(25, 0);
        shape.addPoint(-10, 15);
        shape.addPoint(-10,-15);
        drawShape = new Polygon();
        drawShape.addPoint(20, 0);
        drawShape.addPoint(-10, 15);
        drawShape.addPoint(-10, -15);
        ROTATION = 0.1;
        THRUST = 0.8;
        xposition = 450; 
        yposition = 300;
    }
    
    
    public void accelerate(){
        xspeed += Math.cos(angle)* THRUST;
        yspeed += Math.sin(angle)*THRUST;
    
    
    
    }
    public void rotateLeft(){
        angle -= ROTATION ;
        
    }
     public void rotateRight(){
        angle += ROTATION;
         
         
     }
    
     




}
            
            
    
    
