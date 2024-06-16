package com.sample.domain.membership.usecase;

import com.sample.domain.membership.dataprovider.event.MembershipNotifier;
import com.sample.domain.membership.dataprovider.repository.MembershipRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UpgradeMembershipUseCase {

    private static final Logger logger = LoggerFactory.getLogger(UpgradeMembershipUseCase.class);
    private final MembershipRepository membershipRepository;
    private final MembershipNotifier membershipNotifier;

    public UpgradeMembershipUseCase(final MembershipRepository membershipRepository,
                                     final MembershipNotifier membershipNotifier) {
        this.membershipRepository = membershipRepository;
        this.membershipNotifier = membershipNotifier;
    }


    public void execute() {
        logger.info("Upgrading membership");
        membershipRepository.save();
        membershipNotifier.notifyCustomer();
    }




}
