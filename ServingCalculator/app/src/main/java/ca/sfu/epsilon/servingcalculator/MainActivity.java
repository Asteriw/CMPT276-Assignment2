package ca.sfu.epsilon.servingcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupAddPotLaunch();
    }

    private void setupAddPotLaunch() {
        Button startAddPot = (Button) findViewById(R.id.btn_add_pot);
        startAddPot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked Add Pot", Toast.LENGTH_SHORT)
                        .show();

                Intent AddPotintent = AddPot.makeIntent(MainActivity.this);
                startActivity(AddPotintent);
            }
        });
    }
}
