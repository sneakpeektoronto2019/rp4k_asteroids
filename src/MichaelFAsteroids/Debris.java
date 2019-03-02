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
public class Debris extends VectorSprite {
     public Debris (double x,double y){
   shape = new Polygon();
        shape.addPoint(0,0);
        shape.addPoint(0,0);
        shape.addPoint(0,0);
        shape.addPoint(0,0);
         drawShape = new Polygon();
        drawShape.addPoint(0, 0);
        drawShape.addPoint(0, 0);
        drawShape.addPoint(0, 0);
        
        xposition = x;
                yposition = y;
                
        
         double a;
         a = Math.random() *2*Math.PI;
        
        a = Math.random()* 2*Math.PI    ;
        xspeed = Math.cos(a)*a;
        yspeed = Math.sin(a)*a;
}
    
  
}
