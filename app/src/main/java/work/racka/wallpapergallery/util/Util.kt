/*
 * Copyright 2018, The Android Open Source Project
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
 *
 */

package work.racka.wallpapergallery.util

import work.racka.wallpapergallery.domain.Wallpaper

private val PUNCTUATION = listOf(", ", "; ", ": ", " ")

/**
 * Truncate long text with a preference for word boundaries and without trailing punctuation.
 */
fun String.smartTruncate(length: Int): String {
    val words = split(" ")
    var added = 0
    var hasMore = false
    val builder = StringBuilder()
    for (word in words) {
        if (builder.length > length) {
            hasMore = true
            break
        }
        builder.append(word)
        builder.append(" ")
        added += 1
    }

    PUNCTUATION.map {
        if (builder.endsWith(it)) {
            builder.replace(builder.length - it.length, builder.length, "")
        }
    }

    if (hasMore) {
        builder.append("...")
    }
    return builder.toString()
}

/**
 * A function that takes a collection of Wallpapers then returns a set collections/categories from wallpapers
 * It takes a separator and splits all the items with that separator, adding them to the set
 * Since it is a set it means that there's no duplicates
 * This is useful when creating a ChipGroup from a database and don't want duplicate chips
 * You can modify the function to return other types as needed
 */
fun Collection<Wallpaper>.getCombinedCollection(separator: String): Set<String> {
    val collection = mutableSetOf<String>()
    this.forEach {
        if (it.collections.contains(separator)) {
            collection.addAll(it.collections.split(separator))
        } else collection.add(it.collections)
    }
    return collection
}
