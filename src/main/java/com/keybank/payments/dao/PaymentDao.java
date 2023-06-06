package com.keybank.payments.dao;

import com.keybank.payments.model.PaymentDaoRequest;
import com.keybank.payments.model.PaymentDaoResponse;
import com.keybank.payments.model.PaymentRequest;
import com.keybank.payments.model.PaymentResponse;

public interface PaymentDao {
	PaymentDaoResponse updatesPaymentsInfo(PaymentDaoRequest daoReq);

}
