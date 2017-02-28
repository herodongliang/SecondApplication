package com.example.administrator.secondapplication.fourfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.secondapplication.R;
import com.example.administrator.secondapplication.myclass.LineChartView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeTwoFragment extends Fragment {

    LineChartView lineChartView;

    public MeTwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_me_two, container, false);
        lineChartView = (LineChartView) view.findViewById(R.id.line_chart);
        String[] xdate = new String[]{"1-1", "1-2", "1-3", "1-4", "1-5", "1-6", "1-7"};
        String[] xdate1 = new String[]{"1-1", "1-2", "1-3", "1-4", "1-5", "1-6", "1-7", "1-8", "1-8", "1-10"};
        String[] ydata = lineChartView.getFundWeekYdata("5.00", "1.00");
        float[] data1 = new float[]{4.00f, 2.00f, 3.40f, 2.50f, 5.00f, 1.50f, 5f};
        float[] data = new float[]{1.00f, 1.50f, 2.40f, 2.50f, 3.50f, 4.30f, 4.7f, 4.90f, 4.00f, 5.00f};
        float[] data2 = new float[]{1.00f, 1.30f, 1.40f, 1.50f, 2.00f, 3f, 3.2f, 3.6f, 4.5f, 5.0f};
        lineChartView.setData(xdate, ydata, data1);
        return view;
    }

}
