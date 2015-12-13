package com.example.bogdan.proftime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListProject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_project);
        ParseDB parseDB = new ParseDB();
        parseDB.execute("1");
    }
}
