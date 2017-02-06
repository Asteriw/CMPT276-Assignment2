package ca.sfu.epsilon.servingcalculator;

import java.util.ArrayList;
import java.util.List;
import ca.sfu.epsilon.servingcalculator.Pot;

/**
 * Class to manage a collection of pots.
 */
public class PotCollection {
    private List<Pot> pots = new ArrayList<>();

    //Adding a pot to the Pot Collection
    public void addPot(Pot pot) {
        pots.add(pot);
    }

    //Function that deletes and re-writes a pot at an index
    public void changePot(Pot pot, int indexOfPotEditing) {
        validateIndexWithException(indexOfPotEditing);
        pots.remove(indexOfPotEditing);
        pots.add(indexOfPotEditing, pot);
    }

    //Deletes a pot at an index
    public void deletePot(int indexToDelete){
        validateIndexWithException(indexToDelete);
        pots.remove(indexToDelete);
    }

    //Returns the amount of pots in a PotCollection
    public int countPots() {
        return pots.size();
    }

    //Returns the pot at an index
    public Pot getPot(int index) {
        validateIndexWithException(index);
        return pots.get(index);
    }

    // Useful for integrating with an ArrayAdapter
    public String[] getPotDescriptions() {
        String[] descriptions = new String[countPots()];
        for (int i = 0; i < countPots(); i++) {
            Pot pot = getPot(i);
            descriptions[i] = pot.getName() + " - " + pot.getWeightInG() + "g";
        }
        return descriptions;
    }

    //Function that checks whether or not the index passed in is valid.
    private void validateIndexWithException(int index) {
        if (index < 0 || index >= countPots()) {
            throw new IllegalArgumentException();
        }

    }
}
