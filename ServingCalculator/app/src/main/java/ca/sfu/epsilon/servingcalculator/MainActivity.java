package ca.sfu.epsilon.servingcalculator;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_NEWPOT = 666;

    PotCollection potList = new PotCollection();

    String[] arrayofpots = {};

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
                Intent AddCalculateServingIntent = CalculateServing.makeIntent(MainActivity.this, potList.getPot(position));
                startActivity(AddCalculateServingIntent);
            }
        });
    }

    private void deleteEntry(int index){
        Log.i("Serving Calculator", "First amount: " +String.valueOf(potList.countPots()));
        potList.deletePot(index);
        Log.i("Serving Calculator", "Second amount: " +String.valueOf(potList.countPots()));
        arrayofpots = potList.getPotDescriptions();
        refresher(arrayofpots);
    }

    private void setupListView() {
        refresher(arrayofpots);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case R.id.edit:
                return true;
            case R.id.delete:
                Log.i("Serving Calculator", "We are deleting, possibly.");
                deleteEntry(info.position);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextmenu, menu);
    }

    private void refresher(String[] array) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,                       //Context for activity
                R.layout.adapter_layout,    //Layout to use
                array);               //Pots to put in
        ListView list = (ListView) findViewById(R.id.lv_pot_list);
        list.setAdapter(adapter);
        registerForContextMenu(list);
    }

    private void setupAddPotLaunch() {
        Button startAddPot = (Button) findViewById(R.id.btn_add_pot);
        startAddPot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AddPotintent = AddPot.makeIntent(MainActivity.this);
                startActivityForResult(AddPotintent,  REQUEST_CODE_NEWPOT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case REQUEST_CODE_NEWPOT:
                if (resultCode == Activity.RESULT_OK) {

                    Pot newPot = AddPot.getPotFromIntent(data);
                    potList.addPot(newPot);
                    arrayofpots = potList.getPotDescriptions();
                    refresher(arrayofpots);
                    setupPotClick();
                    
                    String PotName = data.getStringExtra("NewPotName");
                    int PotWeight = data.getIntExtra("NewPotWeight", 1);
                    Log.i("Serving Calculator", "New Pot's name is: " + PotName);
                    Log.i("Serving Calculator", "New Pot's weight is: " + String.valueOf(PotWeight));
                } else {
                    Log.i("Serving Calculator", "Activity Cancelled");
            }
        }
    }
}
