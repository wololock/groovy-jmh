package bench;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * TODO, place to store and benchmark data storage
 */
public class DataJava {

    public static final List<Integer> numbers = IntStream.range(0, 10_000_000)
            .boxed()
            .collect(Collectors.toList());

}
