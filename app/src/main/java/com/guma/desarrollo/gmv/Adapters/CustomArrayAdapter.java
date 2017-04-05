package com.guma.desarrollo.gmv.Adapters;

import java.util.List;

//import com.danielme.blog.demo.listviewcheckbox.R;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.guma.desarrollo.core.Row;
import com.guma.desarrollo.gmv.R;

/**
 * Created by luis.perez on 03/04/2017.
 */

public class CustomArrayAdapter extends ArrayAdapter<Row> implements View.OnClickListener {
    private LayoutInflater layoutInflater;
    public CustomArrayAdapter(Context context, List<Row> objects){
        super(context, 0, objects);
        layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView ==null)
        {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.listview_row, parent, false);
            holder.setTextViewTitle((TextView) convertView.findViewById(R.id.textViewTitle));
            holder.setTextViewSubtitle((TextView) convertView.findViewById(R.id.textViewSubtitle));
            holder.setCheckBox((CheckBox) convertView.findViewById(R.id.checkBox));
            convertView.setTag(holder);
        }
        else
        {
            holder = (Holder) convertView.getTag();
        }
        final Row row = getItem(position);
        holder.getTextViewTitle().setText(row.getTitle());
        holder.getTextViewSubtitle().setText(row.getSubtitle());
        holder.getCheckBox().setTag(position);
        holder.getCheckBox().setChecked(row.isChecked());
        holder.getCheckBox().setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        CheckBox checkBox = (CheckBox) v;
        int position = (Integer) v.getTag();
        getItem(position).setChecked(checkBox.isChecked());

        //String msg = this.getContext().getString(R.string.check_toast, position, checkBox.isChecked());
        //Toast.makeText(this.getContext(), msg, Toast.LENGTH_SHORT).show();;
    }

    static class Holder {
        TextView textViewTitle;
        TextView textViewSubtitle;
        CheckBox checkBox;

        public TextView getTextViewTitle()
        {
            return textViewTitle;
        }

        public void setTextViewTitle(TextView textViewTitle)
        {
            this.textViewTitle = textViewTitle;
        }

        public TextView getTextViewSubtitle()
        {
            return textViewSubtitle;
        }

        public void setTextViewSubtitle(TextView textViewSubtitle)
        {
            this.textViewSubtitle = textViewSubtitle;
        }
        public CheckBox getCheckBox()
        {
            return checkBox;
        }
        public void setCheckBox(CheckBox checkBox)
        {
            this.checkBox = checkBox;
        }

    }

}
