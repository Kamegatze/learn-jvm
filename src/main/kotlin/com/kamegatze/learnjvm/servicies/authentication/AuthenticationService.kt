package com.kamegatze.learnjvm.servicies.authentication

import com.kamegatze.learnjvm.model.users.Users
import com.kamegatze.learnjvm.model.registration.Registration

interface AuthenticationService {

    fun registration(registration: Registration): Users

}