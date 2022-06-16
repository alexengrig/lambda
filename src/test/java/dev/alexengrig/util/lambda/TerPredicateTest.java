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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TerPredicateTest {

    static boolean ter(String s, Integer i, Float f) {
        return s.length() > i && i > f;
    }

    static boolean terReverse(String s, Integer i, Float f) {
        return s.length() < i && i < f;
    }

    @Test
    void should_test() {
        String s = "prefix-";
        int i = 3;
        float f = 1.1F;
        assertTrue(ter(s, i, f));
        TerPredicate<String, Integer, Float> lambda = TerPredicateTest::ter;
        assertTrue(lambda.test(s, i, f));
    }

    @Test
    void should_and() {
        String s = "prefix-";
        int i = 3;
        float f = 1.1F;
        TerPredicate<String, Integer, Float> lambda = TerPredicateTest::ter;
        TerPredicate<String, Integer, Float> newLambda = lambda.and(TerPredicateTest::terReverse);
        assertFalse(newLambda.test(s, i, f));
    }

    @Test
    void should_and_with_npe() {
        TerPredicate<String, Integer, Float> lambda = TerPredicateTest::ter;
        NullPointerException exc = assertThrows(NullPointerException.class, () -> lambda.and(null));
        assertEquals("The other-predicate must not be null", exc.getMessage());
    }

    @Test
    void should_negate() {
        String s = "prefix-";
        int i = 3;
        float f = 1.1F;
        TerPredicate<String, Integer, Float> lambda = TerPredicateTest::ter;
        assertTrue(lambda.test(s, i, f));
        TerPredicate<String, Integer, Float> newLambda = lambda.negate();
        assertFalse(newLambda.test(s, i, f));
    }


    @Test
    void should_or() {
        String s = "prefix-";
        int i = 3;
        float f = 1.1F;
        TerPredicate<String, Integer, Float> lambda = TerPredicateTest::ter;
        TerPredicate<String, Integer, Float> newLambda = lambda.or(TerPredicateTest::terReverse);
        assertTrue(newLambda.test(s, i, f));
    }

    @Test
    void should_or_with_npe() {
        TerPredicate<String, Integer, Float> lambda = TerPredicateTest::ter;
        NullPointerException exc = assertThrows(NullPointerException.class, () -> lambda.or(null));
        assertEquals("The other-predicate must not be null", exc.getMessage());
    }

}