


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

      /* 
    /// <summary>
    /// Compares the relative position of this position to another position.
    /// 1 = east or more east
    /// 2 = north or more north
    /// 3 = west or more west
    /// 4 = south or more south
    /// </summary>
    public int compareTo(Position other) {
        if (x == other.x && y == other.y) {
            return 0;
        }

        // Calculate the differences in x and y coordinates
        int dx = other.x - x;
        int dy = other.y - y;

        // Determine the relative position
        if (dx > 0 && Math.abs(dx) > Math.abs(dy)) {
            return 1; // East
        } else if (dy < 0 && Math.abs(dy) > Math.abs(dx)) {
            return 2; // North
        } else if (dx < 0 && Math.abs(dx) > Math.abs(dy)) {
            return 3; // West
        } else if (dy > 0 && Math.abs(dy) > Math.abs(dx)) {
            return 4; // South
        } else if (dx > 0 && dy < 0) {
            return Math.abs(dx) > Math.abs(dy) ? 1 : 2; // Northeast (More east or more north)
        } else {
            return Math.abs(dx) > Math.abs(dy) ? 3 : 4; // Southwest (More west or more south)
        }
    }
    */

                
}
