package querystream;

/**
 * Replaces Java's ternary operator
 * @param <T> return value
 */
public class QueryReturnStream<T> {

    /**
     * boolean of current
     */
    private boolean cur;
    /**
     * return value if cur is true;
     */
    private T value;

    /**
     * @param query true or false.
     * @param value return value if query is true.
     */
    private QueryReturnStream(boolean query, T value) {
        this.cur = query;
        this.value = value;
    }

    /**
     * @param query true or false.
     * @param trueValue return value if query is true.
     * @param <T> return type.
     * @return QueryReturnStream
     */
    public static <T> QueryReturnStream<T> ifTrueReturn(boolean query, T trueValue) {
        return new QueryReturnStream<>(query, trueValue);
    }

    /**
     * @param query true or false.
     * @param falseValue return value if query is false.
     * @param <T> return type
     * @return QueryReturnStream
     */
    public static <T> QueryReturnStream<T> ifFalseReturn(boolean query, T falseValue) {
        return new QueryReturnStream<>(!query, falseValue);
    }

    /**
     * @param orElseValue return value if cur is false.
     * @return this value when cur is true or else orElseValue
     */
    public T orElse(T orElseValue) {
        return cur ? value : orElseValue;
    }

    /**
     * @param query true or false
     * @param value return value
     * @return QueryReturnStream
     */
    public QueryReturnStream<T> orElseIf(boolean query, T value) {
        if(!this.cur) {
            this.cur = query;
            this.value = value;
        }
        return this;
    }

    /**
     * @param query true or false
     * @param value return value
     * @return QueryReturnStream
     */
    public QueryReturnStream<T> orElseIfNot(boolean query, T value) {
        if(!this.cur) {
            this.cur = !query;
            this.value = value;
        }
        return this;
    }
}
