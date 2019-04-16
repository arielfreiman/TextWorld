import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Scanner input = new Scanner(System.in);
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

        for (int i = 0; i < 10; i++) {
            g.createChickens("Chicken"+i);
            g.createPopStars("PopStar"+i,player);
            g.createWumpuses("Wumpus"+i,player);
        }




        do {
            //display the room that exists
            intitialText(player);
            response = input.nextLine();

            if (response.length() >= 5 && response.substring(0, 5).equals("go to")) {
                executeGoTo(response,player);
            }

            if (response.equals("look") && response.length() >= 4) {
                executeLook(response,player);
            }

            if (response.length() >= 8 && response.substring(0, 8).equals("add room")) {
                executeAddRoom(response,g,player);
            }

            if (response.length() >= 4 && response.substring(0, 4).equals("take")) {
                executeTakeItem(response,player);
            }

            if (response.length() >= 4 && response.substring(0, 4).equals("drop")){
                executeDropItem(response,player);
            }

            if(response.contains("look for chicken")){
                executeLookForChicken(g,player);
            }

            if(response.contains("popstars")){
                executeLookForPopStars(g,player);
            }

            if(response.contains("wumpus")){
                executeLookForWumpus(g,player);
            }

            creatureAct(g.getAllCreatures());

        } while (!response.equals("quit"));
    }

    private static void intitialText(Player player) {
        System.out.println(player.getName()+". You are currently at the " + player.getCurrentRoom().getName()+": "+player.getCurrentRoom().getDescription());
        player.getCurrentRoom().displayInventory();
        System.out.println("What do you want to do? > go to <roomname> , look , add room <roomname> , " +
                "take <itemname> , drop <itemname> , chicken (look for chickens) , look for popstar (look for Popstars) , look for wumpus (look for wumpuses) , quit");
    }

    private static void executeLookForWumpus(Level g, Player player) {
        int numberOfWumpusInRoom = g.findWumpusInARoom(player.getCurrentRoom());
        System.out.println("There are "+numberOfWumpusInRoom+" wumpuses in this room");
    }

    private static void executeLookForPopStars(Level g, Player player) {
        int numberOfPopStarsInRoom = g.findPopStarsInARoom(player.getCurrentRoom());
        System.out.println("There are "+numberOfPopStarsInRoom+" popstars in this room");
    }

    private static void executeLookForChicken(Level g,Player player) {
        int numberOfChickenInRoom = g.findChickenInARoom(player.getCurrentRoom());
        System.out.println("There are "+numberOfChickenInRoom+" chickens in this room");
    }

    private static void executeDropItem(String response, Player player) {
        if (player.hasItem(response.substring(5)) == true) {
            player.getCurrentRoom().addItem(player.removeItem(response.substring(5)));
        }
        else
            System.out.println("you don't have this item");
    }

    private static void executeTakeItem(String response, Player player) {
        if (player.getCurrentRoom().isItemInRoom(response.substring(5)) == true) {
            player.addItem(player.currentRoom.removeItem(response.substring(5)));
        }
        else
            System.out.println("Item is not in this room");
    }

    private static void executeAddRoom(String response,Level g, Player player) {
        String roomName = response.substring(9);
        if (g.getroom(roomName)==null) {
            System.out.print("add description to room: ");
            String description = input.nextLine();
            g.addRoom(roomName, description);
        }
        g.addUndirectedEdge(player.getCurrentRoom().getName(), roomName);
    }

    private static void executeLook(String response, Player player) {
        System.out.println("You can go to the:"+ "\n" + player.getCurrentRoom().getNeighborNames());
    }

    private static void executeGoTo(String response, Player player) {
        Level.Room nextRoom = player.getCurrentRoom().getNeighbor(response.substring(6));
        if(nextRoom==null){
            while (nextRoom == null) {
                System.out.println("You can't go to " + response + " try again");
                System.out.println("You can go to the: " + player.getCurrentRoom().getNeighborNames());
                System.out.println("go to: ");
                response = input.next();
                nextRoom = player.getCurrentRoom().getNeighbor(response);
            }
        }
        player.setCurrentRoom(nextRoom);
    }

    private static void creatureAct(ArrayList<Creature> allCreatures) {
        for (int i = 0; i < allCreatures.size(); i++) {
            allCreatures.get(i).act();
        }
    }
}
