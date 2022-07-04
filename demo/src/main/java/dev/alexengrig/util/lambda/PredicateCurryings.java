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

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PredicateCurryings {
    static {
        String value = Stream.of("-Task", "-Test", "-Text")
                     // s -> s.startsWith("Te", 1)
                .filter(PredicateCurrying.right3(String::startsWith, "Te", 1))
                .collect(Collectors.joining());
        assert "-Test-Text".equals(value);
    }
}
