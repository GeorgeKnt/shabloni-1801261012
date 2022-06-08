package toy_factory_pkg.bikes;

import toy_factory_pkg.Toy;
import toy_factory_pkg.ToyFactory;

public class BikeFactory extends ToyFactory {
    @Override
    public Toy createToy(Enum bikeType){
        if (bikeType == null){
            return null;
        }
        if (bikeType.equals(Bikes.MOUNTAIN)){
            return new MountainBike();
        }
        if (bikeType.equals(Bikes.CITY)){
            return new CityBike();
        }
        if (bikeType.equals(Bikes.RACING)){
            return new RacingBike();
        }
        return null;
    }
}
