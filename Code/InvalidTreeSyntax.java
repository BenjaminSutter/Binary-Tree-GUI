/*
 * File: InvalidTreeSyntax.java
 * Author: Ben Sutter
 * Date: September 22, 2020
 * Purpose: Create a class with a custom name for an exception that allows the message to be changed.
 */

package project3;

public class InvalidTreeSyntax extends Exception {
    
    public InvalidTreeSyntax(String errorMessage) {
        super(errorMessage);
    }
}
