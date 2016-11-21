
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.ArrayList;

public class Alien {

    
    Color color= new Color(0,0,0);
   private int  xcoordinate;
    private int ycoordinate;
    private int width;
    private int height;
    
    
    private int x;
        private int y;
     //direction of x cordinate
      private   int randomNumber= 12-(int)(Math.random()*25);
         //direction of y cordinate
    private     int rando = 12-(int)(Math.random()*25);
 
   // private boolean centered;
    
    private int step=5;
      boolean colision;
       ArrayList<Rectangle> exSquares = new ArrayList<Rectangle>();
   
    
    
    Alien(){
        this.width=2;
        this.height=1;
    }

    
     Alien(int width,int height){  
        this.width=width;
        this.height=height;
    }
      
        Alien(int x,int y,int width,int height){   
        this.xcoordinate=x;
        this.ycoordinate=y;
        this.width=width;
        this.height=height;
    }
            Alien(Color Color,int x,int y,int width,int height){
        this.color=Color;   
        this.xcoordinate=x;
        this.ycoordinate=y;
        this.width=width;
        this.height=height;
    }

      Alien(Color Color,int width,int height){
        this.color=Color;   
        this.width=width;
        this.height=height;
    }
    
    public void setColor(Color color){
        this.color=color;
    }
     public void setWidth(int width){
        this.width=width;
    }
     public void setHeight(int height){
        this.height=height;
    }
     public void setXCoordinate(int x){
        this.xcoordinate=x;
    }
     public void setYCoordinate(int y){
        this.ycoordinate=y;
    }
      public void setStep(int step){
        this.step=step;
    }
     
     public Color getColor(){
         return color;
     }
     public int getWidth(){
         return width;
     }
     public int getHeight(){
         return height;
     }
     public int getXCoordinate(){
         return xcoordinate;
     }
     public int getYCoordinate(){
         return ycoordinate;
     }
     
     public int getArea(){
         return width*height;
     }
     
     
     public void move(){
         xcoordinate+=step;
         
     }
     public void move(int step){
         this.step=step;
         xcoordinate+=this.step;
         
     }
      public void moveDistance(int distance){
         this.step=step;
         xcoordinate+=this.step;
         if(getXCoordinate()<distance){
            xcoordinate+=this.step;     
         }
         else{
             xcoordinate-=this.step;
         
         }
         
     }
      public void moveXPossitionBack(){
         step*=-1;
         
     }
      
     public void moveBack(){
         ycoordinate+=40;
         xcoordinate=0;
     }
     public void moveUp(){
         ycoordinate+=5;
     }
     
     public void boom(){
          
            this.xcoordinate+=rando;
            this.ycoordinate+=randomNumber;
           this.width-=1;
     }
      public void boom(int life){
          
            this.x+=rando;
            this.y+=randomNumber;
           this.width-=life;
         }
      public void explo(){
           for(int k=0;k<5;k++){
           Rectangle explodingSquares= new Rectangle(getXCoordinate(),getYCoordinate(),50,50);
  exSquares.add(explodingSquares);
  
  
            }
          
            this.x+=rando;
            this.y+=randomNumber;
         }
     public  void remove(){
         this.xcoordinate=-100;
     }
             //middle of the triangle
        public boolean collisionTest(Rectangle target){
       // +or -this.getWidth()/2 and +or - this.getHeight()/2
            //because we sat the pointer to the middle of the square
            
         if(this.getXCoordinate() + this.getWidth()/2   >  target.getXCoordinate()
                 && this.getXCoordinate()-this.getWidth()/2     <   target.getXCoordinate() + target.getWidth() ){
           
          if(this.getYCoordinate() +this.getHeight()/2  > target.getYCoordinate() 
                  && this.getYCoordinate()-+this.getHeight()/2  <   target.getYCoordinate()+target.getHeight())
            return true;
         }
           
         return false;
        }
        
        
        
          public boolean collision(Rectangle target){
       // +or -this.getWidth()/2 and +or - this.getHeight()/2
            //because we sat the pointer to the middle of the square
            
         if(this.getXCoordinate() + this.getWidth()   >  target.getXCoordinate()
                 && this.getXCoordinate()  <   target.getXCoordinate() + target.getWidth() ){
           
          if(this.getYCoordinate() +this.getHeight()/2  > target.getYCoordinate() 
                  && this.getYCoordinate()  <   target.getYCoordinate()+target.getHeight())
            return true;
         }
           
         return false;
        }
        
        public String toString(){
            return "X1= "+getXCoordinate()+" Y1= "+getYCoordinate()+
                    "\n      X2= "+(getXCoordinate()+getWidth())+" Y2= "+(getYCoordinate()+getHeight());
        } 

          
          }
 



