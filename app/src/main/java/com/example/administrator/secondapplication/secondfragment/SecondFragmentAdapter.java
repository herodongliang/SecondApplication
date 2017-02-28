package com.example.administrator.secondapplication.secondfragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.secondapplication.R;
import com.example.administrator.secondapplication.bean.ListEntity;
import com.example.administrator.secondapplication.lrecyclertext.AppToast;
import com.example.administrator.secondapplication.myclass.GlideCircleTransform;


import java.util.List;

/**
 * Created by guadong
 * on 2016/11/30.
 */
public class SecondFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int LIST = 2;
    private Context context;
    private List<ListEntity> data;
    public LayoutInflater inflater;
    private View itemView;
    private View itemtwo;

    public SecondFragmentAdapter(Context context, List<ListEntity> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
//        if (data.get(position).layoutType == 0) {
//
//            return IMAGE;
//        } else {
            return LIST;
       // }

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (viewType == IMAGE) {
//            itemtwo = inflater.inflate(R.layout.item_image, null);
//            return new ItemImageViewHolder(itemtwo);
//        } else {
            itemView= inflater.inflate(R.layout.item_secondfragment, null);
            return new ItemListViewHolder(itemView);
       // }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ItemListViewHolder) {
            final ItemListViewHolder itemListViewHolder = (ItemListViewHolder) holder;
            final ListEntity entity = data.get(position);
            itemListViewHolder.secondframgnet_name.setText("Alan");
            itemListViewHolder.secondframgnet_team.setText("department");
            Glide.with(context).load("http://img2.imgtn.bdimg.com/it/u=2452097355,3155741241&fm=21&gp=0.jpg").bitmapTransform(new GlideCircleTransform(context)).into(itemListViewHolder.secondframgnet_image);
            itemListViewHolder.secondframgnet_ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppToast.makeShortToast(context,position+"");
//                    Intent intent = new Intent(context, DetailContentActivity.class);
//                    intent.putExtra("tvname",entity.name);
//                    intent.putExtra("tvdate",entity.date);
//                    intent.putExtra("tvcontent",entity.content);
//                    intent.putExtra("headurl",entity.avatarUrl);
//                    intent.putExtra("contenturl",entity.descUrl);
//                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    private class ItemListViewHolder extends RecyclerView.ViewHolder {
        ImageView secondframgnet_image;
        TextView secondframgnet_name, secondframgnet_team;
        LinearLayout secondframgnet_ll;
        public ItemListViewHolder(View itemView) {
            super(itemView);
            secondframgnet_image = (ImageView) itemView.findViewById(R.id.secondframgnet_image);
            secondframgnet_name = (TextView) itemView.findViewById(R.id.secondframgnet_name);
            secondframgnet_team = (TextView) itemView.findViewById(R.id.secondframgnet_team);
            secondframgnet_ll=(LinearLayout)itemView.findViewById(R.id.secondframgnet_ll);
        }
    }
}
