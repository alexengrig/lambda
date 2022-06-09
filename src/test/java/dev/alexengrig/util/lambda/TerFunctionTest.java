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
import static org.junit.jupiter.api.Assertions.assertThrows;

class TerFunctionTest {

    static String ter(String s, Integer i, Float f) {
        return s + i + f;
    }

    @Test
    void should_apply() {
        String s = "prefix-";
        int i = 1;
        float f = 3.13F;
        String expected = "prefix-13.13";
        assertEquals(expected, ter(s, i, f));
        TerFunction<String, Integer, Float, String> lambda = TerFunctionTest::ter;
        assertEquals(expected, lambda.apply(s, i, f));
    }

    @Test
    void should_andThen() {
        String s = "prefix-";
        int i = 3;
        float f = 1.31F;
        int expected = 12;
        TerFunction<String, Integer, Float, String> lambda = TerFunctionTest::ter;
        TerFunction<String, Integer, Float, Integer> newLambda = lambda.andThen(String::length);
        assertEquals(expected, newLambda.apply(s, i, f));
    }

    @Test
    void should_andThen_with_npe() {
        TerFunction<String, Integer, Float, String> lambda = TerFunctionTest::ter;
        NullPointerException exc = assertThrows(NullPointerException.class, () -> lambda.andThen(null));
        assertEquals("The after-function must not be null", exc.getMessage());
    }

}