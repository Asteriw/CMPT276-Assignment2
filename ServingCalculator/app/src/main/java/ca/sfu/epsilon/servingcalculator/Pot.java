package ca.sfu.epsilon.servingcalculator;

/**
 * Store information about a single pot
 */

public class Pot{

    private String name;
    private int weight;

    // Set member data based on parameters.
    public Pot(String name, int weightInG) {
        this.weight = weightInG;
        this.name = name;
    }

    // Return the weight
    public int getWeightInG() {
        return this.weight;
    }

    // Set the weight. Throws IllegalArgumentException if weight is less than 0.
    public void setWeightInG(int weightInG) {
        if (weightInG <= 0) {
            throw new IllegalArgumentException("Error: weight entered must be positive.");
        } else {
            this.weight = weightInG;
        }
    }

    // Return the name.
    public String getName() {
        return this.name;
    }

    // Set the name. Throws IllegalArgumentException if name is an empty string (length 0),
    // or if name is a null-reference.
    public void setName(String name) {
        if(name.equals("") || name.equals(null)) {
            throw new IllegalArgumentException("Error: improper name format entered.");
        } else {
            this.name = name;
        }
    }
}
