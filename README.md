# Groovy JMH sandbox project

This project was created to benchmark Groovy code easily. 

Running benchmark:

```text
./gradlew clean jmh
```

## Results

for `10,000,000` (10 million iterations) these are results

```
# Run complete. Total time: 00:01:16

REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
experiments, perform baseline and negative tests that provide experimental control, make sure
the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
Do not assume the numbers tell you what you want them to tell.

Benchmark                       Mode  Cnt    Score   Error  Units
GroovyBench.forEachClassic      avgt    5   69.660 ± 1.071  ms/op
GroovyBench.forEachLambda       avgt    5   69.763 ± 1.422  ms/op
GroovyBench.forEachWithClosure  avgt    5  192.056 ± 2.692  ms/op
GroovyBench.forLoopClassic      avgt    5   71.097 ± 2.287  ms/op
GroovyBench.groovyEach          avgt    5   70.413 ± 1.527  ms/op
JavaBench.javaForEach           avgt    5   69.601 ± 0.855  ms/op
JavaBench.javaForLoopClassic    avgt    5   70.384 ± 1.235  ms/op
```

The only significant difference is forEach with a closure. about 3X slower than a java lambda. 
But this is for 10 million iterations, its a 120ms or `0.1s` difference. 
Chances are any slowness in code is not coming from any variations in iterations performance 
or from using a Groovy closure instead of a Java lambda.

