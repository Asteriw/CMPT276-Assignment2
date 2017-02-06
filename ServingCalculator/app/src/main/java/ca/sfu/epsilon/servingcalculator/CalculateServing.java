package ca.sfu.epsilon.servingcalculator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class CalculateServing extends AppCompatActivity {

    Pot potToCalculate = new Pot("n", 0);
    int weight;
    int weightDiffence;
    int servings = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serving_calculator);
        setupEndActivityButton();
        extractDataFromIntent();
        setupWeightEditView();
        setupServingsEditView();
        setupPotTextViews();
    }

    private void extractDataFromIntent() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("PotName");
        Log.i("Serving Calculator", "Name: " + name);
        potToCalculate.setName(name);
        int weight = intent.getIntExtra("PotWeight", 1);
        Log.i("Serving Calculator", "Weight: " + weight);
        potToCalculate.setWeightInG(weight);
    }

    private void setupPotTextViews() {
        TextView potName = (TextView) findViewById(R.id.tv_pot_name);
        potName.setText(potToCalculate.getName());
        TextView potWeight = (TextView) findViewById(R.id.tv_pot_weight);
        potWeight.setText("" + potToCalculate.getWeightInG());
    }

    private void updateTextView(final int buttonID, String value){
        TextView textview = (TextView) findViewById(buttonID);
        textview.setText(value);
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
                if (inputWeight.getText().toString().equals("")){
                    weight = 1;
                } else {
                    weight = Integer.parseInt(inputWeight.getText().toString());
                }
                weightDiffence = calculateWeightOfFood(potToCalculate.getWeightInG(), weight);
                updateTextView(R.id.tv_weight_of_food, ""+weightDiffence);
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
                if (inputServings.getText().toString().equals("")){
                    servings = 1;
                } else{
                    servings = Integer.parseInt(inputServings.getText().toString());
                }
                if (servings == 0){
                    servings = 1;
                    Toast errorServings = Toast.makeText(getApplicationContext(), getString(R.string.error_Servings), LENGTH_LONG);
                    errorServings.show();
                }
                int servingWeight = calculateServing(weightDiffence, servings);
                updateTextView(R.id.tv_total_serving_weight, ""+servingWeight);
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
        Intent returnIntent = new Intent(context, CalculateServing.class);
        returnIntent.putExtra("PotName", pot.getName());
        returnIntent.putExtra("PotWeight", pot.getWeightInG());
        return returnIntent;
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
