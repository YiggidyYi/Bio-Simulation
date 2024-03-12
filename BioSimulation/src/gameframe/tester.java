
package gameframe;

public class tester {
    
    public static void main(String[] args) {
        Human h=new Human(100, 100, null);
        Human h2=new Human(200, 200,null);
        double angle = h.directionTo(h2);
        System.out.println(angle);
        System.out.println( h.distanceTo(h2));
        
    }
}
