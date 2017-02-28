package com.example.administrator.secondapplication.fourfragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.secondapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {


    private TabLayout tab_me;
    private ViewPager vp_me;
    private List<Fragment> listfragment = new ArrayList<>();
    private List<String> liststring = new ArrayList<>();
    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        tab_me = (TabLayout) view.findViewById(R.id.tab_me_id);
        vp_me = (ViewPager) view.findViewById(R.id.vp_me_id);
        liststring.add("one");
        liststring.add("two");
        listfragment.add(new MeOneFragment());
        listfragment.add(new MeTwoFragment());
        MeViewpageAdapter adapter = new MeViewpageAdapter(getChildFragmentManager(),liststring,listfragment);
        vp_me.setAdapter(adapter);
        tab_me.setupWithViewPager(vp_me);
        tab_me.setTabsFromPagerAdapter(adapter);
        return view;
    }

}
