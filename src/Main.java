import singleton_pkg.Santa;

public class Main {
    public static void main(String[] args) {

        Santa santa = Santa.getInstance();

        santa.iWantForChristmas("George", "I wish for a Doll, best to be a Funkopop");
        santa.iWantForChristmas("Stoyan","i wish a big blue racing bike");
        santa.iWantForChristmas("Petar","i wish for a small white mountain bike");

    }
}
