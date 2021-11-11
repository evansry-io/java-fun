import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class printer {

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

        Map<String,Integer> typeMap = getItemTypeMap();

        while (!ans.equalsIgnoreCase("x")) {

            String equips = "==============================================================\n" +
                    "    |Welcome to the Items.Equipment screen!\n" +
                    "    |Here you can equip and unequip stuff.\n" +
                    "    |\n" +
                    "[Aa]|Amulets\n" +
                    "[Bb]|Body Armor\n" +
                    "[Dd]|Boots\n" +
                    "[Gg]|Gloves\n" +
                    "[Hh]|Helm\n" +
                    "[Ll]|Legs\n" +
                    "[Tt]|TrinketsLH\n"  +
                    "[Rr]|TrinketsRH\n"  +
                    "    |\n" +
                    "[Xx]|X-IT";

            ans = prompt(equips);

            if("abdghltr".contains(ans)) {
                System.out.println("==============================================================\n");
                System.out.println(allItems.get(typeMap.get(ans)));

            }

        }
    }

    private static Map<String,Integer> getItemTypeMap() {

            LinkedHashSet<String> itemTypeSet = Arrays.stream("abdghltr".split("")).collect(Collectors.toCollection(LinkedHashSet::new));
            Map<String, Integer> itemTypeMap = new HashMap<>();
            int i = 0;

            for (String type : itemTypeSet) {
//                System.out.println(type);
                itemTypeMap.put(type, i++);
            }
            return itemTypeMap;
    }

    public static void loadEndScreen(){
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
//        ArrayList<Boots> arr = itemParser.getBoots();
//        e.setBoots(arr.get(0));
//
//        ArrayList<Helm> arr2 = itemParser.getHelms();
//        e.setHelm(arr2.get(0));


        player.setEquips(e);

        return player;

    }

    public static Player loadAdventureScreen(CaveGenerator cg, Player p) throws IOException, ParseException, ClassNotFoundException {
        p = createDefaultChar();
        Location startingRoom = cg.getStartingRoom();
        Location playerStart = new Location(startingRoom.getX() + startingRoom.getWidth()/2, startingRoom.getY() + startingRoom.getHeight()/2,1,1);
        cg.setLocationOnMap(playerStart.getX(), playerStart.getY(), PLAYER_CHAR);
        p.setLocation(playerStart);

        cg.printMap(p);

        return p;
    }

    public static void interact(CaveGenerator cg, Player p){
        String ans = printer.prompt("Action(WSAD,M)").toLowerCase();


        while(!ans.equals("m")){

            playerMovement(cg, p, ans);

            cg.printMap(p);
            ans = printer.prompt("Action(WSAD,M)").toLowerCase();
        }
    }

    private static void playerMovement(CaveGenerator cg, Player p, String ans) {
        int x = p.getX();
        int y = p.getY();
        switch(ans){
            case("s"):
                if(cg.playerCanMoveTo(x+1,y)) {
                    cg.setLocationOnMap(x, y, MAPTILE_CHAR);
                    cg.setLocationOnMap(x + 1, y, PLAYER_CHAR);
                    p.setLocation(new Location(x+1,y,1,1));
                }
                break;
            case("w"):
                if(cg.playerCanMoveTo(x-1,y)) {
                    cg.setLocationOnMap(x, y, MAPTILE_CHAR);
                    cg.setLocationOnMap(x - 1, y, PLAYER_CHAR);
                    p.setLocation(new Location(x-1,y,1,1));
                }
                break;
            case("a"):
                if(cg.playerCanMoveTo(x,y-1)) {
                    cg.setLocationOnMap(x, y, MAPTILE_CHAR);
                    cg.setLocationOnMap(x, y-1, PLAYER_CHAR);
                    p.setLocation(new Location(x,y-1,1,1));
                }
                break;
            case("d"):
                if(cg.playerCanMoveTo(x,y+1)) {
                    cg.setLocationOnMap(x, y, MAPTILE_CHAR);
                    cg.setLocationOnMap(x, y+1, PLAYER_CHAR);
                    p.setLocation(new Location(x,y+1,1,1));
                }
                break;


        }
    }
}
