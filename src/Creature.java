import java.util.ArrayList;
import java.util.HashMap;

public abstract class Creature {
    protected String name;

    protected Level.Room currentRoom;

    public Creature(String name, Level.Room currentRoom) {
        this.name=name;
        this.currentRoom=currentRoom;

    }


    public abstract void move();
    public abstract void act();
    public Level.Room getCurrentRoom(){return currentRoom;}
    public void setCurrentRoom(Level.Room room){
        currentRoom=room ;
    }


}


