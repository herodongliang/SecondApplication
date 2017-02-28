package com.example.administrator.secondapplication.fourfragment;

import android.content.Context;
import android.widget.TextView;

import com.example.administrator.secondapplication.R;
import com.example.administrator.secondapplication.bean.ItemModel;
import com.example.administrator.secondapplication.lrecyclertext.SuperViewHolder;


/**
 * Created by Lzx on 2016/12/30.
 */

public class DataAdapter extends ListBaseAdapter<ItemModel> {

    public DataAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_me_one_grid;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        ItemModel item = mDataList.get(position);

        TextView titleText = holder.getView(R.id.tv_me_grid);
        titleText.setText(item.title);
    }
}
