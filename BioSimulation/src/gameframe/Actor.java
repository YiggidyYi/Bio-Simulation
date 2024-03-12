

package gameframe;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Actor {
    
    private ArrayList<Actor> actors;
    private double x, y;
    private double speed;
    private double direction;
    private long acts=0;
    private double size;
    private boolean active=true;
    private Color myColor;
    
    public Actor(double x, double y, ArrayList<Actor> actors) {
        active=true;
        this.x=x;
        this.y=y;
        this.actors=actors; //knowledge of all actors from simulation!
        speed=0;
        direction=0;
        myColor=Color.BLACK;
        size=20;
    }
    
    public void act() {
        //move every turn...
        move();
    }
    
    public ArrayList<Actor> getActors() {
        return actors;
    }
    
    public void move() {
       x+= speed*(Math.cos(Math.toRadians(direction)));
       y+= -1*speed*(Math.sin(Math.toRadians(direction)));  //y opposite of normal math!
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void deactivate() {
       active=false;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public int getRoundedX() {
        return (int)x;
    }
    
    public int getRoundedY() {
        return (int)y;
    }
    
    public double getSpeed() {
        return speed;
    }
    
    public double getDirection() {
        return direction;
    }
    
    public double getSize() {
        return size;
    }
    
    public int getRoundedSize() {
        return (int)size;
    }
    
    public Color getColor() {
        return myColor;
    }
    
    public void setColor(Color newColor) {
        myColor=newColor;
    }
    
    public void setSize(double newSize) {
        if (newSize>0)
            size=newSize;
    }
    
    public void setSpeed(double newSpeed) {
        speed=newSpeed;
    }
    
    public void setDirection(double newDirection) {
        direction=newDirection;
        direction=fixDirection(direction);
    }
    
    public void turn(double degrees) {
        direction+=degrees;
        direction=fixDirection(direction);
    }
    
    public void draw(Graphics g) {
        g.setColor( getColor() );
        g.fillOval( getRoundedX(), getRoundedY(), getRoundedSize(), getRoundedSize() );
    }    
    
    
    public double fixDirection(double d) {
       while(d<0)
            d+=360;
        while(d>360)
            d-=360;
        return d;
    }
    
    public double distanceTo(Actor A) {
       if (A==null) 
           return -1;
       return distanceTo(A.getX(), A.getY() );
    }
    
    public double distanceTo(double x, double y) {
        double dx=this.x-x;
        double dy=this.y-y;
        double distance=Math.pow((dx*dx+dy*dy), 0.5);
        return distance;        
    }
    
    public double directionTo(double x, double y) {
        double dx=x-this.x;
        double dy=-(y-this.y);
        double radian = Math.atan2(dy, dx);
        double angle = Math.toDegrees(radian);
        return fixDirection( angle );
    }
    
    public double directionTo(Actor A) {
        if (A==null) 
            return -1;
        return fixDirection( directionTo(A.getX(), A.getY() ) );
    }
    
}  //end class Actor
