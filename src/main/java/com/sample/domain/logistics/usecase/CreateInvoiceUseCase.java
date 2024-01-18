package com.sample.domain.logistics.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateInvoiceUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CreateInvoiceUseCase.class);

    public void execute() {
        logger.info("Creating invoice");
    }
}
