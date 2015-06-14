package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import data.PracticeData;

public final class CollectorsPractice {

	public static void main(String[] args) {
		List<Transaction> transactions = PracticeData.getTransactions();

		Stream<Trader> traders = transactions.stream()
			.map(Transaction::getTrader)
			.distinct();

		traders.collect(new ToListCollector<>()).forEach(System.out::println);;
		// or (doesn't work for some reason with ArrayList::new)
		traders.collect(() -> new ArrayList<>(), List::add, List::addAll);

		// using takeWhile = LongSummaryStatistics{count=100, sum=2807, min=24, average=28.070000, max=107}
		// using filter = LongSummaryStatistics{count=100, sum=33640, min=305, average=336.400000, max=415}
		LongSummaryStatistics testStats = LongStream.rangeClosed(1, 100)
				.map(i -> {
					Stream<Integer> primeTestRange = IntStream.range(2, 10_000).boxed();
					long ms = System.currentTimeMillis();
					primeTestRange.collect(new PrimeCollector());
					return System.currentTimeMillis() - ms;})
				.summaryStatistics();
		System.out.println(testStats);
	}

}
