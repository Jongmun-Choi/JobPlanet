package com.jobplanet.test.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.jobplanet.test.model.*
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.collections.ArrayList

object JsonParser {

    fun getCustomConverter() : Converter.Factory {
        return GsonConverterFactory.create(getGsonBuilder())
    }

    private fun getGsonBuilder(): Gson {

        val builder = GsonBuilder()
        builder.registerTypeAdapter(object : TypeToken<ArrayList<Any>>() {}.type, JsonDeserializer { json, _, _ ->

            val dataList = ArrayList<Any>()

            if(json.isJsonObject && !json.asJsonObject["items"].isJsonNull) {
                val items = json.asJsonObject["items"].asJsonArray
                items.forEach {
                    val jsonObject: JsonObject = it as JsonObject
                    val data : Any ?= when(jsonObject.opt("cell_type", "")) {
                        "CELL_TYPE_COMPANY" -> getGsonBuilder().fromJson(jsonObject, CompanyData::class.java)
                        "CELL_TYPE_HORIZONTAL_THEME" -> getGsonBuilder().fromJson(jsonObject, HorizontalThemeData::class.java)
                        "CELL_TYPE_INTERVIEW" -> getGsonBuilder().fromJson(jsonObject, InterviewData::class.java)
                        "CELL_TYPE_JOB_POSTING" -> getGsonBuilder().fromJson(jsonObject, JobPosting::class.java)
                        "CELL_TYPE_SALARY" -> getGsonBuilder().fromJson(jsonObject, SalaryData::class.java)
                        "CELL_TYPE_REVIEW" -> getGsonBuilder().fromJson(jsonObject, ReviewData::class.java)
                        else -> null
                    }

                    if(data!=null) {
                        dataList.add(data)
                    }
                }
            }

            dataList
        })

        builder.registerTypeAdapter(CompanyData::class.java, JsonDeserializer { json, _, _ ->
            Gson().fromJson(json, CompanyData::class.java)
        })

        builder.registerTypeAdapter(HorizontalThemeData::class.java, JsonDeserializer { json, _, _ ->
            Gson().fromJson(json, HorizontalThemeData::class.java)
        })

        builder.registerTypeAdapter(InterviewData::class.java, JsonDeserializer { json, _, _->
            Gson().fromJson(json, InterviewData::class.java)

        })

        builder.registerTypeAdapter(JobPosting::class.java, JsonDeserializer { json, _, _ ->
            Gson().fromJson(json, JobPosting::class.java)
        })

        builder.registerTypeAdapter(ReviewData::class.java, JsonDeserializer { json, _, _ ->
            Gson().fromJson(json, ReviewData::class.java)
        })

        builder.registerTypeAdapter(SalaryData::class.java, JsonDeserializer { json, _, _ ->
            Gson().fromJson(json, SalaryData::class.java)
        })

        return builder.create()
    }


    private fun JsonObject.opt(key: String, fallBack: String = ""): String {
        return if (!this.has(key) || this[key].isJsonNull) {
            fallBack
        } else {
            this[key].asString
        }
    }

}