public class Wumpus extends Creature {
    private Player player;

    public Wumpus(String name, Player player){
        super(name);
        this.player=player;
    }

    public void move(){
        Level.Room playerRoom = player.getCurrentRoom();
        if(currentRoom.isNeigbor(playerRoom)){
            setCurrentRoom(currentRoom.randomRoomWithOutThisRoom(playerRoom));
        }
    }
    public void act(){
        move();
    }
}
