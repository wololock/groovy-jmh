package bench

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State

import java.util.concurrent.atomic.AtomicLong
import java.util.function.Consumer

@State(Scope.Benchmark)
@CompileStatic
class GroovyBench {

    //final List<Integer> numbers = DataJava.numbers
    final List<Integer> numbers = 0..10_000_000

    //
    @Benchmark
    AtomicLong forLoopClassic() { //<4>
        return new GroovyLoops().forLoopClassic()
    }

    @Benchmark
    void groovyEach() { //<2>
        new GroovyLoops().groovyEachTest(numbers)
    }

    @Benchmark
    void forEachClassic() { //<3>
        new GroovyLoops().forEachTest()
    }

    //lambda in groovy just as fast
    @Benchmark
    AtomicLong forEachLambda() { //<6>
        return new GroovyLoops().forEachLambda()
    }

    //3X slower
    @Benchmark
    AtomicLong forEachWithClosure() { //<6>
        return new GroovyLoops().forEachWithClosureTest()
    }

    //3X slower and more, almost makes it worse for some reason
    // @Benchmark
    // AtomicLong forEachWithCastClosureTest() { //<6>
    //     return new GroovyLoops().forEachWithCastClosureTest()
    // }

    // here as another baseline, just as fast as java or lambda.
    // @Benchmark
    // AtomicLong forEachWithAnonymousClassTest() { //<7>
    //     return new GroovyLoops().forEachWithAnonymousClassTest()
    // }

    //
    // @Benchmark
    // AtomicLong iteratorTest() { //<5>
    //     return new GroovyLoops().iteratorTest()
    // }

}
