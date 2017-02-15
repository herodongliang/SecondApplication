package com.example.administrator.secondapplication.lrecyclertext;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.secondapplication.R;
import com.example.administrator.secondapplication.info.DetailFeedbackDimensions;
import com.example.administrator.secondapplication.model.ImageInfo;
import com.example.administrator.secondapplication.myclass.GlideCircleTransform;
import com.example.administrator.secondapplication.myclass.RelativeDateFormat;
import com.hedgehog.ratingbar.RatingBar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Lzx on 2016/12/30.
 */

public class MultipleItemAdapter extends MultiAdapter<ImageInfo> {
    private Context context;
    private static final String TAG = "MultipleItemAdapter";
    public MultipleItemAdapter(Context context) {
        super(context);
        this.context=context;
        addItemType(ImageInfo.TEXT, R.layout.item_image);
        addItemType(ImageInfo.IMG, R.layout.item_list);
    }



    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        ImageInfo item = getDataList().get(position);
        switch (item.getItemType()) {
            case ImageInfo.TEXT:
                bindTextItem(holder,item);
                break;
            case ImageInfo.IMG:
                bindPicItem(holder,item);
                break;
            default:
                break;
        }

    }

    private void bindTextItem(SuperViewHolder holder, ImageInfo item) {
       // TextView textView = holder.getView(R.id.info_text);
      //  textView.setText(item.getTitle());

    }

    private void bindPicItem(SuperViewHolder holder, ImageInfo item) {
      //  TextView textView = holder.getView(R.id.info_text);
        //ImageView avatarImage = holder.getView(R.id.avatar_image);

        //textView.setText(item.getTitle());
       // avatarImage.setImageResource(R.mipmap.ic_launcher);
        ListView lv_ratingmoments=holder.getView(R.id.lv_ratingmoments);

        ImageView avatar = holder.getView(R.id.avatar_image);
        Glide.with(context).load(item.getSponsor().getAvatar()).bitmapTransform(new GlideCircleTransform(context)).placeholder(R.mipmap.groupcopy2).error(R.mipmap.groupcopy2).into(avatar);
        TextView tvName = holder.getView(R.id.name);
        tvName.setText(item.getSponsor().getName());
        TextView tvDate = holder.getView(R.id.date);
        tvDate.setText(RelativeDateFormat.format(new Date((long)item.getSponsor().getCreated_at()*1000)).toString());
        TextView tvDesc = holder.getView(R.id.desc);
        tvDesc.setText(item.getDescription());
        TextView first_tv_department= holder.getView(R.id.first_tv_department);
        first_tv_department.setText(item.getSponsor().getDepartment());
        TextView first_readnum=holder.getView(R.id.first_readnum);
        first_readnum.setText(item.getPage_views()+"");
        TextView first_commentsnum=holder.getView(R.id.first_commentsnum);
        first_commentsnum.setText(item.getComment_count()+"");
        TextView tv_contentdescription=holder.getView(R.id.tv_contentdescription);
        LinearLayout ll_first_feedback=(LinearLayout)holder.getView(R.id.ll_first_feedback);
        // layout = (NormalGridLayout) itemView.findViewById(R.id.autoGridLayout);
        TextView tvreadnum = (TextView)holder.getView(R.id.first_readnum);
        //tvcommentsnum=(TextView) itemView.findViewById(R.id.first_commentsnum);
        LinearLayout ll_moment_gone=(LinearLayout)holder.getView(R.id.ll_moment_gone);
        TextView tv_moment_comment=(TextView)holder.getView(R.id.tv_moment_comment);
        RatingBar ratingBar_moment_no=(RatingBar)holder.getView(R.id.ratingBar_moment_no);

        if(item.getEntity_type().equals("Feedback")){
            if(item.getFeedback()!=null && item.getFeedback().toString().length()>0) {
                listdimesion = item.getListdimesion();
                tv_contentdescription.setVisibility(View.GONE);
                if (listdimesion == null) {
                    Log.e(TAG, "listdimesionnull: " + listdimesion);
                } else if (listdimesion.size() > 0 && listdimesion.get(0).getComment() != null) {
                    Log.e(TAG, "listdimesion: " + listdimesion.toString());
                    ll_moment_gone.setVisibility(View.GONE);
                    lv_ratingmoments.setVisibility(View.VISIBLE);
                    adapter = new DetailDemisionAdapter(listdimesion, context);
                    lv_ratingmoments.setAdapter(adapter);
                    fixListViewHeight(lv_ratingmoments);
                } else if (listdimesion.size() == 0) {
                    ll_moment_gone.setVisibility(View.VISIBLE);
                    lv_ratingmoments.setVisibility(View.GONE);
                    tv_moment_comment.setText(item.getFeedback().getComment());
                    ratingBar_moment_no.setmClickable(false);
                    ratingBar_moment_no.setStar((float)item.getFeedback().getStar());
                }
            }
        }else if(item.getEntity_type().equals("FeedbackRequest")){
            tv_contentdescription.setText(item.getFeedback_request().getDescription());
            tv_contentdescription.setVisibility(View.VISIBLE);
            ll_moment_gone.setVisibility(View.GONE);
            lv_ratingmoments.setVisibility(View.GONE);
        }

    }
    private List<DetailFeedbackDimensions.DimensionsBean> listdimesion = new ArrayList<>();
    private DetailDemisionAdapter adapter;
    public void fixListViewHeight(ListView listView) {
        // 如果没有设置数据适配器，则ListView没有子项，返回。
        ListAdapter listAdapter = listView.getAdapter();
        int totalHeight = 0;
        if (listAdapter == null) {
            return;
        }
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            View listViewItem = listAdapter.getView(i , null, listView);
            // 计算子项View 的宽高
            listViewItem.measure(0, 0);
            // 计算所有子项的高度和
            totalHeight += listViewItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // listView.getDividerHeight()获取子项间分隔符的高度
        // params.height设置ListView完全显示需要的高度
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
