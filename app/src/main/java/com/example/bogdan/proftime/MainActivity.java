package com.example.bogdan.proftime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    TaskFragment myFragment;

    String x = "надо полностью переделать макет приложения," +
            " найти новые картинки, сделать фскрины экранов и добавить счет моей карточки заказчику, " +
            "что бы он такой классный пайан мог мне заплатить большие деньги )) а я уже в свою очередь куплю себе крутую" +
            " машину и буду на ней кататься и туда и сюда и опять, и буду возить девочек, оу ну и конечно же куплю собаку, " +
            "да еще и не простую, а золотую, что бы жилания исполняла... хотя стом, так это значит что мне надо просто " +
            "сделать один проект и тогда я могу купить собаку, которая исполняет жилания .... УРАА!! да я же наркаман)) " +
            "какая собака ? какие жилания ??? буду просто писать код и радоваться) ой, наверное тут очень много ошибок, просто уже " +
            "3 утра и я очень устал))";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> listTitle = new ArrayList<>();
        listTitle.add("сделать макет приложения");
        listTitle.add("проверить работу сервера");
        listTitle.add("поставить картинки");
        listTitle.add("изменить цвет фона");

        ArrayList<String> listTime = new ArrayList<>();
        listTime.add("0");
        listTime.add("0");
        listTime.add("0");
        listTime.add("0");

        ArrayList<String> listTaskInfo = new ArrayList<>();
        listTaskInfo.add(x);
        listTaskInfo.add(x);
        listTaskInfo.add(x);
        listTaskInfo.add(x);

        Intent intent = getIntent();
        String projectObject = intent.getStringExtra("data");

        Gson gson = new Gson();
        ProjectObject projectObject1 = gson.fromJson(projectObject, ProjectObject.class);

        int id = projectObject1.getId();

        for (int i = 0; i < listTime.size(); i++) {

            createTaskFragment(listTime.get(i), listTitle.get(i), listTaskInfo.get(i));
        }


//        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                createTaskFragment(0);
//            }
//        });
    }

    public void createTaskFragment(String time, String title, String info) {
        myFragment = new TaskFragment("#ffffff", "#ffa726", time, title, info);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.container, myFragment);
        fragmentTransaction.commit();
    }
}
