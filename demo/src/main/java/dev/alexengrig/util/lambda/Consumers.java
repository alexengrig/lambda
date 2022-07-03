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

import java.nio.charset.Charset;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@SuppressWarnings({"LambdaBodyCanBeCodeBlock", "ResultOfMethodCallIgnored", "Convert2MethodRef"})
class Consumers {
    Consumer<byte[]> fnNew = (byte[] bytes) -> new String(bytes);
    Consumer<byte[]> refNew = String::new;

    BiConsumer<byte[], Charset> fnNew2 = (byte[] bytes, Charset charset) -> new String(bytes, charset);
    BiConsumer<byte[], Charset> refNew2 = String::new;

    TerConsumer<byte[], Integer, Integer> fnNew3 = (byte[] bytes, Integer offset, Integer length) -> new String(bytes, offset, length);
    TerConsumer<byte[], Integer, Integer> refNew3 = String::new;
}
