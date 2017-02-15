package com.example.administrator.secondapplication.lrecyclertext;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.example.administrator.secondapplication.R;
import com.example.administrator.secondapplication.info.DetailFeedbackDimensions;
import com.example.administrator.secondapplication.model.ImageBDInfo;
import com.example.administrator.secondapplication.model.ImageInfo;
import com.example.administrator.secondapplication.myclass.GlideCircleTransform;
import com.example.administrator.secondapplication.myclass.RelativeDateFormat;
import com.hedgehog.ratingbar.RatingBar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by guadong
 * on 2016/11/21.
 */
public class FirstFragmentRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int IMAGE = 2;
    private final int LIST = 1;
    private ImageBDInfo bdInfo;
    private Context context;
    private ArrayList<ImageInfo> data;
    public LayoutInflater inflater;
    //private List<DetailFeedbackDimensions.DimensionsBean> listdimesion = new ArrayList<>();
    private List<DetailFeedbackDimensions.DimensionsBean> listdimesion = new ArrayList<>();
    private DetailDemisionAdapter adapter;
    // List<ImageInfo.FeedbackBean.DimensionsBean> dimensionslist1 = new ArrayList<>();
    // public static int getTypenum;
    private View itemView;
    private View itemtwo;
    private String stroption;
    private static final String TAG = "FirstAdapter";
    ///测试数据
    private String testJsonStr = "[\n" +
            "    [\n" +
            "        \"http://pic38.nipic.com/20140226/18071023_170840846000_2.jpg\",\n" +
            "        \"http://img15.3lian.com/2015/a1/13/d/20.jpg\",\n" +
            "        \"http://img2.3lian.com/2014/f4/63/d/14.jpg\",\n" +
            "        \"http://img15.3lian.com/2015/a1/13/d/17.jpg\"\n" +
            "    ],\n" +
            "    [\n" +
            "        \"http://d.hiphotos.baidu.com/zhidao/pic/item/3b87e950352ac65c1b6a0042f9f2b21193138a97.jpg\"\n" +
            "    ],\n" +
            "    [\n" +
            "        \"http://pic31.nipic.com/20130629/12199841_153811088000_2.jpg\",\n" +
            "        \"http://b.zol-img.com.cn/desk/bizhi/image/3/960x600/1369621315341.jpg\"\n" +
            "    ],\n" +
            "    [\n" +
            "        \"http://sc.jb51.net/uploads/allimg/140520/10-140520212515A9.jpg\",\n" +
            "        \"http://img15.3lian.com/2015/a1/13/d/20.jpg\",\n" +
            "        \"http://img2.3lian.com/2014/f4/63/d/14.jpg\",\n" +
            "        \"http://img.taopic.com/uploads/allimg/110920/2443-1109200TK99.jpg\"\n" +
            "    ],\n" +
            "    [\n" +
            "        \"http://img3.iqilu.com/data/attachment/forum/201308/22/163057jyrg229qerdeciod.jpg\",\n" +
            "        \"http://pic.6188.com/upload_6188s/flashAll/s800/20120508/1336442181tsUxmr.jpg\",\n" +
            "        \"http://img.taopic.com/uploads/allimg/120910/219077-12091016033980.jpg\"\n" +
            "    ]\n" +
            "]";
    private List<List<String>> datas = new ArrayList<>();
    //    private void getPictureData() {
