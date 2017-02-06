package ca.sfu.epsilon.servingcalculator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import static ca.sfu.epsilon.servingcalculator.AddPot.getPotFromIntent;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_NEWPOT = 666;
    private static final int REQUEST_CODE_CHANGEPOT = 111;
    private static final String SHAREDPREF_SET = "ServingCalculator";
    private static final String SHAREDPREF_ITEM_POTLIST_NAME = "PotName";
    private static final String SHAREDPREF_ITEM_POTLIST_WEIGHT = "PotWeight";
    private static final String SHAREDPREF_ITEM_POTLIST_SIZE = "PotListSize";

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

    private void editEntry(int index) {
        Intent ChangePotintent = AddPot.makeIntent(MainActivity.this);
        ChangePotintent.putExtra("Index", index);
        startActivityForResult(ChangePotintent, REQUEST_CODE_CHANGEPOT);
    }

    private void deleteEntry(int index){
        Log.i("Serving Calculator", "First amount: " +String.valueOf(potList.countPots()));
        potList.deletePot(index);
        Log.i("Serving Calculator", "Second amount: " +String.valueOf(potList.countPots()));
        arrayofpots = potList.getPotDescriptions();
        refresher(arrayofpots);
    }

    private void setupListView() {
        potList = loadPotList();
        arrayofpots = potList.getPotDescriptions();
        refresher(arrayofpots);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case REQUEST_CODE_NEWPOT:
                if (resultCode == Activity.RESULT_OK) {

                    Pot newPot = getPotFromIntent(data);
                    potList.addPot(newPot);
                    arrayofpots = potList.getPotDescriptions();
                    refresher(arrayofpots);
                    setupPotClick();

                    String PotName = data.getStringExtra("NewPotName");
                    int PotWeight = data.getIntExtra("NewPotWeight", 1);
                    Log.i("Serving Calculator", "New Pot's name is: " + PotName);
                    Log.i("Serving Calculator", "New Pot's weight is: " + String.valueOf(PotWeight));
                }
                else {
                    Log.i("Serving Calculator", "Add Pot Cancelled");
                }
                break;
            case REQUEST_CODE_CHANGEPOT:
                if (resultCode == Activity.RESULT_OK) {
                    Pot changePot = getPotFromIntent(data);
                    Log.i("Serving Calculator", ""+data.getIntExtra("Index", 0));
                    potList.changePot(changePot, data.getIntExtra("Index", 0));
                    arrayofpots = potList.getPotDescriptions();
                    refresher(arrayofpots);
                } else {
                    Log.i("Serving Calculator", "Edit Cancelled");
                }
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case R.id.edit:
                editEntry(info.position);
                return true;
            case R.id.delete:
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
                array);                     //Pots to put in
        ListView list = (ListView) findViewById(R.id.lv_pot_list);
        list.setAdapter(adapter);
        storePotList();
        registerForContextMenu(list);
    }

    private PotCollection loadPotList(){
        SharedPreferences preferences = getSharedPreferences(SHAREDPREF_SET, MODE_PRIVATE);
        int sizeOfPotList = preferences.getInt(SHAREDPREF_ITEM_POTLIST_SIZE, 0);
        for(int i = 0; i<sizeOfPotList; i++){
            Log.i("serving calculator", "once");
            String potName = preferences.getString(SHAREDPREF_ITEM_POTLIST_NAME+i, "N");
            int potWeight = preferences.getInt(SHAREDPREF_ITEM_POTLIST_WEIGHT+i, 0);
            Pot tempPot = new Pot(potName, potWeight);
            potList.addPot(tempPot);
        }
        return potList;
    }

    private void storePotList(){
        String tempName;
        int tempWeight;
        SharedPreferences preferences = getSharedPreferences(SHAREDPREF_SET, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        int sizeOfPotList = potList.countPots();
        editor.putInt(SHAREDPREF_ITEM_POTLIST_SIZE, sizeOfPotList);
        for(int i = 0; i < potList.countPots();i++){
            tempWeight = (potList.getPot(i).getWeightInG());
            tempName = (potList.getPot(i).getName());
            editor.putString(SHAREDPREF_ITEM_POTLIST_NAME+i, tempName);
            editor.putInt(SHAREDPREF_ITEM_POTLIST_WEIGHT+i, tempWeight);
        }
        editor.commit();
    }

}
