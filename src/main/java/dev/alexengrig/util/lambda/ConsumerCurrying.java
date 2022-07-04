/*
 * Copyright 2022 Alexengrig Dev.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.alexengrig.util.lambda;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

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
 * <li>{@code 2} - {@link BiConsumer} as the function;</li>
 * <li>{@code 3} - {@link TerConsumer} as the function;</li>
 * <li>{@code bi} - {@link BiConsumer} as the result.</li>
 * </ul>
 *
 * @author Grig Alex
 * @version 2.0
 * @see java.util.function
 * @since 2.0
 */
public final class ConsumerCurrying {

    private ConsumerCurrying() {
    }

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
