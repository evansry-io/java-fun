import org.json.simple.parser.ParseException;

import java.io.IOException;

public class driver {

    public static void main(String[] args) throws IOException, ParseException, ClassNotFoundException {
        gameLoop();
    }


    public static void gameLoop() throws IOException, ParseException, ClassNotFoundException {
        String ans = printer.prompt("Start game?(Yy/Nn)");
        switch (ans.toLowerCase()) {
            case ("y"):
                openMenu();
            case ("n"):
                break;
        }
        printer.loadEndScreen();
    }


    private static void openMenu() throws IOException, ParseException, ClassNotFoundException {
        Player player = new Player();
        String ans = "";
        while (!ans.equalsIgnoreCase("x")) {
            ans = printer.prompt("Actions:\n[Aa]|Adventure\n[Bb]|Buy\n[Ee]|Items.Equipment\n[Xx]|X-IT");
            switch (ans.toLowerCase()) {
                case ("a"):
                    ans = "";
                    CaveGenerator cg = new CaveGenerator();
                    player = printer.loadAdventureScreen(cg, player);
                    printer.interact(cg, player);
                    break;
                case ("b"):
//                loadBuyScreen();
                    break;
                case ("e"):
                    ans = "";
                    printer.loadEquipScreen();
                    break;
            }
        }
    }


}
