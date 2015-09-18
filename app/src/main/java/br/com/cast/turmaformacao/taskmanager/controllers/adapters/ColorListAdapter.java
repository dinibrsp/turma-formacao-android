package br.com.cast.turmaformacao.taskmanager.controllers.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entities.Color;


public class ColorListAdapter extends BaseAdapter{

    private Context context;
    private Color[] colors;

    public ColorListAdapter(Activity activity){
        super();
        this.context = activity;
        this.colors = colors;
    }


    @Override
    public int getCount() {
        return colors.length;
    }

    @Override
    public Object getItem(int position) {
        return this.colors[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = context.getLayoutInflater().inflate(R.layout.list_item_color, parent, false);
        int hexColor = android.graphics.Color.parseColor(getItem(position).getHex());
        view.findViewById(R.id.viewColor).setBackgroundColor(hexColor);
        return view;
    }
}
