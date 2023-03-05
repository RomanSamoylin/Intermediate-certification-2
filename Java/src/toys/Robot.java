package toys;

public class Robot extends Toy{

    private static int totalQTY = 0;
    public Robot(int id, String name, int chance) {
        super(name, chance);
        setId(id);
        this.totalQTY++;
    }

    public static int getTotalQTY() {
        return totalQTY;
    }

    @Override
    public String toString() { return String.format("Робот:\n" +
            "id: %d\n" +
            "%s\n" +
            "Всего: %d", getId(), super.toString(), getTotalQTY()); }
}
