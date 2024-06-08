package com.aurora.aurora.Common.CommonAdapter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class MessageAdapter : JsonDeserializer<List<String>> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): List<String> {
        val messages = mutableListOf<String>()
        if (json != null) {
            if (json.isJsonArray) {
                json.asJsonArray.forEach { messages.add(it.asString) }
            } else if (json.isJsonPrimitive) {
                messages.add(json.asString)
            }
        }
        return messages
    }
}