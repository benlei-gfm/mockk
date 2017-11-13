package io.mockk.proxy;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public abstract class MockKDispatcher {
    private static final Map<Long, MockKDispatcher> INSTANCE = new ConcurrentHashMap<Long, MockKDispatcher>();

    public static MockKDispatcher get(long id, Object obj) {
        if (obj == INSTANCE) {
            return null;
        }

        return INSTANCE.get(id);
    }

    public static void set(long id, MockKDispatcher dispatcher) {
        INSTANCE.put(id, dispatcher);
    }

    public abstract Callable<?> handle(Object self,
                                       Method method,
                                       Object[] arguments) throws Exception;
}
