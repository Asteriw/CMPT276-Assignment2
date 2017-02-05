package ca.sfu.epsilon.servingcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupAddPotLaunch();
        setupListView();
        setupPotClick();
    }

    private void setupPotClick() {
        ListView list = (ListView) findViewById(R.id.lv_pot_list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                TextView textView = (TextView) viewClicked;
            }
        });
    }

    private void setupListView() {
        Pot[] arrayOfPots = {};

        ArrayAdapter<Pot> adapter = new ArrayAdapter<Pot>(
                this,                       //Context for activity
                R.layout.adapter_layout,    //Layout to use
                arrayOfPots);               //Pots to put in

        ListView list = (ListView) findViewById(R.id.lv_pot_list);
        list.setAdapter(adapter);
    }

    private void setupAddPotLaunch() {
        Button startAddPot = (Button) findViewById(R.id.btn_add_pot);
        startAddPot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AddPotintent = AddPot.makeIntent(MainActivity.this);
                startActivity(AddPotintent);
            }
        });
    }
    /*
    private void setupCalculatorLaunch() {
        Button startAddPot = (Button) findViewById(R.id.); //ADD A VARIABLE HERE
        startAddPot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AddCalculateServingIntent = CalculateServing.makeIntent(MainActivity.this);
                startActivity(AddCalculateServingIntent);
            }
        });
    }*/
}
