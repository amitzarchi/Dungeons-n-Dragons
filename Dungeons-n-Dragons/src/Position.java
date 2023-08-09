


public class Position {

    private Integer x;
    private Integer y;
    
    public Position(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
    
    public Integer getX() {
        return x;
    }
    
    public Integer getY() {
        return y;
    }
    
    public void setX(Integer x) {
        this.x = x;
    }
    
    public void setY(Integer y) {
        this.y = y;
    }

    public Position up() {
        return new Position(x, y - 1);
    }

    public Position down() {
        return new Position(x, y + 1);
    }

    public Position left() {
        return new Position(x - 1, y);
    }

    public Position right() {
        return new Position(x + 1, y);
    }
    
    /// <summary>
    /// Compares the relative position of this position to another position.
    /// [0] = x difference, positive if other is east of this
    /// [1] = y difference, positive if other is south of this
    /// </summary>
    public int[] compareTo(Position other)
    {
        int[] result = new int[2];
        result[0] = other.x - x;
        result[1] = other.y - y;
        return result;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public int distance(Position other) {
        int[] result = compareTo(other);
        return (int) Math.sqrt(result[0] * result[0] + result[1] * result[1]);
    }

    public boolean equals(Position other) {
        return x == other.x && y == other.y;
    }
                
}
