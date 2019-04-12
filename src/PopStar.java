public class PopStar extends Creature {
    private Player player;

    public PopStar(String name, Player player){
        super(name);
        this.player=player;
    }

    public void move(){
        Level.Room roomToGo = player.getCurrentRoom();
        if(currentRoom!=roomToGo){
            if(currentRoom.hasTheSameNeighbor(roomToGo)!=null && currentRoom.isNeigbor(roomToGo)==false){
                setCurrentRoom(currentRoom.hasTheSameNeighbor(roomToGo));
            }
            else {
                if (currentRoom.isNeigbor(roomToGo)) {
                    setCurrentRoom(roomToGo);
                }
            }
        }
    }
    public void act(){
        move();
    }
}