//        List<List<String>> tempArr ;
//        tempArr = new Gson().fromJson
//                (testJsonStr,  new TypeToken<List<List<String>>>(){}.getType());
//
//        datas.addAll(tempArr);
//        datas.addAll(tempArr);
//        datas.addAll(tempArr);
//        datas.addAll(tempArr);
//        datas.addAll(tempArr);
//    }
    public void refresh(ArrayList<ImageInfo> mdata){
        data=mdata;
        notifyDataSetChanged();
    }

    public FirstFragmentRecyclerAdapter(Context context, ArrayList<ImageInfo> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
        bdInfo = new ImageBDInfo();
        //  getPictureData();
    }

    public void setItems(ArrayList<ImageInfo> data){
        this.data = data;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == IMAGE) {
            itemtwo = inflater.inflate(R.layout.item_image, null);
            return new ItemImageViewHolder(itemtwo);
        } else{
            itemView= inflater.inflate(R.layout.item_list, null);
            return new ItemListViewHolder(itemView);
        }

    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ItemListViewHolder) {
            final ItemListViewHolder itemListViewHolder = (ItemListViewHolder) holder;
            final ImageInfo entity = data.get(position);
            itemListViewHolder.tvName.setText(entity.getSponsor().getName());
            itemListViewHolder.tvDate.setText(RelativeDateFormat.format(new Date((long)entity.getSponsor().getCreated_at()*1000)).toString());
            itemListViewHolder.tvDesc.setText(entity.getDescription());

            itemListViewHolder.first_readnum.setText(entity.getPage_views()+"");
            itemListViewHolder.first_commentsnum.setText(entity.getComment_count()+"");
            itemListViewHolder.first_tv_department.setText(entity.getSponsor().getDepartment());
            Glide.with(context).load(entity.getSponsor().getAvatar()).bitmapTransform(new GlideCircleTransform(context)).placeholder(R.mipmap.groupcopy2).error(R.mipmap.groupcopy2).into(itemListViewHolder.avatar);
            Log.e(TAG, position+"++++++++++++listdimesion: "+listdimesion);
            if(entity.getEntity_type().equals("Feedback")){
                if(data.get(position).getFeedback()!=null && data.get(position).getFeedback().toString().length()>0) {
                    listdimesion = data.get(position).getListdimesion();
                    itemListViewHolder.tv_contentdescription.setVisibility(View.GONE);
                    if (listdimesion == null) {
                        Log.e(TAG, "listdimesionnull: " + listdimesion);
                    } else if (listdimesion.size() > 0 && listdimesion.get(0).getComment() != null) {
                        Log.e(TAG, "listdimesion: " + listdimesion.toString());
                        itemListViewHolder.ll_moment_gone.setVisibility(View.GONE);
                        itemListViewHolder.lv_ratingmoments.setVisibility(View.VISIBLE);
                        adapter = new DetailDemisionAdapter(listdimesion, context);
                        itemListViewHolder.lv_ratingmoments.setAdapter(adapter);
                        fixListViewHeight(itemListViewHolder.lv_ratingmoments);
                    } else if (listdimesion.size() == 0) {
                        itemListViewHolder.ll_moment_gone.setVisibility(View.VISIBLE);
                        itemListViewHolder.lv_ratingmoments.setVisibility(View.GONE);
                        itemListViewHolder.tv_moment_comment.setText(data.get(position).getFeedback().getComment());
                        itemListViewHolder.ratingBar_moment_no.setmClickable(false);
                        itemListViewHolder.ratingBar_moment_no.setStar((float) data.get(position).getFeedback().getStar());
                    }
                }
            }else if(entity.getEntity_type().equals("FeedbackRequest")){
                itemListViewHolder.tv_contentdescription.setText(entity.getFeedback_request().getDescription());
                itemListViewHolder.tv_contentdescription.setVisibility(View.VISIBLE);
                itemListViewHolder.ll_moment_gone.setVisibility(View.GONE);
                itemListViewHolder.lv_ratingmoments.setVisibility(View.GONE);
            }
           // itemListViewHolder.layout.setVisibility(View.GONE);

            itemListViewHolder.ll_first_feedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   AppToast.makeShortToast(context,position+"");
//                    Intent intent = new Intent(context, DetailContentActivity.class);
//                    intent.putExtra("id",entity.getId());
//                    intent.putExtra("headurl",entity.getSponsor().getAvatar());
//                    intent.putExtra("tvname",entity.getSponsor().getName());
//                    intent.putExtra("tvdate",RelativeDateFormat.format(new Date((long)entity.getCreated_at()*1000)).toString());
//                    intent.putExtra("tvcontent",entity.getDescription());
//                    intent.putExtra("department",entity.getSponsor().getDepartment());
//                    intent.putExtra("description",entity.getFeedback_request().getDescription());
//                    intent.putExtra("totaltype","/feedback_star/api/moments/");
//                    intent.putExtra("detailkind", 1);
//                    context.startActivity(intent);
                }
            });
