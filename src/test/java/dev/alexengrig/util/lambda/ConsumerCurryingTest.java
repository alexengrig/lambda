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

class ConsumerCurryingTest {

    static String ONE = "1";
    static String TWO = "2";
    static String THREE = "3";

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
        var lambda = ConsumerCurrying.left2(this::store2);
        var lambda1 = lambda.apply(ONE);
        lambda1.accept(TWO);
        Assertions.assertEquals("12", store);
    }

    @Test
    void testLeft2WithArg() {
        var lambda = ConsumerCurrying.left2(this::store2, ONE);
        lambda.accept(TWO);
        Assertions.assertEquals("12", store);
    }

    @Test
    void testLeft3() {
        var lambda = ConsumerCurrying.left3(this::store3);
        var lambda1 = lambda.apply(ONE);
        var lambda2 = lambda1.apply(TWO);
        lambda2.accept(THREE);
        Assertions.assertEquals("123", store);
    }

    @Test
    void testLeft3WithArg() {
        var lambda = ConsumerCurrying.left3(this::store3, ONE);
        var lambda1 = lambda.apply(TWO);
        lambda1.accept(THREE);
        Assertions.assertEquals("123", store);
    }

    @Test
    void testLeft3WithArgs() {
        var lambda = ConsumerCurrying.left3(this::store3, ONE, TWO);
        lambda.accept(THREE);
        Assertions.assertEquals("123", store);
    }

    @Test
    void testLeftMiddle3() {
        var lambda = ConsumerCurrying.leftMiddle3(this::store3, ONE);
        var lambda1 = lambda.apply(TWO);
        lambda1.accept(THREE);
        Assertions.assertEquals("213", store);
    }

    @Test
    void testMiddle3() {
        var lambda = ConsumerCurrying.middle3(this::store3, ONE, TWO);
        lambda.accept(THREE);
        Assertions.assertEquals("132", store);
    }

    @Test
    void testRightMiddle3() {
        var lambda = ConsumerCurrying.rightMiddle3(this::store3, ONE);
        var lambda1 = lambda.apply(TWO);
        lambda1.accept(THREE);
        Assertions.assertEquals("312", store);
    }

    @Test
    void testRight2() {
        var lambda = ConsumerCurrying.right2(this::store2);
        var lambda1 = lambda.apply(ONE);
        lambda1.accept(TWO);
        Assertions.assertEquals("21", store);
    }

    @Test
    void testRight2WithArg() {
        var lambda = ConsumerCurrying.right2(this::store2, ONE);
        lambda.accept(TWO);
        Assertions.assertEquals("21", store);
    }

    @Test
    void testRight3() {
        var lambda = ConsumerCurrying.right3(this::store3);
        var lambda1 = lambda.apply(ONE);
        var lambda2 = lambda1.apply(TWO);
        lambda2.accept(THREE);
        Assertions.assertEquals("321", store);
    }

    @Test
    void testRight3WithArg() {
        var lambda = ConsumerCurrying.right3(this::store3, ONE);
        var lambda1 = lambda.apply(TWO);
        lambda1.accept(THREE);
        Assertions.assertEquals("321", store);
    }

    @Test
    void testRight3WithArgs() {
        var lambda = ConsumerCurrying.right3(this::store3, ONE, TWO);
        lambda.accept(THREE);
        Assertions.assertEquals("312", store);
    }

    @Test
    void testBiLeft3() {
        var lambda = ConsumerCurrying.biLeft3(this::store3, ONE);
        lambda.accept(TWO, THREE);
        Assertions.assertEquals("123", store);
    }

    @Test
    void testBiMiddle3() {
        var lambda = ConsumerCurrying.biMiddle3(this::store3, ONE);
        lambda.accept(TWO, THREE);
        Assertions.assertEquals("213", store);
    }

    @Test
    void testBiRight3() {
        var lambda = ConsumerCurrying.biRight3(this::store3, ONE);
        lambda.accept(TWO, THREE);
        Assertions.assertEquals("231", store);
    }

    @Test
    void testAll() {
        var lambda = ConsumerCurrying.all(this::store, ONE);
        lambda.run();
        Assertions.assertEquals("1", store);
    }

    @Test
    void testAll2() {
        var lambda = ConsumerCurrying.all2(this::store2, ONE, TWO);
        lambda.run();
        Assertions.assertEquals("12", store);
    }

    @Test
    void testAll3() {
        var lambda = ConsumerCurrying.all3(this::store3, ONE, TWO, THREE);
        lambda.run();
        Assertions.assertEquals("123", store);
    }

}