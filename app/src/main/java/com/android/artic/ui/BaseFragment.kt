package com.android.artic.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

open class BaseFragment(
    private val layoutId: Int
) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    /**
     * this might call when page is scrolled into this fragment
     * @author greedy0110
     * */
    open fun onResumeFragment() {}

    /**
     * this might call when page is scrolled out this fragment
     * @author greedy0110
     * */
    open fun onPauseFragment() {}

}