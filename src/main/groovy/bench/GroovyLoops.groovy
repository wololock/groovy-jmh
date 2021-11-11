package bench

import groovy.transform.CompileStatic

import java.util.concurrent.atomic.AtomicLong
import java.util.function.Consumer

@CompileStatic
class GroovyLoops {

    // final List<Integer> numbers = 0..10_000_000 //<1>

    AtomicLong eachTest() { //<2>
        final AtomicLong result = new AtomicLong()
        DataJava.numbers.each { result.addAndGet(it) }
        return result
    }

    AtomicLong forEachTest() { //<3>
        final AtomicLong result = new AtomicLong()
        for (int number : DataJava.numbers) {
            result.addAndGet(number)
        }
        return result
    }

    AtomicLong forLoopTest() { //<4>
        final AtomicLong result = new AtomicLong()
        for (int i = 0; i < DataJava.numbers.size(); i++) {
            result.addAndGet(DataJava.numbers.get(i))
        }
        return result
    }

    AtomicLong iteratorTest() { //<5>
        final AtomicLong result = new AtomicLong()
        final Iterator<Integer> iterator = DataJava.numbers.iterator()
        while (iterator.hasNext()) {
            result.addAndGet(iterator.next())
        }
        return result
    }

    AtomicLong java8ForEachWithClosureTest() { //<6>
        final AtomicLong result = new AtomicLong()
        DataJava.numbers.forEach { result.addAndGet((int)it) }
        return result
    }

    AtomicLong java8ForEachWithCastClosureTest() { //<6>
        final AtomicLong result = new AtomicLong()
        Consumer consumer = { result.addAndGet((int)it) } as Consumer<Integer>
        DataJava.numbers.forEach(consumer)
        return result
    }

    AtomicLong java8ForEachWithAnonymousClassTest() { //<7>
        final AtomicLong result = new AtomicLong()
        DataJava.numbers.forEach(new Consumer<Integer>() {
            @Override
            void accept(Integer number) {
                result.addAndGet(number)
            }
        })
        return result
    }
}
