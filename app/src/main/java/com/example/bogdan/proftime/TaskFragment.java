package com.example.bogdan.proftime;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


@SuppressLint("ValidFragment")
public class TaskFragment extends Fragment {

    Button button;
    TextView textTime;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    TaskFragment myFragment;
    CardView card;
    String colorCard;
    String colorText;
    String time;
    boolean status;

    TextView textTaskTitle;
    TextView textWithInfo;

    String title;
    String info;

    public TaskFragment(String colorCard, String colorText, String time, String title, String info, boolean status) {
        this.colorCard = colorCard;
        this.colorText = colorText;
        this.time = time;
        this.title = title;
        this.info = info;
        this.status = status;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_task, container, false);

        button = (Button) rootView.findViewById(R.id.btn);
        textTime = (TextView) rootView.findViewById(R.id.textTime);

        textTaskTitle = (TextView) rootView.findViewById(R.id.titleTask);
//        textWithInfo = (TextView) rootView.findViewById(R.id.textWithInfo);
//        textWithInfo.setText(info);
        textTaskTitle.setText(title);


        card = (CardView) rootView.findViewById(R.id.card_view);
        textTime.setTextColor(Color.parseColor(colorText));
        textTime.setText(time);
        card.setCardBackgroundColor(Color.parseColor(colorCard));

        final CountDownTimer timer = new CountDownTimer(Long.parseLong(time), 1000) {

            public void onTick(long millisUntilFinished) {

                int seconds;
                int minutes;
                int hours;
                int days;
                seconds = (int) (millisUntilFinished / 1000) % 60;
                minutes = (int) ((millisUntilFinished / (1000 * 60)) % 60);
                hours = (int) ((millisUntilFinished / (1000 * 60 * 60)) % 24);
                days = (int) ((millisUntilFinished / (1000 * 60 * 60 * 24)) % 365);
                textTime.setText(hours + "");
                Log.d("Tick", days + " days " + hours + " hrs " + minutes + " mins " + seconds + " sec ");

            }

            public void onFinish() {
                textTime.setText("0");
            }
        };

        timer.start();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowTask.taskFragment = TaskFragment.this;
                getActivity().getSupportFragmentManager().beginTransaction().remove(TaskFragment.this).commit();
                startActivity(new Intent(getActivity().getApplicationContext(), ShowTask.class));

            }
        });

        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return longClick();
            }
        });

        return rootView;
    }

    public boolean longClick() {
        myFragment = new TaskFragment("#ffa726", "#ffffff", time, title, info, true);
        int count = StaticValues.listTitle.indexOf(title);
        StaticValues.listTaskStatus.set(count, true);
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container2, myFragment);

        getActivity().getSupportFragmentManager().beginTransaction().remove(TaskFragment.this).commit();
        fragmentTransaction.commit();
        return false;
    }

}
