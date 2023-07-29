public class QuadNode {
        private QuadNode[] children;
        private Tile value;
        private int x;
        private int y;
        private int size;
        private GameObserver observer;

        public QuadNode(int x, int y, int size, GameObserver observer) {
            this.x = x;
            this.y = y;
            this.size = size;
            children = null;
            this.observer = observer;
            value = new Empty();
            value.setPositionWithoutNotify(new Position(x, y));
        }

        public void insert(int targetX, int targetY, Tile newValue) {
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
                children[0] = new QuadNode(childX, childY, childSize, observer);
                children[1] = new QuadNode(childX + childSize, childY, childSize, observer);
                children[2] = new QuadNode(childX, childY + childSize, childSize, observer);
                children[3] = new QuadNode(childX + childSize, childY + childSize, childSize, observer);
            }

            for (int i = 0; i < 4; i++) {
                QuadNode child = children[i];
                int childRight = childX + child.size;
                int childBottom = childY + child.size;

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

        public Tile get(int targetX, int targetY) {
            if (size == 1) {
                return value;
            }

            if (children == null) {
                Empty empty = new Empty();
                empty.initialize(new Position(targetX, targetY), observer);
                return empty;
            }

            for (int i = 0; i < 4; i++) {
                QuadNode child = children[i];
                if (targetX >= child.x && targetX < child.x + child.size &&
                    targetY >= child.y && targetY < child.y + child.size) {
                    return child.get(targetX, targetY);
                }
            }

            Empty empty = new Empty();
            empty.initialize(new Position(targetX, targetY), observer);
            return empty;
        }

        public char getChar() {
            return value.getChar();
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }