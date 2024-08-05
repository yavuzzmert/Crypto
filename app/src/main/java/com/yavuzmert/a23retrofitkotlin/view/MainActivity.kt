package com.yavuzmert.a23retrofitkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yavuzmert.a23retrofitkotlin.R
import com.yavuzmert.a23retrofitkotlin.adapter.RecyclerViewAdapter
import com.yavuzmert.a23retrofitkotlin.model.CryptoModel
import com.yavuzmert.a23retrofitkotlin.service.CryptoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.Listener {

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var cryptoModels : ArrayList<CryptoModel>? = null
    private var recyclerViewAdapter : RecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //RecyclerView
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)


        recyclerView.layoutManager = layoutManager

        loadData()
    }

    private fun loadData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //retrofit ile service'i bağladık
        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getData()

        call.enqueue(object: Callback<List<CryptoModel>>{
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        cryptoModels = java.util.ArrayList(it)

                        cryptoModels?.let {
                            recyclerViewAdapter = RecyclerViewAdapter(it, this@MainActivity)
                            recyclerView.adapter = recyclerViewAdapter
                        }

                        /*
                        for (cryptoModel: CryptoModel in cryptoModels!!){
                            println(cryptoModel.currency)
                            println(cryptoModel.price)
                        }*/
                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this, "clicked: ${cryptoModel.currency}", Toast.LENGTH_LONG).show()
    }

}

/*
    -API, application programming interface, yani iki sistem arasında haberleşmedir.
        örnek; bir uygulamanın api'sine ulaşmamız sunucusuna istek atarak bize yanıt dönmesi demektir ve veriler Json yapısında dönüş yapar bize.
    -Json; javascript object notation; geçici veriler bir web sitesinde gönderilen form     gibi kullanıcı tarafından oluşturulmuş veriler olabilir. JSON, yüksek düzeyde       birlikte çalışabilirlik sunmak için tüm programlama dillerine yönelik bir           serileştirme veri formatı olarak da kullanılabilir.
    -Retrofit, internetten veri çekmek için özellikleri API'lara bir istek yollayıp cevaplarını alıp uygulamamız üstünde göstermek için kullandığımız bir kütüphanedir.
    -Rx.Java, retrofit kütüphanesine yardımcı bir kütüphanedir, özellikle async task ve thread' lerde bize yardımcı oluyor.
    -Bu kripto para uygulamanın verilerini nomics diye bir sitenin API'sini kullanarak alacağız.
    -https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
    -Retrofit implement etmek için, gradle altında implement etmemiz gereken yapılar var
    - response.body()?.let {} -> eğer cevap null değilse let içindeki işlemi yap anlamına geliyor.
    -Manifest içerisine internet iznini eklememiz gerekli.
    -OnCreate altındaki recyclerView'da layoutManager oluşturuyoruz bu da recycler View'da veriler alt alta mı grid olarak mı onu belirliyoruz.
 */