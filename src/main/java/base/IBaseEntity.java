package base;

import org.awaitility.core.ConditionFactory;
import org.awaitility.core.ConditionTimeoutException;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import utils.ConfigFileReader;
import utils.Log;


import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

public interface IBaseEntity {
    Log log = Log.getInstance();
    ConfigFileReader config = ConfigFileReader.getInstance();

    /**
     * Gets delay with specified timeout and delay
     *
     * @param timeout time out in seconds
     * @param delay   delay in milliseconds
     * @return delay
     */
    static ConditionFactory getDelay(int timeout, int delay) {
        return await().atMost(timeout, TimeUnit.SECONDS)
                .ignoreExceptions()
                .pollDelay(delay, TimeUnit.MILLISECONDS);
    }

    /**
     * Gets delay with default values
     *
     * @see #getDelay(int, int)
     */
    static ConditionFactory getDelay() {
        return getDelay(5, 1000);
    }

    /**
     * Waits till the list with specified condition is present
     *
     * @param timeout      time out in seconds
     * @param delay        delay in milliseconds
     * @param errorMessage specified error message
     * @param supplier     condition to meet
     * @param <T>          Type of object for waiting
     * @return List with <T> type
     * @throws AssertionError if condition is not met
     * @see #getDelay(int, int)
     */
    static <T> List<T> waitForList(int timeout, int delay, String errorMessage, Callable<List<T>> supplier) {
        try {
            return getDelay(timeout, delay).until(supplier, not(empty()));
        } catch (ConditionTimeoutException ex) {
            throw new AssertionError(errorMessage, ex);
        }
    }

    static <T> List<T> waitForList(String errorMessage, Callable<List<T>> supplier) {
        try {
            return getDelay(5, 1000).until(supplier, not(empty()));
        } catch (ConditionTimeoutException ex) {
            throw new AssertionError(errorMessage, ex);
        }
    }

}
