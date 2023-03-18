/*
 * Copyright 2022 Rúben Sousa
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

package com.app.worldkids.ui.model

import com.idanatz.oneadapter.external.interfaces.Diffable

data class ListMode(
    val id: Long = 0L,
    val title: String? = null,
    val des: String? = null,
    val image: String? = null,
) : Diffable {
    override val uniqueIdentifier: Long = id
    override fun areContentTheSame(other: Any): Boolean = title == (other as ListMode).title
}