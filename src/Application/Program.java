package Application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Contract;
import entities.Installment;
import service.ContractService;
import service.OnlinePaymentService;
import service.PaypalService;

public class Program {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		// contract values
		System.out.println("Enter contract data");
		System.out.print("Number: ");
		int number = sc.nextInt();
		System.out.print("Date (dd/MM/yyyy): ");
		Date date = sdf.parse(sc.next());
		System.out.print("Contract value: ");
		double totalValue = sc.nextDouble();

		// instance
		Contract contract = new Contract(number, date, totalValue);

		// installments number
		System.out.print("Enter number of installments: ");
		int n = sc.nextInt();

		// instance of ContractService, calling the constructor and injecting the dependency in PaypalService
		ContractService contractService = new ContractService(new PaypalService());
		//calling the method that processes the contract
		contractService.processContract(contract, n);

		// print the contract information
		System.out.println("Installments:");
		for (Installment x : contract.getInstallments()) {
			System.out.println(x);
		}

		sc.close();
	}
}
