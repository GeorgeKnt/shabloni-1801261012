package observer_pkg;

public interface Observable {

    void StartWorkForSanta(Observer Santa);
    void StopWorkForSanta(Observer Santa);
    void notifySanta();
    String getUpdate();
}
