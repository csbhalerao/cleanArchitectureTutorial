package com.chetan.coroutinetutorial.data.apiservice.helper

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.Error
import javax.inject.Inject

class ErrorUtil {
    @Inject
    lateinit var retrofit: Retrofit

    fun parse(responseErrorBody: ResponseBody?): ResourceError {
        var converter: Converter<ResponseBody, ResourceError> =
            retrofit.responseBodyConverter(ResourceError::class.java, arrayOfNulls<Annotation>(0))

        try {
            responseErrorBody?.let {
                return converter.convert(it) as ResourceError
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val resourceError = ResourceError()
        resourceError.error = Error("unable to parse the error response")
        return resourceError
    }
}
