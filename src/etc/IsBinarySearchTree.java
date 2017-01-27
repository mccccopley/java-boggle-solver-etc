package etc;
/**
 * Created by mccccopley on 1/19/2017.
 */

import java.util.*;

public class IsBinarySearchTree {
    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;

        public static ArrayList<Integer> MakeArrayFromTree(BinaryTreeNode root) {
            ArrayList<Integer> treeValues = new ArrayList<>();
            LinkedList<BinaryTreeNode> queueOfNodesToAdd = new LinkedList<>();
            queueOfNodesToAdd.add(root);

            while (!queueOfNodesToAdd.isEmpty()) {
                BinaryTreeNode node = queueOfNodesToAdd.removeFirst();
                treeValues.add(node.value);

                if (node.left != null) {
                    queueOfNodesToAdd.add(node.left);
                }

                if (node.right != null) {
                    queueOfNodesToAdd.add(node.right);
                }
            }

            return treeValues;
        }

        public static BinaryTreeNode MakeTreeFromArrayIndex(int[] values, int nodeIndex) {
            int leftIndex = (nodeIndex * 2) + 1;
            int rightIndex = (nodeIndex * 2) + 2;

            BinaryTreeNode node = new BinaryTreeNode();
            node.value = values[nodeIndex];

            if (leftIndex < values.length) {
                node.left = MakeTreeFromArrayIndex(values, leftIndex);
            }

            if( rightIndex < values.length) {
                node.right = MakeTreeFromArrayIndex(values, rightIndex);
            }

            return node;
        }

        // this will use the strategy of having the children of a node be the indices: 2n + 1, and 2n + 2
        // where n is the parent index
        public static BinaryTreeNode MakeTreeFromArray(int[] values) {
            return MakeTreeFromArrayIndex(values, 0);
        }
    }

    public static class BinaryTreeTestRoot {
        int minValue;
        int maxValue;
        BinaryTreeNode root;

        BinaryTreeTestRoot(int minValue, int maxValue, BinaryTreeNode root) {
            this.minValue = minValue;
            this.maxValue = maxValue;
            this.root = root;
        }
    }

    public static boolean IsBinarySearchTree_V1(BinaryTreeNode root) {
        // use in-order traversal and see if the values remain in sorted order

        int lastValue = Integer.MIN_VALUE;

        Stack<BinaryTreeNode> nodesToProcess = new Stack<BinaryTreeNode>();
        BinaryTreeNode currNode = root;
        while (currNode != null || !nodesToProcess.empty()) {
            while (currNode != null) {
                nodesToProcess.push(currNode);
                currNode = currNode.left;
            }

            if (!nodesToProcess.empty()) {
                BinaryTreeNode nextNode = nodesToProcess.pop();

                if (nextNode.value < lastValue) {
                    return false;
                }

                lastValue = nextNode.value;
                currNode = nextNode.right;
            }
        }

        return true;
    }

    private static boolean IsBinaryTreeWithinRange(BinaryTreeNode root, int minValue, int maxValue) {
        if (root.value < minValue) {
            return false;
        }

        if( root.value > maxValue ) {
            return false;
        }

        if (root.left != null && !IsBinaryTreeWithinRange(root.left, minValue, root.value - 1)) {
            return false;
        }

        if (root.right != null && !IsBinaryTreeWithinRange(root.right, root.value, maxValue)) {
            return false;
        }

        return true;
    }

    public static boolean IsBinarySearchTree_V2(BinaryTreeNode root) {
        // pass down min/max ranges to sub-trees to ensure that the search tree
        // properties are maintained through the tree

        if (root.left != null && !IsBinaryTreeWithinRange(root.left, Integer.MIN_VALUE, root.value - 1)) {
            return false;
        }

        if ( root.right != null && !IsBinaryTreeWithinRange(root.right, root.value, Integer.MAX_VALUE)) {
            return false;
        }

        return true;
    }

    public static boolean IsBinarySearchTree_V2_Iterative(BinaryTreeNode root) {
        Stack<BinaryTreeTestRoot> rootStack = new Stack<BinaryTreeTestRoot>();

        if (root.left != null) {
            rootStack.push(new BinaryTreeTestRoot(Integer.MIN_VALUE, root.value - 1, root.left));
        }

        if (root.right != null) {
            rootStack.push(new BinaryTreeTestRoot(root.value, Integer.MAX_VALUE, root.right));
        }

        while (!rootStack.empty()) {
            BinaryTreeTestRoot node = rootStack.pop();

            if (node.root.value < node.minValue || node.root.value > node.maxValue) {
                return false;
            }

            if( node.root.left != null) {
                rootStack.push(new BinaryTreeTestRoot(node.minValue, node.root.value - 1, node.root.left));
            }

            if (node.root.right != null) {
                rootStack.push(new BinaryTreeTestRoot(node.root.value, node.maxValue, node.root.right));
            }
        }

        return true;
    }
}
