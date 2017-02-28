package com.example.administrator.secondapplication.fourfragment;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.administrator.secondapplication.R;
import com.example.administrator.secondapplication.bean.ItemModel;
import com.example.administrator.secondapplication.lrecyclertext.NetworkUtils;
import com.example.administrator.secondapplication.myclass.SampleHeader;
import com.github.jdsjlzx.interfaces.OnNetWorkErrorListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.moxun.tagcloudlib.view.TagCloudView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeOneFragment extends Fragment {


    private static final String TAG = "MeOneFragment";
    private LRecyclerView gv_me_one;
    private LRecyclerViewAdapter lRecyclerViewAdapter = null;
    private DataAdapter mDataAdapter = null;
    /**服务器端一共多少条数据*/
    private static final int TOTAL_COUNTER = 64;

    /**每一页展示多少条数据*/
    private static final int REQUEST_COUNT = 10;

    /**已经获取到多少条数据了*/
    private static int mCurrentCounter = 0;
    private PreviewHandler mHandler = new PreviewHandler(this);
    public MeOneFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_me_one,container,false);

        gv_me_one = (LRecyclerView) view.findViewById(R.id.gv_me_one);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
        gv_me_one.setLayoutManager(manager);
        mDataAdapter = new DataAdapter(getContext());
        lRecyclerViewAdapter = new LRecyclerViewAdapter(mDataAdapter);
        gv_me_one.setAdapter(lRecyclerViewAdapter);
        requestData();
        gv_me_one.setPullRefreshEnabled(false);
        lRecyclerViewAdapter.addHeaderView(new SampleHeader(getContext()));


        return view;
    }


    private void notifyDataSetChanged() {
        lRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void addItems(ArrayList<ItemModel> list) {
        mDataAdapter.addAll(list);
        mCurrentCounter += list.size();
    }

    private class PreviewHandler extends Handler {

        private WeakReference<MeOneFragment> ref;

        PreviewHandler(MeOneFragment activity) {
            ref = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final MeOneFragment activity = ref.get();
            if (activity == null || activity.getActivity().isFinishing()) {
                return;
            }

            switch (msg.what) {
                case -1:

                    int currentSize = activity.mDataAdapter.getItemCount();

                    //模拟组装10个数据
                    ArrayList<ItemModel> newList = new ArrayList<>();
                    for (int i = 0; i < 40; i++) {
                        if (newList.size() + currentSize >= TOTAL_COUNTER) {
                            break;
                        }
                        ItemModel item = new ItemModel();
                        item.id = currentSize + i;
                        item.title = "item" + (item.id);

                        newList.add(item);
                    }


                    activity.addItems(newList);

                    activity.gv_me_one.refreshComplete(10);

                    break;
                case -2:
                    activity.notifyDataSetChanged();
                    break;
                case -3:
                    activity.gv_me_one.refreshComplete(10);
                    activity.notifyDataSetChanged();
                    activity.gv_me_one.setOnNetWorkErrorListener(new OnNetWorkErrorListener() {
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
