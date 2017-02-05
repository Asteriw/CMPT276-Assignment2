package ca.sfu.epsilon.servingcalculator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalculateServing extends AppCompatActivity {

    int weight;
    int servings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupEndActivityButton();
        setupWeightEditView();
        setupServingsEditView();
        setupTextViews();
    }

    private void setupTextViews() {

    }

    private void setupWeightEditView(){
        final EditText inputWeight = (EditText) findViewById(R.id.edText_weight_with_food);
        inputWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                int weight = Integer.parseInt(inputWeight.getText().toString());
            }
        });
    }

    private void setupServingsEditView(){
        final EditText inputServings = (EditText) findViewById(R.id.edText_servings_amount);
        inputServings.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                int weight = Integer.parseInt(inputServings.getText().toString());

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

    public static Intent makeIntent(Context context, Pot pot) {
        return new Intent(context, CalculateServing.class);
    }

    private void setupEndActivityButton(){
        Button endAddPot = (Button) findViewById(R.id.btn_back);
        endAddPot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
