package com.sample.domain.membership.usecase;

import com.sample.domain.membership.dataprovider.event.MembershipNotifier;
import com.sample.domain.membership.dataprovider.repository.MembershipRepository;
import org.springframework.stereotype.Service;

@Service
public class UpgradeMembershipUseCase {

    private final MembershipRepository membershipRepository;
    private final MembershipNotifier membershipNotifier;

    public UpgradeMembershipUseCase(final MembershipRepository membershipRepository,
                                     final MembershipNotifier membershipNotifier) {
        this.membershipRepository = membershipRepository;
        this.membershipNotifier = membershipNotifier;
    }


    public void execute() {
        System.out.println("Upgrading membership");
        membershipRepository.save();
        membershipNotifier.notifyCustomer();
    }




}
