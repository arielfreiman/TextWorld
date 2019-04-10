public class Chicken extends Creature {
    public Chicken(String name) {
        super(name);
    }


    public void move() {
        currentRoom.getRandomNeighbor();
    }

    public void act() {
        move();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}