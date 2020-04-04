package com.dbms.questionbank;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class questionlist extends ArrayAdapter <quest>{

    private Activity context;
    private List<quest> questList;
    public questionlist(Activity context,List<quest> questList){
         super(context,R.layout.list_layout,questList);

            this.context=context;
            this.questList=questList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater inflater=context.getLayoutInflater();
        View listView=inflater.inflate(R.layout.list_layout,null,true);
        TextView question =(TextView)listView.findViewById(R.id.textView17);
        TextView sub=(TextView)listView.findViewById(R.id.textView18);
        TextView level =(TextView)listView.findViewById(R.id.textView19);
        quest quest1 =questList.get(position);
        question.setText(quest1.getQues());
        sub.setText(quest1.getSubj());
        level.setText(quest1.getLeve());
        return listView;


    }
}
