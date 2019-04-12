import java.util.ArrayList;
import java.util.HashMap;

public class Level {

    private HashMap<String, Room> rooms;
    private ArrayList<Creature> allCreatures;

    public Level() {
        rooms = new HashMap<>();
        allCreatures = new ArrayList<>();
    }

    public void addRoom(String name, String description, ArrayList<Item> items) {
        Room n = new Room(name, description, items);
        rooms.put(name, n);
    }
    public ArrayList<Creature> getAllCreatures(){
        return allCreatures;
    }
    public int findChickenInARoom(Room room){
        int counter=0;
        for (int i = 0; i < allCreatures.size(); i++) {
            if (allCreatures.get(i).getName().contains("Chicken")){
                if (allCreatures.get(i).getCurrentRoom().getName().equals(room.getName())){
                    counter++;
                }
            }
        }
        return counter;
    }

    public void addRoom(String name, String description, Item item) {
        Room n = new Room(name, description, item);
        rooms.put(name, n);
    }

    public void addRoom(String name, String description) {
        Room n = new Room(name, description);
        rooms.put(name, n);
    }

    public void addDirectedEdge(String name1, String name2) {
        Room n1 = getroom(name1);
        Room n2 = getroom(name2);
        n1.addNeighbor(n2);
    }

    public void addUndirectedEdge(String name1, String name2) {
        Room n1 = getroom(name1);
        Room n2 = getroom(name2);
        n1.addNeighbor(n2);
        n2.addNeighbor(n1);
    }

    public Room getroom(String name) {
        return rooms.get(name);
    }

    public Room getRandomRoom() {
        ArrayList<Room> listOfRooms = new ArrayList<>();
        for (Level.Room n : rooms.values()) {
            listOfRooms.add(n);
        }
        int rand = (int) (Math.random() * (listOfRooms.size()));
        return listOfRooms.get(rand);
    }


    public void createChickens(String chickenName) {
        Chicken chicken = new Chicken(chickenName);
        chicken.setCurrentRoom(getRandomRoom());
        allCreatures.add(chicken);
    }
    public void createPopStars(String popName,Player player){
        PopStar popStar = new PopStar(popName,player);
        popStar.setCurrentRoom(getRandomRoom());
        allCreatures.add(popStar);
    }

    public int findPopStarsInARoom(Room currentRoom) {
        int counter=0;
        for (int i = 0; i < allCreatures.size(); i++) {
            if (allCreatures.get(i).getName().contains("PopStar")){
                if (allCreatures.get(i).getCurrentRoom().getName().equals(currentRoom.getName())){
                    counter++;
                }
            }
        }
        return counter;
    }

    public int findWumpusInARoom(Room currentRoom) {
        int counter=0;
        for (int i = 0; i < allCreatures.size(); i++) {
            if (allCreatures.get(i).getName().contains("Wumpus")){
                if (allCreatures.get(i).getCurrentRoom().getName().equals(currentRoom.getName())){
                    counter++;
                }
            }
        }
        return counter;
    }

    public void createWumpuses(String wumpName, Player player) {
        Wumpus wumpus = new Wumpus(wumpName,player);
        wumpus.setCurrentRoom(getRandomRoom());
        allCreatures.add(wumpus);
    }

    public static class Room {
        private String name;
        //private ArrayList<Node> neighbors;
        private HashMap<String, Room> neighbors;
        private String description;
        private ArrayList<Item> items;

        public HashMap<String, Room> getNeighborsByHashMap() {
            return neighbors;
        }

        private Room(String name, String descriptions, ArrayList<Item> items) {
            neighbors = new HashMap<>();
            this.name = name;
            this.description = description;
            this.items = new ArrayList<>(items);
        }

        private Room(String name, String descriptions) {
            neighbors = new HashMap<>();
            this.name = name;
            this.description = description;
            ArrayList<Item> items = new ArrayList<>();
        }

        private Room(String name, String description, Item item) {
            neighbors = new HashMap<>();
            this.name = name;
            this.description = description;
            this.items = new ArrayList<>();
            items.add(item);
        }

        public Room(Room room) {
            neighbors = room.getNeighborsByHashMap();
            this.name = room.name;
            this.description = room.getDescription();
            this.items = room.getItems();
        }

        public String getName() {
            return name;
        }

        public void addNeighbor(Room n) {
            neighbors.put(n.getName(), n);
        }

        //returens string of the names of all neighbors of this node
        public String getNeighborNames() {
            String out = "";
            for (Room n : neighbors.values()) {
                out += n.getName() + ": " + n.getDescription() + "\n";
            }
            return out;
        }
        //return neighbor whose name is name. returns null otherwise.

        public Room getNeighbor(String name) {
            return neighbors.get(name);
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public ArrayList<Item> getItems() {
            return items;
        }

        public void displayInventory() {
            System.out.println("Items in this room: ");
            if (items!=null) {
                for (int i = 0; i < items.size(); i++) {
                    System.out.println(items.get(i).getName() + "> " + items.get(i).getDescription());
                }
            }
        }

        public void addItem(String name) {
            items.add(new Item(name, ""));
        }

        public void addItem(String name, String description) {
            items.add(new Item(name, description));
        }

        public void addItem(Item item) {
            items.add(item);
        }

        public Item removeItem(String name) {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getName().equals(name)) {
                    return items.remove(i);
                }
            }
            return null;
        }

        public boolean destroyItem(String name) {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getName().equals(name)) {
                    return items.remove(items.get(i));
                }
            }
            return false;
        }

        public boolean isItemInRoom(String name) {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getName().equals(name)) return true;
            }
            return false;
        }

        public Room getRandomNeighbor() {
            ArrayList<Room> rooms = new ArrayList<>();
            for (Level.Room n : neighbors.values()) {
                rooms.add(n);
            }
            int rand = (int) (Math.random() * (rooms.size()));
            return (new Level.Room(rooms.get(rand)));
        }
        public boolean isNeigbor(Room room){
            for (Room n : neighbors.values()) {
                if (room == n){
                    return true;
                }
            }
            return false;
        }
        public Room hasTheSameNeighbor( Room room ){
            for (Room n1 : neighbors.values()) {
                for (Room n2 : room.getNeighborsByHashMap().values()) {
                    if (n1 == n2){
                        return n1;
                    }
                }
            }
            return null;
        }
        public Room randomRoomWithOutThisRoom(Room room){
            if(neighbors.size()==1 && this.isNeigbor(room)){
                return this; //NO Neighbor To Run (Trapped)
            }
            if(this==room){
                return room.getRandomNeighbor();
            }
            else{
                Room out=null;
                while (out!=room){
                    out=this.getRandomNeighbor();
                }
                return out;
            }
        }
    }
}
