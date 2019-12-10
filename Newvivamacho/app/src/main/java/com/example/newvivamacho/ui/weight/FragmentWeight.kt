package com.example.newvivamacho.ui.weight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.example.newvivamacho.R

class FragmentWeight : Fragment(){
    // 画面に表示するWebView
    private var mWebView: WebView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_training_webview, container, false)

        // 画面のインスタンスを取得
        mWebView = root.findViewById(R.id.webView) as WebView

        // WebSettingsオブジェクトを取得
        val settings = mWebView!!.settings

        // JavaScriptを有効にする
        settings.javaScriptEnabled = true


        // Activity起動時に初期URL（Google日本語サイト）を読み込む

        mWebView!!.loadUrl("https://tarzanweb.jp/post-184063")

        return root
    }
}