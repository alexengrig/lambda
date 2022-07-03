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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

class CurryingTest {

    static String ONE = "1";
    static String TWO = "2";
    static String THREE = "3";

    String concat(String first) {
        return first;
    }

    String concat2(String first, String second) {
        return first + second;
    }

    String concat3(String first, String second, String third) {
        return first + second + third;
    }

    @Test
    void testLeft2() {
        Function<String, Function<String, String>> lambda = Currying.left2(this::concat2);
        Function<String, String> lambda1 = lambda.apply(ONE);
        String result = lambda1.apply(TWO);
        Assertions.assertEquals("12", result);
    }

    @Test
    void testLeft2WithArg() {
        Function<String, String> lambda = Currying.left2(this::concat2, ONE);
        String result = lambda.apply(TWO);
        Assertions.assertEquals("12", result);
    }

    @Test
    void testLeft3() {
        Function<String, Function<String, Function<String, String>>> lambda = Currying.left3(this::concat3);
        Function<String, Function<String, String>> lambda1 = lambda.apply(ONE);
        Function<String, String> lambda2 = lambda1.apply(TWO);
        String result = lambda2.apply(THREE);
        Assertions.assertEquals("123", result);
    }

    @Test
    void testLeft3WithArg() {
        Function<String, Function<String, String>> lambda = Currying.left3(this::concat3, ONE);
        Function<String, String> lambda1 = lambda.apply(TWO);
        String result = lambda1.apply(THREE);
        Assertions.assertEquals("123", result);
    }

    @Test
    void testLeft3WithArgs() {
        Function<String, String> lambda = Currying.left3(this::concat3, ONE, TWO);
        String result = lambda.apply(THREE);
        Assertions.assertEquals("123", result);
    }

    @Test
    void testLeftMiddle3() {
        Function<String, Function<String, String>> lambda = Currying.leftMiddle3(this::concat3, ONE);
        Function<String, String> lambda1 = lambda.apply(TWO);
        String result = lambda1.apply(THREE);
        Assertions.assertEquals("213", result);
    }

    @Test
    void testMiddle3() {
        Function<String, String> lambda = Currying.middle3(this::concat3, ONE, TWO);
        String result = lambda.apply(THREE);
        Assertions.assertEquals("132", result);
    }

    @Test
    void testRightMiddle3() {
        Function<String, Function<String, String>> lambda = Currying.rightMiddle3(this::concat3, ONE);
        Function<String, String> lambda1 = lambda.apply(TWO);
        String result = lambda1.apply(THREE);
        Assertions.assertEquals("312", result);
    }

    @Test
    void testRight2() {
        Function<String, Function<String, String>> lambda = Currying.right2(this::concat2);
        Function<String, String> lambda1 = lambda.apply(ONE);
        String result = lambda1.apply(TWO);
        Assertions.assertEquals("21", result);
    }

    @Test
    void testRight2WithArg() {
        Function<String, String> lambda = Currying.right2(this::concat2, ONE);
        String result = lambda.apply(TWO);
        Assertions.assertEquals("21", result);
    }

    @Test
    void testRight3() {
        Function<String, Function<String, Function<String, String>>> lambda = Currying.right3(this::concat3);
        Function<String, Function<String, String>> lambda1 = lambda.apply(ONE);
        Function<String, String> lambda2 = lambda1.apply(TWO);
        String result = lambda2.apply(THREE);
        Assertions.assertEquals("321", result);
    }

    @Test
    void testRight3WithArg() {
        Function<String, Function<String, String>> lambda = Currying.right3(this::concat3, ONE);
        Function<String, String> lambda1 = lambda.apply(TWO);
        String result = lambda1.apply(THREE);
        Assertions.assertEquals("321", result);
    }

    @Test
    void testRight3WithArgs() {
        Function<String, String> lambda = Currying.right3(this::concat3, ONE, TWO);
        String result = lambda.apply(THREE);
        Assertions.assertEquals("312", result);
    }

    @Test
    void testBiLeft3() {
        BiFunction<String, String, String> lambda = Currying.biLeft3(this::concat3, ONE);
        String result = lambda.apply(TWO, THREE);
        Assertions.assertEquals("123", result);
    }

    @Test
    void testBiMiddle3() {
        BiFunction<String, String, String> lambda = Currying.biMiddle3(this::concat3, ONE);
        String result = lambda.apply(TWO, THREE);
        Assertions.assertEquals("213", result);
    }

    @Test
    void testBiRight3() {
        BiFunction<String, String, String> lambda = Currying.biRight3(this::concat3, ONE);
        String result = lambda.apply(TWO, THREE);
        Assertions.assertEquals("231", result);
    }

    @Test
    void testAll() {
        Supplier<String> lambda = Currying.all(this::concat, ONE);
        String result = lambda.get();
        Assertions.assertEquals("1", result);
    }

    @Test
    void testAll2() {
        Supplier<String> lambda = Currying.all2(this::concat2, ONE, TWO);
        String result = lambda.get();
        Assertions.assertEquals("12", result);
    }

    @Test
    void testAll3() {
        Supplier<String> lambda = Currying.all3(this::concat3, ONE, TWO, THREE);
        String result = lambda.get();
        Assertions.assertEquals("123", result);
    }

}