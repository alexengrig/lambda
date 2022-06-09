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
 * Represents an operation that accepts three input arguments and returns no result.
 * This is the three-arity specialization of {@link java.util.function.Consumer}.
 * Unlike most other functional interfaces, {@code TerConsumer} is expected
 * to operate via side-effects.
 *
 * <p>This is a
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html">functional interface</a>
 * whose functional method is {@link #accept(Object, Object, Object)}.
 *
 * @param <F> the type of the first argument to the operation
 * @param <S> the type of the second argument to the operation
 * @param <T> the type of the third argument to the operation
 * @author Grig Alex
 * @version 1.0
 * @see java.util.function.BiConsumer
 * @see java.util.function.Consumer
 * @since 1.0
 */
@FunctionalInterface
public interface TerConsumer<F, S, T> {

    /**
     * Performs this operation on the given arguments.
     *
     * @param first  the first input argument
     * @param second the second input argument
     * @param third  the second input argument
     */
    void accept(F first, S second, T third);

    /**
     * Returns a composed {@code TerConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation.
     * If performing either operation throws an exception,
     * it is relayed to the caller of the composed operation.
     * If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code TerConsumer} that performs in sequence this
     * operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default TerConsumer<F, S, T> andThen(TerConsumer<? super F, ? super S, ? super T> after) {
        Objects.requireNonNull(after, "The after-operation must not be null");
        return (F first, S second, T third) -> {
            accept(first, second, third);
            after.accept(first, second, third);
        };
    }

}
