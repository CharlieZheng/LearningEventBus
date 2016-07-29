package com.cdc.eventbuslearning.entity;

/**
 * Created by Charlie on 2016/7/29.
 */

import java.util.List;

public class Event {
    public static class ItemListEvent {
        private List<Item> items;

        public ItemListEvent(List<Item> items) {
            this.items = items;
        }

        public List<Item> getItems() {
            return items;
        }
    }

}