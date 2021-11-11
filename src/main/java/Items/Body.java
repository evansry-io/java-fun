package Items;

public class Body extends Item {
    private int weightMod;

    public int getWeightMod() {
        return weightMod;
    }

    public void setWeightMod(int _speedMod) {
        weightMod = _speedMod;
    }

    @Override
    public String toString(){
        return "\nM:"+getMaterial()+" S:"+getSlot()+" N:"+getName()+"\nL:"+getLevel()+" R:"+getRarity()+" D:"+getDurability()+"\nSm:"+getWeightMod();
    }
}
