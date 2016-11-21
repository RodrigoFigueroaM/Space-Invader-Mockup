
public class Health {
    private int health;
    private String s;
    Health(){
        health=50;
        
    }
     Health(int health){
           this.health=health;
        
    }
    public void setHealth(int health){
        this.health=health;
    }
    public void reduceHealth(int reduce){
        this.health-=reduce;
    }
    public int getHealth(){
        return health;
    }
    public String toString(){
        s="health: "+health;
        return s;
    }
    
}
