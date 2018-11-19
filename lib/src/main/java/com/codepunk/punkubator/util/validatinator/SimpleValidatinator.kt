/*
 * Copyright (C) 2018 Codepunk, LLC
 * Author(s): Scott Slater
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codepunk.punkubator.util.validatinator

abstract class SimpleValidatinator<T> protected constructor(
    validMessage: (input: T) -> CharSequence?,
    invalidMessage: (input: T) -> CharSequence?
) : Validatinator<T>(validMessage, invalidMessage) {

    override fun validate(input: T): Result<T> = when (isValid(input)) {
        true -> Result(true, input, validMessage(input))
        false -> Result(false, input, invalidMessage(input))
    }

    abstract fun isValid(input: T): Boolean

}