package gameframe;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Human extends Actor {
    
    int hunger;
    
    public Human(double x, double y, ArrayList<Actor> actors) {
        super(x,y,actors); 
        setColor(Color.BLUE);
        setSize(25);
        hunger=Randomizer.getInteger(1,500);
    }
    
   
    public void act() {
        randomizeMotion();
        move(); 
        checkFoodCollision();
        handleHunger();
    }
    
    public void randomizeMotion() {
        int r=Randomizer.getInteger(1,100);
        if (r<=1) {
            double newSpeed=Randomizer.getDouble(0.5, 1.2);
            double newDirection=Randomizer.getDouble(0,360);
            setSpeed(newSpeed);
            setDirection(newDirection);
        }
        if ( distanceTo(400,300) > 500 ) {
            double toCenter = directionTo(400,300);
            setDirection(toCenter);
        }
        
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor( getColor() );
        g.fillRect( getRoundedX()-getRoundedSize()/2, getRoundedY()-getRoundedSize()/2, getRoundedSize(), getRoundedSize() );
        g.setColor( Color.WHITE);
        g.fillRect( getRoundedX()-getRoundedSize()/4, getRoundedY()-getRoundedSize()/4, getRoundedSize()/2, getRoundedSize()/2 );
    }
    
    public void checkFoodCollision() {
        //cycle through all actors.  see if we are touching any food.  eat and deactivate!
        for(int k=0; k<getActors().size(); k++) {
             Actor temp = getActors().get(k);
             if ( temp instanceof Food && temp.isActive() ) {
                     if (distanceTo(temp)<getSize() ) {
                         hunger=0;
                         temp.deactivate();
                     }
             }
         }
         
    }
    
    public void handleHunger() {
        hunger+=1;
        if (hunger>=1200) {
            deactivate();
            Death death = new Death(getX(), getY(), getActors() );
            getActors().add( death );
        }
        else if (hunger>=800)
            setColor(Color.RED);
        else if (hunger>=400)
            setColor(Color.ORANGE);
        else
            setColor(Color.BLUE);
    }
    
}
