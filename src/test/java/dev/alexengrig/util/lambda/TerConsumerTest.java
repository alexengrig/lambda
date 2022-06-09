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

class TerConsumerTest {

    static String VALUE = "";

    static void ter(String s, Integer i, Float f) {
        VALUE = s + i + f;
    }

    static void terReverse(String s, Integer i, Float f) {
        VALUE += f + i + s;
    }

    @Test
    void should_accept() {
        String s = "prefix-";
        int i = 1;
        float f = 3.13F;
        String expected = "prefix-13.13";
        TerConsumer<String, Integer, Float> lambda = TerConsumerTest::ter;
        lambda.accept(s, i, f);
        assertEquals(expected, VALUE);
    }

    @Test
    void should_andThen() {
        String s = "prefix-";
        int i = 3;
        float f = 1.31F;
        String expected = "prefix-31.314.31prefix-";
        TerConsumer<String, Integer, Float> lambda = TerConsumerTest::ter;
        TerConsumer<String, Integer, Float> newLambda = lambda.andThen(TerConsumerTest::terReverse);
        newLambda.accept(s, i, f);
        assertEquals(expected, VALUE);
    }

    @Test
    void should_andThen_with_npe() {
        TerConsumer<String, Integer, Float> lambda = TerConsumerTest::ter;
        NullPointerException exc = assertThrows(NullPointerException.class, () -> lambda.andThen(null));
        assertEquals("The after-operation must not be null", exc.getMessage());
    }

}