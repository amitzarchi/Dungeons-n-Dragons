
public class App {
    public static void main(String[] args) throws Exception {
        QuadTree quadTree = new QuadTree(49, 19);

        System.out.println(quadTree.toString());

        quadTree.insert(2, 2, 'X');
        quadTree.insert(4, 1, 'O');
        quadTree.insert(4, 2, 'Z');
        quadTree.insert(4, 3, 'A');
        quadTree.insert(4, 4, 'B');
        quadTree.insert(4, 5, 'C');
        quadTree.insert(4, 6, 'D');
        quadTree.insert(4, 7, 'E');
        quadTree.insert(4, 8, 'F');
        quadTree.insert(17,17, 'G');
        quadTree.insert(18,17, 'H');
        quadTree.insert(19,17, 'I');
        quadTree.insert(20,17, 'J');

        System.out.println(quadTree.toString());
        

        System.out.println(quadTree.get(20,17));
    }
}
