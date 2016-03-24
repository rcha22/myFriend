package com.example.archana.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.archana.myapplication.Map;
import com.example.archana.myapplication.MorePhoto;
import com.example.archana.myapplication.R;
import com.example.archana.myapplication.pojoClass.Friends;
import java.util.ArrayList;

/**
 * Created by Labha on 3/21/2016.
 */
public class MyFriendAdapter extends RecyclerView.Adapter<MyFriendAdapter.ViewHolder>  {
    private Context context;
    private ArrayList<Friends> arrayList;

    public MyFriendAdapter(Context myFriend, ArrayList<Friends> arrayListFriends) {
        this.context = myFriend;
        this.arrayList = arrayListFriends;
    }

    @Override
    public int getItemViewType(int position) {
        Friends friends = arrayList.get(position);
        if(friends.gettype().equals("simple_card")){
            return 1;
        }else if(friends.gettype().equals("checkin_card")){
            return 2;
        }
        return 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        switch (viewType){
            case 1:
                View v1 = inflater.inflate(R.layout.simple_card, viewGroup, false);
                viewHolder = new ViewHolder(v1,1);
                break;
            case 2:
                View v2 = inflater.inflate(R.layout.checkin_card, viewGroup, false);
                viewHolder = new ViewHolder(v2,2);
                break;
            default:
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Friends friends = arrayList.get(position);
        if(friends.gettype().equals("simple_card")){
            holder.wed.setText(friends.gettitle());
            holder.wedData.setText(friends.getcontent());
        }else{
            if(friends.gettitle().equals("At goa")){
                holder.morePhoto.setVisibility(View.GONE);
            }
            holder.text.setText(friends.gettitle());
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context,Map.class));
                }
            });
            holder.morePhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, MorePhoto.class));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class  ViewHolder extends RecyclerView.ViewHolder {
        private TextView morePhoto;
        private ImageView photoImageView;
        private ImageView image;
        private TextView text;
        private TextView wed;
        private TextView wedData;
        public ViewHolder(View itemView,int viewType) {
            super(itemView);
            switch (viewType){
                case 1:
                    wed = (TextView) itemView.findViewById(R.id.wed);
                    wedData = (TextView)itemView.findViewById(R.id.wedData);
                    break;
                case 2:
                    text = (TextView)itemView.findViewById(R.id.text);
                    image = (ImageView)itemView.findViewById(R.id.image);
                    photoImageView = (ImageView)itemView.findViewById(R.id.photoImageView);
                    morePhoto = (TextView)itemView.findViewById(R.id.morePhoto);
            }
        }
    }
}
