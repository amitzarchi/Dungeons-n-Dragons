public class QuadTree {
    private QuadNode root;
    private int boardSize;
    int width;
    int height;

    public QuadTree(int x, int y) {
        boardSize = Math.max(x, y);
        width = x;
        height = y;
        root = new QuadNode(1, 1, boardSize);
    }

    public void insert(int x, int y, char value) {
        if (x < 1 || x > boardSize || y < 1 || y > boardSize) {
            throw new IllegalArgumentException("Invalid coordinates");
        }
        root.insert(x, y, value);
    }

    public char get(int x, int y) {
        if (x < 1 || x > boardSize || y < 1 || y > boardSize) {
            throw new IllegalArgumentException("Invalid coordinates");
        }
        return root.get(x, y);
    }

    public void swap(int x1, int y1, int x2, int y2) {
        if (x1 < 1 || x1 > boardSize || y1 < 1 || y1 > boardSize ||
            x2 < 1 || x2 > boardSize || y2 < 1 || y2 > boardSize) {
            throw new IllegalArgumentException("Invalid coordinates");
        }
        char temp = get(x1, y1);
        insert(x1, y1, get(x2, y2));
        insert(x2, y2, temp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 1; y <= height; y++) {
            for (int x = 1; x <= width; x++) {
                sb.append(get(x, y));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private static class QuadNode {
        private QuadNode[] children;
        private char value;
        private int x;
        private int y;
        private int size;

        public QuadNode(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
            children = null;
            value = '.';
        }

        public void insert(int targetX, int targetY, char newValue) {
            if (size == 1) {
                value = newValue;
                return;
            }

            int childSize = size / 2;
            int childX = x;
            int childY = y;
            if (size % 2 == 1) {
                childSize++;
            }

            if (children == null) {
                children = new QuadNode[4];
                children[0] = new QuadNode(childX, childY, childSize);
                children[1] = new QuadNode(childX + childSize, childY, childSize);
                children[2] = new QuadNode(childX, childY + childSize, childSize);
                children[3] = new QuadNode(childX + childSize, childY + childSize, childSize);
            }

            for (int i = 0; i < 4; i++) {
                QuadNode child = children[i];
                int childRight = childX + child.size;
                int childBottom = childY + child.size;

                // Adjust the conditions to include the last row and column for uneven sizes
                if ((targetX >= childX && targetX < childRight) && (targetY >= childY && targetY < childBottom)) {
                    child.insert(targetX, targetY, newValue);
                    break;
                }

                childX += child.size;
                if (i == 1) {
                    childX = x;
                    childY += child.size;
                }
            }
        }

        public char get(int targetX, int targetY) {
            if (size == 1) {
                return value;
            }

            if (children == null) {
                return '.'; // Default value
            }

            for (int i = 0; i < 4; i++) {
                QuadNode child = children[i];
                if (targetX >= child.x && targetX < child.x + child.size &&
                    targetY >= child.y && targetY < child.y + child.size) {
                    return child.get(targetX, targetY);
                }
            }

            return '.'; // Default value
        }
    }
}
