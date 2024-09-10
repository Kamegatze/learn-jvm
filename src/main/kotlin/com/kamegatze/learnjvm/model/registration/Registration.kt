package com.kamegatze.learnjvm.model.registration

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

class Registration(
    @field:NotNull(message = "required field")
    @field:Size(min = 2, max = 50, message = "This field should be more 1 character and less 51 character")
    var lastName: String,
    @field:NotNull(message = "required field")
    @field:Size(min = 2, max = 50, message = "This field should be more 1 character and less 51 character")
    var firstName: String,
    @field:NotNull(message = "required field")
    @field:Size(min = 2, max = 50, message = "This field should be more 1 character and less 51 character")
    var login: String,
    @field:NotNull(message = "required field")
    @field:Size(min = 8, max = 50, message = "This field should be more 8 character and less 51 character")
    var password: String,
    @field:NotNull(message = "required field")
    @field:Size(min = 8, max = 50, message = "This field should be more 8 character and less 51 character")
    var retryPassword: String
) {
    constructor() : this("", "", "", "", "")
}
