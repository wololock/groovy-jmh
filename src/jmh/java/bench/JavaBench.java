package bench;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@State(Scope.Benchmark)
public class JavaBench {

    final List<Integer> numbers = IntStream.range(0, 10_000_00)
            .boxed()
            .collect(Collectors.toList());

    @Benchmark
    public AtomicLong javaForEach() {
        final AtomicLong result = new AtomicLong();
        for (int number : numbers) {
            result.addAndGet(number);
        }
        return result;
    }

    @Benchmark
    public AtomicLong javaIteratorTest() {
        final AtomicLong result = new AtomicLong();
        final Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            result.addAndGet(iterator.next());
        }
        return result;
    }
}
