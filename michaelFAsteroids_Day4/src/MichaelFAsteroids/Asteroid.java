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
    
    public Asteroid(){
        double h, a;
        h = Math.random();
        a = Math.random();
        xspeed =1;
        yspeed = 1;
        shape = new Polygon();
        shape.addPoint(30, 3);
        shape.addPoint(5, 35);
        shape.addPoint(-25,10);
        shape.addPoint(-17,-15);
        shape.addPoint(20, -35);
        drawShape = new Polygon();
        drawShape.addPoint(30, 3);
        drawShape.addPoint(-25, 10);
        drawShape.addPoint(5, 35);
        drawShape.addPoint(-17, -15);
        drawShape.addPoint(20, -35);
        ROTATION = 0.15;
        THRUST = 0.5;
        xposition = 450; 
        yposition = 300;
    
}
}
