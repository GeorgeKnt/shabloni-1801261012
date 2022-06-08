package toy_factory_pkg.bikes;

import toy_factory_pkg.Toy;

public abstract class Bike extends Toy {
    private String color;
    private int size;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
