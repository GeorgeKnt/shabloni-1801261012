package singleton_pkg;

import observer_pkg.Observer;
import observer_pkg.Observable;
import ordering_command_pkg.IOrderCommand;
import ordering_command_pkg.OrderBike;
import ordering_command_pkg.OrderDoll;
import toy_factory_pkg.Toys;
import toy_factory_pkg.bikes.BikeSizes;
import toy_factory_pkg.bikes.Bikes;
import toy_factory_pkg.bikes.BikesColors;
import toy_factory_pkg.dolls.EDolls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Santa implements Observer {
    private static Santa instance;

    private Observable dwarf;
    private List<String> workProgress = new ArrayList<>();
    IOrderCommand _OrderBike;
    IOrderCommand _OrderDoll;
    private static List<Enum> _ToySpecifics= new ArrayList<>();

    private Santa(){
        _OrderDoll = new OrderDoll();
        _OrderBike = new OrderBike();
    }

    public static Santa getInstance(){
        if (instance == null){
            instance = new Santa();
        }
        return instance;
    }

    @Override
    public void update(){
        if (this.workProgress.isEmpty()){
            System.out.println("No Work has been done yet");
            return;
        }
        String update = this.dwarf.getUpdate();
        workProgress.add(update);
        System.out.println(update);
    }

    @Override
    public void setDwarf(Observable dwarf) {
        this.dwarf = dwarf;

    }

    public void iWantForChristmas(String yourName, String wish){
        this.workProgress.clear();
        this.workProgress.add("Wish has been received.");
        System.out.println(workProgress.get(0));
        Toys toy = SantaUtils.getToyType(wish);
        Enum type = SantaUtils.getToySpecificType(toy,wish);
        String name = SantaUtils.nameGenerator(yourName, toy,type);
        _ToySpecifics.add(toy);
        _ToySpecifics.add(type);

        if (toy.equals(Toys.BIKE)){
            BikesColors color = SantaUtils.getBikeColor(wish);
            BikeSizes size = SantaUtils.getBikeSize(wish);
            _ToySpecifics.add(color);
            _ToySpecifics.add(size);
        }


        IOrderCommand orderCommand = getOrderCommand(toy);
        setDwarf((Observable) orderCommand);

        dwarf.StartWorkForSanta(getInstance());

        this.workProgress.add("Setting order for dwarfs");
        System.out.println(workProgress.get(1));

        orderCommand.order(name,_ToySpecifics);

        dwarf.StopWorkForSanta(getInstance());
    }

    private IOrderCommand getOrderCommand(Toys toy){
        if (toy.equals(Toys.BIKE)){
            return _OrderBike;
        }
        if (toy.equals(Toys.DOLL)){
            return _OrderDoll;
        }
        return null;
    }
}

class SantaUtils {
    private static Random rand = new Random();

    private static final List<Bikes> _Bikes = Arrays.stream(Bikes.values()).toList();
    private static final List<BikesColors> _BikeColors = Arrays.stream(BikesColors.values()).toList();
    private static final List<BikeSizes> _BikeSize = Arrays.stream(BikeSizes.values()).toList();

    private static final List<EDolls> _Dolls = Arrays.stream(EDolls.values()).toList();

    static String nameGenerator(String wisher, Toys toyType, Enum specificType){
        String name = wisher + "'s ";

        if (toyType.equals(Toys.BIKE)){
            name += specificType.toString() + " Bike: " + rand.nextInt(10000);
        }
        if (toyType.equals(Toys.DOLL)){
            name += specificType.toString() + " #" + rand.nextInt(6000);
        }

        return name;
    }
    static Toys getToyType(String text){
        if (text.toLowerCase().contains(Toys.BIKE.toString().toLowerCase())){
            return Toys.BIKE;
        }
        if (text.toLowerCase().contains(Toys.DOLL.toString().toLowerCase())){
            return Toys.DOLL;
        }
        switch (rand.nextInt(2)){
            case 1: return Toys.BIKE;
            case 2: return Toys.DOLL;
            default: return null;
        }
    }

    static Enum getToySpecificType(Toys toy, String text){
        if (toy.equals(Toys.BIKE)){
            for (int i=0; i < _Bikes.size(); i++){
                if (text.toLowerCase().contains(_Bikes.toString().toLowerCase())){
                    return _Bikes.get(i);
                }
            }
            return _Bikes.get(rand.nextInt(_Bikes.size()));
        }
        if (toy.equals(Toys.DOLL)){
            for (int i=0;i<_Dolls.size();i++){
                if (text.toLowerCase().contains(_Dolls.toString().toLowerCase())){
                    return _Dolls.get(i);
                }
            }
            return _Dolls.get(rand.nextInt(_Dolls.size()));
        }
        return null;
    }

    static BikesColors getBikeColor(String text) {
        for (int i = 0; i < _BikeColors.size(); i++){
            if (text.toLowerCase().contains(_BikeColors.get(i).toString().toLowerCase())){
                return _BikeColors.get(i);
            }
        }
        return _BikeColors.get(rand.nextInt(_BikeColors.size()));
    }

    static BikeSizes getBikeSize(String text){
        for (int i = 0; i < _BikeSize.size(); i++){
            if (text.toLowerCase().contains(_BikeSize.get(i).toString().toLowerCase())){
                return _BikeSize.get(i);
            }
        }
        return _BikeSize.get(rand.nextInt(_BikeSize.size()));
    }

}
