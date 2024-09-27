package com.example.messenger.presentation

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.messenger.Utils
import com.example.messenger.data.ApiResponse
import com.example.messenger.databinding.ActivityMainActivityBinding
import com.example.messenger.models.dao.Hero
import com.example.messenger.models.dao.HeroRepository
import com.example.messenger.models.dao.getDatabase
import com.example.messenger.network.KtorNetwork
import com.example.messenger.network.KtorNetworkApi
import com.example.messenger.network.RetrofitNetwork
import com.example.messenger.network.RetrofitNetworkApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivityMain : AppCompatActivity() {
    private lateinit var binding: ActivityMainActivityBinding

    private var _retrofitApi: RetrofitNetworkApi? = null
    private val retrofitApi get() = _retrofitApi!!

    private var _ktorApi: KtorNetworkApi? = null
    private val ktorApi get() = _ktorApi!!
    private var number: Int = 6

    private var job: Job? = null
    private lateinit var heroesRepository: HeroRepository
    private lateinit var characters: List<ApiResponse>

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _ktorApi = KtorNetwork()
        _retrofitApi = RetrofitNetwork()

        val database = getDatabase(applicationContext)
        heroesRepository = HeroRepository(database.heroDao)

        binding.retrofit.setOnClickListener {
            lifecycleScope.launch {
                val users = retrofitApi.getUsers()
                binding.userList.adapter = ApiResponseAdapter(users)
            }
        }

        binding.ktor.setOnClickListener {
            lifecycleScope.launch {
                val users = ktorApi.getUsers(6)
                binding.userList.adapter = ApiResponseAdapter(users)

                val dataString = users.joinToString(separator = "\n") {
                    "Name: ${it.name}, Culture: ${it.culture}, Born: ${it.born}," +
                            " Titles: ${it.titles}, Aliases: ${it.aliases}, PlayedBy: ${it.playedBy}"
                }
                val fileName = "heroes_6.txt"
                Utils.saveDataToFileInDocuments(fileName, dataString)
                Toast.makeText(
                    this@MainActivityMain,
                    "Файл сохранен в Documents",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.archive.setOnClickListener {
            (binding.userList.adapter as ApiResponseAdapter).setData(emptyList())
        }

        binding.sendButton.setOnClickListener {
            number = binding.numberText.text.toString().toIntOrNull() ?: 6
            loadData(number)
        }
    }

    private fun loadData(number: Int) {
        lifecycleScope.launch {
            if (!heroesRepository.checkHeroInDatabase(number)) {
                job?.cancel()
                job = lifecycleScope.launch{
                    heroesRepository.getHeroesByNumber(number).collect { heroes ->
                        characters = heroes.map { hero ->
                            ApiResponse(
                                name = hero.name,
                                culture = hero.culture,
                                born = hero.born,
                                titles = hero.titles?.split(", "),
                                aliases = hero.aliases?.split(", "),
                                playedBy = hero.playedBy?.split(", ")
                            )
                        }
                        binding.userList.adapter = ApiResponseAdapter(characters)
                    }
                }

                characters = ktorApi.getUsers(number)
                heroesRepository.insertHeroes(
                    characters.map { heroesDTO ->
                        Hero(
                            number = number,
                            name = heroesDTO.name,
                            culture = heroesDTO.culture,
                            born = heroesDTO.born,
                            titles = heroesDTO.titles?.joinToString(", "),
                            aliases = heroesDTO.aliases?.joinToString(", "),
                            playedBy = heroesDTO.playedBy?.joinToString(", ")
                        )
                    }
                )
            }
        }
    }
}