package com.impact.mydatabaseproject;


import android.content.Context;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;


/**
 * Created by pra...deep on 4/19/2017.
 */
public class NewAdapter extends RecyclerView.Adapter<NewAdapter.ViewHolder> {
    private ArrayList<Module> moduleItems;


    public NewAdapter(ArrayList<Module> moduleItems) {
        this.moduleItems = moduleItems;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);


        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Module module = moduleItems.get(position);



        holder.textViewId.setText(Integer.toString(module.getId()));
        holder.textViewName.setText(module.getName());
        holder.textViewSurName.setText(module.getSurname());
        holder.textViewMarks.setText(module.getMarks());

    }

    @Override
    public int getItemCount() {
        return moduleItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView  textViewId,textViewName, textViewSurName, textViewMarks;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewId = (TextView) itemView.findViewById(R.id.id_cardview_id);
            textViewName = (TextView) itemView.findViewById(R.id.id_cardview_name);
            textViewSurName = (TextView) itemView.findViewById(R.id.id_cardview_surname);
            textViewMarks = (TextView) itemView.findViewById(R.id.id_cardview_marks);

        }
    }
}
