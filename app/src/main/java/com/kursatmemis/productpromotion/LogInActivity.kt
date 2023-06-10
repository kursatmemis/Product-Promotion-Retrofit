package com.kursatmemis.productpromotion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.kursatmemis.productpromotion.configs.ApiClient
import com.kursatmemis.productpromotion.models.User
import com.kursatmemis.productpromotion.models.UserResponse
import com.kursatmemis.productpromotion.services.DummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogInActivity : AppCompatActivity() {

    private lateinit var userNameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userNameEditText = findViewById(R.id.userNameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = userNameEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (username.contains(" ") || password.contains(" ")) {
                Toast.makeText(
                    this@LogInActivity,
                    "Bosluk kullanmayiniz!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            val user = User(username, password)
            val dummyService = ApiClient.getClient().create(DummyService::class.java)
            dummyService.login(user).enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    Log.d("status", response.code().toString())
                    val userResponse = response.body()
                    Log.d("userResponse", userResponse.toString())
                    if (userResponse != null) {
                        val intent = Intent(this@LogInActivity, ProductsActivity::class.java)
                        intent.putExtra("firstName", userResponse.firstName)
                        intent.putExtra("lastName", userResponse.lastName)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this@LogInActivity,
                            "Kullanıcı adı veya şifre hatalı!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("onFailure:", t.toString())
                }

            })
        }


    }
}