package com.simcom.ecashier.ui.currentCollection;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.simcom.ecashier.R;
import com.simcom.ecashier.model.room.Collection;
import com.simcom.ecashier.model.room.Person;
import com.simcom.ecashier.model.room.PersonExtended;

import java.util.ArrayList;
import java.util.List;

public class CollectionRecyclerViewAdapter extends RecyclerView.Adapter<CollectionRecyclerViewAdapter.ViewHolder> {
    private List<PersonExtended> people = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView order;
        TextView name;
        ImageView tick;
        CardView card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            order=itemView.findViewById(R.id.order);
            name=itemView.findViewById(R.id.name);
            tick=itemView.findViewById(R.id.tick);
            card=itemView.findViewById(R.id.card);
        }
    }

    public void setPeople(List<PersonExtended> people){
        this.people=people;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PersonExtended currentPerson = people.get(position);
        holder.name.setText(currentPerson.getPerson().getName());
        if(currentPerson.hasPaid()){
            holder.card.setCardBackgroundColor(Color.rgb(236, 244, 220));
            holder.tick.setImageResource(R.drawable.ic_baseline_check_24);
            holder.order.setText(currentPerson.getRank());
            holder.order.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return people.size();
    }
}
