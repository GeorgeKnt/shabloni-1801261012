package ordering_command_pkg;

import java.util.List;

public interface IOrderCommand {
    void order(String name,List<Enum> specifications);

}
