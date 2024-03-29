package gameframe;

import java.awt.Color;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameFrameSimple extends javax.swing.JFrame implements KeyEventDispatcher, 
                                                                                                         MouseListener, 
                                                                                                         MouseMotionListener,
                                                                                                         ActionListener { 
   
    //drawing and timer variables
    private Image ib;  
    private Graphics ibg;  
    private Color backgroundColor = new Color(150,255,150);
   
    //keep track how much time has actually passed since last cycle
    private double delta_time;
    private long prev_time;
    public long tickCount=0;
    
    //timer object
    private Timer timer=null;
    private boolean active=false;
    
    //how long to wait between timer calls
    private int timerDelay = 20; 
    //keyboard input variables, is the key currently pressed down?
    boolean key_d=false;
    boolean key_a=false;
    boolean key_s=false;
    boolean key_w=false;
    
    //some mouse stuff
    int leftClickCount=0;
    int rightClickCount=0;
    int lastMouseX=0;
    int lastMouseY=0;
    
    //a list of objects to demonstrate a good game draw set up...
    ArrayList<Actor> actors = new ArrayList<Actor>();
    

    public GameFrameSimple() {
        initComponents();
        setLocationRelativeTo(null);
        //creates the main image that you draw on
        setUpImageBuffer();
        //please notify this frame when keyboard events occur
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(this);
        //please notify this frame when mouse events occur
        addMouseListener(this);  //registers this frame to receive mouse clicks
        addMouseMotionListener(this); //register this frame to receive mouse motions        
      
        //set up intial actors and variables of simulation
        setupSimulation();      
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelDraw = new javax.swing.JPanel();
        buttonStart = new javax.swing.JButton();
        panelInfo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textTick = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textHealth = new javax.swing.JTextField();
        buttonStop = new javax.swing.JButton();
        textReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        panelDraw.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelDrawLayout = new javax.swing.GroupLayout(panelDraw);
        panelDraw.setLayout(panelDrawLayout);
        panelDrawLayout.setHorizontalGroup(
            panelDrawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        panelDrawLayout.setVerticalGroup(
            panelDrawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        buttonStart.setText("Start");
        buttonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonStartActionPerformed(evt);
            }
        });

        panelInfo.setBackground(new java.awt.Color(153, 204, 255));

        jLabel1.setText("Tick:");

        textTick.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setText("Health:");

        textHealth.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout panelInfoLayout = new javax.swing.GroupLayout(panelInfo);
        panelInfo.setLayout(panelInfoLayout);
        panelInfoLayout.setHorizontalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textTick, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(textHealth))
                .addGap(107, 107, 107))
        );
        panelInfoLayout.setVerticalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textTick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textHealth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(567, Short.MAX_VALUE))
        );

        buttonStop.setText("Stop");
        buttonStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonStopActionPerformed(evt);
            }
        });

        textReset.setText("Reset");
        textReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonStart, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonStop, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textReset, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelDraw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonStart)
                            .addComponent(buttonStop)
                            .addComponent(textReset))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelDraw, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /* creates an image the same size as the panel.  We will draw on this panel all the time. */
    public void setUpImageBuffer(){
        ib=panelDraw.createImage(panelDraw.getWidth(),panelDraw.getHeight());
        ibg=ib.getGraphics();
    }
    
    
    
    /* the mouseListener interface requires that we have all 5 of these methods in our class, but you
     * don't have to put code in any of them unless you want to     */
    
    public void mousePressed(MouseEvent e) { 
        //where was the click? That MouseEvent object you will be given contains info about the mouse
        int lastMouseX = e.getX(); 
        int lastMouseY = e.getY(); 
        //I've added the mouse listener to the FRAME and not the PANEL, so the x and y you get is the x and y
        //values in the frame.  I need to adjust the values a bit to match the x and y inside the panel.
        // Easy to test, just click the top left corner of panel to find out the offset of your panel.
        lastMouseX-=14;
        lastMouseY-=70;
        
        //print out mouse info
        System.out.println("Click: " + lastMouseX + ", " + lastMouseY);
        int whichButton = e.getButton();
        System.out.println("Button # " + whichButton + " was pressed");
        
    
        
        //right button is usually #3
        if (whichButton==3) {
            Food newActor = new Food(lastMouseX, lastMouseY, actors);
            actors.add(newActor);
        }
    }
    
    public void mouseReleased(MouseEvent e) { }
    
    public void mouseEntered(MouseEvent e) { }
    
    public void mouseExited(MouseEvent e) {  }
    
    public void mouseClicked(MouseEvent e) {    }
    
    public void mouseMoved(MouseEvent e) {  }
    
    public void mouseDragged(MouseEvent e) {  }
    
    
    public void actionPerformed(ActionEvent e) {  }
    
    
    
    private void buttonStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonStopActionPerformed
        timer.cancel();
        timer=null;
    }//GEN-LAST:event_buttonStopActionPerformed

    private void buttonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonStartActionPerformed
        startTimer();
    }//GEN-LAST:event_buttonStartActionPerformed

    private void textResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textResetActionPerformed
        tickCount=0;
        textTick.setText(""+tickCount);
    }//GEN-LAST:event_textResetActionPerformed

    
    
    /* the KeyEvent dispatcher interface requires we have this method.  This method gets called whenever
     * a key is pressed or released.  You can easily check what is happening and which key.
     * Notice what we are doing here.  We remember the state of the key/s with those booleans.  
     * This is just one way to do it.  If you only want to do something when a key is actually pressed, 
     * then just ask if statements about the keys and you don't have to use the booleans.  */
    public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                    if (e.getKeyCode()==e.VK_D)    key_d=true;
                    else if (e.getKeyCode()==e.VK_A)  key_a=true;   
                    else if (e.getKeyCode()==e.VK_S)  key_s=true;   
                    else if (e.getKeyCode()==e.VK_W) key_w=true;
            } 
            else if (e.getID() == KeyEvent.KEY_RELEASED) {
                if (e.getKeyCode()==e.VK_D)   key_d=false;
                else if (e.getKeyCode()==e.VK_A)  key_a=false;
                if (e.getKeyCode()==e.VK_W)   key_w=false;
                else if (e.getKeyCode()==e.VK_S)    key_s=false;
            }
            return false;
    }    
    
    public void redraw() {
        //switch to white and draw a white rectangle over the entire image buffer to clear it
        ibg.setColor(backgroundColor);
        ibg.clearRect(0,0, panelDraw.getWidth(), panelDraw.getHeight() );
        ibg.fillRect(0,0,panelDraw.getWidth(), panelDraw.getHeight() );
   
        /* you can certainly just put all your draw code in here, but to stay organized, I am going to pass the
         * image buffer object to my own draw method.  This keeps the code a little cleaner... */
        drawStuff(ibg);
        
        /* this is the actual drawing of your stuff onto the actual panel.  It copies the image buffer to the
         * panel's image. */
        Graphics g = panelDraw.getGraphics();
        g.drawImage(ib,0,0,this);
    }
    
    
    
    /* A Timer object manages a TimerTask.  It will ask the TimerTask to run at whatever time interval
     * you set.  So, if their isn't a Timer object, create one.  Then create a TimerTask and assign it code (or
     * a method like tick() to occur when it is run.  Once started, the tick method will be called repeatedly
     */
    public void startTimer() {                                         
        if (timer!=null) {
            System.out.println("A timer is already working!");
            return;
        }
        //make a new timer object
        timer= new Timer(true);
        //make a timertask that has a job to do (call updateTime)
        TimerTask task= new TimerTask() {
            public void run() {
                tick();
            }
        };
        //tell timer to start repeating the task
        timer.scheduleAtFixedRate(task, 0, timerDelay);
        prev_time=System.nanoTime();
    }   
    
    
    /* This method will set the delta_time variable to how much time has past since the last time this
     * method was called.  You don't have to use this, but in video games you want to know how much 
     * time has elapsed so that you know how far to move moving game objects.
     */
    public void timeAdjust() {
        long curr_time=System.nanoTime();
        delta_time= (curr_time-prev_time)/1000000000.0;
        System.out.println(delta_time);
        prev_time=curr_time;
    }

        
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameFrameSimple.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameFrameSimple.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameFrameSimple.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameFrameSimple.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameFrameSimple().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonStart;
    private javax.swing.JButton buttonStop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelDraw;
    private javax.swing.JPanel panelInfo;
    private javax.swing.JTextField textHealth;
    private javax.swing.JButton textReset;
    private javax.swing.JTextField textTick;
    // End of variables declaration//GEN-END:variables

    
    
    //**********************************************************************
    //**********************************************************************
    //***********************  YOU CAN MODIFY BELOW HERE  ******************
    //**********************************************************************
    //**********************************************************************    
    
    
    //called in constructor of this frame)
    public void setupSimulation() {        
        //add some actors to the actors list
        for(int k=1; k<20; k++) {
            int xp=(int)(Math.random()*800)+1;
            int yp=(int)(Math.random()*600)+1;
            actors.add(new Human(xp,yp,actors) );
        }
        
        for(int k=1; k<100; k++) {
            int xp=(int)(Math.random()*800)+1;
            int yp=(int)(Math.random()*600)+1;
            actors.add(new Food(xp,yp,actors) );            
        }
    }
    
    //runs every tick when the timer is on. 
    public void tick() {
        updateTicker();
        keyboardCheck();   
        updateActors();      //most important part of simulation!
        removeDeactivatedActors();    //removes actors from list that are not active any more
        maybeMakeMoreThings();
        redraw();
    } 
    
    
    //updates the tickCount
    public void updateTicker() {
        tickCount=tickCount+1;
        textTick.setText(""+tickCount);
    }
    
    //handle keyboard presses
    public void keyboardCheck() {
        if (key_d==true)
            System.out.println("D");
        if (key_a==true)
            System.out.println("A");
        if (key_w==true)
            System.out.println("W");
        if (key_s==true)
            System.out.println("S");
    }
    
    
    public void updateActors() {
        //ask each actor to run its act() method
        for(int k=0; k<actors.size(); k++ ) {
            Actor temp = actors.get(k); 
            if ( temp.isActive() ) 
                temp.act();
        }
    }
    
    public void removeDeactivatedActors() {
        //if an actor has been deactivated then it should be removed from actor list
        for(int k=actors.size()-1; k>=0; k--) {
            Actor temp=actors.get(k);
            if ( !temp.isActive() ) {
                System.out.println("removing actor " + temp);
                actors.remove(k);
            }
        }         
    }
    
    /* your draw code would go in here.  You could have placed this all in the redraw method way above
     * but this is a bit cleaner and more organized.  */
    public void drawStuff(Graphics g) {
        //ask each actor to draw itself if active.  
        //its draw method needs this frames graphics object
        for(Actor temp: actors) 
            if (temp.isActive())
                temp.draw(g);
    }     
    
    public void maybeMakeMoreThings() {
        int humanCount=0;
        for(Actor a: actors) 
            if (a instanceof Human && a.isActive() )
                humanCount++;
        
        if (humanCount<5) 
            for(int k=0; k<5; k++)
                actors.add(new Human(400,300, actors) );
            
        int foodCount=0;
        for(Actor a: actors) 
            if (a instanceof Food && a.isActive() )
                foodCount++;
        
        if (foodCount<30) {
            for(int k=0; k<15; k++) {
                int xp = Randomizer.getInteger(1,800);
                int yp = Randomizer.getInteger(1,600);
                actors.add(new Food(xp, yp, actors) );            
            }
        }
    }

}  //end class
