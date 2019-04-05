import java.util.ArrayList;

public class Player {
    String name, description;
    ArrayList<Item> items;
    Level.Room currentRoom;

    public Player(String name, String description, Level.Room currentRoom) {
        this.name = name;
        this.description = description;
        items =new ArrayList<>();
        currentRoom= currentRoom;
    }
    public void addItem (Item item){
        items.add(item);
    }

    public String getName() {
        return name;
    }

    public Item removeItem(String name){
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)){
                return items.remove(i);
            }
        }
        return null;
    }
    public boolean destroyItem(String name){
        int c=0;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)){
                items.remove(i);
                c++;
            }
        }
        if (c>=1) return true;
        else return false;
    }
    public ArrayList<Item> getItems(){
        return items;
    }
    public void displayInventory(){
        System.out.println("Items List: ");
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i).getName()+": "+ items.get(i).getDescription());
        }
    }
    public boolean hasItem(String name){
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)) return true;
        }
        return false;
    }

    public Level.Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Level.Room currentRoom) {
        this.currentRoom = currentRoom;
    }


}
