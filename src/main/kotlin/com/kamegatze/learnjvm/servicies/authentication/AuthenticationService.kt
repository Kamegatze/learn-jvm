package com.kamegatze.learnjvm.servicies.authentication

import com.kamegatze.learnjvm.model.db.users.Users
import com.kamegatze.learnjvm.model.registration.Registration

interface AuthenticationService {

    fun registration(registration: Registration): Users

}