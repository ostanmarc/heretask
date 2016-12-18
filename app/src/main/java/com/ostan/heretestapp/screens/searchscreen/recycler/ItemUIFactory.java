package com.ostan.heretestapp.screens.searchscreen.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ostan.heretestapp.R;

/**
 * Created by marco on 15/07/2016.
 */
public class ItemUIFactory {

    public static ItemViewHolder createUIForSession(ViewGroup parent, int itemType, ICardsEventsListener listener) {

        ItemViewHolder vh = null;
        switch (itemType){
            case 0:{
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.search_result_item, parent, false);
                vh = new ItemViewHolder(itemView, listener);

            }
            break;
            case 1:{
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.search_result_route_item, parent, false);
                vh = new RouteItemViewHolder(itemView, listener);
            }
            break;
            case 2:{

            }

        }

        return vh;
    }
}
