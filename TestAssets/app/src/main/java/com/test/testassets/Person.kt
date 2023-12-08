package com.test.testassets

data class Person(
    var first_name: String,
    var last_name: String
) {
    override fun toString(): String {
        return "First Name: $first_name\nLast Name: $last_name"
    }
}