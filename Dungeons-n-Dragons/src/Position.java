package Backend;

public class Position<T1, T2> {
    private T1 x;
    private T2 y;
    
    public Position(T1 x, T2 y) {
        this.x = x;
        this.y = y;
    }
    
    public T1 getX() {
        return x;
    }
    
    public T2 getY() {
        return y;
    }
    
    public void setX(T1 x) {
        this.x = x;
    }
    
    public void setY(T2 y) {
        this.y = y;
    }
}
