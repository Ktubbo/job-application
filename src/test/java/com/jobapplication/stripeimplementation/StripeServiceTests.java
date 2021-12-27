package com.jobapplication.stripeimplementation;

import com.stripe.model.Invoice;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class StripeServiceTests {

    private static final String testCustomerId = "cus_Kr3RabA24yE8qd";
    private static final String stripeTestPriceId = "price_1KBLWNEwd6gE3PUT6TezK6WU";

    @InjectMocks
    private StripeService stripeService;

    private static StripeCustomer customer;
    private static StripeItem item;
    
    @BeforeAll
    static void beforeAll() {
        customer = new StripeCustomer(testCustomerId,"Jan","Kowalski");
        item = new StripeItem("Test item",stripeTestPriceId);
    }
    
    @Test
    void createInvoiceTest() {
        //Given
        Invoice invoice = new Invoice();

        //When
        try {
            stripeService.createInvoiceItem(item,customer);
            invoice = stripeService.createInvoice(customer);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Then
        assertEquals("cus_Kr3RabA24yE8qd",invoice.getCustomer());
    }

    @Test
    void getInvoiceTest() {
        //Given
        Invoice invoice;
        Invoice resultInvoice;
        String invoiceId = "";
        String resultInvoiceId = "";

        try {
            stripeService.createInvoiceItem(item,customer);
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