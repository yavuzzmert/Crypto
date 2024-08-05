package com.yavuzmert.a23retrofitkotlin.model

import com.google.gson.annotations.SerializedName

data class CryptoModel(
    //@SerializedName("currency")
    val currency : String,
    //@SerializedName("price")
    val price : String
    )

/*
    -dışardan gelen veriler bu şekilde parametlerle olacak o yüzden dışardan serializedName ile Json formatında ne dönecekse onları belirtmemiz gerekecek ve key'ler aynı olmak zorunda ama aynı isimle parametre koyduğumuz için serializedName yapmamıza gerek yok
 */