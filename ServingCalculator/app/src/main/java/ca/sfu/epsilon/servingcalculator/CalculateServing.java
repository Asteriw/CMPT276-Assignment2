package ca.sfu.epsilon.servingcalculator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CalculateServing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    private void setupEditView(){
        EditText inputWeight = (EditText) findViewById(R.id.edText_weight_with_food);
        inputWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int inputPotWeight = R.id.edText_weight_with_food;
            }
        });
    }

    private int calculateWeightOfFood(int potWeight, int inputWeight){
        return (inputWeight-potWeight);
    }

    private int calculateServing(int weightInG, int amountOfServings){
        int weightPerServing = (weightInG/amountOfServings);
        return (int) Math.floor(weightPerServing);
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, CalculateServing.class);
    }
}
