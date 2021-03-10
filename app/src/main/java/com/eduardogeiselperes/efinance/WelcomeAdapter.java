package com.eduardogeiselperes.efinance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WelcomeAdapter extends RecyclerView.Adapter<WelcomeAdapter.ViewHolder> {

    Context context;
    ArrayList<Financing> Financing;

    public WelcomeAdapter(Context context, ArrayList<com.eduardogeiselperes.efinance.Financing> financing) {
        this.context = context;
        Financing = financing;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_welcome_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.myFinancing.setText(Financing.get(position).getName());

        String getMyFinancing = Financing.get(position).getName();

    }

    @Override
    public int getItemCount() {
        return Financing.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView myFinancing;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myFinancing = (TextView)itemView.findViewById(R.id.textViewMyFinancing);
        }
    }
}
