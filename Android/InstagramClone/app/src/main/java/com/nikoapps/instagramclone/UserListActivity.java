package com.nikoapps.instagramclone;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    public void getPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getPhoto();
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri selectedImage = data.getData();

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

                Log.i("Image Selected","Wow!");

                //upload image to parse
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);

                byte[] byteArray = outStream.toByteArray();

                ParseFile pFile = new ParseFile("image.png", byteArray);
                ParseObject object = new ParseObject("Image");

                object.put("image", pFile);
                object.put("username",ParseUser.getCurrentUser().getUsername());

                object.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(UserListActivity.this, "Image has been shared!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(UserListActivity.this, "Issue with image upload", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.share_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.share) {
            Log.i("Share Menu", "Pressed!");

            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else {
                getPhoto();
            }
        } else if (item.getItemId() == R.id.logout) {

            Log.i("Current User", "Before Log-out");
            // Log-out from parse
            ParseUser.logOut();
            //Log.i("Current User", "After Log-out");

            Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainActivityIntent);

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        setTitle("Instagram");

        final ListView listView = findViewById(R.id.listView);
        final ArrayList<String> usernames = new ArrayList<String>();
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, usernames);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent userActivityIntent = new Intent(getApplicationContext(), userFeedActivity.class);
                userActivityIntent.putExtra("username", usernames.get(i));
                startActivity(userActivityIntent);
            }
        });

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.addAscendingOrder("username");

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {
                    if (objects.size() > 0) {
                        for (ParseUser user : objects) {
                            usernames.add(user.getUsername());
                        }

                        listView.setAdapter(arrayAdapter);
                    }
                } else {
                    e.printStackTrace();
                }
            }
        });
    }
}
