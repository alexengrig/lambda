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

import java.util.function.BiFunction;
import java.util.function.Function;

@SuppressWarnings("Convert2MethodRef")
class Functions {
    Function<String, Integer> fnLength = (String s) -> s.length();
    Function<String, Integer> refLength = String::length;

    BiFunction<String, Integer, String> fnSubstring = (String s, Integer b) -> s.substring(b);
    BiFunction<String, Integer, String> refSubstring = String::substring;

    TerFunction<String, Integer, Integer, String> fnSubstring2 = (String s, Integer b, Integer e) -> s.substring(b, e);
    TerFunction<String, Integer, Integer, String> refSubstring2 = String::substring;
}
