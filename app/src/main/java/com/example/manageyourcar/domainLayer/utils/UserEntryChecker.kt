package com.example.manageyourcar.domainLayer.utils


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
}

