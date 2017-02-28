package com.example.administrator.secondapplication.fourfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/2/18 0018.
 */
public class MeViewpageAdapter extends FragmentPagerAdapter {
    private List<String> stringList;
    private List<Fragment> fragmentList;
    public MeViewpageAdapter(FragmentManager fm,List<String> stringList,List<Fragment> fragmentList) {
        super(fm);
        this.stringList = stringList;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return stringList.get(position);
    }
}
