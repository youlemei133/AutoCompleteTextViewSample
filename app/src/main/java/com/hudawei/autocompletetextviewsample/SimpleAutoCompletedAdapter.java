package com.hudawei.autocompletetextviewsample;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hudawei on 2017/2/20.
 *
 */

public class SimpleAutoCompletedAdapter extends AutoCompletedAdapter<String> {
    public SimpleAutoCompletedAdapter(Context context, List<String> dates) {
        super(context, dates, R.layout.item_auto_text_view);
    }


    @Override
    public boolean filterResult(String constraintStr, String item) {
        if (item.contains(constraintStr))
            return true;
        return false;
    }

    @Override
    ViewHolder createViewHolder() {
        return new SimpleViewHolder();
    }

    private class SimpleViewHolder extends ViewHolder {
        private TextView textView;
        private ImageView imageView;

        @Override
        void initBindView(View convertView,int position) {
            imageView = (ImageView) convertView.findViewById(R.id.imageView);
            textView = (TextView) convertView.findViewById(R.id.textView);
        }

        @Override
        void bindViewDate(String date,int position) {
            textView.setText(date);
        }
    }
}
