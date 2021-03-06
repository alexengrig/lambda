package dev.alexengrig.util.lambda;

import java.util.Objects;
import java.util.function.Function;

/**
 * Represents a function that accepts three arguments and produces a result.
 * This is the three-arity specialization of {@link java.util.function.Function}.
 *
 * <p>This is a
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(Object, Object, Object)}.
 *
 * @param <F> the type of the first argument to the function
 * @param <S> the type of the second argument to the function
 * @param <T> the type of the third argument to the function
 * @param <R> the type of the result of the function
 * @author Grig Alex
 * @version 1.0
 * @see java.util.function.BiFunction
 * @see java.util.function.Function
 * @since 1.0
 */
@FunctionalInterface
public interface TerFunction<F, S, T, R> {

    /**
     * Applies this function to the given arguments.
     *
     * @param first  the first function argument
     * @param second the second function argument
     * @param third  the third function argument
     * @return the function result
     */
    R apply(F first, S second, T third);

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V>   the type of output of the {@code after} function, and of the
     *              composed function
     * @param after the function to apply after this function is applied
     * @return a composed function that first applies this function and then
     * applies the {@code after} function
     * @throws NullPointerException if after is null
     */
    default <V> TerFunction<F, S, T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after, "The after-function must not be null");
        return (F first, S second, T third) -> after.apply(apply(first, second, third));
    }

}
