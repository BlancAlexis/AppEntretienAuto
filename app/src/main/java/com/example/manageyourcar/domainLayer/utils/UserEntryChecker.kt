package com.example.manageyourcar.domainLayer.utils

import com.example.manageyourcar.dataLayer.model.User

object UserEntryChecker {
fun validateFirstName(newFirstName: String): String {
    return if (newFirstName.isBlank()) {
        "ErrorMessage.FIELD_EMPTY"
    } else if (!newFirstName.matches(Regex("^[a-zA-Z]+(-[a-zA-Z]+)*\$"))) {
        "ErrorMessage.ILLEGAL_ARGUMENT"
    } else {
        ""
    }
}

fun validateLastName(newLastName: String): String {
    return if (newLastName.isBlank()) {
        "ErrorMessage.FIELD_EMPTY"
    } else if (!newLastName.matches(Regex("^[a-zA-Z]+(-[a-zA-Z]+)*\$"))) {
        "ErrorMessage.ILLEGAL_ARGUMENT"
    } else {
        ""
    }
}


fun validatePhoneNumber(newPhoneNumber: String): String {
    return if (newPhoneNumber.isBlank()) {
        "ErrorMessage.FIELD_EMPTY"
    } else if (newPhoneNumber.length < 10) {
        "ErrorMessage.TOO_SHORT"
    } else if (newPhoneNumber.length > 12) {
        "ErrorMessage.TOO_LONG"
    } else if (!newPhoneNumber.matches(Regex("(0|\\+33)[1-9]( *[0-9]{2}){4}"))) {
        "ErrorMessage.ILLEGAL_ARGUMENT"
    } else {
        ""
    }
}

    // A faire
    fun validatePassword(newPassword: String): String {
        return if (newPassword.isBlank()) {
            "ErrorMessage.FIELD_EMPTY"
        } else if (newPassword.length < 10) {
            "ErrorMessage.TOO_SHORT"
        } else if (newPassword.length > 12) {
            "ErrorMessage.TOO_LONG"
        } else if (!newPassword.matches(Regex("(0|\\+33)[1-9]( *[0-9]{2}){4}"))) {
            "ErrorMessage.ILLEGAL_ARGUMENT"
        } else {
            ""
        }
    }

    fun areTwoFieldPasswordTheSame(password: String, validatePassword: String): String {
        return if (password != validatePassword) {
            "ErrorMessage.PASSWORD_NOT_SAME"
        } else {
            ""
        }
    }
    fun isIntNotToLong(int: Int): Boolean {
        return int <= 1000000000
    }

/*
fun validateUserEntry(user : User): CollaboratorValidationResult {
    val collaboratorErrors: CollaboratorErrors = CollaboratorErrors(
        firstNameError = collaborator.firstName?.let { validateFirstName(it) }.toString(),
        lastNameError = collaborator.lastName?.let { validateLastName(it) }.toString(),
        phoneNumberError = collaborator.phoneNumber?.let { validatePhoneNumber(it) }.toString()
    )

    if (collaboratorErrors.firstNameError == ErrorMessage.OK.toString()
        && collaboratorErrors.lastNameError == ErrorMessage.OK.toString()
        && collaboratorErrors.phoneNumberError == ErrorMessage.OK.toString()
    ) {
        return CollaboratorValidationResult.Success(collaborator)
    }
    return CollaboratorValidationResult.Failure(collaboratorErrors)
}
*/



sealed interface CollaboratorValidationResult {
    data class Success(val collaborator: User) : CollaboratorValidationResult
    data class Failure(val collaboratorErrors: CollaboratorErrors) :
        CollaboratorValidationResult
}


data class CollaboratorErrors(
    val firstNameError: String? = null,
    val lastNameError: String? = null,
    val phoneNumberError: String? = null
) : Exception()
}