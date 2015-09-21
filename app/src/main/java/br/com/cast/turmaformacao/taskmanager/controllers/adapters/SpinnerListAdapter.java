package br.com.cast.turmaformacao.taskmanager.controllers.adapters;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entities.Label;

public class SpinnerListAdapter extends BaseAdapter {

    private List<Label> labelList;
    private Activity context;

    public SpinnerListAdapter(Activity context, List<Label> labelList) {
        this.context = context;
        this.labelList = labelList;
    }


    public void setDataValues(List<Label> values) {
        labelList.clear();
        labelList.addAll(values);
    }

    @Override
    public int getCount() {
        return labelList.size();
    }

    @Override
    public Label getItem(int position) {
        return labelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Label label = getItem(position);
        View labelListItemView = context.getLayoutInflater().inflate(R.layout.spinner_item_label, parent, false);


        TextView textViewName = (TextView) labelListItemView.findViewById(R.id.textViewName);
        textViewName.setText(label.getName());

        if (label.getColor() != null) {

            int hexColor = android.graphics.Color.parseColor(label.getColor().getHex());
            labelListItemView.findViewById(R.id.layoutId).setBackgroundColor(hexColor);


        }
        return labelListItemView;
    }
}
