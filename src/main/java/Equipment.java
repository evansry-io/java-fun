import Items.*;

public class Equipment {

    private Helm helm;
    private Body body;
    private Legs legs;
    private Gloves gloves;
    private TrinketLH trinketLH;
    private TrinketRH trinketRH;
    private Amulet amulet;
    private Boots boots;

    public Boots getBoots() {
        return boots;
    }

    public void setBoots(Boots boots) {
        this.boots = boots;
    }

    public Helm getHelm() {
        return helm;
    }

    public Body getBody() {
        return body;
    }

    public Legs getLegs() {
        return legs;
    }

    public Gloves getGloves() {
        return gloves;
    }

    public TrinketLH getTrinketLH() {
        return trinketLH;
    }

    public TrinketRH getTrinketRH() {
        return trinketRH;
    }

    public Amulet getAmulet() {
        return amulet;
    }

    public void setHelm(Helm helm) {
        this.helm = helm;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public void setLegs(Legs legs) {
        this.legs = legs;
    }

    public void setGloves(Gloves gloves) {
        this.gloves = gloves;
    }

    public void setTrinketLH(TrinketLH trinketLH) {
        this.trinketLH = trinketLH;
    }

    public void setTrinketRH(TrinketRH trinketRH) {
        this.trinketRH = trinketRH;
    }

    public void setAmulet(Amulet amulet) {
        this.amulet = amulet;
    }
}
