package com.example.administrator.secondapplication.secondfragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.secondapplication.R;
import com.example.administrator.secondapplication.adapters.HorizontalPagerAdapter;
import com.example.administrator.secondapplication.info.ListEntity;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {
    private RecyclerView recyclerView;

    public SecondFragment() {
        // Required empty public constructor
    }
    //private ChildViewPager mViewPager;
    private PagerAdapter mAdapter;

   // int[] imgRes = {R.mipmap.image1, R.mipmap.image2, R.mipmap.image3};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
     //   mViewPager = (ChildViewPager) view.findViewById(R.id.id_viewpager);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_second_view);
        initListData();
    //    getviewpager();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final HorizontalInfiniteCycleViewPager horizontalInfiniteCycleViewPager =
                (HorizontalInfiniteCycleViewPager) view.findViewById(R.id.hicvp);
        horizontalInfiniteCycleViewPager.setAdapter(new HorizontalPagerAdapter(getContext(), false));

    }

//    private void getviewpager() {
//
//        //设置Page间间距
//        mViewPager.setPageMargin(20);
//        //设置缓存的页面数量
//        mViewPager.setOffscreenPageLimit(3);
//        mViewPager.setAdapter(mAdapter = new PagerAdapter()
//        {
//            @Override
//            public Object instantiateItem(ViewGroup container, int position)
//            {
//                ImageView view = new ImageView(getContext());
//                view.setImageResource(imgRes[position%3]);
//                container.addView(view);
//                return view;
//            }
//
//            @Override
//            public void destroyItem(ViewGroup container, int position, Object object)
//            {
//                container.removeView((View) object);
//            }
//
//            @Override
//            public int getCount()
//            {
//                return Integer.MAX_VALUE;
//            }
//
//            @Override
//            public boolean isViewFromObject(View view, Object o)
//            {
//                return view == o;
//            }
//        });
//        mViewPager.setPageTransformer(true,new AlphaPageTransformer());
//    }

    SecondFragmentAdapter adapter;
    List<ListEntity> data;

    private void initListData() {
        data = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        data.add(new ListEntity());

        for(int i=0;i<20;i++){
            ListEntity listEntity=new ListEntity();
            listEntity.name="username";
            listEntity.date="11月5日1:25";
            listEntity.content="linearLayoutManager we fall in love today";
            listEntity.avatarUrl="http://img2.imgtn.bdimg.com/it/u=2452097355,3155741241&fm=21&gp=0.jpg";
            listEntity.descUrl="http://static.ettoday.net/etlife/images/27/d27068.jpg";
            listEntity.layoutType=1;
            data.add(listEntity);
        }
        setAdapter();
    }

    public void setAdapter() {
        if (adapter == null) {
            adapter = new SecondFragmentAdapter(getContext(), data);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

}
