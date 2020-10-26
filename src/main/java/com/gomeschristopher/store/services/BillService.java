package com.gomeschristopher.store.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.gomeschristopher.store.domain.PaymentBill;

@Service
public class BillService {

	public void fillBillPayment(PaymentBill payment, Date paymentInstant) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(paymentInstant);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		payment.setDueDate(cal.getTime());
	}
}
