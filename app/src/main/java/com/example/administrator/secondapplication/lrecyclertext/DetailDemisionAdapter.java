package com.example.administrator.secondapplication.lrecyclertext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.example.administrator.secondapplication.R;
import com.example.administrator.secondapplication.info.DetailFeedbackDimensions;
import com.hedgehog.ratingbar.RatingBar;

import java.util.List;

/**
 * Created by Administrator on 2017/1/3 0003.
 */
public class DetailDemisionAdapter extends BaseAdapter {
    private List<DetailFeedbackDimensions.DimensionsBean> list;
    private Context context;

    public DetailDemisionAdapter(List<DetailFeedbackDimensions.DimensionsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DetailDemision detailDemision = null;
        if(convertView==null){
            detailDemision = new DetailDemision();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_detai_comment_demision,null);
            detailDemision.iv = (ImageView)convertView.findViewById(R.id.iv_demision_detail_demision);
            detailDemision.tvlable = (TextView)convertView.findViewById(R.id.tv_detaile_demision_lable);
            detailDemision.tvcontent = (TextView)convertView.findViewById(R.id.tv_demision_detail_demision);
            detailDemision.ratingBar= (RatingBar)convertView.findViewById(R.id.ratingBar_demision_content);
            convertView.setTag(detailDemision);
        }else{
            detailDemision = (DetailDemision)convertView.getTag();
        }
        DetailFeedbackDimensions.DimensionsBean dimensionsBean = list.get(position);
        Glide.with(context).load(dimensionsBean.getIcon()).placeholder(R.mipmap.tag).error(R.mipmap.tag).into(detailDemision.iv);
        detailDemision.tvlable.setText(dimensionsBean.getLabel());
        detailDemision.tvcontent.setText(dimensionsBean.getComment());
        double star = dimensionsBean.getStar();
        detailDemision.ratingBar.setmClickable(false);
        detailDemision.ratingBar.setStar((float)star);
        return convertView;
    }

    class DetailDemision{
        ImageView iv;
        TextView tvlable,tvcontent;
        RatingBar ratingBar;
    }
}
