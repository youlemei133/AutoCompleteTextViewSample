package com.hudawei.autocompletetextviewsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompletedTextView;
    private MultiAutoCompleteTextView multiAutoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        autoCompletedTextView=(AutoCompleteTextView)findViewById(R.id.autoCompletedTextView);
        multiAutoCompleteTextView=(MultiAutoCompleteTextView)findViewById(R.id.multiAutoCompleteTextView);

        List<String> dates = initDate();

        SimpleAutoCompletedAdapter sacAdapter=new SimpleAutoCompletedAdapter(this,dates);
        autoCompletedTextView.setAdapter(sacAdapter);

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line,dates);
        multiAutoCompleteTextView.setAdapter(adapter);
        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    private List<String> initDate() {
        List<String> dates=new ArrayList<>();
        dates.add("小猫猫");
        dates.add("小狗狗");
        dates.add("小猪猪");
        dates.add("小妞妞");
        dates.add("小牛牛");
        return dates;
    }
}
