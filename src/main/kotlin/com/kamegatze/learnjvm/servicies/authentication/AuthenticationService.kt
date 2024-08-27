package com.kamegatze.learnjvm.servicies.authentication

import com.kamegatze.learnjvm.model.db.users.Users

interface AuthenticationService {

    fun registration(user: Users): Users

}