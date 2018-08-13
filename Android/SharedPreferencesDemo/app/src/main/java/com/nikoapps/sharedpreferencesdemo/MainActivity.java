package com.nikoapps.sharedpreferencesdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // define shared preferences variable
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.nikoapps.sharedpreferencesdemo", Context.MODE_PRIVATE);

        // assign and save onto the shared preferences variable
        //sharedPreferences.edit().putString("username", "nikopogi").apply();

        // retrieve from the shared preferences variable
        String username = sharedPreferences.getString("username", "");

        Log.i("UName", username);


        // Alert dialog box
        new AlertDialog.Builder(this)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("Are you sure?")
            .setMessage("Do you definitely want to do this?")
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Log.i("Dialog", "Yes");
                }
            })
            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Log.i("Dialog", "No");
                }
            }).show();

    }

    // inflate the menu... inflate means show
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }


    // triggers for menu items on action bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Log.i("Menu Entered","Action Settings");
            return true;
        } else if (id == R.id.action_help) {
            Log.i("Menu Entered","Action Help");
            return true;
        } else if (id == R.id.action) {
            Log.i("Menu Entered","Action Only");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
