package com.omaestre.data.network

import com.omaestre.core.BuildConfig
import com.omaestre.core.base.utils.Utils
import com.omaestre.core.functional.Status
import com.omaestre.core.network.NetworkService
import com.omaestre.data.models.ResultData
import java.io.IOException
import java.util.*

class MarvelService (private val networkService : NetworkService) : MarvelServiceInterface{

    private val service by lazy { networkService.getNetworkProvider().create(RetrofitServiceInterface::class.java) }

    //region override methods
    override fun getHeroes(): Status<ResultData> {
        return callService(false, "")
    }

    override fun getHeroDetail(id: String): Status<ResultData> {
        return callService(true, id)
    }
    //endregion

    private fun callService(isDetail: Boolean, id: String): Status<ResultData> {

        val ts = Calendar.getInstance().timeInMillis
        val hash = Utils.md5(ts.toString() + BuildConfig.PRIVATEKEY + BuildConfig.PUBLICKEY)

        return try {

            val data = if (isDetail) {
                service.getHeroDetail(id, BuildConfig.PUBLICKEY, hash, ts).execute()
            } else {
                service.getHeroes(BuildConfig.PUBLICKEY, hash, ts).execute()
            }
            if (data.isSuccessful && data.body() != null) {
                Status.Success(data.body()!!)
            } else {
                Status.Error()
            }
        } catch (a: IOException) {
            Status.Error()
        } catch (a: RuntimeException) {
            Status.Error()
        }
    }
    //endregion
}