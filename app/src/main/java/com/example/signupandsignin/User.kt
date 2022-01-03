package com.example.signupandsignin

import java.io.Serializable

data class User (val id: Int,
                 val Email: String,
                 val Password: String,
                 val Name: String,
                 val Mobile: String,
                 val Address: String
                 ) : Serializable