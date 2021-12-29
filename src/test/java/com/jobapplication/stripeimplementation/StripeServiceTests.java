package com.jobapplication.stripeimplementation;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Invoice;
import com.stripe.model.Price;
import com.stripe.model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class StripeServiceTests {

    @InjectMocks
    private StripeService stripeService;

    private static Customer customer;
    private static String customerId;
    private static Price price;
    
    @BeforeAll
    static void beforeAll() {

        Stripe.apiKey = System.getenv("stripe_secret_test_key");

        Map<String, Object> customerParams = new HashMap<>();
        customerParams.put("name", "Jan Kowalski");

        Map<String, Object> productParams = new HashMap<>();
        productParams.put("name", "Gold Special");

        try {
            customer = Customer.create(customerParams);
            customerId = customer.getId();

            Product product = Product.create(productParams);

            Map<String, Object> priceParams = new HashMap<>();
            priceParams.put("unit_amount", 2000);
            priceParams.put("currency", "pln");
            priceParams.put("product", product.getId());

            price = Price.create(priceParams);

        } catch (StripeException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    void createInvoiceTest() {
        //Given
        Invoice invoice = new Invoice();

        //When
        try {
            stripeService.createInvoiceItem(price,customer);
            invoice = stripeService.createInvoice(customer);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Then
        assertEquals(customerId,invoice.getCustomer());
    }

    @Test
    void getInvoiceTest() {
        //Given
        Invoice invoice;
        Invoice resultInvoice;
        String invoiceId = "";
        String resultInvoiceId = "";

        try {
            stripeService.createInvoiceItem(price,customer);
            invoice = stripeService.createInvoice(customer);
            invoiceId = invoice.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //When
        try {
            resultInvoice = stripeService.getInvoice(invoiceId);
            resultInvoiceId = resultInvoice.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Then
        assertEquals(invoiceId,resultInvoiceId);
    }
}