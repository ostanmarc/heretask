package com.ostan.heretestapp.screens.searchscreen.recycler;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ostan.heretestapp.R;
import com.ostan.heretestapp.pojo.AbstractResponseItem;
import com.ostan.heretestapp.pojo.Item;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by marco on 18/12/2016.
 */

public class ItemViewHolder  extends RecyclerView.ViewHolder   {

    @BindView(R.id.item_view_title_tv)
    TextView title;
    @BindView(R.id.result_card_root_view)
    LinearLayout rootView;


    IResultCardsEventsListener listener;
    public Item item;



    public ItemViewHolder(View view, IResultCardsEventsListener listener) {
        super(view);
        ButterKnife.bind(this, view);
        this.listener = listener;

    }

    void bindViewItemImpl(final AbstractResponseItem item) {
        title.setText(item.getTitle());
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCardWasClicked(item);
                if(item instanceof Item){
                    Item internalItem = (Item) item;
                    String latitude = "Latitude: " + + internalItem.getPosition().get(0);
                    String longitude = "Longitude: " + internalItem.getPosition().get(1);
                    Log.i("location", "Clicked on item located at Lat: "+latitude+"  lon: " + longitude);
                }
            }
        });

    }
}
