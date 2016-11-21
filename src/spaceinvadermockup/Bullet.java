
import java.awt.Color;
import java.awt.Image;
import java.awt.Polygon;
import java.util.Timer;

public class Bullet extends Rectangle{
    private Color color= new Color(0,0,0);
   private int  xcoordinate;
    private int ycoordinate;
    private int width;
    private int height;
//    Timer timer=new Timer();
    Bullet(){
        
    }
     Bullet(int width,int height){
        this.width=width;
        this.height=height;
        
    }
   
    
    Bullet(int width,int height,int ycoordinate){
        this.width=width;
        this.height=height;
        this.ycoordinate=ycoordinate;
        
    }
    Bullet(int width,int height,int xcoordinate,int ycoordinate){
        this.width=width;
        this.height=height;
        this.xcoordinate=xcoordinate;
        this.ycoordinate=ycoordinate;
        
    }public void setColor(Color color){
        this.color=color;
    }
     public void setWidth(int width){
        this.width=width;
    }
     public void setHeight(int height){
        this.height=height;
    }
     public void setYCoordinate(int y){
        this.ycoordinate=y;
    }
     public void setXCoordinate(int x){
        this.xcoordinate=x;
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
      
     //motions
     
       public void moveUp(){
         ycoordinate-=5;
     }
       public void moveUp(int dy){
         ycoordinate-=dy;
     }
        public boolean collision(Rectangle target){
       // +or -this.getWidth()/2 and +or - this.getHeight()/2
            //because we sat the pointer to the middle of the square
            
         if(this.getXCoordinate() - this.getWidth()/2   >  target.getXCoordinate()
                 && this.getXCoordinate()+this.getWidth()/2  <   target.getXCoordinate() + target.getWidth() ){
           
          if(this.getYCoordinate() -this.getHeight()/2  > target.getYCoordinate() 
                  && this.getYCoordinate()  <   target.getYCoordinate()+target.getHeight())
            return true;
         }
           
         return false;
        }
        public boolean collisionPolygon(int[] xpoints,int[] ypoints){
       // +or -this.getWidth()/2 and +or - this.getHeight()/2
            //because we sat the pointer to the middle of the square
            
         if(this.getXCoordinate() - this.getWidth()/2   > xpoints[3]
                 
                 && this.getXCoordinate()+this.getWidth()/2  <  xpoints[1] ){
           
          if(this.getYCoordinate() -this.getHeight()/2  > ypoints[0]
                  && this.getYCoordinate()  <   ypoints[1])
            return true;
         }
           
         return false;
        }
        public boolean collisionPolygonImage(int[] xpoints,int[] ypoints){
       // +or -this.getWidth()/2 and +or - this.getHeight()/2
            //because we sat the pointer to the middle of the square
            
         if(this.getXCoordinate() - this.getWidth()/2   > xpoints[5]
                 
                 && this.getXCoordinate()+this.getWidth()/2  <  xpoints[2] ){
           
          if(this.getYCoordinate() -this.getHeight()/2  > ypoints[0]
                  && this.getYCoordinate()  <   ypoints[2])
            return true;
         }
           
         return false;
        }
       
           
         
}
