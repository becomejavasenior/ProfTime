package com.example.bogdan.proftime;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ListProject extends AppCompatActivity {

    ProgressBar progressBar;
    ParseDB parseDB;
    ImageView textView;
    TextView textView1;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_project);

        progressBar = (ProgressBar) findViewById(R.id.download);
        textView = (ImageView) findViewById(R.id.imageIcon);
        textView1 = (TextView) findViewById(R.id.textTempq);

        parseDB = new ParseDB(getApplicationContext(), progressBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_favorite:
                parseDB.execute("737");
                textView.setVisibility(View.GONE);
                textView1.setVisibility(View.GONE);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
