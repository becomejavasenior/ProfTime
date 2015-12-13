package com.example.bogdan.proftime;

import android.net.http.Headers;
import android.os.AsyncTask;
import android.preference.PreferenceActivity;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 12.12.15.
 */
public class ParseDB extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        String param = params[0];
        String resultJson = null;
        try {
            URL url = new URL("https://proffstore.com/api/v1/project/" + param);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-Type", "multipart/form-data");
            urlConnection.setRequestProperty("x-client-code", "d676844f3b44");
            urlConnection.setRequestProperty("x-access-token", "a49663dd3783262e0128daf0b0b741ea4f912f946c839cd5b0e9924aec60b0d8");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            resultJson = buffer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultJson;
    }

    @Override
    protected void onPostExecute(String s) {
        JSONObject jsonObject = null;
        ProjectObject projectObject = null;
        try {
            jsonObject  = new JSONObject(s);
            projectObject = new ProjectObject();
            projectObject.setStatus(jsonObject.getString("status"));
            projectObject.setId(jsonObject.getInt("id"));
            int count = jsonObject.getInt("countOfTasks");
            JSONObject jsonObject1 = jsonObject.getJSONObject("owner");
            projectObject.setFirstNameCustomer(jsonObject1.getString("firstName"));
            projectObject.setLastNameCustomer(jsonObject1.getString("lastName"));
            if (count == 0) {
                projectObject.setTasks(null);
            } else {
                JSONObject object = jsonObject.getJSONObject("tasks").getJSONObject("tasks");
                List<Task> tasks = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    JSONObject json = object.getJSONObject("task_" + i);
                    Task task = new Task();
                    task.setId(json.getInt("id"));
                    task.setStatus(json.getString("status"));
                    task.setAmount(json.getInt("amount"));
                    task.setTitle(json.getString("title"));
                    task.setUrl(json.getString("url"));
                    task.setCreate(json.getString("created"));
                    tasks.add(task);
                }
                projectObject.setTasks(tasks);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
