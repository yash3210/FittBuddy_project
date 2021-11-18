package com.example.fit_buddy;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fit_buddy.DataModel.DetailsListModel;

import java.util.ArrayList;

public class ContentRecyclerViewAdaptor extends RecyclerView.Adapter<ContentRecyclerViewAdaptor.ViewHolder> {
    private final Context context;
    private ArrayList<DetailsListModel.Details.Exercise> dataList;
    private final LayoutInflater mInflator;
    private ContentRecyclerViewAdaptor.ItemClickListener mClickListener;

    ContentRecyclerViewAdaptor(Context context,ArrayList<DetailsListModel.Details.Exercise> data)
    {
        this.mInflator=LayoutInflater.from(context);
        this.dataList=data;
        this.context=context;
    }

    @NonNull
    @Override
    public ContentRecyclerViewAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=mInflator.inflate(R.layout.recyclerview_row_content,parent,false);
        return new ContentRecyclerViewAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DetailsListModel.Details.Exercise model=dataList.get(position);
        holder.exnum.setText("Activity: "+(position+1));
        holder.name.setText(model.exercise);
        holder.det.setText(model.stepContent);
        Glide.with(context).load(model.imageUrl).placeholder(R.drawable.loadingchemical).into(holder.img);
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,det,exnum;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            exnum=itemView.findViewById(R.id.exnumber);
            name=itemView.findViewById(R.id.title_here);
            det=itemView.findViewById(R.id.content_here);
            img=itemView.findViewById(R.id.gif_or_image);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (mClickListener !=null) mClickListener.onItemClick(view,getAdapterPosition());
        }
    }
    DetailsListModel.Details.Exercise getItem(int id) {
        return dataList.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ContentRecyclerViewAdaptor.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}