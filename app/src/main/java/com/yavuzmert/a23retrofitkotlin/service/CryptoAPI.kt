package com.yavuzmert.a23retrofitkotlin.service

import com.yavuzmert.a23retrofitkotlin.model.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {

    //Get, post, update, delete
    //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData(): Call<List<CryptoModel>>

}

/*
    -Interface yani arayüz, bu sınıftan obje oluşturmak yerine burada yaptığımız işlemleri diğer taraflarda kullanabiliyorduk
    -verilen adres'in baz kısmını mainACtivity tarafından çekeceğiz, ve get yaparak, crypto modellerini liste olarak getir, Call ile ve bunu getData fun içerisine at.
 */