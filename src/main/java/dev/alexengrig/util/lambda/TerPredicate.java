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

/**
 * Represents a predicate (boolean-valued function) of three arguments.
 * This is the three-arity specialization of {@link java.util.function.Predicate}.
 *
 * <p>This is a
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html">functional interface</a>
 * whose functional method is {@link #test(Object, Object, Object)}.
 *
 * @param <F> the type of the first argument to the predicate
 * @param <S> the type of the second argument the predicate
 * @param <T> the type of the third argument the predicate
 * @author Grig Alex
 * @version 1.0
 * @see java.util.function.BiPredicate
 * @see java.util.function.Predicate
 * @since 1.0
 */
@FunctionalInterface
public interface TerPredicate<F, S, T> {

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param first  the first input argument
     * @param second the second input argument
     * @param third  the third input argument
     * @return {@code true} if the input arguments match the predicate,
     * otherwise {@code false}
     */
    boolean test(F first, S second, T third);

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * AND of this predicate and another.
     * When evaluating the composed predicate,
     * if this predicate is {@code false},
     * then the {@code other} predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed to the caller;
     * if evaluation of this predicate throws an exception,
     * the {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ANDed with this predicate
     * @return a composed predicate that represents the short-circuiting logical
     * AND of this predicate and the {@code other} predicate
     * @throws NullPointerException if other is null
     */
    default TerPredicate<F, S, T> and(TerPredicate<? super F, ? super S, ? super T> other) {
        Objects.requireNonNull(other, "The other-predicate must not be null");
        return (F first, S second, T third) -> test(first, second, third) && other.test(first, second, third);
    }

    /**
     * Returns a predicate that represents the logical negation of this
     * predicate.
     *
     * @return a predicate that represents the logical negation of this
     * predicate
     */
    default TerPredicate<F, S, T> negate() {
        return (F first, S second, T third) -> !test(first, second, third);
    }

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * OR of this predicate and another.
     * When evaluating the composed predicate,
     * if this predicate is {@code true},
     * then the {@code other} predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed to the caller;
     * if evaluation of this predicate throws an exception,
     * the {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ORed with this predicate
     * @return a composed predicate that represents the short-circuiting logical
     * OR of this predicate and the {@code other} predicate
     * @throws NullPointerException if other is null
     */
    default TerPredicate<F, S, T> or(TerPredicate<? super F, ? super S, ? super T> other) {
        Objects.requireNonNull(other, "The other-predicate must not be null");
        return (F first, S second, T third) -> test(first, second, third) || other.test(first, second, third);
    }

}

