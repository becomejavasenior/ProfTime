package com.example.bogdan.proftime;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class TaskFragment extends Fragment {

    Button button;
    TextView textTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_task, container, false);

        button = (Button) rootView.findViewById(R.id.btn);
        textTime = (TextView) rootView.findViewById(R.id.textTime);

        final CountDownTimer timer = new CountDownTimer(15000, 1000) {

            public void onTick(long millisUntilFinished) {
                long k = millisUntilFinished/1000;
                textTime.setText(k + "");
            }

            public void onFinish() {
                textTime.setText("0");
            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
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
