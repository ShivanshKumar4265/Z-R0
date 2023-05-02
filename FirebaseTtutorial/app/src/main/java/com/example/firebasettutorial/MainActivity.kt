package com.example.firebasettutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    lateinit var database: FirebaseDatabase
    lateinit var ref: DatabaseReference
    lateinit var name: EditText
    lateinit var age: EditText
    lateinit var getName: TextView
    lateinit var getAge: TextView
    lateinit var pushData: Button
    lateinit var getData: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = FirebaseDatabase.getInstance()
        ref = database.reference

        name = findViewById(R.id.name)
        age = findViewById(R.id.age)
        getName = findViewById(R.id.getName)
        getAge = findViewById(R.id.getAge)
        pushData = findViewById(R.id.pushData)
        getData = findViewById(R.id.getData)


        pushData.setOnClickListener {

            val name = name.text.toString()
            val age = age.text.toString()
            ref.child("name").setValue(name)
            ref.child("age").setValue(age)
        }

        getData.setOnClickListener {

            ref.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val name = dataSnapshot.child("name").value
                    val age = dataSnapshot.child("age").value

                    if (name != null) {
                        getName.text = "name is $name"
                    }

                    if (age != null) {
                        getAge.text = "age is $age years"
                    }

                }


//                val map = mutableMapOf<String, Any>()
//                for (child in dataSnapshot.children) {
//                    val key = child.key
//                    val value = child.value
//                    if (key != null && value != null) {
//                        map[key] = value
//                    }
//                }
//
//                val name = map["name"]
//                val age = map["age"]
//
//                if (name != null) {
//                    getName.text = "name is $name"
//                }
//
//                if (age != null) {
//                    getAge.text = "age is $age years"
//                }
//            }


                override fun onCancelled(databaseError: DatabaseError) {

                }
            })


        }
    }
}