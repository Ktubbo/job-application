package com.jobapplication.stripeimplementation;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Invoice;
import com.stripe.model.InvoiceItem;
import com.stripe.model.Price;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StripeService {

    private final String stripeTestKey = System.getenv("stripe_secret_test_key");
    private final Logger logger = LoggerFactory.getLogger(StripeService.class);

    public Invoice createInvoice(Customer customer) throws StripeException {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("customer",customer.getId());

        Stripe.apiKey = stripeTestKey;
        logger.info("Invoice created.");
        return Invoice.create(parameters);
    }

    public Invoice getInvoice(String invoiceId) throws StripeException {
        Stripe.apiKey = stripeTestKey;
        logger.info("Invoice retrived.");
        return Invoice.retrieve(invoiceId);
    }

    public void createInvoiceItem(Price price, Customer customer) throws StripeException{
        Map<String, Object> invoiceItemParameters = new HashMap<>();
        invoiceItemParameters.put("customer", customer.getId());
        invoiceItemParameters.put("price", price.getId());

        Stripe.apiKey = stripeTestKey;
        logger.info("Invoice item created.");
        InvoiceItem.create(invoiceItemParameters);
    }
}
