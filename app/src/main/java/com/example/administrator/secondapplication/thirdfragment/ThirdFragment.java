package com.example.administrator.secondapplication.thirdfragment;


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
import com.example.administrator.secondapplication.bean.Messages;
import com.example.administrator.secondapplication.lrecyclertext.AppToast;
import com.example.administrator.secondapplication.lrecyclertext.NetworkUtils;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
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
public class ThirdFragment extends Fragment implements View.OnClickListener {

    private LRecyclerView recycler_third;
    //  private List<String> list  = new ArrayList<>();
    private ThirdMyAdapter adapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    /**服务器端一共多少条数据*/
    private static int TOTAL_COUNTER = 0;

    /**每一页展示多少条数据*/
    private static final int REQUEST_COUNT = 10;

    /**已经获取到多少条数据了*/
    private static int mCurrentCounter = 0;
    private PreviewHandler mHandler = new PreviewHandler(this);
    public ThirdFragment() {
        // Required empty public constructor
    }


    private ArrayList<Messages> list=new ArrayList<>();
    private static final String TAG = "ThirdFragment";
    private int getoneid;
    private String content;
    private boolean read;
    private int entity_id;
    private String entity_type;
    private Messages.StakeHolderBean stake_holder;
    private int user_id;
    private Double onecreated_at;

    private String country;
    private int twocreated_at;
    private String lastnum;
    private String department;
    private String email;
    private int gettwoid;
    private String lastlink="http://tw.chinacloudapp.cn:8001/feedback_star/api/messages?page=1&page_size=10";
    private String name;
    private int numpages;
    private List<Integer> typeList = new ArrayList<>();

    private LinearLayout tv_nomessage;
    private Animation rotate;
    private LinearLayout ll_third_rotate;
    private ImageView iv_third_rotate;
    private LinearLayout ll_error;
    private ImageView but_third_reload;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_third, container, false);
        ll_third_rotate = (LinearLayout) view.findViewById(R.id.ll_thirt_rotate);
        iv_third_rotate = (ImageView)view.findViewById(R.id.iv_third_rotate);
        ll_error=(LinearLayout) view.findViewById(R.id.ll_third_error);
        but_third_reload = (ImageView) view.findViewById(R.id.but_third_reload);
        but_third_reload.setOnClickListener(this);
        rotate= AnimationUtils.loadAnimation(getActivity(), R.anim.anim_rotate);	//获取“旋转”动画资源
        ll_third_rotate.setVisibility(View.VISIBLE);
        iv_third_rotate.startAnimation(rotate);
        tv_nomessage = (LinearLayout) view.findViewById(R.id.ll_nomessage);

        recycler_third = (LRecyclerView) view.findViewById(R.id.recycler_third);
        adapter = new ThirdMyAdapter(getActivity());

        mLRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        recycler_third.setAdapter(mLRecyclerViewAdapter);

        //mRecyclerView.setHasFixedSize(true);

        recycler_third.setLayoutManager(new LinearLayoutManager(getActivity()));

        recycler_third.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
        recycler_third.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        recycler_third.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);

        //add a HeaderView
