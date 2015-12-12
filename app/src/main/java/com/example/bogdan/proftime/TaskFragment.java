package com.example.bogdan.proftime;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;




public class TaskFragment extends Fragment {

    Button button;
    TextView textTime;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    TaskFragment myFragment;
    LinearLayout newLL;
    CardView card;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_task, container, false);

        button = (Button) rootView.findViewById(R.id.btn);
        textTime = (TextView) rootView.findViewById(R.id.textTime);
        card = (CardView) rootView.findViewById(R.id.card_view);

        final CountDownTimer timer = new CountDownTimer(15000, 1000) {

            public void onTick(long millisUntilFinished) {
                long k = millisUntilFinished/1000;
                textTime.setText(k + "");
            }

            public void onFinish() {
                textTime.setText("0");
            }
        };

        timer.start();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myFragment = new TaskFragment();
                myFragment.card.setCardBackgroundColor(Color.parseColor("#ffa726"));

                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.add(R.id.container2, myFragment);
                getActivity().getSupportFragmentManager().beginTransaction().remove(TaskFragment.this).commit();
                fragmentTransaction.commit();


            }
        });

        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(v.getContext())
                        .setTitle("Удаление")
                        .setMessage("Хотите удалить задачу?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                getActivity().getSupportFragmentManager().beginTransaction().remove(TaskFragment.this).commit();
                                timer.cancel();
                            }
                        })
                        .show();
                return false;
            }
        });

        return rootView;
    }

}
