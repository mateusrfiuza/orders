package com.sample.dataprovider.producer;

public interface GenericProducer<V> {

    void send(V value);

}
