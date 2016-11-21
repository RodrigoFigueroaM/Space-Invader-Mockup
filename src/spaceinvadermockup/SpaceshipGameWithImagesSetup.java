
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rui
 */
public class SpaceshipGameWithImagesSetup extends JPanel implements KeyListener, ActionListener {
     

    Font font = new Font("Verdana", Font.PLAIN, 30);
    //  String s = "collide";
    // setup 
    int delay = 25;
    //Score
    Score score = new Score();
    private int addToScorePoints = 100;
    //health
    Health health = new Health(100);
    //coordinate Labels
    int xLabelCoordinates = 900;
    int yLabelCoordinates = 0;
    int ySeparationOfCoordinates = 15;
    //bullets
    int bulletWidth = 5;
    int bulletHeight = 10;
    int playerBulletSpeed = 11;
    int targetBulletSpeed=3;
    //targets
    int numberOfTargetsColumns = 6;
    int numberOfTargetsRows = 6;
    int targetsXSpace = 10;
    int targetsYPossition = 20;
    int targetImageWidth = 47;
    int targetImageHeight = 23;
    Rectangle target = new Rectangle(targetImageWidth, targetImageHeight);
    Rectangle[] targets = new Rectangle[numberOfTargetsColumns * numberOfTargetsRows];
    private ArrayList<Alien> targetArraylist = new ArrayList<Alien>();
    //spaceship setup
    
    //spaceship
    int dx = 20;
    int dy = 0;
    int[] xpoints = {225,250, 250, 225, 200,200};
    int[] ypoints = {625,650, 675, 660, 675,650};
    Polygon spaceship = new Polygon(xpoints, ypoints, 6);
    Color spacechipColor = Color.RED;
    //bullets
 //  Bullet bull = new Bullet(bulletWidth, bulletHeight);
    private ArrayList<Bullet> targetBullets = new ArrayList<Bullet>();
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    //explotionefect squares
    // Rectangle explodingSquares= new Rectangle(5,5);
    ArrayList<Rectangle> exSquares = new ArrayList<Rectangle>();
    //timer
    Timer timer = new Timer(delay, this);
    int randomTimeTargetForBullets;
    
    //images
    //targets A.K.A. aliens
    Image alien;
    Image background;
    Image playerImageBullet;
    Image targetImageBullet;
    Image spaceshipImage;

