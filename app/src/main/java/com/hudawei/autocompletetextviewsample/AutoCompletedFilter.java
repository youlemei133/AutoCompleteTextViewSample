package com.hudawei.autocompletetextviewsample;

import android.util.Log;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hudawei on 2017/2/20.
 *
 */

public abstract class AutoCompletedFilter<E> extends Filter {
    private List<E> mSrcDates;
    private List<E> mResults;
    private AutoCompletedAdapter mAdapter;
    public AutoCompletedFilter(AutoCompletedAdapter adapter,List<E> srcDates){
        mResults=new ArrayList<>();
        mSrcDates=srcDates;
        mAdapter=adapter;
    }
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        mResults.clear();
        if(constraint!=null&&constraint.length()!=0){
            String constraintStr=constraint.toString().toLowerCase().trim();
            for(E item: mSrcDates){
                if(filterResult(constraintStr,item)){
                    mResults.add(item);
                }
            }
        }else{
            mResults.addAll(mSrcDates);
        }
        results.count=mResults.size();
        results.values=mResults;
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        mAdapter.mFilteredDates.clear();
        mAdapter.mFilteredDates.addAll((List<E>)results.values);
        mAdapter.notifyDataSetChanged();
    }
    
    public abstract boolean filterResult(String constraintStr,E item);
}
