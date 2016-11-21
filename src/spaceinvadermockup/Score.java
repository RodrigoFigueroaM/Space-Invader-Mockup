
public class Score {
    private int currentScore;
    private String s;
   Score(){
       
   }
   public int getScore(){
       return currentScore;
   }
   public void addToScore(int pointsToAdd){
       currentScore+=pointsToAdd;
   }
   public String toString(){
       s="Score: "+currentScore;
       return s;
   }
    
}
