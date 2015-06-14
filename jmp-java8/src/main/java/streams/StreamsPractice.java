package streams;

import static java.lang.System.out;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import data.PracticeData;

public class StreamsPractice {

	public static void main(String[] args) {
		List<Transaction> transactions = PracticeData.getTransactions();

		System.out.println("1 Find all transactions in the year 2011 and sort them by value (small to high).");
		List<Transaction> sortedTransactions = transactions.stream()
				.filter(transaction -> transaction.getYear() == 2011)
				.sorted(comparing(Transaction::getValue))
				.collect(toList());
		sortedTransactions.forEach(out::println);

		System.out.println("2 What are all the unique cities where the traders work?");
		Set<String> uniqueCities = transactions.stream()
				.map(Transaction::getTrader)
				.map(Trader::getCity)
				.collect(toSet());
		uniqueCities.forEach(out::println);

		System.out.println("3 Find all traders from Cambridge and sort them by name.");
		List<Trader> cambridgeTraders = transactions.stream()
				.map(Transaction::getTrader)
				.filter(trader -> "Cambridge".equals(trader.getCity()))
				.distinct()
				.sorted(comparing(Trader::getName))
				.collect(toList());
		cambridgeTraders.forEach(out::println);
		
		System.out.println("4 Return a string of all traders’ names sorted alphabetically.");
		String traderNames = transactions.stream()
				.map(Transaction::getTrader)
				.map(Trader::getName)
				.distinct()
				.sorted()
				.collect(joining(" "));
		System.out.println(traderNames);

		System.out.println("5 Are any traders based in Milan?");
		boolean hasMilanBasedTrader = transactions.stream()
				.allMatch(t -> "Milan".equals(t.getTrader().getCity()));
		System.out.println(hasMilanBasedTrader);

		System.out.println("6 Print all transactions’ values from the traders living in Cambridge.");
		transactions.stream()
				.filter(t -> "Cambridge".equals(t.getTrader().getCity()))
				.map(Transaction::getValue)
				.forEach(out::println);

		System.out.println("7 What’s the highest value of all the transactions?");
		Optional<Integer> maxTransactionValue = transactions.stream()
				.map(Transaction::getValue)
				.reduce(Integer::max);
		System.out.println(maxTransactionValue.isPresent() ? maxTransactionValue.get() : "no transactions");

		System.out.println("8 Find the transaction with the smallest value.");
		Optional<Transaction> smalestTransaction = transactions.stream()
				.min(comparing(Transaction::getValue));
		System.out.println(smalestTransaction.isPresent() ? smalestTransaction.get() : "no transactions");
	}

}
