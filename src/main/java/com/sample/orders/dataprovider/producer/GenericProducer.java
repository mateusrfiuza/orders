package com.sample.orders.dataprovider.producer;

public interface GenericProducer<V> {

    void send(V value);

}
