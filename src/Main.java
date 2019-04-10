import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // build graph
        Level g = new Level();
        Item i1= new Item("Swimsuit", "suit");
        Item i2= new Item("goggles", "speedo goggles");
        g.addRoom("hall","long room", i1);
        g.addRoom("closet","dark closet", i2);
        g.addRoom("dungeon", "cool dungeon");

        g.addUndirectedEdge("hall", "dungeon");
        g.addUndirectedEdge("hall", "closet");
        //"game loop" get user input and move player

        Player player = new Player("Ariel","Swimmer",g.getroom("hall"));
        player.setCurrentRoom(g.getroom("hall"));
        String response = "";
        Scanner input = new Scanner(System.in);
        ArrayList<Chicken> allChickens = initializeChicken(100 ,g);



        do {
            //display the room that exists
            System.out.println(player.getName()+". You are currently at the " + player.getCurrentRoom().getName()+": "+player.getCurrentRoom().getDescription());
            player.getCurrentRoom().displayInventory();
            System.out.println("What do you want to do? > go to <roomname> , look , add room <roomname> , " +
                    "take <itemname> , drop <itemname> , look for chicken , quit");
            response = input.nextLine();
            if (response.length() >= 5 && response.substring(0, 5).equals("go to")) {
                if (player.getCurrentRoom().getNeighbor(response.substring(6)) != null) {
                    Level.Room nextRoom = player.getCurrentRoom().getNeighbor(response.substring(6));
                    while (nextRoom == null && !response.equals("quit")) {
                        System.out.println("You can't go to " + response + " try again");
                        System.out.println("You can go to the: " + player.getCurrentRoom().getNeighborNames());
                        response = input.nextLine();
                    }
                    player.setCurrentRoom(nextRoom);
                }
            }
            if (response.equals("look") && response.length() >= 4) {
                System.out.println("You can go to the:"+ "\n" + player.getCurrentRoom().getNeighborNames());
            }
            if (response.length() >= 8 && response.substring(0, 8).equals("add room")) {
                String roomName = response.substring(9);
                if (g.getroom(roomName)==null) {
                    System.out.print("add description to room: ");
                    String description = input.nextLine();
                    g.addRoom(roomName, description);
                }
                g.addUndirectedEdge(player.getCurrentRoom().getName(), roomName);
            }
            if (response.length() >= 4 && response.substring(0, 4).equals("take")) {
                if (player.getCurrentRoom().isItemInRoom(response.substring(5)) == true) {
                    player.addItem(player.currentRoom.removeItem(response.substring(5)));
                }
                else
                    System.out.println("Item is not in this room");
            }
            if (response.length() >= 4 && response.substring(0, 4).equals("drop")){
                if (player.hasItem(response.substring(5)) == true) {
                    player.getCurrentRoom().addItem(player.removeItem(response.substring(5)));
                }
                else
                    System.out.println("you don't have this item");
            }
            if(response.contains("chicken")){
                player.currentRoom.displayChickensInRoom();
            }
            creatureAct(allChickens);

        } while (!response.equals("quit"));
    }

    private static void creatureAct(ArrayList<Chicken> allChickens) {
        chickenAct(allChickens);
    }

    private static void chickenAct(ArrayList<Chicken> allChickens) {
        for (int i = 0; i < allChickens.size(); i++) {
            allChickens.get(i).getCurrentRoom().removeChicken(allChickens.get(i).getName());
            allChickens.get(i).act();
            allChickens.get(i).getCurrentRoom().addChicken(allChickens.get(i));
        }
    }

    private static ArrayList<Chicken> initializeChicken(int number, Level g) {
        ArrayList<Chicken> chicks = new ArrayList<>();
        for (int i = 0; i < number; i++) {
           Chicken chick = new Chicken("Chicken"+i,g);
           chick.getCurrentRoom().addChicken(chick);
           chicks.add(chick);
        }
        return chicks;
    }

}
