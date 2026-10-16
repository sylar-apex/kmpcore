package com.psbapp.networking.utils

interface UploadImageRequest {
    fun toFormData(): Map<String, Any?>

}