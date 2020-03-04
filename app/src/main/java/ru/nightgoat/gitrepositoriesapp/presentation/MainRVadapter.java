package ru.nightgoat.gitrepositoriesapp.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.nightgoat.gitrepositoriesapp.R;
import ru.nightgoat.gitrepositoriesapp.data.api.RepModel;

public class MainRVadapter extends RecyclerView.Adapter<MainRVadapter.ViewHolder> {

    private List<RepModel> data = new ArrayList<>();

    void updateData(List<RepModel> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView repNameTV;
        private TextView repDescTV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            repNameTV = itemView.findViewById(R.id.rep_nameTV);
            repDescTV = itemView.findViewById(R.id.rep_descTV);
        }

        void bind(RepModel model) {
            repNameTV.setText(model.name);
            repDescTV.setText(model.description);
        }
    }
}
