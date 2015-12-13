package com.example.bogdan.proftime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.gson.Gson;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;

import static com.example.bogdan.proftime.StaticValues.listTaskInfo;
import static com.example.bogdan.proftime.StaticValues.listTaskStatus;
import static com.example.bogdan.proftime.StaticValues.listTime;
import static com.example.bogdan.proftime.StaticValues.listTitle;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    TaskFragment myFragment;
    //private String projectObject;
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

        if (listTitle == null) {
            listTitle = new ArrayList<>();
            listTitle.add("Сделать макет приложения");
            listTitle.add("Проверить работу сервера");
            listTitle.add("Поставить картинки");
            listTitle.add("Изменить цвет фона");
        }

        if (listTime == null) {
            listTime = new ArrayList<>();
            listTime.add("0");
            listTime.add("0");
            listTime.add("0");
            listTime.add("0");
        }

        if (listTaskInfo == null) {
            listTaskInfo = new ArrayList<>();
            listTaskInfo.add(x);
            listTaskInfo.add(x);
            listTaskInfo.add(x);
            listTaskInfo.add(x);
        }

        if (listTaskStatus == null) {
            listTaskStatus = new ArrayList<>();
            listTaskStatus.add(false);
            listTaskStatus.add(false);
            listTaskStatus.add(false);
            listTaskStatus.add(false);
        }

        Intent intent = getIntent();
        if (ProjectObject.initProject == null) {
            ProjectObject.initProject = intent.getStringExtra("data");
        }

        Gson gson = new Gson();
        ProjectObject projectObject1 = gson.fromJson(ProjectObject.initProject, ProjectObject.class);

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName("SmartHouse Project");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        new DrawerBuilder().withActivity(this).build();

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.proffstorelogo)
                .addProfiles(
                        new ProfileDrawerItem().withName(projectObject1.getFirstNameCustomer() + " "
                                + projectObject1.getLastNameCustomer()).withEmail("body_stepanyuk@gmail.com")
                                .withIcon(getResources().getDrawable(R.drawable.sloy))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

//create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D

                        return true;
                    }
                })
                .build();

        int id = projectObject1.getId();

        for (int i = 0; i < listTime.size(); i++) {

            createTaskFragment(listTaskStatus.get(i), listTime.get(i), listTitle.get(i), listTaskInfo.get(i));
        }


//        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                createTaskFragment(0);
//            }
//        });
    }

    public void createTaskFragment(Boolean aBoolean, String time, String title, String info) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (!aBoolean) {
            myFragment = new TaskFragment("#ffffff", "#ffa726", time, title, info, false);
            fragmentTransaction.add(R.id.container, myFragment);
        } else {
            myFragment = new TaskFragment("#ffa726", "#ffffff", time, title, info, true);
            fragmentTransaction.add(R.id.container2, myFragment);
        }
        fragmentTransaction.commit();
    }

}
