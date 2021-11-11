package Items;

public class Helm extends Item {

    private int sightMod;

    public int getSightMod() {
        return sightMod;
    }

    public void setSightMod(int sightMod) {
        this.sightMod = sightMod;
    }

    @Override
    public String toString(){
        return "\nM:"+getMaterial()+" S:"+getSlot()+" N:"+getName()+"\nL:"+getLevel()+" R:"+getRarity()+" D:"+getDurability()+"\nSm:"+getSightMod();
    }
}
