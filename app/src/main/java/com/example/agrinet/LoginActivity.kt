package com.example.agrinet

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        // Initialize Firebase Authentication
        auth = FirebaseAuth.getInstance()

        // Initialize EditTexts
        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)

        val loginButton = findViewById<ConstraintLayout>(R.id.loginButton)
        val registerButton = findViewById<TextView>(R.id.registerButton)

        loginButton.setOnClickListener {
            val email = editEmail.text?.toString()
            val password = editPassword.text?.toString()

            if (!email.isNullOrBlank() && !password.isNullOrBlank()) {
                // Call function to authenticate user
                authenticateUser(email, password)
            } else {
                // Handle empty email or password
                Toast.makeText(this, "Email and password cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun authenticateUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Authentication successful
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // Authentication failed
                    Toast.makeText(this, "Login failed. Please check your credentials.", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            // User is signed in
            // Redirect to the main activity, home screen, or perform any other action

            // Example: Start a new activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // Finish the current activity to prevent going back to the login screen
            finish()
        } else {
            // User authentication failed or user is not signed in
            // Display an error toast message
            Toast.makeText(this, "Login failed. Please check your credentials.", Toast.LENGTH_SHORT).show()
        }
    }
}
