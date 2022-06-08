package ordering_command_pkg;
import toy_factory_pkg.Toy;

import java.util.List;

public class OrderDoll extends OrderToy{

    @Override
    public void order(String name,List<Enum> specifications) {
        this._ordersList.add(specifications);
        set_ToysSpecifications(specifications);
        this.notifySanta();
        if (this._toyFactory != get_toyFactory(this._Toy))
            this._toyFactory = get_toyFactory(this._Toy);
        Toy toy = _toyFactory.createToy(_ToyType);
        toy.setName(name);
        this._WorkProgress = "Toy: " + name + " has been created.";
        this._toysMade.add(toy);
    }

    @Override
    protected void set_ToysSpecifications(List<Enum> specifications) {
        for (int i = 0; i < specifications.stream().count(); i++)
        switch (i){
            case 0 : this._Toy = specifications.get(i);
            case 1 : this._ToyType = specifications.get(i);
        }
        this._WorkProgress = "Toy specifications are set: " + this._Toy + " " + this._ToyType + ".";
    }
}
