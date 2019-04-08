public class Chicken extends Creature{
    public Chicken (String n, Level lev){
        this.level = lev;
        currentRoom=level.getRandomRoom();
        this.name=n;
    }



    public void move(){
        currentRoom.getRandomNeighbor();
    }
    public void act(){
        move();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}