package at.spengergasse.pos3.modernjava.idgenerator;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.assertj.core.api.Assertions.assertThat;

class IdGeneratorTest {

    @Test
    void everyConstructorIsPrivate() {
        var constructors = IdGenerator.class.getDeclaredConstructors();
        assertThat(constructors).isNotEmpty();
        assertThat(constructors).allMatch(c -> Modifier.isPrivate(c.getModifiers()));
    }

    @Test
    void getInstanceReturnsTheSameObjectEveryTime() {
        var first = IdGenerator.getInstance();
        assertThat(first).isNotNull();
        assertThat(IdGenerator.getInstance()).isSameAs(first);
        assertThat(IdGenerator.getInstance()).isSameAs(first);
    }

    @Test
    void nextCountsUpByOne() {
        var generator = IdGenerator.getInstance();
        var first = generator.next();
        assertThat(generator.next()).isEqualTo(first + 1);
        assertThat(generator.next()).isEqualTo(first + 2);
    }

    @Test
    void theCounterIsSharedBecauseThereIsOnlyOneInstance() {
        var first = IdGenerator.getInstance().next();
        var second = IdGenerator.getInstance().next();
        assertThat(second).isEqualTo(first + 1);
    }

    @Test
    void idsAreNeverHandedOutTwiceUnderConcurrentAccess() throws Exception {
        var threads = 8;
        var perThread = 1000;
        var start = new CountDownLatch(1);
        ExecutorService pool = Executors.newFixedThreadPool(threads);
        try {
            List<Future<List<Long>>> futures = new ArrayList<>();
            for (var t = 0; t < threads; t++) {
                Callable<List<Long>> task = () -> {
                    start.await();
                    List<Long> ids = new ArrayList<>();
                    for (var i = 0; i < perThread; i++) {
                        ids.add(IdGenerator.getInstance().next());
                    }
                    return ids;
                };
                futures.add(pool.submit(task));
            }
            start.countDown();
            Set<Long> all = new HashSet<>();
            for (var future : futures) {
                all.addAll(future.get());
            }
            assertThat(all).hasSize(threads * perThread);
        } finally {
            pool.shutdownNow();
        }
    }

    @Test
    void concurrentCallsToGetInstanceAllSeeTheSameObject() throws Exception {
        var threads = 8;
        var start = new CountDownLatch(1);
        ExecutorService pool = Executors.newFixedThreadPool(threads);
        try {
            List<Future<IdGenerator>> futures = new ArrayList<>();
            for (var t = 0; t < threads; t++) {
                futures.add(pool.submit(() -> {
                    start.await();
                    return IdGenerator.getInstance();
                }));
            }
            start.countDown();
            Set<IdGenerator> instances = new HashSet<>();
            for (var future : futures) {
                instances.add(future.get());
            }
            assertThat(instances).hasSize(1);
        } finally {
            pool.shutdownNow();
        }
    }
}
