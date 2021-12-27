package com.jobapplication.stripeimplementation;

import lombok.Getter;

@Getter
public class StripeCustomer {

    private String customerId;
    private String firstName;
    private String secondName;

    public StripeCustomer(String customerId, String firstName, String secondName) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.secondName = secondName;
    }
}
