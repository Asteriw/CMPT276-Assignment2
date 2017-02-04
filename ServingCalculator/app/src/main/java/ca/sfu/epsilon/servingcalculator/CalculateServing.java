package ca.sfu.epsilon.servingcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CalculateServing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private int calculateWeightOfFood(int potWeight, int inputWeight){
        return (inputWeight-potWeight);
    }

    private int calculateServing(int weightInG, int amountofServings){
        int weightPerServing = (weightInG/amountofServings);
        return (int) Math.floor(weightPerServing);
    }

}
