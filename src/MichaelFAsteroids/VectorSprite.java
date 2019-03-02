/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MichaelFAsteroids;

import java.awt.Graphics;
import java.awt.Polygon;

/**
 *
 * @author Tyler
 */
public class VectorSprite {

    //variables here
    double xposition, yposition, xspeed, yspeed, angle;
    Polygon shape, drawShape;
    double ROTATION;
    double THRUST;
    boolean active;
    int counter;
    //constructor goes here

    public void paint(Graphics g) {
        g.drawPolygon(drawShape);
    }

    public void updatePosition() {
        counter++;
        xposition += xspeed;
        yposition += yspeed;
        Wraparound();
        int x, y;

        for (int i = 0; i < shape.npoints; i++) {
//            shape.xpoints[i] += xspeed;
//            shape.ypoints[i] += yspeed;
            x = (int) Math.round(shape.xpoints[i] * Math.cos(angle)
                    - shape.ypoints[i] * Math.sin(angle));
            y = (int) Math.round(shape.xpoints[i] * Math.sin(angle)
                    + shape.ypoints[i] * Math.cos(angle));
            drawShape.xpoints[i] = x;
            drawShape.ypoints[i] = y;
        }
        drawShape.invalidate();
        drawShape.translate((int) xposition, (int) yposition);
    }

    public void Wraparound() {
        if (xposition > 900) {
            xposition = 0;

        }
        if (xposition < 0) {
            xposition = 900;
        }
        if (yposition > 600) {
            yposition = 0;

        }
        if (yposition < 0) {
            yposition = 600;

        }
    }
}
