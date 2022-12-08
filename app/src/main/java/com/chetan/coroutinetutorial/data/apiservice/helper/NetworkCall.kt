package com.chetan.coroutinetutorial.data.apiservice.helper

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkCall<T> {
    lateinit var call: Call<T>

    fun exec(call: Call<T>): MutableLiveData<Resource<T>> {
        this.call = call
        val callBackKt = CallbackImpl<T>()
        callBackKt.result.value = Resource.loading(null)
        this.call.clone().enqueue(callBackKt)
        return callBackKt.result
    }
}

class CallbackImpl<T> : Callback<T> {
    var result: MutableLiveData<Resource<T>> = MutableLiveData()

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            result.value = Resource.success(response.body())
            return
        }

        result.value = Resource.error(ErrorUtil().parse(response.errorBody()))
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        val resourceError = ResourceError()
        resourceError.error = Error(t.localizedMessage)
        result.value = Resource.error(resourceError)
        t.printStackTrace()
    }
}
