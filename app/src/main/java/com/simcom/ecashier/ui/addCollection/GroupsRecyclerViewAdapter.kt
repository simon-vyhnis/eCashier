package com.simcom.ecashier.ui.addCollection;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.simcom.ecashier.R;
import com.simcom.ecashier.model.room.Group;
import com.simcom.ecashier.ui.currentCollection.CurrentCollectionViewModel;
import com.simcom.ecashier.ui.currentCollection.CollectionRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class GroupsRecyclerViewAdapter extends RecyclerView.Adapter<GroupsRecyclerViewAdapter.ViewHolder> {

    private List<Group> groups = new ArrayList<>();

    public void setGroups(List<Group> groups) {
        this.groups = groups;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(groups.get(position).getName());
        holder.card.setOnClickListener(view -> {
           holder.card.setCardBackgroundColor(Color.rgb(236, 244, 220));
        });
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        CardView card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            card = itemView.findViewById(R.id.card);
        }
    }
}
