package com.ostan.heretestapp.screens.searchscreen.recycler;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.ostan.heretestapp.pojo.AbstractResponseItem;
import com.ostan.heretestapp.pojo.Item;
import com.ostan.heretestapp.screens.searchscreen.IAdapterCallback;


public class RecyclerItemAdapter
        extends AbstractSearchResultsViewAdapter implements ICardsEventsListener {


    public RecyclerItemAdapter(final Context context, IAdapterCallback callback) {
        super(context, callback);
    }


    @Override
    public int getItemViewType(int position) {
        if (dataItems.get(position) instanceof Item) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ItemUIFactory.createUIForSession(parent, viewType, this);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        AbstractResponseItem item = (AbstractResponseItem) dataItems.get(position);
        holder.bindViewItemImpl(item);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public void onBindViewHolderConcrete(ItemViewHolder holder, AbstractResponseItem item) {
        holder.bindViewItemImpl(item);
    }

    public void clearItems(){
        dataItems.clear();
        notifyDataSetChanged();
    }
    public void addItem(AbstractResponseItem item) {
        dataItems.add(item);
        notifyItemInserted(dataItems.size());
    }

    @Override
    public void onCardWasClicked(AbstractResponseItem item) {
        callback.itemWasClicked(item);
    }
}
