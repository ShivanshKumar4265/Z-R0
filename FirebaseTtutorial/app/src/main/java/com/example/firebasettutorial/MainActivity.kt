package com.example.firebasettutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private lateinit var database: FirebaseDatabase
    private lateinit var ref: DatabaseReference
    private lateinit var name: EditText
    private lateinit var age: EditText
    lateinit var getName: TextView
    lateinit var getAge: TextView
    private lateinit var pushData: Button
    private lateinit var getData: Button
    private lateinit var updateData: Button
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
        updateData = findViewById(R.id.updateData)

        /**
         *
         * inserting data to firebase database
         */

        pushData.setOnClickListener {

            val name = name.text.toString()
            val age = age.text.toString()

            /**
             * This snippet is inserting without  push id
             */
//            ref.child("name").setValue(name)
//            ref.child("age").setValue(age)

            val pushID: String? = ref.push().key
            if (pushID != null) {
                ref.child(pushID).child("name").setValue(name)
                ref.child(pushID).child("age").setValue(age)
            }
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

        updateData.setOnClickListener {

            /**
             *  Updating values using " updateChildren() " methods
             */

            val name = name.text.toString()
            val age = age.text.toString()

            val list = mutableMapOf<String, Any>()
//            list["/-NUSW3lz3tg4RXvvdh6U/name"] = name
            list["/-NUSW3lz3tg4RXvvdh6U/age"] = age
            ref.updateChildren(list)



            /**
             * To remove value
             */


//            val task :Task<Void> = ref.child("-NUSeJlkmS3grYIhWJ8c").removeValue()
//            task.addOnSuccessListener{
//
//            }.addOnFailureListener{


            }


        }
    }
}