package com.example.archana.myapplication;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.archana.myapplication.Adapter.MyFriendAdapter;
import com.example.archana.myapplication.pojoClass.Friends;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MyFriend extends AppCompatActivity {
    private Toolbar toolbar;
    private JSONObject jsonObject;
    private ImageView imageView;
    private TextView aboutMyFriend;
    private TextView title;
    private JSONObject friendsJsonObject;
    private Friends friendsObject;
    private JSONArray jsonArray;
    private TextView meet;
    private TextView meetData;
    private ArrayList<Friends> arrayListFriends;
    private RecyclerView list;
    private MyFriendAdapter myFriendAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friend);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        String json = loadJSONFromAsset();
        try {
            jsonObject = new JSONObject(json);
            toolbar.setTitle(jsonObject.getString("name"));
            toolbar.setLogo(R.mipmap.ic_launcher);
            jsonArray = new JSONArray(jsonObject.getString("our_story"));
            arrayListFriends = new ArrayList<Friends>();
            for (int i=0;i<jsonArray.length();i++){
                friendsObject = new Friends();
                friendsJsonObject = jsonArray.getJSONObject(i);
                if(!friendsJsonObject.isNull("type")){
                    friendsObject.settype(friendsJsonObject.getString("type"));
                }
                if(!friendsJsonObject.isNull("title")) {
                    friendsObject.settitle(friendsJsonObject.getString("title"));
                    //title.setText(friendsObject.gettitle());
                }
                if(!friendsJsonObject.isNull("content")) {
                    friendsObject.setcontent(friendsJsonObject.getString("content"));
                   // aboutMyFriend.setText(friendsObject.getcontent());
                }
                if(!friendsJsonObject.isNull("image_url")) {
                    friendsObject.setimage_url(friendsJsonObject.getString("image_url"));
                }
                if(!friendsJsonObject.isNull("location_url")) {
                    friendsObject.setlocation_url(friendsJsonObject.getString("location_url"));
                }
                if(!friendsJsonObject.isNull("more_images")) {
                    friendsObject.setmore_images(friendsJsonObject.getString("more_images"));
                }
                arrayListFriends.add(friendsObject);
            }
            asyncDownload(jsonObject.getString("photo"));

            list.setLayoutManager(new LinearLayoutManager(this));
            list.setAdapter(new MyFriendAdapter(this,arrayListFriends));
        }catch (JSONException je){
            je.printStackTrace();
        }
    }

    private void init() {
        imageView = (ImageView)findViewById(R.id.imageView);
        list = (RecyclerView)findViewById(R.id.list);
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getApplicationContext().getAssets().open("sample_response.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void asyncDownload(String URL) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectNetwork().build());

        DownloadImageTask task = new DownloadImageTask(URL);
        task.execute();
    }

    class DownloadImageTask extends AsyncTask<Void, Void, InputStream> {

        private ProgressDialog dialog;
        String URL;

        DownloadImageTask(String strURL) {
            this.URL = strURL;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MyFriend.this);
            dialog.setTitle("Please wait");
            dialog.setMessage("Please wait while the application is downloading the image");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected InputStream doInBackground(Void... params) {

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            try {
                java.net.URL url = new URL(this.URL);
                URLConnection connection = url.openConnection();
                InputStream stream = connection.getInputStream();
                return stream;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(InputStream result) {
            super.onPostExecute(result);
            Bitmap bitmap = BitmapFactory.decodeStream(result);
            imageView.setImageBitmap(bitmap);
            dialog.cancel();
        }
    }
}
