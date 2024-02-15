package com.example.database_ex

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        // db 만들기
        val db = openOrCreateDatabase("test2db", Context.MODE_PRIVATE, null)

        // db 테이블
        try {
            db.execSQL("CREATE TABLE my_db (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "address)")
            db.execSQL("INSERT INTO my_db (name, address) VALUES(?,?)",
                arrayOf("홍길동", "조선"))
            db.execSQL("INSERT INTO my_db (name, address) VALUES(?,?)",
                arrayOf("둘리", "서울"))
            db.execSQL("INSERT INTO my_db (name, address) VALUES(?,?)",
                arrayOf("누구?", "부산"))


        }catch (e: Exception) {
            e.printStackTrace()
        }

        val btn1 = findViewById<Button>(R.id.btn1)
        val txtData = findViewById<TextView>(R.id.txtData)

        db.execSQL("DELETE FROM my_db")

        btn1.setOnClickListener {
            Log.d("test", "btn1 Clicked.................")

            val cursor = db.rawQuery("SELECT name, address FROM my_db", null)
            var data: String = ""
            while (cursor.moveToNext()){
                val name = cursor.getString(0)
                val address = cursor.getString(1)
                data += "이름 : $name\t주소: $address\n"
            }
            txtData.text = data
        }
    }
}