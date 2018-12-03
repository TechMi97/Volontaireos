package com.example.asad.volontaireos;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;



public class FormList extends ArrayAdapter<Infodata> {

    private Activity context;
    private List<Infodata> formList;

    public FormList(Activity context,List<Infodata> formList){
    super(context,R.layout.list_layout,formList);
    this.context=context;
    this.formList = formList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null,true);

        TextView textViewTitle = (TextView) listViewItem.findViewById(R.id.textViewTitle);
        TextView textViewDesc = (TextView) listViewItem.findViewById(R.id.textViewDesc);

        Infodata form = formList.get(position);

        textViewTitle.setText(form.getInfoTitle());
        textViewDesc.setText(form.getInfoDescription());


        return listViewItem;


    }
}
