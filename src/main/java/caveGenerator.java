import java.util.ArrayList;

public class CaveGenerator {
    public static final char MAPTILE_CHAR = '.';
    public static final char ITEMBAG_CHAR = '¸';
    private int ht = 30;
    private int wd = 120;
    private char[][] map;
    private int[][] fogOfWar;
    private int[][] itemBags;
//    private ArrayList<ItemBag> itemBags;
    private ArrayList<Location> rooms;

    public CaveGenerator() {
        init();
        generateNumberOfRooms(4, 4, 16, 5, 8);
        connectRooms(false);
//        printMap();
    }

    private void init() {
        map = new char[ht][wd];
        fogOfWar = new int[ht][wd];
        itemBags = new int[ht][wd];

        rooms = new ArrayList<>();
        for (int i = 0; i < ht; i++) {
            for (int j = 0; j < wd; j++) {
                map[i][j] = ' ';
                fogOfWar[i][j] = 0;
            }
        }
    }

    public Location getStartingRoom() {
        return rooms.get((int) Math.floor(Math.random() * rooms.size()));
    }

    public void setLocationOnMap(int x, int y, char c) {
        map[x][y] = c;
    }

    public boolean playerCanMoveTo(int x, int y) {
        return map[x][y] != ' ';
    }

    public void printMap(Player p) {

        itemBags[p.getX()][p.getY()+1] = 1;
        setFogOfWar(p.getLocation());
        for (int i = 0; i < ht; i++) {
            for (int j = 0; j < wd; j++) {
//                if(itemBags[i][j] == 1){
//                    map[i][j] = ITEMBAG_CHAR;
//                }

                if (fogOfWar[i][j] == 1) {
                    System.out.print(map[i][j]);
                } else
                    System.out.print(' ');
            }
            System.out.print("\n");
        }
    }

    public void setFogOfWar(Location playerLoc) {
        int sightDist = 4;
        for (int i = playerLoc.getX() - sightDist; i < playerLoc.getX() + sightDist; i++) {
            for (int j = playerLoc.getY() - sightDist; j < playerLoc.getY() + sightDist; j++) {
                if(i>0&& i<ht && j>0 && j<wd)
                fogOfWar[i][j] = 1;
            }
        }

    }

    private void generateNumberOfRooms(int x, int y, int dx, int dy, int times) {
        int actualX = (int) Math.floor(Math.random() * dx) + x;
        int actualY = (int) Math.floor(Math.random() * dy) + y;

        int ct = 0;
        while (ct++ < times) {
            generateRoom(actualY, actualX);
        }
//        rooms.forEach(loc -> System.out.println(loc.toString()));

    }

    private void generateRoom(int a, int b) {
        if (a > 0 && b > 0 && a < 20 && b < 20) {
            int rht = (int) Math.floor(Math.random() * a) + 4;
            int rwd = (int) Math.floor(Math.random() * b) + 4;
            int x = (int) Math.floor(Math.random() * ht);
            int y = (int) Math.floor(Math.random() * wd);

//            System.out.println(rht + "," + rwd + "\n" + x + "," + y);
//            System.out.println((int)(rht+x) + "<"+ ht + "\n" + (int)(rwd+y) + "<" + wd);

            while (rht + x > ht || rwd + y > wd) {
                x = (int) Math.floor(Math.random() * ht);
                y = (int) Math.floor(Math.random() * wd);
            }


            if (rht + x > 0 && rht + x < ht && rwd + y > 0 && rwd + y < wd) {
                for (int i = x; i < rht + x; i++) {
                    for (int j = y; j < rwd + y; j++) {
                        map[i][j] = MAPTILE_CHAR;
                    }
                }
                rooms.add(new Location(x, y, rht, rwd));
            }
        }
    }

    private void connectRooms(boolean hasDiagonalCorridors) {
        int index = 0;
        for (Location room : rooms) {
            index++;
            if (index < rooms.size()) {

                int nextRoomCornerX = rooms.get(index).getX();
                int nextRoomCornerY = rooms.get(index).getY();

                int nextRoomMidX = nextRoomCornerX + ((rooms.get(index).getWidth() - 1) / 2);
                int nextRoomMidY = nextRoomCornerY + ((rooms.get(index).getHeight() - 1) / 2);


                int x = room.getX() + (room.getWidth() / 2);
                int y = room.getY() + (room.getHeight() / 2);
                int targetX = (Math.random() * 10 > 5) ? nextRoomCornerX : nextRoomMidX;
                int targetY = (Math.random() * 10 > 5) ? nextRoomCornerY : nextRoomMidY;

                while (x != targetX || y != targetY) {

                    if (x < targetX) {
                        x++;
                    }
                    if (x > targetX) {
                        x--;
                    }

                    map[x][y] = MAPTILE_CHAR;


                    if (y < targetY) {
                        y++;
                    }
                    if (y > targetY) {
                        y--;
                    }

                    map[x][y] = MAPTILE_CHAR;

                    if (x - 1 > 0) {
                        map[x - 1][y] = MAPTILE_CHAR;
                    }
                    if (y - 1 > 0) {
                        map[x][y - 1] = MAPTILE_CHAR;
                    }
                }
            }
        }
    }


}
