package service;

public interface OnlinePaymentService {
	
	
	//Metodos abstrados que s�o implementados na classe PaypalService, a interface faz a ponte entre contrato e o servi�o de Paypal.
	
	public double PaymentFee(double Amount);
	
	public Double Interest(Double Amount, Integer months);

}
