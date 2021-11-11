public class Amulet extends Item {

    public Amulet(){

    }
    public Amulet(int _level, double _rarity, int _durability){
    level = _level;
    rarity=_rarity;
    durability=_durability;
    }

    public String getMagic() {
        return magic;
    }

    public void setMagic(String magic) {
        this.magic = magic;
    }

    private String magic = "magic";

    @Override
    public String toString(){
        return "\nM:"+getMaterial()+" S:"+getSlot()+" N:"+getName()+"\nL:"+getLevel()+" R:"+getRarity()+" D:"+getDurability()+"\nSm:"+getMagic();}

}
