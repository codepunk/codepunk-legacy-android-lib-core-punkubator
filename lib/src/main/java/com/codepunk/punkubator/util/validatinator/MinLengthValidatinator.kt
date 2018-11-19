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

import android.content.Context
import com.codepunk.punkubator.R

class MinLengthValidatinator protected constructor(
    validMessage: (input: CharSequence?) -> CharSequence?,
    invalidMessage: (input: CharSequence?) -> CharSequence?,
    protected val minLength: Int = 0
) : SimpleValidatinator<CharSequence?>(validMessage, invalidMessage) {

    override fun isValid(input: CharSequence?): Boolean =
        input != null && input.length >= minLength

    class Builder(

        val minLength: Int = 0

    ) : AbsBuilder<CharSequence?, MinLengthValidatinator, Builder>() {

        override val thisBuilder: Builder = this

        override fun getInvalidMessage(
            context: Context?,
            inputName: CharSequence?,
            input: CharSequence?
        ): CharSequence? = context?.run {
            getString(
                R.string.validatinator_invalid_min,
                inputName ?: "\"$input\"",
                minLength
            )
        }

        override fun build(): MinLengthValidatinator =
            MinLengthValidatinator(validMessage, invalidMessage, minLength)

    }

}