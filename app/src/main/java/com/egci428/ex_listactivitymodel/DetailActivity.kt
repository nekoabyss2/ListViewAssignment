package com.egci428.ex_listactivitymodel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_detail.*
import android.view.Menu
import java.text.NumberFormat

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val bundle = intent.extras
        if (bundle != null) {
            val des = bundle.getString("des")
            val title = bundle.getString("title")
            val courseNum = bundle.getInt("courseNum")
            val credits = bundle.getDouble("credit")
            val position = bundle.getInt("image")

            descriptionText.setText(des)
            titleText.setText(title)
            creditsText.setText(credits.toString())
            courseNoText.setText(courseNum.toString())

            val impos = position%3+1
            val res = resources.getIdentifier("image1010"+impos,"drawable",packageName)
            imageCourse.setImageResource(res)



        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }


}



