package com.example.administrator.secondapplication.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.secondapplication.R;
import com.example.administrator.secondapplication.utils.AnotherUtils;


/**
 * Created by GIGAMOLE on 7/27/16.
 */
public class HorizontalPagerAdapter extends PagerAdapter {

    private final AnotherUtils.LibraryObject[] LIBRARIES = new AnotherUtils.LibraryObject[]{
            new AnotherUtils.LibraryObject(
                    R.mipmap.image1,
                    "Strategy"
            ),
            new AnotherUtils.LibraryObject(
                    R.mipmap.image2,
                    "Design"
            ),
            new AnotherUtils.LibraryObject(
                    R.mipmap.image3,
                    "Development"
            ),
            new AnotherUtils.LibraryObject(
                    R.mipmap.image4,
                    "Quality Assurance"
            )
    };

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private boolean mIsTwoWay;

    public HorizontalPagerAdapter(final Context context, final boolean isTwoWay) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mIsTwoWay = isTwoWay;
    }

    @Override
    public int getCount() {
        return mIsTwoWay ? 6 : LIBRARIES.length;
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view;
        view = mLayoutInflater.inflate(R.layout.item, container, false);
        AnotherUtils.setupItem(view, LIBRARIES[position]);
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }
}
