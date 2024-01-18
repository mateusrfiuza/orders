package com.sample.dataprovider.repository;

import com.sample.domain.membership.dataprovider.repository.MembershipRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MembershipRepositoryImpl implements MembershipRepository {

    private static final Logger logger = LoggerFactory.getLogger(MembershipRepositoryImpl.class);

    @Override
    public void save() {
        logger.info("Saving membership");
    }
}
