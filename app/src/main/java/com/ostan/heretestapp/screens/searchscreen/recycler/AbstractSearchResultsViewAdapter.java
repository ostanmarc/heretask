package com.ostan.heretestapp.screens.searchscreen.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.ostan.heretestapp.pojo.AbstractResponseItem;
import com.ostan.heretestapp.screens.searchscreen.IAdapterCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marco on 11/07/2016.
 */
public abstract class AbstractSearchResultsViewAdapter<VH extends ItemViewHolder>
        extends RecyclerView.Adapter<VH> implements IResultCardsEventsListener {

    protected Context context;

    List<AbstractResponseItem> dataItems;
    IAdapterCallback callback;

    public AbstractSearchResultsViewAdapter(final Context context, IAdapterCallback callback) {
        this.context = context;
        this.dataItems = new ArrayList<>();
        this.callback = callback;
    }


    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public abstract void onBindViewHolderConcrete(VH holder, AbstractResponseItem item);

    @Override
    public void onBindViewHolder(VH viewHolder, int position) {
        onBindViewHolderConcrete(viewHolder, dataItems.get(position));
    }


}
