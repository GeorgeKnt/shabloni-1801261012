package toy_factory_pkg.dolls;

import toy_factory_pkg.Toy;
import toy_factory_pkg.ToyFactory;

public class DollFactory extends ToyFactory {
    @Override
    public Toy createToy(Enum dollType){
        if (dollType == null){
            return null;
        }
        if (dollType.equals(EDolls.BABY)){
            return new BabyDoll();
        }
        if (dollType.equals(EDolls.PRINCES)){
            return new PrincesDoll();
        }
        if (dollType.equals(EDolls.FUNKOPOP)){
            return new FunkopopDoll();
        }
        return null;
    }
}
