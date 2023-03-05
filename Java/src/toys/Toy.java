package toys;

public abstract class Toy {
    private int id;
    private String name;
    private int chance;

    public Toy(String name, int chance){
        this.name = name;
        this.chance = chance;    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getChance() {
        return chance;
    }

    @Override
    public String toString() {
        return String.format("Название: %s", getName());
    }
 
} 

