package gameframe;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Death extends Actor {
        
    public Death(double x, double y, ArrayList<Actor> actors) {
        super(x,y,actors); 
        setColor(Color.BLACK);
        setSize(10);
    }
    
    public void act() {
        //food does nothing! just sits there       
    }
    
    public void draw(Graphics g) {
        g.setColor( getColor() );
        g.drawLine(getRoundedX()-10, getRoundedY()-10, getRoundedX()+10, getRoundedY()+10);
        g.drawLine(getRoundedX()+10, getRoundedY()-10, getRoundedX()-10, getRoundedY()+10);
    }
    
}
