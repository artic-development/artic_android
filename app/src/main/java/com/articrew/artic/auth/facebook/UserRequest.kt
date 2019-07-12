package com.articrew.artic.auth.facebook

import android.os.Bundle
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.HttpMethod

object UserRequest {
    fun makeUserRequest(callback: GraphRequest.Callback) {
        val params = Bundle().apply {
            putString("fields", "picture,name,id,email,permissions")
        }

        GraphRequest(
            AccessToken.getCurrentAccessToken(),
            "/me",
            params,
            HttpMethod.GET,
            callback
        ).executeAsync()
    }
}