import java.util.ArrayList;
import java.util.HashMap;

public abstract class Creature {
    protected String name;

    protected Level.Room currentRoom;

    public Creature(String name) {
        this.name=name;
    }


    public abstract void move();
    public abstract void act();
    public Level.Room getCurrentRoom(){return currentRoom;}
    public String getName(){return name;}
    public void setCurrentRoom(Level.Room room){
        currentRoom=room ;
    }


}


