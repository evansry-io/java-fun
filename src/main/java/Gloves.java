public class Gloves extends Item {
    private int speedMod;

    public int getSpeedMod() {
        return speedMod;
    }

    public void setSpeedMod(int _speedMod) {
        speedMod = _speedMod;
    }

    @Override
    public String toString(){
        return "\nM:"+getMaterial()+" S:"+getSlot()+" N:"+getName()+"\nL:"+getLevel()+" R:"+getRarity()+" D:"+getDurability()+"\nSm:"+getSpeedMod();
    }
}
