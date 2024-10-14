package com.kamegatze.learnjvm.utils

interface HtmlConvector {
    fun toFirstAttributeToText(nameAttribute: String, content: String): String
}