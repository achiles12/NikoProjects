package com.nikoapps.notesapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView notesView;
    static RecyclerAdapter recyclerAdapter;
    static ArrayList<Note> myNotes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("Niko Log","Test On Create");

        try {
            SQLiteDatabase notesDB = this.openOrCreateDatabase("notes", MODE_PRIVATE, null);

            notesDB.execSQL("CREATE TABLE IF NOT EXISTS text (noteID INTEGER PRIMARY KEY UNIQUE NOT NULL, note TEXT, last_update_date DATETIME)");

            //notesDB.execSQL("DELETE FROM text WHERE 1 = 1");

            for (int x = 0;  x < 10 ; x++){
                Log.i("Niko Log","loop" + Integer.toString(x));
               //notesDB.execSQL("INSERT INTO text (note, last_update_date) VALUES ('"+ Integer.toString(x) +"',datetime(strftime('%s','now'), 'unixepoch', 'localtime'))");
            }

            //notesDB.execSQL("INSERT INTO text (note, last_update_date) VALUES ('Ako si Niko',datetime(strftime('%s','now'), 'unixepoch', 'localtime'))");
            //notesDB.execSQL("INSERT INTO text (note, last_update_date) VALUES ('Ako si Pogi',datetime(strftime('%s','now'), 'unixepoch', 'localtime'))");
            //notesDB.execSQL("INSERT INTO text (note, last_update_date) VALUES ('Ako si Pogi',datetime())");

            Cursor c = notesDB.rawQuery("SELECT * FROM text ORDER BY last_update_date DESC", null);

            int noteID = c.getColumnIndex("noteID");
            int note = c.getColumnIndex("note");
            int last_update_date = c.getColumnIndex("last_update_date");

            // c.moveToFirst();


            while (c.moveToNext()) {
                Log.i("Niko Log","In Loop");

                Log.i("Results - noteID", Integer.toString(c.getInt(noteID)));
                Log.i("Results - note", c.getString(note));
                Log.i("Results - update_date", c.getString(last_update_date));//Integer.toString(c.getInt(last_update_date)));

                myNotes.add(new Note(c.getInt(noteID),c.getString(note), c.getString(last_update_date)));



            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        notesView = (RecyclerView) findViewById(R.id.notesView);
        notesView.setLayoutManager(new LinearLayoutManager(this));

        recyclerAdapter = new RecyclerAdapter(this, myNotes, new OnItemClickListner() {
            @Override
            public void OnItemClicked(int pos) {
                Toast.makeText(getApplicationContext(), "Niko Ampogi Mo!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), Edit.class);
                Bundle b = new Bundle();

                b.putBoolean("insert", false);
                b.putString("note", myNotes.get(pos).noteText);
                b.putInt("noteID", myNotes.get(pos).noteID);
                b.putInt("position", pos);

                Log.i("Bundle", Integer.toString(myNotes.get(pos).noteID));
                Log.i("Bundle", Integer.toString(pos));

                intent.putExtras(b);

                startActivity(intent);
            }
        }, new OnLongItemClick() {
            @Override
            public void OnItemLongClicked(final int pos) {
                Toast.makeText(getApplicationContext(), "Niko Napaka Pogi Mo!", Toast.LENGTH_SHORT).show();

                /*
                new AlertDialog.Builder(getApplicationContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Delete Message")
                        .setMessage("Are you sure you want to continue?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                myNotes.remove(pos);
                                recyclerAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), "Niko Saksakan ng Pogi Mo!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show(); */
            }
        });

        notesView.setAdapter(recyclerAdapter);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action) {
            Log.i("Menu Entered","Add Note");

            Intent addIntent = new Intent(getApplicationContext(),Edit.class);

            Bundle b = new Bundle();

            b.putBoolean("insert", true);
            b.putString("note", "");
            b.putInt("noteID", 0);
            b.putInt("position", myNotes.size());

            addIntent.putExtras(b);

            startActivity(addIntent);

            return true;
        }

        /*else if (id == R.id.action_help) {
            Log.i("Menu Entered","Action Help");
            return true;
        } else if (id == R.id.action) {
            Log.i("Menu Entered","Action Only");
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }

}
