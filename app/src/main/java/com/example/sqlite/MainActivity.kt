package com.example.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var edName: EditText
    private lateinit var edEmail : EditText
    private lateinit var btnAdd :Button
    private lateinit var btnViwe : Button

    private lateinit var  sqlitehelper : SQliteHelper
    private lateinit var recyclerView: RecyclerView
    private  var adapter:StudentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initRecyclerView()
        sqlitehelper = SQliteHelper(this)
        btnAdd.setOnClickListener { addStudent() }
        btnViwe.setOnClickListener { getSudents() }
    }

    private fun getSudents() {
        val stdList = sqlitehelper.getAllStudnet()
        Log.e("ppp","${stdList.size}")

        adapter?.addItems(stdList)
    }
    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentAdapter()
        recyclerView.adapter=adapter
    }
    private fun initView()
    {
        edName = findViewById(R.id.edName)
        edEmail = findViewById(R.id.edEmail)
        btnAdd= findViewById(R.id.btnAdd)
        btnViwe = findViewById(R.id.btnView)
        recyclerView=findViewById(R.id.recyclerView)
    }
    private fun addStudent()
    {
        val name = edName.text.toString();
        val email=edEmail.text.toString();

        if(name.isEmpty()||email.isEmpty())
        {
            Toast.makeText(this, "no dejes los campos vacios", Toast.LENGTH_SHORT).show()
        }else {
            val std = StudentMode(name = name, email = email)
            val status = sqlitehelper.insertStudent(std)
            if (status > 1) {
                Toast.makeText(this, "añadiendo el estudiante", Toast.LENGTH_LONG).show()
                clearEditText()
            } else {
                Toast.makeText(this, "no se guardo el alumno", Toast.LENGTH_LONG).show()
                }
        }
    }
    private fun clearEditText()
    {
        edName.setText("")
        edEmail.setText("")
        edName.requestFocus()
    }
}