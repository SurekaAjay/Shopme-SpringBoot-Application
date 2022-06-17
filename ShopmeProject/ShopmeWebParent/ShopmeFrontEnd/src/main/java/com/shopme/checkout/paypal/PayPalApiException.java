package com.shopme.checkout.paypal;

import org.springframework.beans.factory.annotation.Autowired;

import com.shopme.setting.SettingService;

public class PayPalApiException extends Exception {
	
	public PayPalApiException(String message) {
		super(message);
	}


}
