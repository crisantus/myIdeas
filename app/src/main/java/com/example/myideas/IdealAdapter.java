package com.example.myideas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myideas.model.Idea;

import java.util.ArrayList;

public class IdealAdapter extends RecyclerView.Adapter<IdealAdapter.myViewHolder> {

    ArrayList<Idea> mIdeas=new ArrayList<>();

    public IdealAdapter(ArrayList<Idea> list){
        this.mIdeas=list;
    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.owner.setText(mIdeas.get(position).getOwner());
        holder.descp.setText(mIdeas.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mIdeas.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView owner,descp;

        public myViewHolder(@NonNull View itemView) {

            super(itemView);
            owner=(TextView)itemView.findViewById(R.id.owner);
            descp=(TextView)itemView.findViewById(R.id.description);
        }
    }

    public interface OnIdealListner{
        void onIdeaClick(int postion);
    }
}
