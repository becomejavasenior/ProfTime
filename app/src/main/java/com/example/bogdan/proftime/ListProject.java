package com.example.bogdan.proftime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class ListProject extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_project);

        progressBar = (ProgressBar) findViewById(R.id.download);

        ParseDB parseDB = new ParseDB(getApplicationContext(), progressBar);
        parseDB.execute("737");
    }
}
