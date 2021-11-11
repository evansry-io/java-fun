import Items.Item;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

class Printer {

    public static final char MAPTILE_CHAR = '.';
    public static final char ITEMBAG_CHAR = '¸';
    public static final char PLAYER_CHAR = '@';

    public static String prompt(String message) {
        System.out.println(message);
        Scanner reader = new Scanner(System.in);
        String answer = "";
        if (reader.hasNext()) {
            answer = reader.nextLine();
        }
        return answer;
    }

    public static void loadEquipScreen() throws IOException, ParseException, ClassNotFoundException {
        String ans = "";
        ItemMapper itemMapper = new ItemMapper();
        ArrayList<ArrayList<Item>> allItems = itemMapper.getItems();

        Map<String, Integer> typeMap = getItemTypeMap();

        while (!ans.equalsIgnoreCase("x")) {

            String equips = "==============================================================\n" +
                    "    |Welcome to the Equipment screen!\n" +
                    "    |Here you can equip and unequip stuff.\n" +
                    "    |\n" +
                    "[Aa]|Amulets\n" +
                    "[Bb]|Body Armor\n" +
                    "[Dd]|Boots\n" +
                    "[Gg]|Gloves\n" +
                    "[Hh]|Helm\n" +
                    "[Ll]|Legs\n" +
                    "[Tt]|TrinketsLH\n" +
                    "[Rr]|TrinketsRH\n" +
                    "    |\n" +
                    "[Xx]|X-IT";

            ans = prompt(equips);

            if ("abdghltr".contains(ans)) {
                System.out.println("==============================================================\n");
                System.out.println(allItems.get(typeMap.get(ans)));

            }

        }
    }

    private static Map<String, Integer> getItemTypeMap() {

        LinkedHashSet<String> itemTypeSet = Arrays.stream("abdghltr".split("")).collect(Collectors.toCollection(LinkedHashSet::new));
        Map<String, Integer> itemTypeMap = new HashMap<>();
        int i = 0;

        for (String type : itemTypeSet) {
//                System.out.println(type);
            itemTypeMap.put(type, i++);
        }
        return itemTypeMap;
    }

    public static void loadEndScreen() {
        System.out.println("      * ***                                                     * ***                                        \n" +
                "    *  ****  *                                                *  ****                                        \n" +
                "   *  *  ****                                                *  *  ***   **                                  \n" +
                "  *  **   **                                                *  **   ***  **                                  \n" +
                " *  ***                                                    *  ***    ***  **    ***             ***  ****    \n" +
                "**   **             ****    *** **** ****       ***       **   **     **   **    ***     ***     **** **** * \n" +
                "**   **   ***      * ***  *  *** **** ***  *   * ***      **   **     **   **     ***   * ***     **   ****  \n" +
                "**   **  ****  *  *   ****    **  **** ****   *   ***     **   **     **   **      **  *   ***    **         \n" +
                "**   ** *  ****  **    **     **   **   **   **    ***    **   **     **   **      ** **    ***   **         \n" +
                "**   ***    **   **    **     **   **   **   ********     **   **     **   **      ** ********    **         \n" +
                " **  **     *    **    **     **   **   **   *******       **  **     **   **      ** *******     **         \n" +
                "  ** *      *    **    **     **   **   **   **             ** *      *    **      *  **          **         \n" +
                "   ***     *     **    **     **   **   **   ****    *       ***     *      *******   ****    *   ***        \n" +
                "    *******       ***** **    ***  ***  ***   *******         *******        *****     *******     ***       \n" +
                "      ***          ***   **    ***  ***  ***   *****            ***                     *****                \n" +
                "                                                                                                             ");
    }

    private static Player createDefaultChar() throws IOException, ParseException, ClassNotFoundException {
        Player player = new Player();
        Equipment e = new Equipment();
        ItemMapper itemMapper = new ItemMapper();

//        System.out.println(itemMapper.getItems());
//        ArrayList<Items.Boots> arr = itemParser.getBoots();
//        e.setBoots(arr.get(0));
//
//        ArrayList<Items.Helm> arr2 = itemParser.getHelms();
//        e.setHelm(arr2.get(0));


        player.setEquips(e);

        return player;

    }

