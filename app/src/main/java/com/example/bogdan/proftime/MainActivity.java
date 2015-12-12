package com.example.bogdan.proftime;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    TaskFragment myFragment;
    LinearLayout newLL;

    private SlidingUpPanelLayout slidingLayout;
    private Button btnHide;

    Button add;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = (Button) findViewById(R.id.add);
        newLL = new LinearLayout(getApplicationContext());

        btnHide = (Button)findViewById(R.id.btn_hide);
        btnHide.setOnClickListener(onHideListener());
        slidingLayout = (SlidingUpPanelLayout)findViewById(R.id.sliding_layout);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myFragment = new TaskFragment();

                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.add(R.id.container, myFragment);
                fragmentTransaction.commit();
            }
        });
    }

    private View.OnClickListener onHideListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hide sliding layout
                slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
            }
        };
    }
}
