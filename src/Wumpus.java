public class Wumpus extends Creature {
    private Player player;

    public Wumpus(String name, Player player){
        super(name);
        this.player=player;
    }

    public void move(){
        Level.Room playerRoom = player.getCurrentRoom();
        if (playerRoom.getName().equals(currentRoom.getName())){
            setCurrentRoom(currentRoom.getRandomNeighbor());
        }
        if(currentRoom.isNeigbor(playerRoom)){
            if(currentRoom.getNeighborsByHashMap().size()==1 && currentRoom.isNeigbor(playerRoom)){
                //do nothing
            }
            if(currentRoom.getNeighborsByHashMap().size()>1 && currentRoom.isNeigbor(playerRoom)){
                Level.Room out=null;
                while (out!=playerRoom){
                    out=currentRoom.getRandomNeighbor();
                }
                System.out.println(out.getName());
                setCurrentRoom(out);
            }

        }
    }
    public void act(){
        move();
    }
}
