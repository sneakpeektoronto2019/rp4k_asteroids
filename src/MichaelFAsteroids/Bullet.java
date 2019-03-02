/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MichaelFAsteroids;
import java.awt.Polygon;
/*
 * @author RealProgramming4Kids
 */
public class Bullet extends VectorSprite {
    public Bullet(double x,double y,double a){
        shape = new Polygon();
        shape.addPoint(0,0);
        shape.addPoint(0,0);
        shape.addPoint(0,0);
        shape.addPoint(0,0);
        THRUST = 10;
        angle = a;
        xspeed = Math.cos(angle) * THRUST;
        yspeed = Math.sin(angle) * THRUST;
        drawShape = new Polygon();
        drawShape.addPoint(0, 0);
        drawShape.addPoint(0, 0);
        drawShape.addPoint(0, 0);
        drawShape.addPoint(0, 0);
        xposition = x;
        yposition = y;
        active = true;
    }
    
}
