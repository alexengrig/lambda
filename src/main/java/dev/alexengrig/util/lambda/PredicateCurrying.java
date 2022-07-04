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
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Predicate;

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
 * <li>{@code 2} - {@link BiPredicate} as the function;</li>
 * <li>{@code 3} - {@link TerPredicate} as the function;</li>
 * <li>{@code bi} - {@link BiPredicate} as the result.</li>
 * </ul>
 *
 * @author Grig Alex
 * @version 2.0
 * @see java.util.function
 * @since 2.0
 */
public final class PredicateCurrying {

    private PredicateCurrying() {
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first) -> boolean
     * }</pre>
     * to:
     * <pre>{@code
     * () -> boolean
     * }</pre>
     *
     * @param predicate the predicate for currying
     * @param first     the value of the first argument to {@code predicate}
     * @param <F>       the type of the first argument to {@code predicate}
     * @return currying of {@code predicate}
     * @throws NullPointerException if {@code predicate} is null
     * @since 2.0
     */
    public static <F> BooleanSupplier
    all(Predicate<F> predicate, F first) {
        Objects.requireNonNull(predicate, "The predicate must not be null");
        return () -> predicate.test(first);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second) -> boolean
     * }</pre>
     * to:
     * <pre>{@code
     * (first) -> (second) -> boolean
     * }</pre>
     *
     * @param biPredicate the predicate for currying
     * @param <F>         the type of the first argument to {@code biPredicate}
     * @param <S>         the type of the second argument to {@code biPredicate}
     * @return currying of {@code biPredicate}
     * @throws NullPointerException if {@code biPredicate} is null
     * @since 2.0
     */
    public static <F, S> Function<F, Predicate<S>>
    left2(BiPredicate<F, S> biPredicate) {
        Objects.requireNonNull(biPredicate, "The bi-predicate must not be null");
        return (F first) -> (S second) -> biPredicate.test(first, second);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second) -> boolean
     * }</pre>
     * to:
     * <pre>{@code
     * (second) -> boolean
     * }</pre>
     *
     * @param biPredicate the predicate for currying
     * @param first       the value of the first argument to {@code biPredicate}
     * @param <F>         the type of the first argument to {@code biPredicate}
     * @param <S>         the type of the second argument to {@code biPredicate}
     * @return currying of {@code biPredicate}
     * @throws NullPointerException if {@code biPredicate} is null
     * @since 2.0
     */
    public static <F, S> Predicate<S>
    left2(BiPredicate<F, S> biPredicate, F first) {
        Objects.requireNonNull(biPredicate, "The bi-predicate must not be null");
        return (S second) -> biPredicate.test(first, second);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second) -> boolean
     * }</pre>
     * to:
     * <pre>{@code
     * () -> boolean
     * }</pre>
     *
     * @param biPredicate the predicate for currying
     * @param first       the value of the first argument to {@code biPredicate}
     * @param second      the value of the second argument to {@code biPredicate}
     * @param <F>         the type of the first argument to {@code biPredicate}
     * @param <S>         the type of the second argument to {@code biPredicate}
     * @return currying of {@code biPredicate}
     * @throws NullPointerException if {@code biPredicate} is null
     * @since 2.0
     */
    public static <F, S> BooleanSupplier
    all2(BiPredicate<F, S> biPredicate, F first, S second) {
        Objects.requireNonNull(biPredicate, "The bi-predicate must not be null");
        return () -> biPredicate.test(first, second);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> boolean
     * }</pre>
     * to:
     * <pre>{@code
     * (first) -> (second) -> (third) -> boolean
     * }</pre>
     *
     * @param terPredicate the predicate for currying
     * @param <F>          the type of the first argument to {@code terPredicate}
     * @param <S>          the type of the second argument to {@code terPredicate}
     * @param <T>          the type of the third argument to {@code terPredicate}
     * @return currying of {@code terPredicate}
     * @throws NullPointerException if {@code terPredicate} is null
     * @since 2.0
     */
    public static <F, S, T> Function<F, Function<S, Predicate<T>>>
    left3(TerPredicate<F, S, T> terPredicate) {
        Objects.requireNonNull(terPredicate, "The ter-predicate must not be null");
        return (F first) -> (S second) -> (T third) -> terPredicate.test(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> boolean
     * }</pre>
     * to:
     * <pre>{@code
     * (second) -> (third) -> boolean
     * }</pre>
     *
     * @param terPredicate the predicate for currying
     * @param first        the value of the first argument to {@code terPredicate}
     * @param <F>          the type of the first argument to {@code terPredicate}
     * @param <S>          the type of the second argument to {@code terPredicate}
     * @param <T>          the type of the third argument to {@code terPredicate}
     * @return currying of {@code terPredicate}
     * @throws NullPointerException if {@code terPredicate} is null
     * @since 2.0
     */
    public static <F, S, T> Function<S, Predicate<T>>
    left3(TerPredicate<F, S, T> terPredicate, F first) {
        Objects.requireNonNull(terPredicate, "The ter-predicate must not be null");
        return (S second) -> (T third) -> terPredicate.test(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> boolean
     * }</pre>
     * to:
     * <pre>{@code
     * (third) -> boolean
     * }</pre>
     *
     * @param terPredicate the predicate for currying
     * @param first        the value of the first argument to {@code terPredicate}
     * @param second       the value of the second argument to {@code terPredicate}
     * @param <F>          the type of the first argument to {@code terPredicate}
     * @param <S>          the type of the second argument to {@code terPredicate}
     * @param <T>          the type of the third argument to {@code terPredicate}
     * @return currying of {@code terPredicate}
     * @throws NullPointerException if {@code terPredicate} is null
     * @since 2.0
     */
    public static <F, S, T> Predicate<T>
    left3(TerPredicate<F, S, T> terPredicate, F first, S second) {
        Objects.requireNonNull(terPredicate, "The ter-predicate must not be null");
        return (T third) -> terPredicate.test(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> boolean
     * }</pre>
     * to:
     * <pre>{@code
     * () -> boolean
     * }</pre>
     *
     * @param terPredicate the predicate for currying
     * @param first        the value of the first argument to {@code terPredicate}
     * @param second       the value of the second argument to {@code terPredicate}
     * @param third        the value of the third argument to {@code terPredicate}
     * @param <F>          the type of the first argument to {@code terPredicate}
     * @param <S>          the type of the second argument to {@code terPredicate}
     * @param <T>          the type of the third argument to {@code terPredicate}
     * @return currying of {@code terPredicate}
     * @throws NullPointerException if {@code terPredicate} is null
     * @since 2.0
     */
    public static <F, S, T> BooleanSupplier
    all3(TerPredicate<F, S, T> terPredicate, F first, S second, T third) {
        Objects.requireNonNull(terPredicate, "The ter-predicate must not be null");
        return () -> terPredicate.test(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> boolean
     * }</pre>
     * to:
     * <pre>{@code
     * (first) -> (third) -> boolean
     * }</pre>
     *
     * @param terPredicate the predicate for currying
     * @param second       the value of the second argument to {@code terPredicate}
     * @param <F>          the type of the first argument to {@code terPredicate}
     * @param <S>          the type of the second argument to {@code terPredicate}
     * @param <T>          the type of the third argument to {@code terPredicate}
     * @return currying of {@code terPredicate}
     * @throws NullPointerException if {@code terPredicate} is null
     * @since 2.0
     */
    public static <F, S, T> Function<F, Predicate<T>>
    leftMiddle3(TerPredicate<F, S, T> terPredicate, S second) {
        Objects.requireNonNull(terPredicate, "The ter-predicate must not be null");
        return (F first) -> (T third) -> terPredicate.test(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> boolean
     * }</pre>
     * to:
     * <pre>{@code
     * (second) -> boolean
     * }</pre>
     *
     * @param terPredicate the predicate for currying
     * @param first        the value of the first argument to {@code terPredicate}
     * @param third        the value of the third argument to {@code terPredicate}
     * @param <F>          the type of the first argument to {@code terPredicate}
     * @param <S>          the type of the second argument to {@code terPredicate}
     * @param <T>          the type of the third argument to {@code terPredicate}
     * @return currying of {@code terPredicate}
     * @throws NullPointerException if {@code terPredicate} is null
     * @since 2.0
     */
    public static <F, S, T> Predicate<S>
    middle3(TerPredicate<F, S, T> terPredicate, F first, T third) {
        Objects.requireNonNull(terPredicate, "The ter-predicate must not be null");
        return (S second) -> terPredicate.test(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> boolean
     * }</pre>
     * to:
     * <pre>{@code
     * (third) -> (first) -> boolean
     * }</pre>
     *
     * @param terPredicate the predicate for currying
     * @param second       the value of the second argument to {@code terPredicate}
     * @param <F>          the type of the first argument to {@code terPredicate}
     * @param <S>          the type of the second argument to {@code terPredicate}
     * @param <T>          the type of the third argument to {@code terPredicate}
     * @return currying of {@code terPredicate}
     * @throws NullPointerException if {@code terPredicate} is null
     * @since 2.0
     */
    public static <F, S, T> Function<T, Predicate<F>>
    rightMiddle3(TerPredicate<F, S, T> terPredicate, S second) {
        Objects.requireNonNull(terPredicate, "The ter-predicate must not be null");
        return (T third) -> (F first) -> terPredicate.test(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second) -> boolean
     * }</pre>
     * to:
     * <pre>{@code
     * (second) -> (first) -> boolean
     * }</pre>
     *
     * @param biPredicate the predicate for currying
     * @param <F>         the type of the first argument to {@code biPredicate}
     * @param <S>         the type of the second argument to {@code biPredicate}
     * @return currying of {@code biPredicate}
     * @throws NullPointerException if {@code biPredicate} is null
     * @since 2.0
     */
    public static <F, S> Function<S, Predicate<F>>
    right2(BiPredicate<F, S> biPredicate) {
        Objects.requireNonNull(biPredicate, "The bi-predicate must not be null");
        return (S second) -> (F first) -> biPredicate.test(first, second);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second) -> boolean
     * }</pre>
     * to:
     * <pre>{@code
     * (first) -> boolean
     * }</pre>
     *
     * @param biPredicate the predicate for currying
     * @param second      the value of the second argument to {@code biPredicate}
     * @param <F>         the type of the first argument to {@code biPredicate}
     * @param <S>         the type of the second argument to {@code biPredicate}
     * @return currying of {@code biPredicate}
     * @throws NullPointerException if {@code biPredicate} is null
     * @since 2.0
     */
    public static <F, S> Predicate<F>
    right2(BiPredicate<F, S> biPredicate, S second) {
        Objects.requireNonNull(biPredicate, "The bi-predicate must not be null");
        return (F first) -> biPredicate.test(first, second);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> boolean
     * }</pre>
     * to:
     * <pre>{@code
     * (third) -> (second) -> (first) -> boolean
     * }</pre>
     *
     * @param terPredicate the predicate for currying
     * @param <F>          the type of the first argument to {@code terPredicate}
     * @param <S>          the type of the second argument to {@code terPredicate}
     * @param <T>          the type of the third argument to {@code terPredicate}
     * @return currying of {@code terPredicate}
     * @throws NullPointerException if {@code terPredicate} is null
     * @since 2.0
     */
    public static <F, S, T> Function<T, Function<S, Predicate<F>>>
    right3(TerPredicate<F, S, T> terPredicate) {
        Objects.requireNonNull(terPredicate, "The ter-predicate must not be null");
        return (T third) -> (S second) -> (F first) -> terPredicate.test(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> boolean
     * }</pre>
     * to:
     * <pre>{@code
     * (second) -> (first) -> boolean
     * }</pre>
     *
     * @param terPredicate the predicate for currying
     * @param third        the value of the third argument to {@code terPredicate}
     * @param <F>          the type of the first argument to {@code terPredicate}
     * @param <S>          the type of the second argument to {@code terPredicate}
     * @param <T>          the type of the third argument to {@code terPredicate}
     * @return currying of {@code terPredicate}
     * @throws NullPointerException if {@code terPredicate} is null
     * @since 2.0
     */
    public static <F, S, T> Function<S, Predicate<F>>
    right3(TerPredicate<F, S, T> terPredicate, T third) {
        Objects.requireNonNull(terPredicate, "The ter-predicate must not be null");
        return (S second) -> (F first) -> terPredicate.test(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> boolean
     * }</pre>
     * to:
     * <pre>{@code
     * (first) -> boolean
     * }</pre>
     *
     * @param terPredicate the predicate for currying
     * @param second       the value of the second argument to {@code terPredicate}
     * @param third        the value of the third argument to {@code terPredicate}
     * @param <F>          the type of the first argument to {@code terPredicate}
     * @param <S>          the type of the second argument to {@code terPredicate}
     * @param <T>          the type of the third argument to {@code terPredicate}
     * @return currying of {@code terPredicate}
     * @throws NullPointerException if {@code terPredicate} is null
     * @since 2.0
     */
    public static <F, S, T> Predicate<F>
    right3(TerPredicate<F, S, T> terPredicate, S second, T third) {
        Objects.requireNonNull(terPredicate, "The ter-predicate must not be null");
        return (F first) -> terPredicate.test(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> boolean
     * }</pre>
     * to:
     * <pre>{@code
     * (second, third) -> boolean
     * }</pre>
     *
     * @param terPredicate the predicate for currying
     * @param first        the value of the first argument to {@code terPredicate}
     * @param <F>          the type of the first argument to {@code terPredicate}
     * @param <S>          the type of the second argument to {@code terPredicate}
     * @param <T>          the type of the third argument to {@code terPredicate}
     * @return currying of {@code terPredicate}
     * @throws NullPointerException if {@code terPredicate} is null
     * @since 2.0
     */
    public static <F, S, T> BiPredicate<S, T>
    biLeft3(TerPredicate<F, S, T> terPredicate, F first) {
        Objects.requireNonNull(terPredicate, "The ter-predicate must not be null");
        return (S second, T third) -> terPredicate.test(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> boolean
     * }</pre>
     * to:
     * <pre>{@code
     * (first, third) -> boolean
     * }</pre>
     *
     * @param terPredicate the predicate for currying
     * @param second       the value of the second argument to {@code terPredicate}
     * @param <F>          the type of the first argument to {@code terPredicate}
     * @param <S>          the type of the second argument to {@code terPredicate}
     * @param <T>          the type of the third argument to {@code terPredicate}
     * @return currying of {@code terPredicate}
     * @throws NullPointerException if {@code terPredicate} is null
     * @since 2.0
     */
    public static <F, S, T> BiPredicate<F, T>
    biMiddle3(TerPredicate<F, S, T> terPredicate, S second) {
        Objects.requireNonNull(terPredicate, "The ter-predicate must not be null");
        return (F first, T third) -> terPredicate.test(first, second, third);
    }

    /**
     * Currying from:
     * <pre>{@code
     * (first, second, third) -> boolean
     * }</pre>
     * to:
     * <pre>{@code
     * (first, second) -> boolean
     * }</pre>
     *
     * @param terPredicate the predicate for currying
     * @param third        the value of the third argument to {@code terPredicate}
     * @param <F>          the type of the first argument to {@code terPredicate}
     * @param <S>          the type of the second argument to {@code terPredicate}
     * @param <T>          the type of the third argument to {@code terPredicate}
     * @return currying of {@code terPredicate}
     * @throws NullPointerException if {@code terPredicate} is null
     * @since 2.0
     */
    public static <F, S, T> BiPredicate<F, S>
    biRight3(TerPredicate<F, S, T> terPredicate, T third) {
        Objects.requireNonNull(terPredicate, "The ter-predicate must not be null");
        return (F first, S second) -> terPredicate.test(first, second, third);
    }

}
