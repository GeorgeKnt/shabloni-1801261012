package ordering_command_pkg;

import observer_pkg.Observer;
import observer_pkg.Observable;
import toy_factory_pkg.Toys;
import toy_factory_pkg.Toy;
import toy_factory_pkg.ToyFactory;
import toy_factory_pkg.bikes.BikeFactory;
import toy_factory_pkg.dolls.DollFactory;
import java.util.ArrayList;
import java.util.List;

abstract class OrderToy implements IOrderCommand, Observable {

    protected Observer _Santa;
    protected final List<List<Enum>> _ordersList = new ArrayList<>();
    protected final List<Toy> _toysMade = new ArrayList<>();
    protected ToyFactory _toyFactory;
    protected Enum _Toy;
    protected Enum _ToyType;

    protected String _WorkProgress;

    public abstract void order(String name,List<Enum> specifications);

    protected ToyFactory get_toyFactory(Enum toy) {
        ToyFactory factory = null;
        if (toy.equals(Toys.BIKE))
            factory = new BikeFactory();
        if (toy.equals(Toys.DOLL))
            factory = new DollFactory();

        _WorkProgress = toy + " factory initiated";
        notifySanta();
        return factory;
    }

    protected abstract void set_ToysSpecifications(List<Enum> specifications);
    @Override
    public void StartWorkForSanta(Observer santa){
        if (this._Santa == null)
            this._Santa = santa;
    }
    @Override
    public void StopWorkForSanta(Observer santa){
        if (this._Santa != null && this._Santa == santa)
            this._Santa = null;
    }
    @Override
    public void notifySanta() {
        this._Santa.update();
    }
    @Override
    public String getUpdate() {
        return this._WorkProgress;
    }
}
