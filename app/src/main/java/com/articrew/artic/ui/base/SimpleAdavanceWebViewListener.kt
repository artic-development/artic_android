package com.articrew.artic.ui.base

import android.graphics.Bitmap
import im.delight.android.webview.AdvancedWebView

open class SimpleAdavanceWebViewListener : AdvancedWebView.Listener {
    override fun onPageFinished(url: String?) {

    }

    override fun onPageError(errorCode: Int, description: String?, failingUrl: String?) {
    }

    override fun onDownloadRequested(
        url: String?,
        suggestedFilename: String?,
        mimeType: String?,
        contentLength: Long,
        contentDisposition: String?,
        userAgent: String?
    ) {
    }

    override fun onExternalPageRequest(url: String?) {
    }

    override fun onPageStarted(url: String?, favicon: Bitmap?) {
    }
}