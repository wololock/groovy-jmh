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

    @Benchmark
    public void javaForEach() {
        new JavaLoops().javaForEach();
    }

    @Benchmark
    public void javaForLoopClassic() {
        new JavaLoops().forLoopClassic();
    }

    // @Benchmark
    // public void javaForLoop() {
    //     new JavaLoops().javaForLoop();
    // }
    //
    // @Benchmark
    // public void javaIteratorTest() {
    //     new JavaLoops().javaIteratorTest();
    // }
}
