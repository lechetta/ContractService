package service;

public interface OnlinePaymentService {
	
	
	//Metodos abstrados que são implementados na classe PaypalService, a interface faz a ponte entre contrato e o serviço de Paypal.
	
	public double PaymentFee(double Amount);
	
	public Double Interest(Double Amount, Integer months);

}
