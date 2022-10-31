package querystream;

import java.util.function.Supplier;

import static querystream.QueryReturnStream.ifFalseReturn;
import static querystream.QueryReturnStream.ifTrueReturn;

/**
 * Query Stream
 */
public class QueryStream {

    /**
     * boolean of current
     */
    private boolean cur;

    /**
     * @param query true or false
     */
    private QueryStream(boolean query) {
        this.cur = query;
    }

    /**
     * create QueryStream starts with query is true
     * @param query true or false
     * @return New QueryStream
     */
    public static QueryStream ask(boolean query) {
        return new QueryStream(query);
    }

    /**
     * create QueryStream starts with query is false
     * @param query true or false
     * @return New QueryStream
     */
    public static QueryStream askNot(boolean query) {
        return new QueryStream(!query);
    }

    /**
     * initialize QueryStream starts with query is true
     * @param query true or false.
     * @return this
     */
    public QueryStream reAsk(boolean query) {
        this.cur = query;
        return this;
    }

    /**
     * initialize QueryStream starts with query is false
     * @param query true or false
     * @return this
     */
    public QueryStream reAskNot(boolean query) {
        this.cur = !query;
        return this;
    }

    /**
     * assign (cur and other) to cur
     * @param other true or false
     * @return this
     */
    public QueryStream and(boolean other) {
        cur = (cur && other);
        return this;
    }

    /**
     * assign (cur and not other) to cur
     * @param other true or false
     * @return this
     */
    public QueryStream andNot(boolean other) {
        cur = cur && !other;
        return this;
    }

    /**
     * assign (cur or other) to cur
     * @param other true or false
     * @return this
     */
    public QueryStream or(boolean other) {
        cur = (cur || other);
        return this;
    }

    /**
     * assign (cur or other) to cur
     * @param other true or false
     * @return this
     */
    public QueryStream orNot(boolean other) {
        cur = cur || !other;
        return this;
    }

    /**
     * if cur is true run param
     * @param runnable runnable if cur is true
     * @return this
     */
    public QueryStream then(Runnable runnable) {
        if(cur) runnable.run();
        return this;
    }

    /**
     * if cur is false run param
     * @param runnable runnable if cur is false
     * @return this
     */
    public QueryStream orElse(Runnable runnable) {
        if(!cur) runnable.run();
        return this;
    }

    /**
     * if cur is true throws exception
     * @param exceptionSupplier supplier of exception
     * @param <X> exception extends Throwable type
     * @return this
     * @throws X exception extends throwable
     */
    public <X extends Throwable> QueryStream thenThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if(cur) throw exceptionSupplier.get();
        return this;
    }

    /**
     * if cur is false throws exception
     * @param exceptionSupplier supplier of exception
     * @param <X> exception extends Throwable type
     * @return this
     * @throws X exception extends throwable
     */
    public <X extends Throwable> QueryStream orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if(!cur) throw exceptionSupplier.get();
        return this;
    }

    /**
     * @return cur is true
     */
    public boolean isTrue() {
        return cur == true;
    }

    /**
     * @return cur is false
     */
    public boolean isFalse() {
        return cur == false;
    }

    /**
     * @param comparison comparison value
     * @return cur is comparison
     */
    public boolean is(boolean comparison) {
        return cur == comparison;
    }


    /**
     * create QueryReturnStream when cur is true
     * @param trueValue value when cur is true;
     * @param <T> value type
     * @return QueryReturnStream
     */
    public <T> QueryReturnStream<T> thenReturn(T trueValue) {
        return ifTrueReturn(cur, trueValue);
    }

    /**
     * create QueryReturnStream when cur is false
     * @param falseValue value when cur is false;
     * @param <T> value type
     * @return QueryReturnStream
     */
    public <T> QueryReturnStream<T> orElseReturn(T falseValue) {
        return ifFalseReturn(cur, falseValue);
    }
}
