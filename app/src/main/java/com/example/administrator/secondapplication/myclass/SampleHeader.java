package com.example.administrator.secondapplication.myclass;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.example.administrator.secondapplication.R;
import com.example.administrator.secondapplication.fourfragment.MeOneCloudAdapter;
import com.moxun.tagcloudlib.view.TagCloudView;

import java.util.ArrayList;
import java.util.List;


/**
 * RecyclerView的HeaderView，简单的展示一个TextView
 */
public class SampleHeader extends RelativeLayout {
    private TagCloudView tagcloudview;
    private MeOneCloudAdapter adapter;
    private List<String> list  = new ArrayList<>();
    public SampleHeader(Context context) {
        super(context);
        init(context);
    }

    public SampleHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SampleHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        inflate(context, R.layout.header_cloud, this);
        tagcloudview = (TagCloudView)findViewById(R.id.tagcloudview);
        tagcloudview.setBackgroundColor(Color.LTGRAY);
        getList();
        adapter = new MeOneCloudAdapter(new String[20]);
        tagcloudview.setAdapter(adapter);
    }

    private void getList() {
        for (int i = 0; i < 20; i++) {
            list.add("哈哈"+i);
        }
    }
}