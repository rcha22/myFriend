package com.example.archana.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.archana.myapplication.R;

public class MorePhotoAdapter extends BaseAdapter {
    private Context context;
    private int[] images;
    public MorePhotoAdapter(Context context, int[] imageId) {
        this.context = context;
        this.images = imageId;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            grid = new View(context);
            grid = inflater.inflate(R.layout.activity_more_photo_adapter, null);
            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
            imageView.setImageResource(images[position]);
        } else {
            grid = (View) convertView;
        }
        return grid;
    }
}
