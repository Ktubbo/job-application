package com.jobapplication.stripeimplementation;

import lombok.Getter;

@Getter
public class StripeItem {
    
    private String productName;
    private String priceId;

    public StripeItem(String productName, String priceId) { 
        this.productName = productName;
        this.priceId = priceId;
    }
}
