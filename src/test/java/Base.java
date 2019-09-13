import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Base {

    private static final Scanner scanner = new Scanner(System.in);

    @Test
    @Ignore
    public void BaseTestClassMethod() {
        double r = Math.random();
        if (r < 0.4) {
            aisehi();
        }
    }

    private static void aisehi(){
        final int t = 1;

        for (int tItr = 0; tItr < t; tItr++) {
            final int n = 3;

            final int[][] ladders = new int[n][2];

            ladders[0][0] = 32;
            ladders[0][1] = 62;

            ladders[1][0] = 42;
            ladders[1][1] = 68;

            ladders[2][0] = 12;
            ladders[2][1] = 98;

            final int m = 7;

            final int[][] snakes = new int[m][2];

            snakes[0][0] = 95;
            snakes[0][1] = 13;

            snakes[1][0] = 97;
            snakes[1][1] = 25;

            snakes[2][0] = 93;
            snakes[2][1] = 37;

            snakes[3][0] = 79;
            snakes[3][1] = 27;

            snakes[4][0] = 75;
            snakes[4][1] = 19;

            snakes[5][0] = 49;
            snakes[5][1] = 47;

            snakes[6][0] = 67;
            snakes[6][1] = 17;

            final int result = quickestWayUp(ladders, snakes);

            System.out.println(result);
        }
    }

    private static int quickestWayUp(final int[][] ladders, final int[][] snakes) {

        final HashMap<Integer, Node> treeStructure = new HashMap<>();

        for (int i = 1; i <= 100; i++) {
            treeStructure.put(i, new Node(i));
        }

        for (final int[] ladder : ladders) {
            treeStructure.get(ladder[0]).isLadder = true;
            treeStructure.get(ladder[0]).nextNode = treeStructure.get(ladder[1]);
        }

        for (final int[] snake : snakes) {
            treeStructure.get(snake[0]).isSnake = true;
            treeStructure.get(snake[0]).previousNode = treeStructure.get(snake[1]);
        }

        final ArrayList<Node> localPath = new ArrayList<>();
        localPath.add(treeStructure.get(1));
        return breathFirstSearch(100, 0, treeStructure, localPath);
    }

    private static int breathFirstSearch(final int destination, int currentLevel,
                                         final HashMap<Integer, Node> treeStructure, final ArrayList<Node> currentLevelNodes) {
        while (!currentLevelNodes.isEmpty()) {
            final ArrayList<Node> newList = new ArrayList<>();
            for (final Node currentNode : currentLevelNodes) {
                if (currentNode.nodeValue == destination) {
                    return currentLevel;
                }

                //Check If Ladder than Add to currentLevelNodes
                //Add snakes in the picture to check if going down and coming up will give lesser count
                for (int i = 1; i <= 6 && (currentNode.nodeValue + i) <= 100; i++) {
                    final Node nodeCurrent = treeStructure.get(currentNode.nodeValue + i);
                    if (nodeCurrent.isLadder) {
                        newList.add(nodeCurrent.nextNode);
                    }
                    if (nodeCurrent.isSnake) {
                        newList.add(nodeCurrent.previousNode);
                    }
                }

                //Add the maximum count one after adding 1 to 6 which does not consist of snake. DOnt add if have ladder
                for (int i = 6; i > 0; i--) {
                    if (currentNode.nodeValue + i <= 100) {
                        final Node nodeCurrent = treeStructure.get(currentNode.nodeValue + i);
                        if (!nodeCurrent.isLadder && !nodeCurrent.isSnake) {
                            newList.add(nodeCurrent);
                            System.out.println(newList.get(500));
                            break;
                        }
                    }
                }

            }
            currentLevelNodes.clear();
            currentLevelNodes.addAll(newList);
            currentLevel++;
            if (currentLevel > 36) {
                break;
            }
        }
        return -1;
    }

    static class Node {
        private final int nodeValue;
        private final boolean isVisited = false;
        private boolean isSnake;
        private boolean isLadder;
        private Node nextNode;
        private Node previousNode;

        private Node(final int nodeValue) {
            this.nodeValue = nodeValue;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "nodeValue=" + nodeValue +
                    '}';
        }
    }

}

