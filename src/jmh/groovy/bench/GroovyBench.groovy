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

    @Benchmark
    void eachTest() { //<2>
        new GroovyLoops().eachTest()
    }

    @Benchmark
    void forEachTest() { //<3>
        new GroovyLoops().forEachTest()
    }

    @Benchmark
    AtomicLong forLoopTest() { //<4>
        return new GroovyLoops().forLoopTest()
    }

    @Benchmark
    AtomicLong iteratorTest() { //<5>
        return new GroovyLoops().iteratorTest()
    }

    @Benchmark
    AtomicLong java8ForEachWithCastClosureTest() { //<6>
        return new GroovyLoops().java8ForEachWithCastClosureTest()
    }

    @Benchmark
    AtomicLong java8ForEachWithAnonymousClassTest() { //<7>
        return new GroovyLoops().java8ForEachWithAnonymousClassTest()
    }

    @Benchmark
    AtomicLong java8ForEachWithClosureTest() { //<6>
        return new GroovyLoops().java8ForEachWithClosureTest()
    }
}