//        final View header = LayoutInflater.from(this).inflate(R.layout.sample_header,(ViewGroup)findViewById(android.R.id.content), false);
//        mLRecyclerViewAdapter.addHeaderView(header);

        recycler_third.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {

                adapter.clear();
                mLRecyclerViewAdapter.notifyDataSetChanged();//fix bug:crapped or attached views may not be recycled. isScrap:false isAttached:true
                list.clear();
                mCurrentCounter = 0;
                numpages=0;
                lastlink="http://tw.chinacloudapp.cn:8001/feedback_star/api/messages?page=1&page_size=10";
                requestData();
            }
        });

        recycler_third.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                if (mCurrentCounter < TOTAL_COUNTER) {
                    // loading more
                    list.clear();
                    numpages++;
                    lastlink="http://tw.chinacloudapp.cn:8001/feedback_star/api/messages?page="+ numpages + "&page_size=10";
                    requestData();
                } else {
                    //the end
                    recycler_third.setNoMore(true);
                }
            }
        });

        recycler_third.setLScrollListener(new LRecyclerView.LScrollListener() {

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
        //设置头部加载颜色
        recycler_third.setHeaderViewColor(R.color.colorAccent, R.color.titleColor ,android.R.color.white);
        //设置底部加载颜色
        recycler_third.setFooterViewColor(R.color.colorAccent, R.color.text_color ,android.R.color.white);
        //设置底部加载文字提示
        recycler_third.setFooterViewHint("拼命加载中","已经全部为你呈现了","网络不给力啊，点击再试一次吧");

        recycler_third.refresh();

        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (adapter.getDataList().size() > position) {
                    Messages item = adapter.getDataList().get(position);
                    AppToast.showShortText(getActivity(), position);
                   // adapter.remove(position);
                }

            }

        });

        return view;
    }
    private void initData(String url) {

        OkHttpUtils.get().url(url)
                .addHeader("Authorization", MainActivity.token)
                .build()
                .execute(new StringCallback() {
                    private String avatar;

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        //Log.e(TAG, "onError: "+e.toString());
                        ll_error.setVisibility(View.VISIBLE);
                        ll_third_rotate.setVisibility(View.INVISIBLE);
                        iv_third_rotate.clearAnimation();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //Log.e(TAG, "onResponse: "+response);

                        try {
                            JSONObject jsonobjectdata=new JSONObject(response);
                            if(jsonobjectdata!=null && jsonobjectdata.length()>0) {
                                JSONObject jsonObject = jsonobjectdata.getJSONObject("_links");
                                String last = jsonObject.getString("last");
                                String next = jsonObject.getString("next");
                                lastlink = next;
                                lastnum=last.substring(last.indexOf("=")+1,last.lastIndexOf("&"));
                                TOTAL_COUNTER = Integer.parseInt(lastnum);
                                Log.e(TAG, "onResponse:mmmmmmmmmmmmmmmmmmmmmmmm "+TOTAL_COUNTER);

                                JSONArray jsonarray = jsonobjectdata.getJSONArray("data");
                                if(jsonarray!=null && jsonarray.length()>0){
                                    recycler_third.setVisibility(View.VISIBLE);
                                    for (int i = 0; i < jsonarray.length(); i++) {
                                        JSONObject jsonobject2 = jsonarray.getJSONObject(i);
                                        getoneid = jsonobject2.getInt("id");
                                        content = jsonobject2.getString("content");
                                        read = jsonobject2.getBoolean("read");
                                        entity_id = jsonobject2.getInt("entity_id");
                                        entity_type = jsonobject2.getString("entity_type");

                                        typeList.add(1);
                                        //recycler_third.setTypeList(typeList);

                                        user_id = jsonobject2.getInt("user_id");
                                        onecreated_at = jsonobject2.getDouble("created_at");
                                        JSONObject jsonobject3 = jsonobject2.getJSONObject("stake_holder");
                                        if (jsonobject3 != null && jsonobject3.length() > 0) {
                                            country = jsonobject3.getString("country");
                                            twocreated_at = jsonobject3.getInt("created_at");
                                            department = jsonobject3.getString("department");
                                            email = jsonobject3.getString("email");
                                            gettwoid = jsonobject3.getInt("id");
                                            name = jsonobject3.getString("name");
                                            avatar = jsonobject3.getString("avatar");
                                        }
                                        Messages.StakeHolderBean stakeHolderBean = new Messages.StakeHolderBean(country, twocreated_at, department, email, gettwoid, name,avatar);
                                        list.add(new Messages(getoneid, content, read, entity_id, stakeHolderBean, user_id, onecreated_at, entity_type));
                                        Log.e(TAG, "list: " + list.toString());
                                        if (list.size() > 0 && list != null) {
                                            tv_nomessage.setVisibility(View.GONE);
                                            Log.e(TAG, "onResponse: GONE");
                                        }

                                    }
                                }else{
                                    Log.e(TAG, "error: " );
                                    tv_nomessage.setVisibility(View.VISIBLE);
                                    //recyclerMagicView.setVisibility(View.GONE);
                                }
                            }
                            activity.addItems(list);
                            activity.recycler_third.refreshComplete(10);
                            activity.notifyDataSetChanged();
                            //setThirdAdapter();
                            ll_third_rotate.setVisibility(View.INVISIBLE);
                            ll_third_rotate.clearAnimation();
                            //int id,String content, boolean read, int entity_id, StakeHolderBean stake_holder, int user_id, String created_at, String entity_type
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        // }
    }


    private void notifyDataSetChanged() {
        mLRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void addItems(ArrayList<Messages> list) {
        adapter.addAll(list);
        mCurrentCounter ++;
    }
    private WeakReference<ThirdFragment> ref;
    private ThirdFragment activity;

    @Override
    public void onClick(View view) {
      switch (view.getId()){
          case R.id.but_third_reload:
              ll_error.setVisibility(View.INVISIBLE);
              ll_third_rotate.setVisibility(View.VISIBLE);
              iv_third_rotate.startAnimation(rotate);

              adapter.clear();
              mLRecyclerViewAdapter.notifyDataSetChanged();//fix bug:crapped or attached views may not be recycled. isScrap:false isAttached:true
              list.clear();
              mCurrentCounter = 0;
              numpages=0;
              lastlink="http://tw.chinacloudapp.cn:8001/feedback_star/api/messages?page=1&page_size=10";
              requestData();
              break;
      }
    }

    private class PreviewHandler extends Handler {

        PreviewHandler(ThirdFragment activity) {
            ref = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            activity = ref.get();
            if (activity == null || activity.getActivity().isFinishing()) {
                return;
            }

            switch (msg.what) {
                case -1:

                   // int currentSize = activity.adapter.getItemCount();

                    //模拟组装10个数据
                   // ArrayList<Messages> newList = new ArrayList<>();
                    initData(lastlink);

                    break;
                case -2:
                    activity.notifyDataSetChanged();
                    break;
                case -3:
                    ll_error.setVisibility(View.VISIBLE);
                    ll_third_rotate.setVisibility(View.INVISIBLE);
                    iv_third_rotate.clearAnimation();

                    activity.recycler_third.refreshComplete(10);
                    activity.notifyDataSetChanged();
                    activity.recycler_third.setOnNetWorkErrorListener(new OnNetWorkErrorListener() {
                        @Override
                        public void reload() {
                            requestData();
                        }
                    });
                    break;
            }
        }
    }

    /**
     * 模拟请求网络
     */
    private void requestData() {

        new Thread() {

            @Override
            public void run() {
                super.run();

                try {
                    Thread.sleep(100);
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
