/*
 * File: Project3.java
 * Author: Ben Sutter
 * Date: September 22, 2020
 * Purpose: Create a GUI that used buttons to invoke methods from BinaryTree.java
 */
package project3;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Project3 extends JFrame {

    //Initializes GUI components
    private final JPanel inputPanel = new JPanel();
    private final JPanel outputPanel = new JPanel();
    private final JPanel buttonPanel = new JPanel();

    private final JLabel inputLabel = new JLabel("Enter Tree:");
    private final JLabel outputLabel = new JLabel("Output");
    private final JTextField inputText = new JTextField(49);
    private final JTextField outputText = new JTextField(51);

    private final JButton treeButton = new JButton("Make Tree");
    private final JButton balancedButton = new JButton("Is Balanced?");
    private final JButton fullButton = new JButton("Is Full?");
    private final JButton properButton = new JButton("Is Proper?");
    private final JButton heightButton = new JButton("Height");
    private final JButton nodeButton = new JButton("Nodes");
    private final JButton inorderButton = new JButton("Inorder");

    public Project3() {

        //Adds expression components to the first panel, and places it north on the GUI
        inputPanel.add(inputLabel);
        inputPanel.add(inputText);
        add("North", inputPanel);

        //Adds the calculation components to the panel, then adds the panel to the frame
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(treeButton);
        treeButton.addActionListener(new treeButtonPress());
        buttonPanel.add(balancedButton);
        balancedButton.addActionListener(new balancedButtonPress());
        buttonPanel.add(fullButton);
        fullButton.addActionListener(new fullButtonPress());
        buttonPanel.add(properButton);
        properButton.addActionListener(new properButtonPress());
        buttonPanel.add(heightButton);
        heightButton.addActionListener(new heightButtonPress());
        buttonPanel.add(nodeButton);
        nodeButton.addActionListener(new nodeButtonPress());
        buttonPanel.add(inorderButton);
        inorderButton.addActionListener(new inorderButtonPress());
        add(buttonPanel);//Adds all of the buttons to the panel

        outputPanel.add(outputLabel);
        outputPanel.add(outputText);
        outputText.setEditable(false);
        add("South", outputPanel);

        setTitle("Binary Tree Categorizer");//Sets title of window

        setLocationRelativeTo(null); //Centers GUI on screen
        setVisible(true);//Makes frame viewable
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Ends program when x button is pressed
        pack();//Packs all components onto the frame
    }//End GUI

    private BinaryTree tree;//Creates a tree to be created in treeButtonPress

    //Assigns value to tree and gives confirmation if sucessful. Catches exception from BinaryTree.java
    public class treeButtonPress implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                tree = new BinaryTree(inputText.getText());
                outputText.setText("Tree sucessfully created");
            } catch (InvalidTreeSyntax e) {
                JOptionPane.showMessageDialog(null, "Invalid string supplied" + e,
                        "InvalidTreeSyntax", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    //If tree is not null, calls isBalanced from BinaryTree.java to determine if it is balanced or not
    public class balancedButtonPress implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (tree == null) {
                outputText.setText("There is no tree, please press the Make Tree button with a valid input");
            } else {
                if (tree.isBalanced(tree.getRoot()) == true) {
                    outputText.setText("The tree is balanced");
                } else {
                    outputText.setText("The tree is not balanced");
                }
            }
        }
    }

    //If tree is not null, calls isFull from BinaryTree.java to determine if it is full or not
    public class fullButtonPress implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (tree == null) {
                outputText.setText("There is no tree, please press the Make Tree button with a valid input");
            } else {
                if (tree.isFull() == true) {
                    outputText.setText("The tree is full");
                } else {
                    outputText.setText("The tree is not full");
                }
            }
        }
    }

    //If tree is not null, calls isProperfrom BinaryTree.java to determine if it is proper or not
    public class properButtonPress implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (tree == null) {
                outputText.setText("There is no tree, please press the Make Tree button with a valid input");
            } else {
                if (tree.isProper(tree.getRoot()) == true) {
                    outputText.setText("The tree is proper");
                } else {
                    outputText.setText("The tree is not proper");
                }
            }
        }
    }

    //If tree is not null, calls getHeight from BinaryTree.java to determine height
    public class heightButtonPress implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (tree == null) {
                outputText.setText("There is no tree, please press the Make Tree button with a valid input");
            } else {
                int height = tree.getHeight(tree.getRoot()) - 1;//Need to subtract 1 since height should start at 0
                outputText.setText("Height of the tree is " + height);
            }
        }
    }

    //If tree is not null, calls getNodeCount from BinaryTree.java to determine the number of nodes present
    public class nodeButtonPress implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (tree == null) {
                outputText.setText("There is no tree, please press the Make Tree button with a valid input");
            } else {
                outputText.setText("The tree has " + tree.getNodeCount() + " nodes");
            }
        }
    }

    //If tree is not null, calls inOrder from BinaryTree.java to determine create the inorder traversal
    public class inorderButtonPress implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (tree == null) {
                outputText.setText("There is no tree, please press the Make Tree button with a valid input");
            } else {
                outputText.setText(tree.inOrder(tree.getRoot()));
            }
        }
    }

    public static void main(String[] args) {
        Project3 project = new Project3();//Creates the GUI
    }

}//End Project3.java