//            final List<String> uriStrs = datas.get(position);
//            itemListViewHolder.layout.setHowMuchImageView(uriStrs,true);
//            itemListViewHolder.layout.setItemClickListener(new AutoGridLayout.ItemClickListener() {
//                @Override
//                public void onItemClick(View view, int position) {
//                    ///这里holderUri应该传入缩略图的Uri ，但是测试没有缩略图，所以传入的大图。
//                    //
//                    // sorry
//                    YImageBrowser.newInstance().startBrowserImage((Activity) context ,uriStrs, uriStrs , view , position);
//                }
//            });
        }

    }

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

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ItemImageViewHolder extends RecyclerView.ViewHolder {
        ImageView descImage_two,avatar_two;
        TextView tvName_two, tvDate_two, tvDesc_two,second_tv_department,first_option,second_option;
        public ItemImageViewHolder(View itemView) {
            super(itemView);
            avatar_two = (ImageView) itemView.findViewById(R.id.avatar_image_two);
            descImage_two = (ImageView) itemView.findViewById(R.id.desc_image_two);
            tvName_two = (TextView) itemView.findViewById(R.id.name_two);
            tvDate_two = (TextView) itemView.findViewById(R.id.date_two);
            tvDesc_two = (TextView) itemView.findViewById(R.id.desc_two);
            first_option= (TextView)itemView.findViewById(R.id.first_option);
            second_option=(TextView)itemView.findViewById(R.id.second_option);
            second_tv_department = (TextView)itemView.findViewById(R.id.second_tv_department);
        }
    }

    public static class ItemListViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView tvName, tvDate, tvDesc,tvreadnum,tv_contentdescription,first_readnum,first_commentsnum,first_tv_department,tv_moment_comment;
        LinearLayout ll_first_feedback,ll_moment_gone;
        ListView lv_ratingmoments;
        RatingBar ratingBar_moment_no;
       // private NormalGridLayout layout;
        public ItemListViewHolder(View itemView) {
            super(itemView);
            lv_ratingmoments=(ListView)itemView.findViewById(R.id.lv_ratingmoments);
            avatar = (ImageView) itemView.findViewById(R.id.avatar_image);
            tvName = (TextView) itemView.findViewById(R.id.name);
            tvDate = (TextView) itemView.findViewById(R.id.date);
            tvDesc = (TextView) itemView.findViewById(R.id.desc);
            first_tv_department= (TextView)itemView.findViewById(R.id.first_tv_department);
            first_readnum=(TextView)itemView.findViewById(R.id.first_readnum);
            first_commentsnum=(TextView)itemView.findViewById(R.id.first_commentsnum);
            tv_contentdescription=(TextView)itemView.findViewById(R.id.tv_contentdescription);
            ll_first_feedback=(LinearLayout)itemView.findViewById(R.id.ll_first_feedback);
           // layout = (NormalGridLayout) itemView.findViewById(R.id.autoGridLayout);
            tvreadnum = (TextView) itemView.findViewById(R.id.first_readnum);
            //tvcommentsnum=(TextView) itemView.findViewById(R.id.first_commentsnum);
            ll_moment_gone=(LinearLayout)itemView.findViewById(R.id.ll_moment_gone);
            tv_moment_comment=(TextView)itemView.findViewById(R.id.tv_moment_comment);
            ratingBar_moment_no=(RatingBar)itemView.findViewById(R.id.ratingBar_moment_no);
        }
    }
    public void clear(){

    }
}
