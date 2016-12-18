package com.ostan.heretestapp.screens.searchscreen;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.ostan.heretestapp.R;
import com.ostan.heretestapp.pojo.AbstractResponseItem;
import com.ostan.heretestapp.screens.searchscreen.recycler.RecyclerItemAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by marco on 17/12/2016.
 */

public class SearchViewImpl implements ISearchView, IAdapterCallback {

    private ISearchPresenter presenter;
    @BindView(R.id.results_recycler_view)
    RecyclerView recycler;

    RecyclerItemAdapter adapter;


    public SearchViewImpl(final ISearchPresenter presenter, View rootView) {
        this.presenter = presenter;
        ButterKnife.bind(this, rootView);
        adapter = new RecyclerItemAdapter(rootView.getContext(), this);

        LinearLayoutManager llm = new LinearLayoutManager(rootView.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);

        recycler.setAdapter(adapter);


    }

    @Override
    public void itemWasClicked(AbstractResponseItem item) {
        presenter.onResultClicked(item);
    }

    @Override
    public void addAddressItem(List<AbstractResponseItem> items) {
        adapter.clearItems();
        for (AbstractResponseItem item: items) {
            adapter.addItem(item);
        }
    }

    @Override
    public void activateQuerryChangesListening(EditText editText) {


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.onQuerryTextEdited(s.toString());
            }
        });
    }
}
