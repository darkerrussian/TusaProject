package com.example.tusaproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PartyAdapter extends RecyclerView.Adapter <PartyAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<Party> parties;
    Context context;


    PartyAdapter(Context context, List<Party> parties){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.parties = parties;


    }

    @NonNull
    @Override
    public PartyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.partie_info, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartyAdapter.ViewHolder holder, int position) {
        Party party = parties.get(position);
        holder.peoples.setText(party.getNumMans());
        holder.name.setText(party.getName());
        holder.image.setImageResource(party.getImagePath());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PartyItem.class);
                intent.putExtra("imageUrl", party.getImagePath());
                intent.putExtra("count", party.getNumMans());
                intent.putExtra("location_party", party.getLocation());
                intent.putStringArrayListExtra("partyUsers", party.getUsersMails());

                context.startActivity(intent);



            }
        });

    }

    @Override
    public int getItemCount() {
        return parties.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        final TextView peoples;
        final ImageView image;
        final TextView name;
        Button btn;
        ViewHolder(View view){
            super(view);
            peoples = view.findViewById(R.id.peoples);
            image = view.findViewById(R.id.image_party);
            btn = view.findViewById(R.id.button);
            name = view.findViewById(R.id.name);

        }
    }
}
