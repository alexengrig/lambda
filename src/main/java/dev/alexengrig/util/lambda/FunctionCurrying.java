package dev.alexengrig.util.lambda;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
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
 * <li>{@code 2} - {@link BiFunction} as the function;</li>
 * <li>{@code 3} - {@link TerFunction} as the function;</li>
 * <li>{@code bi} - {@link BiFunction} as the result.</li>
 * </ul>
 *
 * @author Grig Alex
 * @version 2.0
 * @see java.util.function
 * @since 1.0
 */
public final class FunctionCurrying {

    private FunctionCurrying() {
    }

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
     * @param <S>        the type of the second argument to {@code biFunction}
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
     * @param <S>         the type of the second argument to {@code terFunction}
     * @param <T>         the type of the third argument to {@code terFunction}
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

}
