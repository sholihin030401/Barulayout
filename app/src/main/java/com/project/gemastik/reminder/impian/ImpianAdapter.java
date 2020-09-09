package com.project.gemastik.reminder.impian;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.gemastik.reminder.R;

import java.util.ArrayList;
import java.util.List;

public class ImpianAdapter extends RecyclerView.Adapter<ImpianAdapter.ImpianHolder> {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private ArrayList<ImpianItem> itemList;

    public ImpianAdapter(ArrayList<ImpianItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull

    @Override
    public ImpianAdapter.ImpianHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_impian_item,parent,false);

        return new ImpianHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImpianAdapter.ImpianHolder holder, int position) {
        ImpianItem item = itemList.get(position);
        holder.tximpian.setText(item.getTxImpian());

        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.rvhabits.getContext(),LinearLayoutManager.VERTICAL,false);
        layoutManager.setInitialPrefetchItemCount(item.getItemList().size());

        HabitsAdapter kebiasaanAdapter = new HabitsAdapter(item.getItemList());
        kebiasaanAdapter.notifyDataSetChanged();
        holder.rvhabits.setLayoutManager(layoutManager);
        holder.rvhabits.setAdapter(kebiasaanAdapter);
        holder.rvhabits.setRecycledViewPool(viewPool);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ImpianHolder extends RecyclerView.ViewHolder {

        TextView tximpian;
        RecyclerView rvhabits;

        public ImpianHolder(@NonNull View itemView) {
            super(itemView);

            tximpian = itemView.findViewById(R.id.impian);
            rvhabits = itemView.findViewById(R.id.rv_kebiasaan);

        }
    }
}