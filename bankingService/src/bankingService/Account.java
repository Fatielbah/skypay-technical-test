package bankingService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Account implements AccountService {
	private final ArrayList<Transaction> transactions;
	private int balance;

	public Account() {
		this.transactions = new ArrayList<>();
		this.balance = 0;
	}

	@Override
	public void deposit(int amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Deposit amount must be positive. Provided: " + amount);
		}

		balance += amount;
		Transaction transaction = new Transaction(LocalDate.now(), amount, balance);
		transactions.add(transaction);
	}

	@Override
	public void withdraw(int amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Withdrawal amount must be positive. Provided: " + amount);
		}

		if (amount > balance) {
			throw new IllegalArgumentException(
					"Insufficient funds. Balance: " + balance + ", Withdrawal amount: " + amount
					);
		}

		balance -= amount;
		Transaction transaction = new Transaction(LocalDate.now(), -amount, balance);
		transactions.add(transaction);
	}

	@Override
	public void printStatement() {
		System.out.println("Date     || Amount || Balance");
		ArrayList<Transaction> reversedTransactions = new ArrayList<>(transactions);
		Collections.reverse(reversedTransactions);

		for (Transaction transaction : reversedTransactions) {
			System.out.printf("%s || %d || %d%n", 
					transaction.getFormattedDate(),
					transaction.getAmount(),
					transaction.getBalance()
					);
		}
	}


	public void depositWithDate(int amount, LocalDate date) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Deposit amount must be positive. Provided: " + amount);
		}

		balance += amount;
		Transaction transaction = new Transaction(date, amount, balance);
		transactions.add(transaction);
	}

	public void withdrawWithDate(int amount, LocalDate date) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Withdrawal amount must be positive. Provided: " + amount);
		}

		if (amount > balance) {
			throw new IllegalArgumentException(
					"Insufficient funds. Balance: " + balance + ", Withdrawal amount: " + amount
					);
		}

		balance -= amount;
		Transaction transaction = new Transaction(date, -amount, balance);
		transactions.add(transaction);
	}

	public int getBalance() {
		return balance;
	}

	public int getTransactionCount() {
		return transactions.size();
	}
}
