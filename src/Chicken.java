public class Chicken extends Creature{
    public Chicken (){
        currentRoom=level.getRandomRoom();
    }
    public void move(){
        currentRoom.getRandomNeighbor();
    }
    public void act(){
        move();
    }

}