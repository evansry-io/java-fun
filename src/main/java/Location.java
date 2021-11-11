public class Location {
    private int x;
    private int y;
    private int width;
    private int height;

    public Location(int _x, int _y, int _width, int _height){
        x = _x;
        y = _y;
        width = _width;
        height = _height;
    }

    @Override
    public String toString(){
        return "X: "+x + ",Y: "+y + "\nWD: "+width+",HT: "+height+"\n";
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


}
