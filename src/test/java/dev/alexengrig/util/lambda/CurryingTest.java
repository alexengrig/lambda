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
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CurryingTest {

    static String ONE = "1";
    static String TWO = "2";
    static String THREE = "3";

    @Nested
    class Functions {

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
            var lambda = Currying.left2(this::concat2);
            var lambda1 = lambda.apply(ONE);
            String result = lambda1.apply(TWO);
            Assertions.assertEquals("12", result);
        }

        @Test
        void testLeft2WithArg() {
            var lambda = Currying.left2(this::concat2, ONE);
            String result = lambda.apply(TWO);
            Assertions.assertEquals("12", result);
        }

        @Test
        void testLeft3() {
            var lambda = Currying.left3(this::concat3);
            var lambda1 = lambda.apply(ONE);
            var lambda2 = lambda1.apply(TWO);
            String result = lambda2.apply(THREE);
            Assertions.assertEquals("123", result);
        }

        @Test
        void testLeft3WithArg() {
            var lambda = Currying.left3(this::concat3, ONE);
            var lambda1 = lambda.apply(TWO);
            String result = lambda1.apply(THREE);
            Assertions.assertEquals("123", result);
        }

        @Test
        void testLeft3WithArgs() {
            var lambda = Currying.left3(this::concat3, ONE, TWO);
            String result = lambda.apply(THREE);
            Assertions.assertEquals("123", result);
        }

        @Test
        void testLeftMiddle3() {
            var lambda = Currying.leftMiddle3(this::concat3, ONE);
            var lambda1 = lambda.apply(TWO);
            String result = lambda1.apply(THREE);
            Assertions.assertEquals("213", result);
        }

        @Test
        void testMiddle3() {
            var lambda = Currying.middle3(this::concat3, ONE, TWO);
            String result = lambda.apply(THREE);
            Assertions.assertEquals("132", result);
        }

        @Test
        void testRightMiddle3() {
            var lambda = Currying.rightMiddle3(this::concat3, ONE);
            var lambda1 = lambda.apply(TWO);
            String result = lambda1.apply(THREE);
            Assertions.assertEquals("312", result);
        }

        @Test
        void testRight2() {
            var lambda = Currying.right2(this::concat2);
            var lambda1 = lambda.apply(ONE);
            String result = lambda1.apply(TWO);
            Assertions.assertEquals("21", result);
        }

        @Test
        void testRight2WithArg() {
            var lambda = Currying.right2(this::concat2, ONE);
            String result = lambda.apply(TWO);
            Assertions.assertEquals("21", result);
        }

        @Test
        void testRight3() {
            var lambda = Currying.right3(this::concat3);
            var lambda1 = lambda.apply(ONE);
            var lambda2 = lambda1.apply(TWO);
            String result = lambda2.apply(THREE);
            Assertions.assertEquals("321", result);
        }

        @Test
        void testRight3WithArg() {
            var lambda = Currying.right3(this::concat3, ONE);
            var lambda1 = lambda.apply(TWO);
            String result = lambda1.apply(THREE);
            Assertions.assertEquals("321", result);
        }

        @Test
        void testRight3WithArgs() {
            var lambda = Currying.right3(this::concat3, ONE, TWO);
            String result = lambda.apply(THREE);
            Assertions.assertEquals("312", result);
        }

        @Test
        void testBiLeft3() {
            var lambda = Currying.biLeft3(this::concat3, ONE);
            String result = lambda.apply(TWO, THREE);
            Assertions.assertEquals("123", result);
        }

        @Test
        void testBiMiddle3() {
            var lambda = Currying.biMiddle3(this::concat3, ONE);
            String result = lambda.apply(TWO, THREE);
            Assertions.assertEquals("213", result);
        }

        @Test
        void testBiRight3() {
            var lambda = Currying.biRight3(this::concat3, ONE);
            String result = lambda.apply(TWO, THREE);
            Assertions.assertEquals("231", result);
        }

        @Test
        void testAll() {
            var lambda = Currying.all(this::concat, ONE);
            String result = lambda.get();
            Assertions.assertEquals("1", result);
        }

        @Test
        void testAll2() {
            var lambda = Currying.all2(this::concat2, ONE, TWO);
            String result = lambda.get();
            Assertions.assertEquals("12", result);
        }

        @Test
        void testAll3() {
            var lambda = Currying.all3(this::concat3, ONE, TWO, THREE);
            String result = lambda.get();
            Assertions.assertEquals("123", result);
        }

    }

    @Nested
    class Consumers {

        String store;

        void store(String first) {
            store = first;
        }

        void store2(String first, String second) {
            store = first + second;
        }

        void store3(String first, String second, String third) {
            store = first + second + third;
        }

        @Test
        void testLeft2() {
            var lambda = Currying.left2(this::store2);
            var lambda1 = lambda.apply(ONE);
            lambda1.accept(TWO);
            Assertions.assertEquals("12", store);
        }

        @Test
        void testLeft2WithArg() {
            var lambda = Currying.left2(this::store2, ONE);
            lambda.accept(TWO);
            Assertions.assertEquals("12", store);
        }

        @Test
        void testLeft3() {
            var lambda = Currying.left3(this::store3);
            var lambda1 = lambda.apply(ONE);
            var lambda2 = lambda1.apply(TWO);
            lambda2.accept(THREE);
            Assertions.assertEquals("123", store);
        }

        @Test
        void testLeft3WithArg() {
            var lambda = Currying.left3(this::store3, ONE);
            var lambda1 = lambda.apply(TWO);
            lambda1.accept(THREE);
            Assertions.assertEquals("123", store);
        }

        @Test
        void testLeft3WithArgs() {
            var lambda = Currying.left3(this::store3, ONE, TWO);
            lambda.accept(THREE);
            Assertions.assertEquals("123", store);
        }

        @Test
        void testLeftMiddle3() {
            var lambda = Currying.leftMiddle3(this::store3, ONE);
            var lambda1 = lambda.apply(TWO);
            lambda1.accept(THREE);
            Assertions.assertEquals("213", store);
        }

        @Test
        void testMiddle3() {
            var lambda = Currying.middle3(this::store3, ONE, TWO);
            lambda.accept(THREE);
            Assertions.assertEquals("132", store);
        }

        @Test
        void testRightMiddle3() {
            var lambda = Currying.rightMiddle3(this::store3, ONE);
            var lambda1 = lambda.apply(TWO);
            lambda1.accept(THREE);
            Assertions.assertEquals("312", store);
        }

        @Test
        void testRight2() {
            var lambda = Currying.right2(this::store2);
            var lambda1 = lambda.apply(ONE);
            lambda1.accept(TWO);
            Assertions.assertEquals("21", store);
        }

        @Test
        void testRight2WithArg() {
            var lambda = Currying.right2(this::store2, ONE);
            lambda.accept(TWO);
            Assertions.assertEquals("21", store);
        }

        @Test
        void testRight3() {
            var lambda = Currying.right3(this::store3);
            var lambda1 = lambda.apply(ONE);
            var lambda2 = lambda1.apply(TWO);
            lambda2.accept(THREE);
            Assertions.assertEquals("321", store);
        }

        @Test
        void testRight3WithArg() {
            var lambda = Currying.right3(this::store3, ONE);
            var lambda1 = lambda.apply(TWO);
            lambda1.accept(THREE);
            Assertions.assertEquals("321", store);
        }

        @Test
        void testRight3WithArgs() {
            var lambda = Currying.right3(this::store3, ONE, TWO);
            lambda.accept(THREE);
            Assertions.assertEquals("312", store);
        }

        @Test
        void testBiLeft3() {
            var lambda = Currying.biLeft3(this::store3, ONE);
            lambda.accept(TWO, THREE);
            Assertions.assertEquals("123", store);
        }

        @Test
        void testBiMiddle3() {
            var lambda = Currying.biMiddle3(this::store3, ONE);
            lambda.accept(TWO, THREE);
            Assertions.assertEquals("213", store);
        }

        @Test
        void testBiRight3() {
            var lambda = Currying.biRight3(this::store3, ONE);
            lambda.accept(TWO, THREE);
            Assertions.assertEquals("231", store);
        }

        @Test
        void testAll() {
            var lambda = Currying.all(this::store, ONE);
            lambda.run();
            Assertions.assertEquals("1", store);
        }

        @Test
        void testAll2() {
            var lambda = Currying.all2(this::store2, ONE, TWO);
            lambda.run();
            Assertions.assertEquals("12", store);
        }

        @Test
        void testAll3() {
            var lambda = Currying.all3(this::store3, ONE, TWO, THREE);
            lambda.run();
            Assertions.assertEquals("123", store);
        }

    }

    @Nested
    class Predicates {

        boolean odd(String first) {
            return (Integer.parseInt(first) & 1) != 0;
        }

        boolean odd2(String first, String second) {
            return ((Integer.parseInt(first) + Integer.parseInt(second)) & 1) != 0;
        }

        boolean odd3(String first, String second, String third) {
            return ((Integer.parseInt(first) + Integer.parseInt(second) + Integer.parseInt(third)) & 1) != 0;
        }

        @Test
        void testLeft2() {
            var lambda = Currying.left2(this::odd2);
            var lambda1 = lambda.apply(ONE);
            boolean result = lambda1.test(TWO);
            Assertions.assertTrue(result, "12");
        }

        @Test
        void testLeft2WithArg() {
            var lambda = Currying.left2(this::odd2, ONE);
            boolean result = lambda.test(TWO);
            Assertions.assertTrue(result, "12");
        }

        @Test
        void testLeft3() {
            var lambda = Currying.left3(this::odd3);
            var lambda1 = lambda.apply(ONE);
            var lambda2 = lambda1.apply(TWO);
            boolean result = lambda2.test(THREE);
            Assertions.assertFalse(result, "123");
        }

        @Test
        void testLeft3WithArg() {
            var lambda = Currying.left3(this::odd3, ONE);
            var lambda1 = lambda.apply(TWO);
            boolean result = lambda1.test(THREE);
            Assertions.assertFalse(result, "123");
        }

        @Test
        void testLeft3WithArgs() {
            var lambda = Currying.left3(this::odd3, ONE, TWO);
            boolean result = lambda.test(THREE);
            Assertions.assertFalse(result, "123");
        }

        @Test
        void testLeftMiddle3() {
            var lambda = Currying.leftMiddle3(this::odd3, ONE);
            var lambda1 = lambda.apply(TWO);
            boolean result = lambda1.test(THREE);
            Assertions.assertFalse(result, "213");
        }

        @Test
        void testMiddle3() {
            var lambda = Currying.middle3(this::odd3, ONE, TWO);
            boolean result = lambda.test(THREE);
            Assertions.assertFalse(result, "132");
        }

        @Test
        void testRightMiddle3() {
            var lambda = Currying.rightMiddle3(this::odd3, ONE);
            var lambda1 = lambda.apply(TWO);
            boolean result = lambda1.test(THREE);
            Assertions.assertFalse(result, "312");
        }

        @Test
        void testRight2() {
            var lambda = Currying.right2(this::odd2);
            var lambda1 = lambda.apply(ONE);
            boolean result = lambda1.test(TWO);
            Assertions.assertTrue(result, "21");
        }

        @Test
        void testRight2WithArg() {
            var lambda = Currying.right2(this::odd2, ONE);
            boolean result = lambda.test(TWO);
            Assertions.assertTrue(result, "21");
        }

        @Test
        void testRight3() {
            var lambda = Currying.right3(this::odd3);
            var lambda1 = lambda.apply(ONE);
            var lambda2 = lambda1.apply(TWO);
            boolean result = lambda2.test(THREE);
            Assertions.assertFalse(result, "321");
        }

        @Test
        void testRight3WithArg() {
            var lambda = Currying.right3(this::odd3, ONE);
            var lambda1 = lambda.apply(TWO);
            boolean result = lambda1.test(THREE);
            Assertions.assertFalse(result, "321");
        }

        @Test
        void testRight3WithArgs() {
            var lambda = Currying.right3(this::odd3, ONE, TWO);
            boolean result = lambda.test(THREE);
            Assertions.assertFalse(result, "312");
        }

        @Test
        void testBiLeft3() {
            var lambda = Currying.biLeft3(this::odd3, ONE);
            boolean result = lambda.test(TWO, THREE);
            Assertions.assertFalse(result, "123");
        }

        @Test
        void testBiMiddle3() {
            var lambda = Currying.biMiddle3(this::odd3, ONE);
            boolean result = lambda.test(TWO, THREE);
            Assertions.assertFalse(result, "213");
        }

        @Test
        void testBiRight3() {
            var lambda = Currying.biRight3(this::odd3, ONE);
            boolean result = lambda.test(TWO, THREE);
            Assertions.assertFalse(result, "231");
        }

        @Test
        void testAll() {
            var lambda = Currying.all(this::odd, ONE);
            boolean result = lambda.getAsBoolean();
            Assertions.assertTrue(result, "1");
        }

        @Test
        void testAll2() {
            var lambda = Currying.all2(this::odd2, ONE, TWO);
            boolean result = lambda.getAsBoolean();
            Assertions.assertTrue(result, "12");
        }

        @Test
        void testAll3() {
            var lambda = Currying.all3(this::odd3, ONE, TWO, THREE);
            boolean result = lambda.getAsBoolean();
            Assertions.assertFalse(result, "123");
        }

    }

}