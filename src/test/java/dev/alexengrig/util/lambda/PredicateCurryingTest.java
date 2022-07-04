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

class PredicateCurryingTest {

    static String ONE = "1";
    static String TWO = "2";
    static String THREE = "3";

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
        var lambda = PredicateCurrying.left2(this::odd2);
        var lambda1 = lambda.apply(ONE);
        boolean result = lambda1.test(TWO);
        Assertions.assertTrue(result, "12");
    }

    @Test
    void testLeft2WithArg() {
        var lambda = PredicateCurrying.left2(this::odd2, ONE);
        boolean result = lambda.test(TWO);
        Assertions.assertTrue(result, "12");
    }

    @Test
    void testLeft3() {
        var lambda = PredicateCurrying.left3(this::odd3);
        var lambda1 = lambda.apply(ONE);
        var lambda2 = lambda1.apply(TWO);
        boolean result = lambda2.test(THREE);
        Assertions.assertFalse(result, "123");
    }

    @Test
    void testLeft3WithArg() {
        var lambda = PredicateCurrying.left3(this::odd3, ONE);
        var lambda1 = lambda.apply(TWO);
        boolean result = lambda1.test(THREE);
        Assertions.assertFalse(result, "123");
    }

    @Test
    void testLeft3WithArgs() {
        var lambda = PredicateCurrying.left3(this::odd3, ONE, TWO);
        boolean result = lambda.test(THREE);
        Assertions.assertFalse(result, "123");
    }

    @Test
    void testLeftMiddle3() {
        var lambda = PredicateCurrying.leftMiddle3(this::odd3, ONE);
        var lambda1 = lambda.apply(TWO);
        boolean result = lambda1.test(THREE);
        Assertions.assertFalse(result, "213");
    }

    @Test
    void testMiddle3() {
        var lambda = PredicateCurrying.middle3(this::odd3, ONE, TWO);
        boolean result = lambda.test(THREE);
        Assertions.assertFalse(result, "132");
    }

    @Test
    void testRightMiddle3() {
        var lambda = PredicateCurrying.rightMiddle3(this::odd3, ONE);
        var lambda1 = lambda.apply(TWO);
        boolean result = lambda1.test(THREE);
        Assertions.assertFalse(result, "312");
    }

    @Test
    void testRight2() {
        var lambda = PredicateCurrying.right2(this::odd2);
        var lambda1 = lambda.apply(ONE);
        boolean result = lambda1.test(TWO);
        Assertions.assertTrue(result, "21");
    }

    @Test
    void testRight2WithArg() {
        var lambda = PredicateCurrying.right2(this::odd2, ONE);
        boolean result = lambda.test(TWO);
        Assertions.assertTrue(result, "21");
    }

    @Test
    void testRight3() {
        var lambda = PredicateCurrying.right3(this::odd3);
        var lambda1 = lambda.apply(ONE);
        var lambda2 = lambda1.apply(TWO);
        boolean result = lambda2.test(THREE);
        Assertions.assertFalse(result, "321");
    }

    @Test
    void testRight3WithArg() {
        var lambda = PredicateCurrying.right3(this::odd3, ONE);
        var lambda1 = lambda.apply(TWO);
        boolean result = lambda1.test(THREE);
        Assertions.assertFalse(result, "321");
    }

    @Test
    void testRight3WithArgs() {
        var lambda = PredicateCurrying.right3(this::odd3, ONE, TWO);
        boolean result = lambda.test(THREE);
        Assertions.assertFalse(result, "312");
    }

    @Test
    void testBiLeft3() {
        var lambda = PredicateCurrying.biLeft3(this::odd3, ONE);
        boolean result = lambda.test(TWO, THREE);
        Assertions.assertFalse(result, "123");
    }

    @Test
    void testBiMiddle3() {
        var lambda = PredicateCurrying.biMiddle3(this::odd3, ONE);
        boolean result = lambda.test(TWO, THREE);
        Assertions.assertFalse(result, "213");
    }

    @Test
    void testBiRight3() {
        var lambda = PredicateCurrying.biRight3(this::odd3, ONE);
        boolean result = lambda.test(TWO, THREE);
        Assertions.assertFalse(result, "231");
    }

    @Test
    void testAll() {
        var lambda = PredicateCurrying.all(this::odd, ONE);
        boolean result = lambda.getAsBoolean();
        Assertions.assertTrue(result, "1");
    }

    @Test
    void testAll2() {
        var lambda = PredicateCurrying.all2(this::odd2, ONE, TWO);
        boolean result = lambda.getAsBoolean();
        Assertions.assertTrue(result, "12");
    }

    @Test
    void testAll3() {
        var lambda = PredicateCurrying.all3(this::odd3, ONE, TWO, THREE);
        boolean result = lambda.getAsBoolean();
        Assertions.assertFalse(result, "123");
    }

}