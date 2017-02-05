package ca.sfu.epsilon.servingcalculator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPot extends AppCompatActivity {

    int weight;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pot);
        setupNameEditText();
        setupWeightEditText();
        setupEndActivityButton();
        setupAddPotButton();
    }

    private void setupNameEditText() {
        EditText editTextName = (EditText) findViewById(R.id.Pot_Name_Field);
        String name = editTextName.getText().toString();
    }

    private void setupWeightEditText() {
        EditText editTextInt = (EditText) findViewById(R.id.Pot_Weight_Field);
        //int weight = Integer.parseInt(editTextInt.getText().toString());
    }

    private void setupEndActivityButton(){
        Button endAddPot = (Button) findViewById(R.id.btn_end_add_pot);
        endAddPot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupAddPotButton() {
        Button acceptAddPot = (Button) findViewById(R.id.btn_accept_add_pot);
        acceptAddPot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                makePot(name, weight);
            }

            private void makePot(String name, int weight) {
                if ((!name.equals(null)) && weight > 0) {
                    Pot pot = new Pot(name, weight);
                    Log.i("CalculateServing", "Created new pot with name " + name + " and weight " + weight);
                } else {
                    throw new IllegalArgumentException("Error: Invalid Pot Parameters.");
                }
            }
        });
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, AddPot.class);
    }
}
