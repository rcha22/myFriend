package com.example.archana.myapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.example.archana.myapplication.Adapter.MorePhotoAdapter;
import com.example.archana.myapplication.Adapter.MyFriendAdapter;

public class MorePhoto extends AppCompatActivity {
    private GridView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_photo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        grid = (GridView)findViewById(R.id.grid);
        toolbar.setLogo(R.mipmap.friend);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        int[] imageId = {
                R.drawable.friend1,
                R.drawable.friend2,
                R.drawable.friend3,
                R.drawable.friend4,
                R.drawable.friend5,
                R.drawable.friend6,
                R.drawable.friend7,
                R.drawable.friend8,
                R.drawable.friend9
        };
        MorePhotoAdapter morePhotoAdapter = new MorePhotoAdapter(MorePhoto.this,imageId);
        grid.setAdapter(morePhotoAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_my_friend, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean val = setMenuOptions(item);
        return val;
    }

    public boolean setMenuOptions(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                finish();
                break;
        }
        return (super.onOptionsItemSelected(item));
    }
}
