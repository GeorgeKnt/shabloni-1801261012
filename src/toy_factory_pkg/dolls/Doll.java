package toy_factory_pkg.dolls;

import toy_factory_pkg.Toy;

public abstract class Doll extends Toy {
    private int for_age;
    private String for_gender;

    public int getFor_age() {
        return for_age;
    }

    public void setFor_age(int for_age) {
        this.for_age = for_age;
    }

    public String getFor_gender() {
        return for_gender;
    }

    public void setFor_gender(String for_gender) {
        this.for_gender = for_gender;
    }
}
