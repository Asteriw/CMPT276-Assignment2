package ca.sfu.epsilon.servingcalculator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import ca.sfu.epsilon.servingcalculator.Pot;

public class AddPot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pot);

        setupEndActivityButton();
    }

    private void setupEndActivityButton() {
        Button endAddPot = (Button) findViewById(R.id.btn_end_addpot);
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
                makePot(String.valueOf(R.id.Pot_Name_Field), R.id.Pot_Weight_Field);
            }

            private void makePot(String name, int weight) {
                if (!name.equals(null) && weight > 0) {
                    Pot pot = new Pot(name, weight);
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
