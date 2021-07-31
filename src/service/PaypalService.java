package service;

public class PaypalService implements OnlinePaymentService {

	@Override
	public double PaymentFee(double Amount) {
		return Amount * 0.02;
	}
	
	@Override
	public Double Interest(Double Amount, Integer months) {
		return Amount * 0.01 * months;
	}
}
