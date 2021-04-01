/*
 * File: BinaryTree.java
 * Author: Ben Sutter
 * Date: September 22, 2020
 * Purpose: Hold a static nested class (Node) and various methods that will be used in the GUI
 */

package project3;

import static java.lang.Integer.max;
import java.util.EmptyStackException;
import java.util.Stack;

public class BinaryTree {

    private Node root;
    private Node child;

    private int nodeCount;//This count goes up as the string is read

    public BinaryTree(String input) throws InvalidTreeSyntax {
        String[] stringArray = input.split("");//Creates array from string

        root = new Node(stringArray[1]);//Assigns value to root
        Stack<Node> nodeStack = new Stack();//Creates a node stack for use later

        //Checks every character in array to make sure there is no invalid input
        for (int i = 0; i < stringArray.length; i++) {

            //Increases the node count if there is an alphanumerical character
            if (Character.isLetter(stringArray[i].charAt(0)) == true
                    || Character.isDigit(stringArray[i].charAt(0)) == true) {
                nodeCount++;
            }

            //Throws exception if the character is not alphanumeric or parentheses
            if (Character.isLetter(stringArray[i].charAt(0)) == false
                    && Character.isDigit(stringArray[i].charAt(0)) == false
                    && stringArray[i].contains("(") == false
                    && stringArray[i].contains(")") == false) {
                throw new InvalidTreeSyntax(stringArray[i] + " is an invalid character "
                        + "\n The only characters are alphanumeric or parentheses");

            }
        }

        //Will throw exception if there are no nodes (invalidsytax)
        if (nodeCount == 0) {
            throw new InvalidTreeSyntax("The tree has no nodes, please try again with a valid tree");
        }

        //Runs through the array at the 2nd characte (the parenthsis before node #2) and stops before the last node (the end parenthesis)
        for (int i = 2; i < stringArray.length - 1; i++) {
            //If the current character is a left paranthesis, it means the current root should be put on the stack
            if (stringArray[i].contains("(") == true) {
                nodeStack.push(root);
                //If child has a value, it will be the new root.
                if (child != null) {
                    root = child;
                }
                //A right parenthsis indicates that the root should transfer to the child, while the root equals the previous pop 
            } else if (stringArray[i].contains(")") == true) {
                child = root;
                root = nodeStack.pop();
                //If it is not a parenthesis, that means it is the next node        
            } else {
                child = new Node(stringArray[i]);
                if (root != null) {
                    root.setChild(child);
                }
            }//End if/else if/else loop
        }//End for
    }//End BinaryTree constructor

    //Static nested class that defines the nodes if the binary tree
    private static class Node {

        private String value;
        private Node left;
        private Node right;

        public Node(String value) {
            this.value = value;
        }

        private void addLeft(Node left) {
            this.left = left;
        }

        private void addRight(Node right) {
            this.right = right;
        }

        //Makes sure that the left child will always be filled first, if the left has a child the next child will go the the right node
        private void setChild(Node children) throws InvalidTreeSyntax {
            if (this.left == null) {
                this.addLeft(children);
            } else if (this.right == null) {
                this.addRight(children);
            } else {
                throw new InvalidTreeSyntax("Nodes may only have two children");
            }
        }

    }//Code for Node class inspired by: https://www.programiz.com/java-programming/examples/binary-tree-implementation
    //And: https://www.baeldung.com/java-binary-tree

    //Allows access to root in Project3
    public Node getRoot() {
        return root;
    }

    //When the constructer saw an alphanumeric character, nodeCount went up. All that is left is to retrieve the value of nodeCount
    public int getNodeCount() {
        return nodeCount;
    }

    //Compare the maximum number of nodes available in a tree of its height to the actualy number of nodes
    public boolean isFull() {
        int maxNodes = (int) Math.pow(2, getHeight(root)) - 1;//The equation is 2^(height + 1) but height is already +1
        if (maxNodes == getNodeCount()) {
            return true;
        } else {
            return false;
        }
    }//Equation for max nodes from: https://www.geeksforgeeks.org/relationship-number-nodes-height-binary-tree/ 

    //Recursive method convert node into the inorder traversal
    public String inOrder(Node root) {
        return (root == null) ? "" : "(" + inOrder(root.left) + " " + root.value + " " + inOrder(root.right) + ")";
    }

    //Runs through both sides of the tree to find their height, whichever side is bigger determines the height of the tree
    public int getHeight(Node node) {
        if (node == null) {
            return 0;
        } else {
            int leftHeight = getHeight(node.left);
            int rightHeight = getHeight(node.right);
            return max(leftHeight + 1, rightHeight + 1);
        }
    }//Code for method heavily inspired by: https://www.geeksforgeeks.org/write-a-c-program-to-find-the-maximum-depth-or-height-of-a-tree/

    //Compares the left and right heights of the tree to see their difference
    public boolean isBalanced(Node node) {
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        //If the difference in height between the right and the left is greater than 1, it is unbalanced.
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        } else {
            return true;
        }
    }

    //Checks the children of nodes to make sure they are proper
    public boolean isProper(Node node) {
        //If there are no children, it is proper
        if (node.left == null && node.right == null) {
            return true;
        }
        //If both trees are not null (have node) then they are proper
        if ((node.left != null) && (node.right != null)) {
            return (isProper(node.left) && isProper(node.right));
        }
        //If they are not both empty or full, they are not proper
        return false;
    }//Method retrieved from: https://www.geeksforgeeks.org/check-whether-binary-tree-full-binary-tree-not/

}//End BinaryTree.java
