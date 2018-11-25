package com.wuhao028.pokedex.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.wuhao028.pokedex.DataManager
import com.wuhao028.pokedex.R
import com.wuhao028.pokedex.util.getTypeImageRes
import org.json.JSONArray

/**
 *Created by WuHao028 on 15/11/18
 */

class MoveAdapter(val mContext: Context, val content: String) : RecyclerView.Adapter<MoveAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MoveAdapter.ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.move_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return JSONArray(content).length()
    }

    override fun onBindViewHolder(holder: MoveAdapter.ViewHolder?, position: Int) {
        val view = holder!!.mView
        val textview = view.findViewById<TextView>(R.id.skill_name)
        val imageView = view.findViewById<ImageView>(R.id.skill_image)
        val skill = DataManager.instance.getSkill(JSONArray(content)[position].toString().toInt())
        textview.text = skill.ename
        imageView.setImageResource(getTypeImageRes(skill.type))

        view.setOnClickListener {
            // val intent = Intent(mContext, SecondActivity::class.java)
            // mContext.startActivity(intent)
        }
    }

    class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView)
}