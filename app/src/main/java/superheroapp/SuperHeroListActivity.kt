package superheroapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appbodymassindexcalculator_xml.R
import com.example.appbodymassindexcalculator_xml.databinding.ActivitySuperHeroListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import superheroapp.DetailSuperHeroActivity.Companion.EXTRA_ID

class SuperHeroListActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySuperHeroListBinding
    private lateinit var retrofit: Retrofit
    private lateinit var retrofitCreated: Retrofit

    private lateinit var adapter: SuperheroAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initAdapter()
        initUI()


    }

    private fun initUI() {
        /*binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?) = false
            adapter.updateList(response.body()?.results)

        }
        )*/


        binding.searchView.doAfterTextChanged {
            searchByNameSecondVersion(it.toString())
        }


        binding.rvSuperhero.setHasFixedSize(true)
        binding.rvSuperhero.adapter = adapter

    }

    private fun initAdapter() {
        binding.rvSuperhero.layoutManager = LinearLayoutManager(this)
        adapter = SuperheroAdapter { heroName -> navigateToDetail(heroName)}


    }

    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<SuperHeroDataResponse> =
                retrofit.create(ApiService::class.java).getSuperheroes(query)
            if (myResponse.isSuccessful) {
                Log.i("aristidevs", "funciona:)")
                val response: SuperHeroDataResponse? = myResponse.body()
                if (response != null) {
                    Log.i("aristidevs", response.toString())
                    runOnUiThread {
                        adapter.updateList(response.superheroes)
                        binding.progressBar.isVisible = false
                    }
                }
            } else {
                Log.i("aristidevs", "No funciona:(")
            }
        }
    }

    private fun searchByNameSecondVersion(heroName: String) {
        showProgressBar(true)
        CoroutineScope(Dispatchers.IO).launch {
            val response = retrofit.create(ApiService::class.java).getSuperheroes(heroName)
            if (response.isSuccessful) {
                response.body()?.let {
                    runOnUiThread {
                        adapter.updateList(it.superheroes)
                        showProgressBar(false)
                    }
                }
            } else {
                Toast.makeText(applicationContext, response.message(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showProgressBar(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }


    private fun navigateToDetail(id:String){
        val intent = Intent(this, DetailSuperHeroActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}

