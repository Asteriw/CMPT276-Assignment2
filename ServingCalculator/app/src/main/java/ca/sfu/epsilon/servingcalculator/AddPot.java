package ca.sfu.epsilon.servingcalculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPot extends AppCompatActivity {

    int weight = -1;
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pot);
        setupEndActivityButton();
        setupAddPotButton();
    }

    private void setupNameEditText() {
        EditText nameinput = (EditText) findViewById(R.id.Pot_Name_Field);
        name = nameinput.getText().toString();
    }

    private void setupWeightEditText() {
        EditText weightinput = (EditText) findViewById(R.id.Pot_Weight_Field);
        if ((weightinput.getText().toString()).equals("")){
            weight = -1;
        } else {
            weight = Integer.parseInt(weightinput.getText().toString());
        }
    }

    private void setupEndActivityButton(){
        Button endAddPot = (Button) findViewById(R.id.btn_end_add_pot);
        endAddPot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }

    private void setupAddPotButton() {
        Button acceptAddPot = (Button) findViewById(R.id.btn_accept_add_pot);
        acceptAddPot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setupNameEditText();
                setupWeightEditText();

                makePot(name, weight);
            }

            private void makePot(String name, int weight) {
                if ((!name.matches("")) && weight > 0){
                    Pot pot = new Pot(name, weight);
                    Log.i("Serving Calculator", "Created new pot with name " + pot.getName() + " and weight " + pot.getWeightInG());
                    Intent PotReturn = new Intent();
                    PotReturn.putExtra("NewPotName", name);
                    PotReturn.putExtra("NewPotWeight", weight);
                    setResult(Activity.RESULT_OK, PotReturn);
                    finish();
                } else {
                    Toast error = Toast.makeText(getApplicationContext(), getString(R.string.toast_error), Toast.LENGTH_LONG);
                    error.show();
                }
            }
        });
    }

    public static Pot getPotFromIntent(Intent data) {
        Pot pot = new Pot(data.getStringExtra("NewPotName"), data.getIntExtra("NewPotWeight", 1));
        return pot;
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, AddPot.class);
    }
}
