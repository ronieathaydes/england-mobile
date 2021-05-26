package com.england

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}