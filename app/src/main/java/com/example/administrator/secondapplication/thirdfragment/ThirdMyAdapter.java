package com.example.administrator.secondapplication.thirdfragment;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.secondapplication.R;
import com.example.administrator.secondapplication.bean.ItemModel;
import com.example.administrator.secondapplication.bean.Messages;
import com.example.administrator.secondapplication.fourfragment.ListBaseAdapter;
import com.example.administrator.secondapplication.lrecyclertext.SuperViewHolder;
import com.example.administrator.secondapplication.myclass.GlideCircleTransform;
import com.example.administrator.secondapplication.myclass.RelativeDateFormat;
import com.example.administrator.secondapplication.myclass.XCRoundImageView;

import java.util.Date;

/**
 * Created by Administrator on 2017/2/24 0024.
 */
public class ThirdMyAdapter extends ListBaseAdapter<Messages> {

    public ThirdMyAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_messages;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        Messages item = mDataList.get(position);
        TextView tv_time = (TextView)holder.getView(R.id.tv_time);

        TextView tv_username = (TextView) holder.getView(R.id.tv_username);
        ImageView iv_headpicture=(ImageView)holder.getView(R.id.iv_messages);
        TextView tv_content=(TextView)holder.getView(R.id.tv_content);
        XCRoundImageView image_point=(XCRoundImageView)holder.getView(R.id.image_point);
        LinearLayout ll_messages=(LinearLayout)holder.getView(R.id.ll_messages);

        tv_username.setText(item.getStake_holder().getName());
        tv_content.setText(item.getContent());
        double time = item.getCreated_at()*1000;
        long longtime = (long)time;
        tv_time.setText(RelativeDateFormat.format(new Date(longtime)));
        if(item.isRead()==false){
            image_point.setVisibility(View.VISIBLE);
        }
        Glide.with(mContext).load(item.getStake_holder().getAvatar()).placeholder(R.mipmap.groupcopy2).error(R.mipmap.groupcopy2).bitmapTransform(new GlideCircleTransform(mContext)).into(iv_headpicture);

    }
}