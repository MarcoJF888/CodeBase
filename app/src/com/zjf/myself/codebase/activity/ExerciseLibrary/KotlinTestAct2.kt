package com.zjf.myself.codebase.activity.ExerciseLibrary

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.act_kotlin_test2.*;

import com.zjf.myself.codebase.R
import com.zjf.myself.codebase.activity.BaseAct

class KotlinTestAct2 : BaseAct() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_kotlin_test2)


        var a :String = intent.getStringExtra("aaa")
        txtResult.text = a
    }
}
