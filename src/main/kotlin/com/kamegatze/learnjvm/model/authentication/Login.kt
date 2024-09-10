package com.kamegatze.learnjvm.model.authentication


data class Login(var username: String, var password: String) {
    constructor() : this("", "")
}
