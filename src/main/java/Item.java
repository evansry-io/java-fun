public class Item {

    String slot;
    String material;
    String name;
    int level;
    double rarity;
    int durability;
//    Trait[] traits = new Trait[0];


    public String getSlot() {
        return slot;
    }

    public int getLevel() {
        return level;
    }

    public double getRarity() {
        return rarity;
    }

    public int getDurability() {
        return durability;
    }

    public void setSlot(String _slot) {
        slot = _slot;
    }

    public void setMaterial(String _material) {
        material = _material;
    }
    public String getMaterial() {
        return material;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setRarity(double rarity) {
        this.rarity = rarity;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

//    public Trait[] getTraits() {
//        return traits;
//    }
//
//    public void setTraits(Trait[] traits) {
//        this.traits = traits;
//    }


    @Override
    public String toString(){
        return "\nM:"+getMaterial()+" S:"+getSlot()+" N:"+getName()+"\nL:"+getLevel()+" R:"+getRarity()+" D:"+getDurability();
    }

}
