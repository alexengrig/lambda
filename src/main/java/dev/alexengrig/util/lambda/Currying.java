package dev.alexengrig.util.lambda;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * <a href="https://en.wikipedia.org/wiki/Currying">
 * Currying
 * </a>
 * is the technique of converting a function
 * that takes multiple arguments into a sequence of functions.
 *
 * <p>Parts of method name:</p>
 * <ul>
 * <li>{@code left} - arguments from left to right;</li>
 * <li>{@code right} - arguments from right to left;</li>
 * <li>{@code middle} - arguments from middle;</li>
 * <li>{@code all} - all arguments;</li>
 * <li>{@code 2} - {@code Bi*} as the function;</li>
 * <li>{@code 3} - {@code Ter*} as the function;</li>
 * <li>{@code bi} - {@code Bi*} as the result.</li>
 * </ul>
 *
 * @author Grig Alex
 * @version 2.0
 * @see java.util.function
 * @since 1.0
 */
public final class Currying {

    private Currying() {
    }

//    Functions

    /**
     * Currying from:
     * <pre>{@code
     * (first) -> result
     * }</pre>
     * to:
     * <pre>{@code
     * () -> result
     * }</pre>
     *
     * @param function the function for currying
     * @param first    the value of the first argument to {@code function}
     * @param <F>      the type of the first argument to {@code function}
     * @param <R>      the type of the result of {@code function}
     * @return currying of {@code function}
     * @throws NullPointerException if {@code function} is null
     * @since 1.0
     */
    public static <F, R> Supplier<R>
    all(Function<F, R> function, F first) {
        Objects.requireNonNull(function, "The function must not be null");
        return () -> function.apply(first);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second) -> result
     * }</pre>
     * to:
     * <pre>{@code
     * (first) -> (second) -> result
     * }</pre>
     *
     * @param biFunction the function for currying
     * @param <F>        the type of the first argument to {@code biFunction}
     * @param <S>        the type of the second argument to {@code biFunction}
     * @param <R>        the type of the result of {@code biFunction}
     * @return currying of {@code biFunction}
     * @throws NullPointerException if {@code biFunction} is null
     * @since 1.0
     */
    public static <F, S, R> Function<F, Function<S, R>>
    left2(BiFunction<F, S, R> biFunction) {
        Objects.requireNonNull(biFunction, "The bi-function must not be null");
        return (F first) -> (S second) -> biFunction.apply(first, second);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second) -> result
     * }</pre>
     * to:
     * <pre>{@code
     * (second) -> result
     * }</pre>
     *
     * @param biFunction the function for currying
     * @param first      the value of the first argument to {@code biFunction}
     * @param <F>        the type of the first argument to {@code biFunction}
     * @param <S>        the type of the second argument to {@code biFunction}
     * @param <R>        the type of the result of {@code biFunction}
     * @return currying of {@code biFunction}
     * @throws NullPointerException if {@code biFunction} is null
     * @since 1.0
     */
    public static <F, S, R> Function<S, R>
    left2(BiFunction<F, S, R> biFunction, F first) {
        Objects.requireNonNull(biFunction, "The bi-function must not be null");
        return (S second) -> biFunction.apply(first, second);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second) -> result
     * }</pre>
     * to:
     * <pre>{@code
     * () -> result
     * }</pre>
     *
     * @param biFunction the function for currying
     * @param first      the value of the first argument to {@code biFunction}
     * @param second     the value of the second argument to {@code biFunction}
     * @param <F>        the type of the first argument to {@code biFunction}
     * @param <R>        the type of the result of {@code biFunction}
     * @return currying of {@code biFunction}
     * @throws NullPointerException if {@code biFunction} is null
     * @since 1.0
     */
    public static <F, S, R> Supplier<R>
    all2(BiFunction<F, S, R> biFunction, F first, S second) {
        Objects.requireNonNull(biFunction, "The bi-function must not be null");
        return () -> biFunction.apply(first, second);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> result
     * }</pre>
     * to:
     * <pre>{@code
     * (first) -> (second) -> (third) -> result
     * }</pre>
     *
     * @param terFunction the function for currying
     * @param <F>         the type of the first argument to {@code terFunction}
     * @param <S>         the type of the second argument to {@code terFunction}
     * @param <T>         the type of the third argument to {@code terFunction}
     * @param <R>         the type of the result of {@code terFunction}
     * @return currying of {@code terFunction}
     * @throws NullPointerException if {@code terFunction} is null
     * @since 1.0
     */
    public static <F, S, T, R> Function<F, Function<S, Function<T, R>>>
    left3(TerFunction<F, S, T, R> terFunction) {
        Objects.requireNonNull(terFunction, "The ter-function must not be null");
        return (F first) -> (S second) -> (T third) -> terFunction.apply(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> result
     * }</pre>
     * to:
     * <pre>{@code
     * (second) -> (third) -> result
     * }</pre>
     *
     * @param terFunction the function for currying
     * @param first       the value of the first argument to {@code terFunction}
     * @param <F>         the type of the first argument to {@code terFunction}
     * @param <S>         the type of the second argument to {@code terFunction}
     * @param <T>         the type of the third argument to {@code terFunction}
     * @param <R>         the type of the result of {@code terFunction}
     * @return currying of {@code terFunction}
     * @throws NullPointerException if {@code terFunction} is null
     * @since 1.0
     */
    public static <F, S, T, R> Function<S, Function<T, R>>
    left3(TerFunction<F, S, T, R> terFunction, F first) {
        Objects.requireNonNull(terFunction, "The ter-function must not be null");
        return (S second) -> (T third) -> terFunction.apply(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> result
     * }</pre>
     * to:
     * <pre>{@code
     * (third) -> result
     * }</pre>
     *
     * @param terFunction the function for currying
     * @param first       the value of the first argument to {@code terFunction}
     * @param second      the value of the second argument to {@code terFunction}
     * @param <F>         the type of the first argument to {@code terFunction}
     * @param <S>         the type of the second argument to {@code terFunction}
     * @param <T>         the type of the third argument to {@code terFunction}
     * @param <R>         the type of the result of {@code terFunction}
     * @return currying of {@code terFunction}
     * @throws NullPointerException if {@code terFunction} is null
     * @since 1.0
     */
    public static <F, S, T, R> Function<T, R>
    left3(TerFunction<F, S, T, R> terFunction, F first, S second) {
        Objects.requireNonNull(terFunction, "The ter-function must not be null");
        return (T third) -> terFunction.apply(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> result
     * }</pre>
     * to:
     * <pre>{@code
     * () -> result
     * }</pre>
     *
     * @param terFunction the function for currying
     * @param first       the value of the first argument to {@code terFunction}
     * @param second      the value of the second argument to {@code terFunction}
     * @param third       the value of the third argument to {@code terFunction}
     * @param <F>         the type of the first argument to {@code terFunction}
     * @param <R>         the type of the result of {@code terFunction}
     * @return currying of {@code terFunction}
     * @throws NullPointerException if {@code terFunction} is null
     * @since 1.0
     */
    public static <F, S, T, R> Supplier<R>
    all3(TerFunction<F, S, T, R> terFunction, F first, S second, T third) {
        Objects.requireNonNull(terFunction, "The ter-function must not be null");
        return () -> terFunction.apply(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> result
     * }</pre>
     * to:
     * <pre>{@code
     * (first) -> (third) -> result
     * }</pre>
     *
     * @param terFunction the function for currying
     * @param second      the value of the second argument to {@code terFunction}
     * @param <F>         the type of the first argument to {@code terFunction}
     * @param <S>         the type of the second argument to {@code terFunction}
     * @param <T>         the type of the third argument to {@code terFunction}
     * @param <R>         the type of the result of {@code terFunction}
     * @return currying of {@code terFunction}
     * @throws NullPointerException if {@code terFunction} is null
     * @since 1.0
     */
    public static <F, S, T, R> Function<F, Function<T, R>>
    leftMiddle3(TerFunction<F, S, T, R> terFunction, S second) {
        Objects.requireNonNull(terFunction, "The ter-function must not be null");
        return (F first) -> (T third) -> terFunction.apply(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> result
     * }</pre>
     * to:
     * <pre>{@code
     * (second) -> result
     * }</pre>
     *
     * @param terFunction the function for currying
     * @param first       the value of the first argument to {@code terFunction}
     * @param third       the value of the third argument to {@code terFunction}
     * @param <F>         the type of the first argument to {@code terFunction}
     * @param <S>         the type of the second argument to {@code terFunction}
     * @param <T>         the type of the third argument to {@code terFunction}
     * @param <R>         the type of the result of {@code terFunction}
     * @return currying of {@code terFunction}
     * @throws NullPointerException if {@code terFunction} is null
     * @since 1.0
     */
    public static <F, S, T, R> Function<S, R>
    middle3(TerFunction<F, S, T, R> terFunction, F first, T third) {
        Objects.requireNonNull(terFunction, "The ter-function must not be null");
        return (S second) -> terFunction.apply(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> result
     * }</pre>
     * to:
     * <pre>{@code
     * (third) -> (first) -> result
     * }</pre>
     *
     * @param terFunction the function for currying
     * @param second      the value of the second argument to {@code terFunction}
     * @param <F>         the type of the first argument to {@code terFunction}
     * @param <S>         the type of the second argument to {@code terFunction}
     * @param <T>         the type of the third argument to {@code terFunction}
     * @param <R>         the type of the result of {@code terFunction}
     * @return currying of {@code terFunction}
     * @throws NullPointerException if {@code terFunction} is null
     * @since 1.0
     */
    public static <F, S, T, R> Function<T, Function<F, R>>
    rightMiddle3(TerFunction<F, S, T, R> terFunction, S second) {
        Objects.requireNonNull(terFunction, "The ter-function must not be null");
        return (T third) -> (F first) -> terFunction.apply(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second) -> result
     * }</pre>
     * to:
     * <pre>{@code
     * (second) -> (first) -> result
     * }</pre>
     *
     * @param biFunction the function for currying
     * @param <F>        the type of the first argument to {@code biFunction}
     * @param <S>        the type of the second argument to {@code biFunction}
     * @param <R>        the type of the result of {@code biFunction}
     * @return currying of {@code biFunction}
     * @throws NullPointerException if {@code biFunction} is null
     * @since 1.0
     */
    public static <F, S, R> Function<S, Function<F, R>>
    right2(BiFunction<F, S, R> biFunction) {
        Objects.requireNonNull(biFunction, "The bi-function must not be null");
        return (S second) -> (F first) -> biFunction.apply(first, second);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second) -> result
     * }</pre>
     * to:
     * <pre>{@code
     * (first) -> result
     * }</pre>
     *
     * @param biFunction the function for currying
     * @param second     the value of the second argument to {@code biFunction}
     * @param <F>        the type of the first argument to {@code biFunction}
     * @param <S>        the type of the second argument to {@code biFunction}
     * @param <R>        the type of the result of {@code biFunction}
     * @return currying of {@code biFunction}
     * @throws NullPointerException if {@code biFunction} is null
     * @since 1.0
     */
    public static <F, S, R> Function<F, R>
    right2(BiFunction<F, S, R> biFunction, S second) {
        Objects.requireNonNull(biFunction, "The bi-function must not be null");
        return (F first) -> biFunction.apply(first, second);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> result
     * }</pre>
     * to:
     * <pre>{@code
     * (third) -> (second) -> (first) -> result
     * }</pre>
     *
     * @param terFunction the function for currying
     * @param <F>         the type of the first argument to {@code terFunction}
     * @param <S>         the type of the second argument to {@code terFunction}
     * @param <T>         the type of the third argument to {@code terFunction}
     * @param <R>         the type of the result of {@code terFunction}
     * @return currying of {@code terFunction}
     * @throws NullPointerException if {@code terFunction} is null
     * @since 1.0
     */
    public static <F, S, T, R> Function<T, Function<S, Function<F, R>>>
    right3(TerFunction<F, S, T, R> terFunction) {
        Objects.requireNonNull(terFunction, "The ter-function must not be null");
        return (T third) -> (S second) -> (F first) -> terFunction.apply(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> result
     * }</pre>
     * to:
     * <pre>{@code
     * (second) -> (first) -> result
     * }</pre>
     *
     * @param terFunction the function for currying
     * @param third       the value of the third argument to {@code terFunction}
     * @param <F>         the type of the first argument to {@code terFunction}
     * @param <S>         the type of the second argument to {@code terFunction}
     * @param <T>         the type of the third argument to {@code terFunction}
     * @param <R>         the type of the result of {@code terFunction}
     * @return currying of {@code terFunction}
     * @throws NullPointerException if {@code terFunction} is null
     * @since 1.0
     */
    public static <F, S, T, R> Function<S, Function<F, R>>
    right3(TerFunction<F, S, T, R> terFunction, T third) {
        Objects.requireNonNull(terFunction, "The ter-function must not be null");
        return (S second) -> (F first) -> terFunction.apply(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> result
     * }</pre>
     * to:
     * <pre>{@code
     * (first) -> result
     * }</pre>
     *
     * @param terFunction the function for currying
     * @param third       the value of the third argument to {@code terFunction}
     * @param second      the value of the second argument to {@code terFunction}
     * @param <F>         the type of the first argument to {@code terFunction}
     * @param <S>         the type of the second argument to {@code terFunction}
     * @param <T>         the type of the third argument to {@code terFunction}
     * @param <R>         the type of the result of {@code terFunction}
     * @return currying of {@code terFunction}
     * @throws NullPointerException if {@code terFunction} is null
     * @since 1.0
     */
    public static <F, S, T, R> Function<F, R>
    right3(TerFunction<F, S, T, R> terFunction, S second, T third) {
        Objects.requireNonNull(terFunction, "The ter-function must not be null");
        return (F first) -> terFunction.apply(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> result
     * }</pre>
     * to:
     * <pre>{@code
     * (second, third) -> result
     * }</pre>
     *
     * @param terFunction the function for currying
     * @param first       the value of the first argument to {@code terFunction}
     * @param <F>         the type of the first argument to {@code terFunction}
     * @param <S>         the type of the second argument to {@code terFunction}
     * @param <T>         the type of the third argument to {@code terFunction}
     * @param <R>         the type of the result of {@code terFunction}
     * @return currying of {@code terFunction}
     * @throws NullPointerException if {@code terFunction} is null
     * @since 1.0
     */
    public static <F, S, T, R> BiFunction<S, T, R>
    biLeft3(TerFunction<F, S, T, R> terFunction, F first) {
        Objects.requireNonNull(terFunction, "The ter-function must not be null");
        return (S second, T third) -> terFunction.apply(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> result
     * }</pre>
     * to:
     * <pre>{@code
     * (first, third) -> result
     * }</pre>
     *
     * @param terFunction the function for currying
     * @param second      the value of the second argument to {@code terFunction}
     * @param <F>         the type of the first argument to {@code terFunction}
     * @param <S>         the type of the second argument to {@code terFunction}
     * @param <T>         the type of the third argument to {@code terFunction}
     * @param <R>         the type of the result of {@code terFunction}
     * @return currying of {@code terFunction}
     * @throws NullPointerException if {@code terFunction} is null
     * @since 1.0
     */
    public static <F, S, T, R> BiFunction<F, T, R>
    biMiddle3(TerFunction<F, S, T, R> terFunction, S second) {
        Objects.requireNonNull(terFunction, "The ter-function must not be null");
        return (F first, T third) -> terFunction.apply(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> result
     * }</pre>
     * to:
     * <pre>{@code
     * (first, second) -> result
     * }</pre>
     *
     * @param terFunction the function for currying
     * @param third       the value of the third argument to {@code terFunction}
     * @param <F>         the type of the first argument to {@code terFunction}
     * @param <S>         the type of the second argument to {@code terFunction}
     * @param <T>         the type of the third argument to {@code terFunction}
     * @param <R>         the type of the result of {@code terFunction}
     * @return currying of {@code terFunction}
     * @throws NullPointerException if {@code terFunction} is null
     * @since 1.0
     */
    public static <F, S, T, R> BiFunction<F, S, R>
    biRight3(TerFunction<F, S, T, R> terFunction, T third) {
        Objects.requireNonNull(terFunction, "The ter-function must not be null");
        return (F first, S second) -> terFunction.apply(first, second, third);
    }

//    Consumers

    /**
     * Currying from:
     * <pre>{@code
     * (first) -> {}
     * }</pre>
     * to:
     * <pre>{@code
     * () -> {}
     * }</pre>
     *
     * @param consumer the consumer for currying
     * @param first    the value of the first argument to {@code biConsumer}
     * @param <F>      the type of the first argument to {@code biConsumer}
     * @return currying of {@code biConsumer}
     * @throws NullPointerException if {@code biConsumer} is null
     * @since 2.0
     */
    public static <F> Runnable
    all(Consumer<F> consumer, F first) {
        Objects.requireNonNull(consumer, "The consumer must not be null");
        return () -> consumer.accept(first);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second) -> {}
     * }</pre>
     * to:
     * <pre>{@code
     * (first) -> (second) -> {}
     * }</pre>
     *
     * @param biConsumer the consumer for currying
     * @param <F>        the type of the first argument to {@code biConsumer}
     * @param <S>        the type of the second argument to {@code biConsumer}
     * @return currying of {@code biConsumer}
     * @throws NullPointerException if {@code biConsumer} is null
     * @since 2.0
     */
    public static <F, S> Function<F, Consumer<S>>
    left2(BiConsumer<F, S> biConsumer) {
        Objects.requireNonNull(biConsumer, "The bi-consumer must not be null");
        return (F first) -> (S second) -> biConsumer.accept(first, second);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second) -> {}
     * }</pre>
     * to:
     * <pre>{@code
     * (second) -> {}
     * }</pre>
     *
     * @param biConsumer the consumer for currying
     * @param first      the value of the first argument to {@code biConsumer}
     * @param <F>        the type of the first argument to {@code biConsumer}
     * @param <S>        the type of the second argument to {@code biConsumer}
     * @return currying of {@code biConsumer}
     * @throws NullPointerException if {@code biConsumer} is null
     * @since 2.0
     */
    public static <F, S> Consumer<S>
    left2(BiConsumer<F, S> biConsumer, F first) {
        Objects.requireNonNull(biConsumer, "The bi-consumer must not be null");
        return (S second) -> biConsumer.accept(first, second);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second) -> {}
     * }</pre>
     * to:
     * <pre>{@code
     * () -> {}
     * }</pre>
     *
     * @param biConsumer the consumer for currying
     * @param first      the value of the first argument to {@code biConsumer}
     * @param second     the value of the second argument to {@code biConsumer}
     * @param <F>        the type of the first argument to {@code biConsumer}
     * @param <S>        the type of the second argument to {@code biConsumer}
     * @return currying of {@code biConsumer}
     * @throws NullPointerException if {@code biConsumer} is null
     * @since 2.0
     */
    public static <F, S> Runnable
    all2(BiConsumer<F, S> biConsumer, F first, S second) {
        Objects.requireNonNull(biConsumer, "The bi-consumer must not be null");
        return () -> biConsumer.accept(first, second);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> {}
     * }</pre>
     * to:
     * <pre>{@code
     * (first) -> (second) -> (third) -> {}
     * }</pre>
     *
     * @param terConsumer the consumer for currying
     * @param <F>         the type of the first argument to {@code terConsumer}
     * @param <S>         the type of the second argument to {@code terConsumer}
     * @param <T>         the type of the third argument to {@code terConsumer}
     * @return currying of {@code terConsumer}
     * @throws NullPointerException if {@code terConsumer} is null
     * @since 2.0
     */
    public static <F, S, T> Function<F, Function<S, Consumer<T>>>
    left3(TerConsumer<F, S, T> terConsumer) {
        Objects.requireNonNull(terConsumer, "The ter-consumer must not be null");
        return (F first) -> (S second) -> (T third) -> terConsumer.accept(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> {}
     * }</pre>
     * to:
     * <pre>{@code
     * (second) -> (third) -> {}
     * }</pre>
     *
     * @param terConsumer the consumer for currying
     * @param first       the value of the first argument to {@code terConsumer}
     * @param <F>         the type of the first argument to {@code terConsumer}
     * @param <S>         the type of the second argument to {@code terConsumer}
     * @param <T>         the type of the third argument to {@code terConsumer}
     * @return currying of {@code terConsumer}
     * @throws NullPointerException if {@code terConsumer} is null
     * @since 2.0
     */
    public static <F, S, T> Function<S, Consumer<T>>
    left3(TerConsumer<F, S, T> terConsumer, F first) {
        Objects.requireNonNull(terConsumer, "The ter-consumer must not be null");
        return (S second) -> (T third) -> terConsumer.accept(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> {}
     * }</pre>
     * to:
     * <pre>{@code
     * (third) -> {}
     * }</pre>
     *
     * @param terConsumer the consumer for currying
     * @param first       the value of the first argument to {@code terConsumer}
     * @param second      the value of the second argument to {@code terConsumer}
     * @param <F>         the type of the first argument to {@code terConsumer}
     * @param <S>         the type of the second argument to {@code terConsumer}
     * @param <T>         the type of the third argument to {@code terConsumer}
     * @return currying of {@code terConsumer}
     * @throws NullPointerException if {@code terConsumer} is null
     * @since 2.0
     */
    public static <F, S, T> Consumer<T>
    left3(TerConsumer<F, S, T> terConsumer, F first, S second) {
        Objects.requireNonNull(terConsumer, "The ter-consumer must not be null");
        return (T third) -> terConsumer.accept(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> {}
     * }</pre>
     * to:
     * <pre>{@code
     * () -> {}
     * }</pre>
     *
     * @param terConsumer the consumer for currying
     * @param first       the value of the first argument to {@code terConsumer}
     * @param second      the value of the second argument to {@code terConsumer}
     * @param third       the value of the third argument to {@code terConsumer}
     * @param <F>         the type of the first argument to {@code terConsumer}
     * @param <S>         the type of the second argument to {@code terConsumer}
     * @param <T>         the type of the third argument to {@code terConsumer}
     * @return currying of {@code terConsumer}
     * @throws NullPointerException if {@code terConsumer} is null
     * @since 2.0
     */
    public static <F, S, T> Runnable
    all3(TerConsumer<F, S, T> terConsumer, F first, S second, T third) {
        Objects.requireNonNull(terConsumer, "The ter-consumer must not be null");
        return () -> terConsumer.accept(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> {}
     * }</pre>
     * to:
     * <pre>{@code
     * (first) -> (third) -> {}
     * }</pre>
     *
     * @param terConsumer the consumer for currying
     * @param second      the value of the second argument to {@code terConsumer}
     * @param <F>         the type of the first argument to {@code terConsumer}
     * @param <S>         the type of the second argument to {@code terConsumer}
     * @param <T>         the type of the third argument to {@code terConsumer}
     * @return currying of {@code terConsumer}
     * @throws NullPointerException if {@code terConsumer} is null
     * @since 2.0
     */
    public static <F, S, T> Function<F, Consumer<T>>
    leftMiddle3(TerConsumer<F, S, T> terConsumer, S second) {
        Objects.requireNonNull(terConsumer, "The ter-consumer must not be null");
        return (F first) -> (T third) -> terConsumer.accept(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> {}
     * }</pre>
     * to:
     * <pre>{@code
     * (second) -> {}
     * }</pre>
     *
     * @param terConsumer the consumer for currying
     * @param first       the value of the first argument to {@code terConsumer}
     * @param third       the value of the third argument to {@code terConsumer}
     * @param <F>         the type of the first argument to {@code terConsumer}
     * @param <S>         the type of the second argument to {@code terConsumer}
     * @param <T>         the type of the third argument to {@code terConsumer}
     * @return currying of {@code terConsumer}
     * @throws NullPointerException if {@code terConsumer} is null
     * @since 2.0
     */
    public static <F, S, T> Consumer<S>
    middle3(TerConsumer<F, S, T> terConsumer, F first, T third) {
        Objects.requireNonNull(terConsumer, "The ter-consumer must not be null");
        return (S second) -> terConsumer.accept(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> {}
     * }</pre>
     * to:
     * <pre>{@code
     * (third) -> (first) -> {}
     * }</pre>
     *
     * @param terConsumer the consumer for currying
     * @param second      the value of the second argument to {@code terConsumer}
     * @param <F>         the type of the first argument to {@code terConsumer}
     * @param <S>         the type of the second argument to {@code terConsumer}
     * @param <T>         the type of the third argument to {@code terConsumer}
     * @return currying of {@code terConsumer}
     * @throws NullPointerException if {@code terConsumer} is null
     * @since 2.0
     */
    public static <F, S, T> Function<T, Consumer<F>>
    rightMiddle3(TerConsumer<F, S, T> terConsumer, S second) {
        Objects.requireNonNull(terConsumer, "The ter-consumer must not be null");
        return (T third) -> (F first) -> terConsumer.accept(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second) -> {}
     * }</pre>
     * to:
     * <pre>{@code
     * (second) -> (first) -> {}
     * }</pre>
     *
     * @param biConsumer the consumer for currying
     * @param <F>        the type of the first argument to {@code biConsumer}
     * @param <S>        the type of the second argument to {@code biConsumer}
     * @return currying of {@code biConsumer}
     * @throws NullPointerException if {@code biConsumer} is null
     * @since 2.0
     */
    public static <F, S> Function<S, Consumer<F>>
    right2(BiConsumer<F, S> biConsumer) {
        Objects.requireNonNull(biConsumer, "The bi-consumer must not be null");
        return (S second) -> (F first) -> biConsumer.accept(first, second);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second) -> {}
     * }</pre>
     * to:
     * <pre>{@code
     * (first) -> {}
     * }</pre>
     *
     * @param biConsumer the consumer for currying
     * @param second     the value of the second argument to {@code biConsumer}
     * @param <F>        the type of the first argument to {@code biConsumer}
     * @param <S>        the type of the second argument to {@code biConsumer}
     * @return currying of {@code biConsumer}
     * @throws NullPointerException if {@code biConsumer} is null
     * @since 2.0
     */
    public static <F, S> Consumer<F>
    right2(BiConsumer<F, S> biConsumer, S second) {
        Objects.requireNonNull(biConsumer, "The bi-consumer must not be null");
        return (F first) -> biConsumer.accept(first, second);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> {}
     * }</pre>
     * to:
     * <pre>{@code
     * (third) -> (second) -> (first) -> {}
     * }</pre>
     *
     * @param terConsumer the consumer for currying
     * @param <F>         the type of the first argument to {@code terConsumer}
     * @param <S>         the type of the second argument to {@code terConsumer}
     * @param <T>         the type of the third argument to {@code terConsumer}
     * @return currying of {@code terConsumer}
     * @throws NullPointerException if {@code terConsumer} is null
     * @since 2.0
     */
    public static <F, S, T> Function<T, Function<S, Consumer<F>>>
    right3(TerConsumer<F, S, T> terConsumer) {
        Objects.requireNonNull(terConsumer, "The ter-consumer must not be null");
        return (T third) -> (S second) -> (F first) -> terConsumer.accept(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> {}
     * }</pre>
     * to:
     * <pre>{@code
     * (second) -> (first) -> {}
     * }</pre>
     *
     * @param terConsumer the consumer for currying
     * @param third       the value of the third argument to {@code terConsumer}
     * @param <F>         the type of the first argument to {@code terConsumer}
     * @param <S>         the type of the second argument to {@code terConsumer}
     * @param <T>         the type of the third argument to {@code terConsumer}
     * @return currying of {@code terConsumer}
     * @throws NullPointerException if {@code terConsumer} is null
     * @since 2.0
     */
    public static <F, S, T> Function<S, Consumer<F>>
    right3(TerConsumer<F, S, T> terConsumer, T third) {
        Objects.requireNonNull(terConsumer, "The ter-consumer must not be null");
        return (S second) -> (F first) -> terConsumer.accept(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> {}
     * }</pre>
     * to:
     * <pre>{@code
     * (first) -> {}
     * }</pre>
     *
     * @param terConsumer the consumer for currying
     * @param second      the value of the second argument to {@code terConsumer}
     * @param third       the value of the third argument to {@code terConsumer}
     * @param <F>         the type of the first argument to {@code terConsumer}
     * @param <S>         the type of the second argument to {@code terConsumer}
     * @param <T>         the type of the third argument to {@code terConsumer}
     * @return currying of {@code terConsumer}
     * @throws NullPointerException if {@code terConsumer} is null
     * @since 2.0
     */
    public static <F, S, T> Consumer<F>
    right3(TerConsumer<F, S, T> terConsumer, S second, T third) {
        Objects.requireNonNull(terConsumer, "The ter-consumer must not be null");
        return (F first) -> terConsumer.accept(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> {}
     * }</pre>
     * to:
     * <pre>{@code
     * (second, third) -> {}
     * }</pre>
     *
     * @param terConsumer the consumer for currying
     * @param first       the value of the first argument to {@code terConsumer}
     * @param <F>         the type of the first argument to {@code terConsumer}
     * @param <S>         the type of the second argument to {@code terConsumer}
     * @param <T>         the type of the third argument to {@code terConsumer}
     * @return currying of {@code terConsumer}
     * @throws NullPointerException if {@code terConsumer} is null
     * @since 2.0
     */
    public static <F, S, T> BiConsumer<S, T>
    biLeft3(TerConsumer<F, S, T> terConsumer, F first) {
        Objects.requireNonNull(terConsumer, "The ter-consumer must not be null");
        return (S second, T third) -> terConsumer.accept(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> {}
     * }</pre>
     * to:
     * <pre>{@code
     * (first, third) -> {}
     * }</pre>
     *
     * @param terConsumer the consumer for currying
     * @param second      the value of the second argument to {@code terConsumer}
     * @param <F>         the type of the first argument to {@code terConsumer}
     * @param <S>         the type of the second argument to {@code terConsumer}
     * @param <T>         the type of the third argument to {@code terConsumer}
     * @return currying of {@code terConsumer}
     * @throws NullPointerException if {@code terConsumer} is null
     * @since 2.0
     */
    public static <F, S, T> BiConsumer<F, T>
    biMiddle3(TerConsumer<F, S, T> terConsumer, S second) {
        Objects.requireNonNull(terConsumer, "The ter-consumer must not be null");
        return (F first, T third) -> terConsumer.accept(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> {}
     * }</pre>
     * to:
     * <pre>{@code
     * (first, second) -> {}
     * }</pre>
     *
     * @param terConsumer the consumer for currying
     * @param third       the value of the third argument to {@code terConsumer}
     * @param <F>         the type of the first argument to {@code terConsumer}
     * @param <S>         the type of the second argument to {@code terConsumer}
     * @param <T>         the type of the third argument to {@code terConsumer}
     * @return currying of {@code terConsumer}
     * @throws NullPointerException if {@code terConsumer} is null
     * @since 2.0
     */
    public static <F, S, T> BiConsumer<F, S>
    biRight3(TerConsumer<F, S, T> terConsumer, T third) {
        Objects.requireNonNull(terConsumer, "The ter-consumer must not be null");
        return (F first, S second) -> terConsumer.accept(first, second, third);
    }

}
