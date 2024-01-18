package com.sample.dataprovider.event;

import com.sample.domain.membership.dataprovider.event.MembershipNotifier;

public class MembershipNotifierImpl implements MembershipNotifier {
    @Override
    public void notifyCustomer() {
        System.out.println("Notifying customer XPTO");
    }
}
