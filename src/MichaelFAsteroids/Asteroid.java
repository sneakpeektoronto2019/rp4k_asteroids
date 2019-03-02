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
public class Asteroid extends VectorSprite {
    
    int size;
    public Asteroid(){
        size = 3;
        initializeAsteroid();
        
    }
    
    public Asteroid(double x, double y,int s){
        size = s;
        initializeAsteroid();
        
        xposition = x;
        yposition = y;
    }
    
    public void initializeAsteroid(){
       
        shape = new Polygon();
        shape.addPoint(15*size, 6*size);
        shape.addPoint(7*size, 17*size);
        shape.addPoint(-13*size,8*size);
        shape.addPoint(-11*size,-10*size);
        shape.addPoint(12*size, -16*size);
        drawShape = new Polygon();
        drawShape.addPoint(15*size, 6*size);
        drawShape.addPoint(7*size, 17*size);
        drawShape.addPoint(-13*size, 8*size);
        drawShape.addPoint(-11*size, -10*size);
        drawShape.addPoint(12*size, -16*size);
        ROTATION = 0.15;
        THRUST = 0.5;
        xposition = 450; 
        yposition = 300;
         double h, a;
        h = Math.random() + 1;
        a = Math.random()* 2*Math.PI    ;
        xspeed = Math.cos(a)*h;
        yspeed = Math.sin(a)*h;
        h = Math.random()*400 + 100;
        a = Math.random()* 2*Math.PI    ;
        xposition = Math.cos(a)*h + 450;
        yposition = Math.sin(a)*h + 300;
        active = true;
    
    }
    
    public void updatePosition(){
        angle += ROTATION;
        super.updatePosition();
    }

}
        
        
