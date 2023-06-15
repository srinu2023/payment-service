package com.keybank.payments.serviceclient;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.keybank.payments.model.StripeRequest;
import com.keybank.payments.model.StripeResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;

import com.stripe.model.Token;


@Component
public class StripeServiceClientImpl implements StripeService {

	@Override
	public StripeResponse doPayment(StripeRequest stripeRequest) {
		// TODO: Write the payment gateway stripe client code which will connect to Stripe system to make the Payments
	
		
		try {
			Stripe.apiKey="sk_test_51NFqs4SApHRyIOC2DKrBfzBFBriwhcofeKeFTSNGvDqheon0sV996v4AEPtcMGuCTtzst07g3dqlziS7g4bwtfLE00dM6zfrZS";
			//1.Add the customer details
			Map<String,Object> customerParams=new HashMap<String,Object>();
			customerParams.put("name", "srinuRudransh");
			customerParams.put("email", "srinu2015.java@gmail.com");
			Customer.create(customerParams);// if u open create stripe has written the code internally
			//2.Retrive the customer details
			//Customer customerDetails=Customer.retrieve("cus_O1tOxkKzpxEXk2");
			//System.out.println("customer Details are:"+customerDetails);
			//3.Add card to customer
			/*List<String> expandList=new ArrayList<String>();
			expandList.add("sources");
			
			customerParams.put("expand", expandList);
			
			Customer customerDetails=Customer.retrieve("cus_O1unBCJ6QZfem7",customerParams,null);
			
			System.out.println("customer Details are:"+customerDetails);
			
			Map<String,Object> cardParams=new HashMap<String,Object>();
			cardParams.put("number", "4242424242424242");
			cardParams.put("exp_month", "12");
			cardParams.put("exp_year", "2024");
			cardParams.put("cvv", "123");
			
			Map<String,Object> tokenParams=new HashMap<String,Object>();
			tokenParams.put("card", cardParams);
			
			Token token=Token.create(tokenParams);
			
			Map<String,Object> sources=new HashMap<String,Object>();
			sources.put("source", token.getId());
			
			System.out.println("customerDetails.getSources():"+customerDetails.getSources());
			
			customerDetails.getSources().create(sources);
			System.out.println("ADDED CARD TO CUSTOMER SUCCESSFULLY");*/
			
			//4.Transfer the amount-Initialize the payment
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("amount", 2000000);
			params.put("currency", "usd");
			params.put("customer", "cus_O1unBCJ6QZfem7");
			
			Charge charge=Charge.create(params);
			System.out.println(charge);
			
			System.out.println("Payment has been done");
			//##
			StripeResponse stripeResponse=new StripeResponse();
			stripeResponse.setAckNum(charge.getBalanceTransaction());
			stripeResponse.setStatus("success");
			stripeResponse.setDescription("Payment has been done successfully");
		} catch (StripeException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	public static void main(String[] args) {
		StripeServiceClientImpl stripeServiceClientImpl=new StripeServiceClientImpl();
		StripeRequest stripeRequest=new StripeRequest();
		StripeResponse  stripeResponse=stripeServiceClientImpl.doPayment(stripeRequest);
	}

}
