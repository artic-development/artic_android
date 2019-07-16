package com.articrew.artic.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.articrew.artic.logger.Logger
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.koin.android.ext.android.inject

open class BaseFragment(
    private val layoutId: Int
) : Fragment() {
    private val compositeDisposable = CompositeDisposable()
    protected val logger: Logger by inject()

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

    open fun requestTopScroll() {}

    protected fun addDisposable(dis: Disposable) {
        compositeDisposable.add(dis)
    }

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.clear()
    }

}