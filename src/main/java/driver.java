import org.json.simple.parser.ParseException;

import java.io.IOException;

public class driver {

    public static void main(String[] args) throws IOException, ParseException, ClassNotFoundException {
        gameLoop();
    }


    public static void gameLoop() throws IOException, ParseException, ClassNotFoundException {
        String ans = Printer.prompt("Start game?(Yy/Nn)");
        switch (ans.toLowerCase()) {
            case ("y"):
                openMenu();
            case ("n"):
                break;
        }
        Printer.loadEndScreen();
    }


    private static void openMenu() throws IOException, ParseException, ClassNotFoundException {
        Player player = new Player();
        String ans = "";
        while (!ans.equalsIgnoreCase("x")) {
            ans = Printer.prompt("Actions:\n[Aa]|Adventure\n[Bb]|Buy\n[Ee]|Equipment\n[Xx]|X-IT");
            switch (ans.toLowerCase()) {
                case ("a"):
                    ans = "";
                    CaveGenerator cg = new CaveGenerator();
                    player = Printer.loadAdventureScreen(cg, player);
                    Printer.interact(cg, player);
                    break;
                case ("b"):
//                loadBuyScreen();
                    break;
                case ("e"):
                    ans = "";
                    Printer.loadEquipScreen();
                    break;
            }
        }
    }


}
