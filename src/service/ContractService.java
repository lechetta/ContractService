package service;

import java.util.Calendar;
import java.util.Date;

import entities.Contract;
import entities.Installment;

public class ContractService {
	
	//instanciando a interface OnlinePaymentService que contem a "Ponte" entre a plataforma de pagamento (Paypal)>
	private OnlinePaymentService onlinePaymentService;

	//Construtor que obriga  o serviço a ser instanciado, enibindo a responsabilidade da classe ContractService, forçando uma injeção de dependencia.
	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	//processar o contrato
	public void processContract(Contract contract, int months) {
		//dividir o valor do contrato pelo numero de meses informado pelo usuario.
		double basicQuota = contract.getTotalValue() / months;
		//o for ira implementar de acordo com o numero de meses (numero de parcelas).
        for (int i = 1; i <= months; i++) {
        	//incrementar o date de acordo com o valor de parcela (I).
            Date date = addMonths(contract.getDate(), i);
            //somar os valores com suas respectivas taxas
            double updatedQuota = basicQuota + onlinePaymentService.Interest(basicQuota, i);
            double fullQuota =  updatedQuota + onlinePaymentService.PaymentFee(updatedQuota);
            //passa ao construtor do installment os valores necessarios de acordo com a classe contract
            contract.addInstallment(new Installment(date, fullQuota));
        }
	}
	
	//adicionar meses/horas ou dias a uma data.
	private Date addMonths(Date date, int n) {
		//instanciando um calendario buscando o dia atual.
		Calendar cal = Calendar.getInstance();
		//definindo o calenando com o date que sera informado como parametro
		cal.setTime(date);
		//adicionar ao calendario o N (numero de parcelas) de acordo com o mes.
		cal.add(Calendar.MONTH, n);
		//retornar data.
		return cal.getTime();
	}

}
