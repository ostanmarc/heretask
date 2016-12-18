package com.ostan.heretestapp.screens.searchscreen.recycler;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.ostan.heretestapp.R;
import com.ostan.heretestapp.pojo.AbstractResponseItem;
import com.ostan.heretestapp.pojo.Route;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by marco on 18/12/2016.
 */

public class RouteItemViewHolder extends ItemViewHolder {

    @BindView(R.id.item_view_description_tv)
    TextView description;

    public RouteItemViewHolder(View view, ICardsEventsListener listener) {
        super(view, listener);
        ButterKnife.bind(this, view);
    }

    @Override
    void bindViewItemImpl(AbstractResponseItem item) {
        super.bindViewItemImpl(item);
        Route route = (Route) item;

        description.setText(Html.fromHtml(route.getDescription()));
    }
}
