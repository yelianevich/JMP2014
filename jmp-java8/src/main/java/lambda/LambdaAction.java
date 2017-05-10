package lambda;

import static java.lang.System.out;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaAction {

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(
                new Apple(10, "Yellow", "BY"),
                new Apple(6, "Red", "US"),
                new Apple(15, "Blue", "UK"),
                new Apple(15, "Green", "Andora")
        );

        System.out.println("Initial");
        apples.forEach(out::println);

        System.out.println("Filtered BY");
        List<Apple> applesBy = apples
                .stream()
                .filter(apple -> "BY".equals(apple.getCountry()))
                .collect(toList());
        applesBy.forEach(out::println);

        System.out.println("Sorted by weight ASC");
        apples.sort(comparing(Apple::getWeight));
        apples.forEach(out::println);

        System.out.println("Sorted by weight DESC then by country if equals");
        apples.sort(comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getCountry));
        apples.forEach(out::println);

        System.out.println("Filter apples that go to shops");
        Predicate<Apple> redApples = apple -> "Red".equals(apple.getColor());
        Predicate<Apple> heavy = apple -> apple.getWeight() > 5;
        Predicate<Apple> fromBy = apple -> "BY".equals(apple.getCountry());
        Predicate<Apple> byFilter = redApples.and(heavy).or(fromBy);
        List<Apple> applesToShop = apples.stream()
                .filter(byFilter)
                .collect(toList());
        applesToShop.forEach(out::println);

        System.out.println("Combine functions");
        Function<Apple, CutApple> cut = apple -> new CutApple(apple, 5);
        Function<CutApple, DriedApple> dry = cutApple -> new DriedApple(cutApple, 50);
        apples.stream()
                .map(cut.andThen(dry))
                .collect(toList())
                .forEach(out::println);
    }

}
