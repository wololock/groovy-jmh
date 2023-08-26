package bench;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaLoops {

    final List<Integer> numbers = DataJava.numbers;

    public AtomicLong javaForEach() {
        final AtomicLong result = new AtomicLong();
        DataJava.numbers.forEach(number -> {
            result.addAndGet(number);
        });
        return result;
    }

    //baseline with old school loop
    AtomicLong forLoopClassic() { //<4>
        final AtomicLong result = new AtomicLong();
        for (int i = 0; i < numbers.size(); i++) {
            result.addAndGet(numbers.get(i));
        }
        return result;
    }

    public AtomicLong javaForLoop() {
        final AtomicLong result = new AtomicLong();
        for (int number : DataJava.numbers) {
            result.addAndGet(number);
        }
        return result;
    }

    public AtomicLong javaIteratorTest() {
        final AtomicLong result = new AtomicLong();
        final Iterator<Integer> iterator = DataJava.numbers.iterator();
        while (iterator.hasNext()) {
            result.addAndGet(iterator.next());
        }
        return result;
    }
}
