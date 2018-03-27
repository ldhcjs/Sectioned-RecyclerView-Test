package com.ldhcjs.sectionedrecyclerviewtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by tony.lee on 2017-04-06.
 */

public class CustomAdapter extends SectionedRecyclerViewAdapter<CustomAdapter.MainVH> {

    private Context mContext;
    private ArrayList<Items> mArrayList;

    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public CustomAdapter(Context context, ArrayList<Items> arrayList) {
        this.mContext = context;
        this.mArrayList = arrayList;
    }

    @Override
    public int getSectionCount() {
        return 3; // number of sections.
    }

    @Override
    public int getItemCount(int section) {
        return mArrayList.get(section).getContent().size();

//        return 8; // number of items in section (section index is parameter).
    }

    @Override
    public void onBindHeaderViewHolder(MainVH holder, int section) {
        // Setup header view.
        holder.headerTxt.setText(mArrayList.get(section).getTitle());
    }

    @Override
    public void onBindViewHolder(MainVH holder, int section, int relativePosition, int absolutePosition) {
        // Setup non-header view.
        // 'section' is section index.
        // 'relativePosition' is index in this section.
        // 'absolutePosition' is index out of all non-header items.
        // See sample project for a visual of how these indices work.
        holder.contentTxt.setText(mArrayList.get(section).getContent().get(relativePosition));

        final int rel = relativePosition;
        final int abs = absolutePosition;
        // Set Onclick Listener
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(rel, abs);
            }
        };
        holder.contentTxt.setOnClickListener(listener);
    }

    @Override
    public MainVH onCreateViewHolder(ViewGroup parent, int viewType) {
        // Change inflated layout based on 'header'.
        int layout;
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                layout = R.layout.header_view;
                break;
            case VIEW_TYPE_ITEM:
                layout = R.layout.content_view;
                break;
            default:
                layout = R.layout.content_view;
                break;
        }

        View v = LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);
        return new MainVH(v);
    }

    public static class MainVH extends RecyclerView.ViewHolder {

        final TextView headerTxt;
        final TextView contentTxt;

        public MainVH(View itemView) {
            super(itemView);
            headerTxt = (TextView) itemView.findViewById(R.id.header_txt);
            contentTxt = (TextView) itemView.findViewById(R.id.content_txt);
            // Setup view holder.
            // You'd want some views to be optional, e.g. for header vs. normal.
        }
    }
}
