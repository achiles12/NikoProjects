package com.nikoapps.notesapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Edit extends AppCompatActivity implements TextWatcher {

    Intent mainIntent;
    EditText editNote;
    String note;
    int noteId;
    int position;
    Boolean insert;

    SQLiteDatabase notesDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        notesDB = this.openOrCreateDatabase("notes", MODE_PRIVATE, null);

        editNote = (EditText) findViewById(R.id.editNote);

        // Defining and calling the App bar to place a back button on the map
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        // this line will suppress the onCreate method when going back to the previous activity or next activity
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        // end of call to the App Bar

        Bundle b = getIntent().getExtras();
        note = b.getString("note");
        noteId = b.getInt("noteID");
        position = b.getInt("position");
        insert = b.getBoolean("insert");



        Log.i("Edit Log", Integer.toString(noteId));
        Log.i("Edit Log", note);
        Log.i("Edit Log", Integer.toString(position));
        Log.i("Edit Log", Boolean.toString(insert));

        editNote.setText(note);

        editNote.addTextChangedListener(this);

    }

    // when button clicked on the app bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(mainIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        Log.i("Edit Log", "Before Text");

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        Log.i("Edit Log", "On Text");

        String sql;

        if (insert == false) {
            //+ editNote.getText() +
            //text = String.valueOf(editNote.getText());

            // Update DB

            notesDB.beginTransaction();

            ContentValues cv = new ContentValues();
            cv.put("note", String.valueOf(editNote.getText()));

            //notesDB.update("text", cv, "noteID="+Integer.toString(noteId), null);

            sql = "UPDATE text SET note='"+ String.valueOf(editNote.getText()+"', last_update_date = datetime() WHERE noteID=" + Integer.toString(noteId));

            Log.i("SQL Update", sql);

            notesDB.execSQL(sql);

            //notesDB.execSQL("COMMIT");
            notesDB.setTransactionSuccessful();
            notesDB.endTransaction();

            Log.i("Edit Log", "After Update");

            MainActivity.myNotes.get(position).setNoteText(String.valueOf(editNote.getText()));

            MainActivity.recyclerAdapter.notifyDataSetChanged();
        } else if (insert == true) {

            noteId = position;
            insert = false;


            notesDB.beginTransaction();
            sql = "INSERT INTO text (note, last_update_date) VALUES ('"+ String.valueOf(editNote.getText())+"',datetime())";
            Log.i("SQL Insert", sql);

            notesDB.execSQL(sql);
            notesDB.setTransactionSuccessful();
            notesDB.endTransaction();

            Log.i("Edit Log", "After Insert");

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

            MainActivity.myNotes.add(new Note(noteId,String.valueOf(editNote.getText()), timeStamp));

            MainActivity.recyclerAdapter.notifyDataSetChanged();

        }


    }

    @Override
    public void afterTextChanged(Editable editable) {
        Log.i("Edit Log", "After Text");


    }
}
