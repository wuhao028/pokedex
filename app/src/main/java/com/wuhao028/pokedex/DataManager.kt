package com.wuhao028.pokedex

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.wuhao028.pokedex.model.Pokemon
import org.json.JSONArray


/**
 *Created by WuHao028 on 1/11/18
 */

class DataManager private constructor() {

    lateinit var pokemons: JSONArray
    lateinit var data: MutableList<Pokemon>

    val tag: String = "DataManager"

    companion object {
        val instance: DataManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DataManager()
        }
    }

    fun initDataManager(context: Context) {
        val assetManager = context.getAssets()
        val json_String = assetManager.open(Constants.POKEMON_JSON).bufferedReader().use { it.readText() }
        try {
            pokemons = JSONArray(json_String)
        } catch (e: Exception) {
            Log.e(tag, e.message)
        }
        val gson = Gson()
        data = arrayListOf()
        for (i in 0..pokemons.length() - 1) {
            data.add(gson.fromJson(pokemons[i].toString(), Pokemon::class.java))
        }

    }


    fun getPokemonData(): List<Pokemon> {
        return data
    }

    fun getDrawableID(name: String): Int {
        //TODO  protection
        return PokeApplication.context.getResources().getIdentifier(name?.filterAlph(), "drawable", PokeApplication.context.getPackageName())
    }
}
