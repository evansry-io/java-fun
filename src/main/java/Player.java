public class Player {

    private Equipment equips;
    private String name;
    private Location location;

    public int getX(){
        return location.getX();
    }
    public int getY(){
        return location.getY();
    }
    public void setLocation(Location _loc){
        location = _loc;
    }
    public Location getLocation(){ return location; }

    public void setEquips(Equipment equips) {
        this.equips = equips;
    }

    public Equipment getEquips() {
        return equips;
    }
}
