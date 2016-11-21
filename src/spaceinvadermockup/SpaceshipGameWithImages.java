
import java.awt.Color;
import javax.swing.JFrame;

public class SpaceshipGameWithImages extends JFrame{
    SpaceshipGameWithImages(){ 
        SpaceshipGameWithImagesSetup game = new SpaceshipGameWithImagesSetup();
      add( game );
 
      
      
      //window
        setSize(1200,800);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main (String[] args){
       SpaceshipGameWithImages spaceshipGame= new SpaceshipGameWithImages(); 
    }
    
}

    


    

