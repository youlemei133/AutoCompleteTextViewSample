package com.hudawei.autocompletetextviewsample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hudawei on 2017/2/20.
 */

public abstract class AutoCompletedAdapter<E> extends ArrayAdapter {
    public List<E> mDates;
    public List<E> mFilteredDates;
    private Context mContext;
    private int mResource;
    private ViewHolder mHolder;

    public AutoCompletedAdapter(Context context, List<E> dates, int resource) {
        super(context, 0,dates);
        mFilteredDates=new ArrayList<>();
        mContext = context;
        mDates = dates;
        mResource = resource;
    }

    @Override
    public int getCount() {
        return mFilteredDates == null ? 0 : mFilteredDates.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            mHolder = createViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
            mHolder.initBindView(convertView,position);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        mHolder.bindViewDate(mFilteredDates.get(position),position);

        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new AutoCompletedFilter<E>(this, mDates) {
            @Override
            public boolean filterResult(String constraintStr, E item) {
                return AutoCompletedAdapter.this.filterResult(constraintStr,item);
            }
        };
    }

    public abstract boolean filterResult(String constraintStr, E item);

    abstract ViewHolder createViewHolder();

    abstract class ViewHolder {
        abstract void initBindView(View convertView,int position) ;

        abstract void bindViewDate(E date,int position) ;
    }
}
