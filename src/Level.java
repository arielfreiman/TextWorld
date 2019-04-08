import java.util.ArrayList;
import java.util.HashMap;

public class Level {

    private HashMap<String, Room> rooms;

    public Level() {
        rooms = new HashMap<>();
    }

    public void addRoom(String name, String description, ArrayList<Item> items) {
        Room n = new Room(name, description, items);
        rooms.put(name, n);
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


    public void createChickenInRoom(String roomName, String chickenName) {
        Room room = getroom(roomName);
        room.getChickens().add(new Chicken(chickenName, this));

    }

    public static class Room {
        private String name;
        //private ArrayList<Node> neighbors;
        private HashMap<String, Room> neighbors;
        private String description;
        private ArrayList<Item> items;
        private ArrayList<Chicken> chickens;


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
            this.chickens = new ArrayList<>();
        }

        private Room(String name, String description, Item item) {
            neighbors = new HashMap<>();
            this.name = name;
            this.description = description;
            this.items = new ArrayList<>();
            items.add(item);
            this.chickens = new ArrayList<>();
        }

        public Room(Room room) {
            neighbors = room.getNeighborsByHashMap();
            this.name = room.name;
            this.description = room.getDescription();
            this.items = room.getItems();
            this.chickens = new ArrayList<>();
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
            for (int i = 0; i < items.size(); i++) {
                System.out.println(items.get(i).getName() + "> " + items.get(i).getDescription());
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

        public ArrayList<Chicken> getChickens() {
            return chickens;
        }

        public void displayChickensInRoom() {
            if (chickens!= null) {
                System.out.println("Chickens in this room: " + chickens.size());
            }
            else
                System.out.println("No Chickens");
        }

        public void addChicken(Chicken chicken) {
            chickens.add(chicken);
        }

        public Chicken removeChicken(String name) {
            for (int i = 0; i < chickens.size(); i++) {
                if (chickens.get(i).getName().equals(name)) {
                    return chickens.remove(i);
                }
            }
            return null;
        }

        public boolean destroyChicken(String name) {
            for (int i = 0; i < chickens.size(); i++) {
                if (chickens.get(i).getName().equals(name)) {
                    return chickens.remove(chickens.get(i));
                }
            }
            return false;
        }
    }
}