    SpaceshipGameWithImagesSetup(){
        timer.start();
        addKeyListener(this);
        setFocusable(true);
        //start spaceship image
        spaceshipImage = new ImageIcon("image/spaceship.png").getImage();
        //aliens aka targets image initialize
        alien=new ImageIcon("image/alien.png").getImage();
        
        
        background=new ImageIcon("image/backgroundSpace.jpg").getImage();
        targetImageBullet=new ImageIcon("image/bullet.png").getImage();
        playerImageBullet=new ImageIcon("image/playerBullet.png").getImage();
        
        

        //columns of targets
        for (int i = 0, targetsXSpace = this.targetsXSpace; i < numberOfTargetsColumns; i++, targetsXSpace += target.getWidth() + this.targetsXSpace) {
            //rows of targets  
            for (int j = 0, targetsYPossition = this.targetsYPossition; j < numberOfTargetsRows; j++, targetsYPossition += this.targetsYPossition + target.getHeight()) {
                Alien target = new Alien(targetsXSpace, targetsYPossition, targetImageWidth, targetImageHeight);
                targetArraylist.add(target);
            }
        }



    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        int rightBound = (int) (getWidth() * .4);
         //backgorund
g.drawImage(background, 0, 0, null);



        //spaceship
    //  g.setColor(spacechipColor);
        g.drawImage(spaceshipImage, spaceship.xpoints[0]-spaceshipImage.getWidth(this)/2, spaceship.ypoints[0], null);
      // g.fillPolygon(spaceship);
       


        //draw targets
        for (int i = 0, ySeparationOfCoordinates = this.ySeparationOfCoordinates; i < targetArraylist.size(); i++, ySeparationOfCoordinates += this.ySeparationOfCoordinates) {
           
            //targets motion,movement
            targetArraylist.get(i).move();
            if (targetArraylist.get(0).getXCoordinate() < 0||targetArraylist.get(0).getXCoordinate() >rightBound) {
                targetArraylist.get(i).moveXPossitionBack();
                 
            }
         
            g.setColor(Color.CYAN);
                g.drawString(targetArraylist.get(i).toString(), xLabelCoordinates, yLabelCoordinates + ySeparationOfCoordinates);
    
            
g.drawImage(alien, targetArraylist.get(i).getXCoordinate(), targetArraylist.get(i).getYCoordinate(), null);
         }

        //targets bullets
        for (Bullet bull : targetBullets) {
            Alien target = new Alien(targetsXSpace, targetsYPossition, targetImageWidth, targetImageHeight);

            if (bull.getYCoordinate() > getHeight() - 20) {
                targetBullets.remove(bull);
            }
            g.setColor(Color.CYAN);
            //    for(int i=0;i<targetArraylist.size();i++){

g.drawImage(targetImageBullet, bull.getXCoordinate(), bull.getYCoordinate(), null);

             //move bullet
            bull.moveUp(-targetBulletSpeed);
            if (bull.collisionPolygonImage(spaceship.xpoints, spaceship.ypoints) == true) {
                health.reduceHealth(10);
                targetBullets.remove(bull);
            }

            //   }

        }

        //player bullets
        for (Bullet bullet : bullets) {

            //draw bullet data
            if (bullet.getYCoordinate() < 2) {
                bullets.remove(bullet);

            }
            g.setColor(Color.RED);


            //draw cordinates of the bullet
            g.drawString(bullet.toString(), xLabelCoordinates, 750);


            //draw player bullet
            g.drawImage(playerImageBullet,bullet.getXCoordinate(),bullet.getYCoordinate(),null);;
            //move bullet
            bullet.moveUp(playerBulletSpeed);


            //collision and remove bullet when it hits target   
            for (int i = 0; i < targetArraylist.size(); i++) {

                if (targetArraylist.get(i).collision(bullet) == true) {

                    score.addToScore(addToScorePoints);

                    //  g.drawString(s, 300, 200);
                    //use for debuging
                    // System.out.println("col"+targetArraylist);
               /*
                     for (int j=0; j<10;j++){
                     Rectangle explodingSquares= new Rectangle(50,50);
                     exSquares.add(explodingSquares);
                     explodingSquares.boom();
                     }
                     for(Rectangle explodingSquares : exSquares){
                     
                     g.setColor(Color.CYAN);
                     g.fillRect(targetArraylist.get(i).getXCoordinate(),targetArraylist.get(i).getYCoordinate(),50,50);
                     
              
                  
                     }
                     */ 
                     
                    targetArraylist.remove(targetArraylist.get(i));
                    bullets.remove(bullet);
                    //      System.out.println(targetArraylist.size());



                }



            }
        }
        //score and health labels
        g.setColor(Color.RED);
        g.setFont(font);
        g.drawString(score.toString(), (int) (getWidth() * .72), (int) (getHeight() * .7));
        g.setColor(Color.RED);
        g.drawString(health.toString(), (int) (getWidth() * .72), (int) (getHeight() * .8));

        //game over and win
        if (targetArraylist.size() == 0) {
            g.setFont(font);
            g.drawString("You Rock!", (int) (getWidth() * .35), (int) (getHeight() * .4));
            timer.stop();
        }

        if (health.getHealth() <= 0) {
            g.setFont(font);
            g.drawString("GAME OVER!", (int) (getWidth() * .35), (int) (getHeight() * .4));
            timer.stop();
            
        }



    }//end paint component

    //keys
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
          int counter=1;
        int rightBound = (int) (getWidth() * .67);
        switch (ke.getKeyCode()) {
            //forward
            case KeyEvent.VK_UP:
                //update the first cordinate of the space ship changing the ypoints and xpoints
                ypoints[0] -= dy;
                spaceship.translate(0, -dy);
                break;


            //backward
            case KeyEvent.VK_DOWN:
                ypoints[0] += dy;
                spaceship.translate(0, dy);
                break;

            //left
            case KeyEvent.VK_LEFT:
                if (spaceship.xpoints[3] > 0) {

                    xpoints[0] -= dx;
                    spaceship.translate(-dx, 0);
                } else {
                }
                break;

            //rigth
            case KeyEvent.VK_RIGHT:
                if (spaceship.xpoints[0] < rightBound) {
                    xpoints[0] += dx;
                    spaceship.translate(dx, 0);
                } else {
                }

                break;
            //bullet
            case KeyEvent.VK_SPACE:
                if(bullets.size()<3){
                Bullet bullet = new Bullet(bulletWidth, bulletHeight);
                bullet.setYCoordinate(ypoints[0] - bullet.getHeight() / 2);
                bullet.setXCoordinate(xpoints[0] - bullet.getWidth() / 2);
                bullet.setColor(Color.WHITE);
                bullets.add(bullet);
                }

                break;
            case KeyEvent.VK_SHIFT:
                    timer.start();
                    break;
            case KeyEvent.VK_Z:
                timer.stop();
                
                break;






            default:
                //nothing
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        


randomTimeTargetForBullets = (int) (Math.random() * (targetArraylist.size())-1);
   //bullets fired from the targets use randomTimeTargetForBullets  to get the shooter
        if (randomTimeTargetForBullets >= 0 && targetBullets.size() <= targetArraylist.size()) {
             Bullet bull = new Bullet(bulletWidth, bulletHeight, targetArraylist.get(randomTimeTargetForBullets).getXCoordinate() + targetArraylist.get(randomTimeTargetForBullets).getWidth() / 2, targetArraylist.get(randomTimeTargetForBullets).getYCoordinate() + targetArraylist.get(randomTimeTargetForBullets).getHeight());
                targetBullets.add(bull);

        }


        repaint();

    }
}
    

