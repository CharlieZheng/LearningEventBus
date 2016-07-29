package com.cdc.eventbuslearning.fragment;

/**
 * Created by Charlie on 2016/7/29.
 */

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cdc.eventbuslearning.entity.Event;
import com.cdc.eventbuslearning.entity.Item;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;


public class ItemListFragment extends ListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Register
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Unregister
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(2000);
                    EventBus.getDefault().post(new Event.ItemListEvent(Item.ITEMS));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onEventMainThread(Event.ItemListEvent event) {
        setListAdapter(new ArrayAdapter<Item>(getActivity(), android.R.layout.simple_list_item_activated_1, android.R.id.text1, event.getItems()));
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        EventBus.getDefault().post(getListView().getItemAtPosition(position));
    }

}