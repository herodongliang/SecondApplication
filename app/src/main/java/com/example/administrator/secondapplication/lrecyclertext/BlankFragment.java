package com.example.administrator.secondapplication.lrecyclertext;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.example.administrator.secondapplication.MainActivity;
import com.example.administrator.secondapplication.R;
import com.example.administrator.secondapplication.bean.DetailFeedbackDimensions;
import com.example.administrator.secondapplication.model.ImageInfo;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnNetWorkErrorListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements View.OnClickListener {

    /**服务器端一共多少条数据*/
    private static int TOTAL_COUNTER = 0;

    /**每一页展示多少条数据*/
    private static final int REQUEST_COUNT = 10;

    /**已经获取到多少条数据了*/
    private static int mCurrentCounter = 0;
    private PreviewHandler mHandler = new PreviewHandler(this);
    private LRecyclerView mRecyclerView = null;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private MultipleItemAdapter mMultipleItemAdapter = null;

    private ArrayList<ImageInfo> data = new ArrayList<>();

    private static final String TAG = "FirstFragment";
    private ImageInfo.NiceOneBean niceOneBean = new ImageInfo.NiceOneBean();
    private ImageInfo.FeedbackRequestBean feedbackRequestBean = new ImageInfo.FeedbackRequestBean();
    private ImageInfo.FeedbackBean feedbackBean = new ImageInfo.FeedbackBean();
    private ImageInfo.FeedbackBean.ImagesBean imagesBean = new ImageInfo.FeedbackBean.ImagesBean();
    private List<ImageInfo.FeedbackBean.DimensionsBean> dimensionsBeanList = new ArrayList<>();
    private List<DetailFeedbackDimensions.DimensionsBean> listdimesion;
    private String urlmain="http://tw.chinacloudapp.cn:8001/feedback_star/api/moments";
    private int type;
    private int labelid;
    private String labelname;
    private int providerid;
    private String providername;
    private int numpages = 1;
    String dimenlabel;
    String dimenicon;
    private String feedreq_description;
    private BlankFragment fragment;
    private WeakReference<BlankFragment> ref;


    private ImageView iv_first_rotate;
    private LinearLayout ll_first_rotate;
    private Animation rotate;
    private LinearLayout ll_first_error;
    private ImageView but_reload;
    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        iv_first_rotate = (ImageView) view.findViewById(R.id.iv_first_rotate);
        ll_first_rotate = (LinearLayout) view.findViewById(R.id.ll_first_rotate);
        ll_first_error = (LinearLayout) view.findViewById(R.id.ll_first_error);
        but_reload = (ImageView) view.findViewById(R.id.but_reload);
        but_reload.setOnClickListener(this);


        rotate = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_rotate);    //获取“旋转”动画资源
        ll_first_rotate.setVisibility(View.VISIBLE);
        iv_first_rotate.startAnimation(rotate);


        mRecyclerView =(LRecyclerView) view.findViewById(R.id.recyclerview);
        mMultipleItemAdapter = new MultipleItemAdapter(getContext());

        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mMultipleItemAdapter);
        mRecyclerView.setAdapter(mLRecyclerViewAdapter);

        DividerDecoration divider = new DividerDecoration.Builder(getContext())
                .setHeight(R.dimen.default_divider_height)
                .setPadding(R.dimen.default_divider_padding)
                .setColorResource(R.color.linecolor)
                .build();
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(divider);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);

        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mMultipleItemAdapter.clear();
                mLRecyclerViewAdapter.notifyDataSetChanged();//fix bug:crapped or attached views may not be recycled. isScrap:false isAttached:true
                data.clear();
                dimensionsBeanList.clear();
                Log.e(TAG, "onRefresh:3"+data.toString());
                mCurrentCounter = 0;
                //isRefresh = true;
                urlmain="http://tw.chinacloudapp.cn:8001/feedback_star/api/moments?page=1&page_size=10";
                Log.e(TAG, "onRefresh: 1"+urlmain);
                requestData();
            }
        });

        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (mCurrentCounter < TOTAL_COUNTER) {
                    // loading more
                    numpages++;
                    data.clear();
                    urlmain="http://tw.chinacloudapp.cn:8001/feedback_star/api/moments?page="+ numpages + "&page_size=10";
                    requestData();
                } else {
                    //the end
                    mRecyclerView.setNoMore(true);
                }
            }
        });

        mRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {

            @Override
            public void onScrollUp() {
            }

            @Override
            public void onScrollDown() {
            }


            @Override
            public void onScrolled(int distanceX, int distanceY) {
            }

            @Override
            public void onScrollStateChanged(int state) {

            }

        });

        fragment= ref.get();
        mRecyclerView.refresh();

        return view;
    }

    private void notifyDataSetChanged() {
        mLRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void addItems(ArrayList<ImageInfo> list) {

        mMultipleItemAdapter.addAll(list);
        mCurrentCounter += list.size();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.but_reload:
                ll_first_error.setVisibility(View.INVISIBLE);
                ll_first_rotate.setVisibility(View.VISIBLE);
                iv_first_rotate.startAnimation(rotate);

                mMultipleItemAdapter.clear();
                mLRecyclerViewAdapter.notifyDataSetChanged();//fix bug:crapped or attached views may not be recycled. isScrap:false isAttached:true
                data.clear();
                mCurrentCounter = 0;
                urlmain="http://tw.chinacloudapp.cn:8001/feedback_star/api/moments?page=1&page_size=10";
                requestData();
                break;
        }
    }

    private class PreviewHandler extends Handler {

        PreviewHandler(BlankFragment fragment) {
            ref = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {

            if (fragment == null || fragment.getActivity().isFinishing()) {
                return;
            }
            switch (msg.what) {

                case -1:
                    Log.e(TAG, "handleMessage:1 "+urlmain);
                    initListData(urlmain);
                    break;
                case -2:
                    fragment.notifyDataSetChanged();
                    break;
                case -3:
                    ll_first_error.setVisibility(View.VISIBLE);
                    iv_first_rotate.clearAnimation();
                    ll_first_rotate.setVisibility(View.INVISIBLE);

                        fragment.mRecyclerView.refreshComplete(REQUEST_COUNT);
                        fragment.notifyDataSetChanged();

                        fragment.mRecyclerView.setOnNetWorkErrorListener(new OnNetWorkErrorListener() {
                            @Override
                            public void reload() {
                                requestData();
                            }
                        });

                    break;
                default:
                    break;
            }
        }
    }
    private void initListData(String myurl) {

        OkHttpUtils.get().url(myurl)
                .addHeader("Authorization", MainActivity.token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: " + e.toString());
                        ll_first_error.setVisibility(View.VISIBLE);
                        iv_first_rotate.clearAnimation();
                        ll_first_rotate.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "onResponse: --------------------------->>>>>>>>" + id + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>" + response.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            JSONObject jsonObject1 = jsonObject.getJSONObject("_links");
                            String last = jsonObject1.getString("last");
                            String lastnum=last.substring(last.indexOf("=")+1,last.lastIndexOf("&"));

                            TOTAL_COUNTER=Integer.parseInt(lastnum)*10;
                            Log.e(TAG, "onResponse:mmmmmmmmmmmmmmmmmmmmmmmm "+TOTAL_COUNTER);


                            JSONArray jsonarray = jsonObject.getJSONArray("data");
                            int currentSize = fragment.mMultipleItemAdapter.getItemCount();
                            for (int j = 0; j < jsonarray.length(); j++) {

                                if (data.size() + currentSize >= TOTAL_COUNTER) {
                                    break;
                                }

                                JSONObject jsonObject3 = jsonarray.getJSONObject(j);
                                int getid = jsonObject3.getInt("id");
                                int comment_count = jsonObject3.getInt("comment_count");
                                Double created_at = jsonObject3.getDouble("created_at");
                                Double update_at = jsonObject3.getDouble("updated_at");
                                String entity_type = jsonObject3.getString("entity_type");

                                if (entity_type.equals("NiceOne")) {
                                    type=ImageInfo.TEXT;
                                } else {
                                    type=ImageInfo.IMG;
                                }
                                String maindescription = jsonObject3.getString("description");
                                int likes = jsonObject3.getInt("likes");
                                int page_views = jsonObject3.getInt("page_views");
                                Log.e(TAG, "3: " );
                                JSONObject jsonObject4 = jsonObject3.getJSONObject("sponsor");
                                String sponsoremail = jsonObject4.getString("email");
                                String sponsorname = jsonObject4.getString("name");
                                String sponsorcountry = jsonObject4.getString("country");
                                String sponsordepartment = jsonObject4.getString("department");
                                Double sponsorcreated_at = jsonObject4.getDouble("created_at");
                                String avatar = jsonObject4.getString("avatar");
                                ImageInfo.SponsorBean sponsorBean = new ImageInfo.SponsorBean(avatar, null, sponsorcreated_at, sponsordepartment, sponsoremail, 0, sponsorname);
                                JSONObject jsonObject5 = jsonObject3.getJSONObject("feedback_request");
                                JSONObject jsonObject9 = jsonObject3.getJSONObject("feedback");
                                JSONObject jsonObject6 = jsonObject3.getJSONObject("nice_one");
                                Log.e(TAG, "2 " );
                                if (jsonObject6.length() != 0 && jsonObject6 != null) {
                                    Double niceonecreated_at = jsonObject6.getDouble("created_at");
                                    int niceoneid = jsonObject6.getInt("id");
                                    JSONArray jsonArray = jsonObject6.getJSONArray("label");
                                    ArrayList<ImageInfo.NiceOneBean.LabelBean> labelBeanArrayList = new ArrayList<>();
                                    for (int n = 0; n < jsonArray.length(); n++) {
                                        JSONObject jsonobject7 = jsonArray.getJSONObject(n);
                                        labelid = jsonobject7.getInt("id");
                                        labelname = jsonobject7.getString("name");
                                        JSONObject jsonobject8 = jsonObject6.getJSONObject("provider");
                                        String providerdp = jsonobject8.getString("department");
                                        providerid = jsonobject8.getInt("id");
                                        providername = jsonobject8.getString("name");
                                        ImageInfo.NiceOneBean.LabelBean labelBean = new ImageInfo.NiceOneBean.LabelBean(labelid, labelname);
                                        labelBeanArrayList.add(labelBean);
                                        niceOneBean = new ImageInfo.NiceOneBean(niceonecreated_at, niceoneid, new ImageInfo.NiceOneBean.ProviderBean(providerdp, providerid, providername), labelBeanArrayList);
                                    }
                                    Log.e(TAG, "labelBeanArrayList: "+labelBeanArrayList.toString());
                                } else if(jsonObject5 != null && jsonObject5.length() != 0){
                                    feedreq_description = jsonObject5.getString("description");
                                    feedbackRequestBean = new ImageInfo.FeedbackRequestBean(feedreq_description);

                                }else if(jsonObject9 != null && jsonObject9.length() != 0){
                                    listdimesion = new ArrayList<DetailFeedbackDimensions.DimensionsBean>();
                                    String comment = jsonObject9.getString("comment");
                                    int star = jsonObject9.getInt("star");
                                    JSONArray jsonArray = jsonObject9.getJSONArray("dimensions");
                                    List<ImageInfo.FeedbackBean.ImagesBean> imagesBeanList = new ArrayList<>();
                                    if(jsonArray.length()>0) {
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject jsonObject10 = jsonArray.getJSONObject(i);
                                            String dimencomment = jsonObject10.getString("comment");
                                            if(jsonObject10.getInt("evaluative_dimension_id")!=0) {
                                                dimenlabel = jsonObject10.getString("label");
                                                dimenicon = jsonObject10.getString("icon");
                                            }
                                            int dimenstar = jsonObject10.getInt("star");
                                            DetailFeedbackDimensions.DimensionsBean dimensionsBean = new DetailFeedbackDimensions.DimensionsBean(dimencomment,dimenicon,dimenlabel,(double)dimenstar,0.0);
                                            listdimesion.add(dimensionsBean);
                                            feedbackBean = new ImageInfo.FeedbackBean(comment,star,dimensionsBeanList,imagesBeanList);
                                        }
                                    }
                                    JSONArray jsonArray1 = jsonObject9.getJSONArray("images");
                                   // Log.e(TAG, "jsonArray1: "+jsonArray1.toString());

                                    if(jsonArray1.length()>0) {
                                        for (int i = 0; i < jsonArray1.length(); i++) {
                                            JSONObject jsonObject11 = jsonArray1.getJSONObject(i);
                                            int imageid = jsonObject11.getInt("id");
                                            String url = jsonObject11.getString("url");
                                            Log.e(TAG, "url: "+url+"___________"+imageid);
                                            imagesBean = new ImageInfo.FeedbackBean.ImagesBean(imageid,url);
                                            imagesBeanList.add(imagesBean);
                                        }
                                        Log.e(TAG, "imagesBeanList: "+imagesBeanList.toString());
                                    }
                                    feedbackBean = new ImageInfo.FeedbackBean(comment,star,dimensionsBeanList,imagesBeanList);
                                }
                                ImageInfo imageInfo = new ImageInfo(listdimesion,comment_count, created_at, maindescription, entity_type,feedbackBean , feedbackRequestBean, getid, likes, niceOneBean, page_views, sponsorBean, 0, update_at, type, 0);

                                data.add(imageInfo);
                            }
                            Log.e(TAG, "data: "+data.toString());
                            ll_first_error.setVisibility(View.INVISIBLE);
                            iv_first_rotate.clearAnimation();
                            ll_first_rotate.setVisibility(View.INVISIBLE);

                            fragment.addItems(data);
                            fragment.mRecyclerView.refreshComplete(REQUEST_COUNT);
                            fragment.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
        //}
    }

    /**
     * 模拟请求网络
     */
    private void requestData() {
        Log.e(TAG, "requestData");
        new Thread() {

            @Override
            public void run() {
                super.run();

                try {
                    Thread.sleep(800);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //模拟一下网络请求失败的情况
                if(NetworkUtils.isNetAvailable(getActivity())) {
                    mHandler.sendEmptyMessage(-1);
                } else {
                    mHandler.sendEmptyMessage(-3);
                }
            }
        }.start();
    }


}
