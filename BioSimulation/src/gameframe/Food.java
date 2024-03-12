package gameframe;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Food extends Actor {
        
    public Food(double x, double y, ArrayList<Actor> actors) {
        super(x,y,actors); 
        setColor(Color.PINK);
        setSize(10);
    }
    
    public void act() {
        //food does nothing! just sits there       
    }
    
    public void draw(Graphics g) {
        g.setColor( getColor() );
        g.fillOval( getRoundedX(), getRoundedY(), getRoundedSize(), getRoundedSize() );
    }
}
