package bench

import groovy.transform.CompileStatic

import java.util.concurrent.atomic.AtomicLong
import java.util.function.Consumer

@CompileStatic
class GroovyLoops {

    final List<Integer> numbers = 0..10_000_000 //<1>

    AtomicLong groovyEachTest(List<Integer> numbers) { //<2>
        final AtomicLong result = new AtomicLong()
        numbers.each { result.addAndGet(it) }
        return result
    }

    AtomicLong forEachTest() { //<3>
        final AtomicLong result = new AtomicLong()
        for (int number : numbers) {
            result.addAndGet(number)
        }
        return result
    }

    //Lambda is just as fast
    AtomicLong forEachLambda() {
        final AtomicLong result = new AtomicLong()
        numbers.forEach( number -> {
            result.addAndGet(number);
        })
        return result
    }

    //THIS IS 3X SLOWER, but for 1 million iterations its 7ms vs 20ms on my machine.
    AtomicLong forEachWithClosureTest() { //<6>
        final AtomicLong result = new AtomicLong()
        numbers.forEach { result.addAndGet(it) }
        return result
        //casting the closure doesnt help
        // Consumer consumer = { result.addAndGet((int)it) } as Consumer<Integer>
        // numbers.forEach(consumer)
    }

    //baseline with old school loop
    AtomicLong forLoopClassic() { //<4>
        final AtomicLong result = new AtomicLong()
        for (int i = 0; i < numbers.size(); i++) {
            result.addAndGet(numbers.get(i))
        }
        return result
    }

    AtomicLong iteratorTest() { //<5>
        final AtomicLong result = new AtomicLong()
        final Iterator<Integer> iterator = numbers.iterator()
        while (iterator.hasNext()) {
            result.addAndGet(iterator.next())
        }
        return result
    }

    //STILL VERY SLOW, DOESNT HELP
    AtomicLong forEachWithCastClosureTest() { //<6>
        final AtomicLong result = new AtomicLong()
        Consumer consumer = { result.addAndGet((int)it) } as Consumer<Integer>
        numbers.forEach(consumer)
        return result
    }

    //THIS IS FAST
    AtomicLong forEachWithAnonymousClassTest() { //<7>
        final AtomicLong result = new AtomicLong()
        numbers.forEach(new Consumer<Integer>() {
            @Override
            void accept(Integer number) {
                result.addAndGet(number)
            }
        })
        return result
    }
}