    public static Player loadAdventureScreen(CaveGenerator cg, Player p) throws IOException, ParseException, ClassNotFoundException {
        p = createDefaultChar();
        Location startingRoom = cg.getStartingRoom();
        Location playerStart = new Location(startingRoom.getX() + startingRoom.getWidth() / 2, startingRoom.getY() + startingRoom.getHeight() / 2, 1, 1);
        cg.setLocationOnMap(playerStart.getX(), playerStart.getY(), PLAYER_CHAR);
        p.setLocation(playerStart);

        cg.printMap(p);

        return p;
    }

    public static void interact(CaveGenerator cg, Player p) {
        String ans = Printer.prompt("Action(WSAD,M)").toLowerCase();


        while (!ans.equals("m")) {

            playerMovement(cg, p, ans);
            playerAction(cg, p, ans);
            cg.printMap(p);
            if (cg.hasItems(p.getX(), p.getY())) {
                ans = Printer.prompt("Action(WSAD,M,I to inspect ItemBag)").toLowerCase();
            } else
                ans = Printer.prompt("Action(WSAD,M)").toLowerCase();
        }
    }

    private static void playerMovement(CaveGenerator cg, Player p, String ans) {
        int x = p.getX();
        int y = p.getY();
        switch (ans) {
            case ("s"):
                if (cg.playerCanMoveTo(x + 1, y)) {
                    cg.setLocationOnMap(x, y, MAPTILE_CHAR);
                    cg.setLocationOnMap(x + 1, y, PLAYER_CHAR);
                    p.setLocation(new Location(x + 1, y, 1, 1));
                }
                break;
            case ("w"):
                if (cg.playerCanMoveTo(x - 1, y)) {
                    cg.setLocationOnMap(x, y, MAPTILE_CHAR);
                    cg.setLocationOnMap(x - 1, y, PLAYER_CHAR);
                    p.setLocation(new Location(x - 1, y, 1, 1));
                }
                break;
            case ("a"):
                if (cg.playerCanMoveTo(x, y - 1)) {
                    cg.setLocationOnMap(x, y, MAPTILE_CHAR);
                    cg.setLocationOnMap(x, y - 1, PLAYER_CHAR);
                    p.setLocation(new Location(x, y - 1, 1, 1));
                }
                break;
            case ("d"):
                if (cg.playerCanMoveTo(x, y + 1)) {
                    cg.setLocationOnMap(x, y, MAPTILE_CHAR);
                    cg.setLocationOnMap(x, y + 1, PLAYER_CHAR);
                    p.setLocation(new Location(x, y + 1, 1, 1));
                }
                break;
        }
    }

    private static void playerAction(CaveGenerator cg, Player p, String ans) {
        int x = p.getX();
        int y = p.getY();
        switch (ans) {
            case ("i"):
                if (cg.hasItems(x, y)) {
                    printItemBag(cg, x, y);
                }
                break;
            case ("x"):
                return;
        }
    }


    private static void printItemBag(CaveGenerator cg, int x, int y) {
        ArrayList<String> items = cg.getItems(x, y);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        items.forEach(item -> System.out.printf("[%s] %s\n", items.indexOf(item),item));

        String itemPrompt = String.format("\n>>>Would you like to take any items?\n%s, Aa, Xx]", items.size() > 1 ? "[0..%s" :"[0" );
        String ans = prompt(String.format(itemPrompt, items.size() - 1));

        try {
            if (ans.equals("a")) {
                System.out.print("You grabbed the contents from the item bag");
                cg.setLocationOnMap(x, y, PLAYER_CHAR);
                cg.removeAllItemsFromItemBag(x, y);

            } else if (Integer.parseInt(ans) > -1 && Integer.parseInt(ans) < items.size()) {
                System.out.printf("You grabbed a %s",items.get(Integer.parseInt(ans)));
                if (cg.getItems(x, y).size() == 1) {
                    cg.setLocationOnMap(x, y, PLAYER_CHAR);
                    cg.removeAllItemsFromItemBag(x, y);
                } else
                    cg.removeItemFromItemBag(Integer.parseInt(ans), x, y);
            }
        } catch (NumberFormatException nfe) {
        }

    }

}
