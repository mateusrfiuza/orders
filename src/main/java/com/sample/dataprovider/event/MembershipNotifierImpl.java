package com.sample.dataprovider.event;

import com.sample.dataprovider.repository.MembershipRepositoryImpl;
import com.sample.domain.membership.dataprovider.event.MembershipNotifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MembershipNotifierImpl implements MembershipNotifier {

    private static final Logger logger = LoggerFactory.getLogger(MembershipNotifier.class);
    @Override
    public void notifyCustomer() {
        logger.info("Notifying customer XPTO");
    }
}
