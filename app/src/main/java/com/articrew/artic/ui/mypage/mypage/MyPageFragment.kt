package com.articrew.artic.ui.mypage.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.articrew.artic.R
import com.articrew.artic.logger.Logger
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.base.BaseFragment
import com.articrew.artic.ui.new_archive.MakeNewArchiveActivity
import com.articrew.artic.ui.setting.setting.SettingActivity
import com.articrew.artic.util.defaultHolderOptions
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_my_page.*
import kotlinx.android.synthetic.main.my_page_tablayout.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject


class MyPageFragment() : BaseFragment(R.layout.fragment_my_page) {
    private lateinit var adapter: MyPagePagerAdapter
    private val repository: ArticRepository by inject()
    private lateinit var scrapFragment: MyPageScrapFragment
    private lateinit var meFragment: MyPageMeFragment

    private val archiveAddButtonShowTask: (Boolean)-> Unit = {
        logger.log("archive add button $it")
        if (it)
            mypage_plus_btn.visibility = View.VISIBLE
        else
            mypage_plus_btn.visibility = View.INVISIBLE
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.run {
            scrapFragment = MyPageScrapFragment()
            meFragment = MyPageMeFragment()

            adapter = MyPagePagerAdapter(supportFragmentManager, listOf(scrapFragment, meFragment))
            vp_my_page.adapter = adapter
            tl_my_page.setupWithViewPager(vp_my_page)

            val view = LayoutInflater.from(this).inflate(R.layout.my_page_tablayout, null, false)
            tl_my_page.getTabAt(0)!!.customView = view.findViewById(R.id.my_page_tablayout_srcap)
            tl_my_page.getTabAt(1)!!.customView = view.findViewById(R.id.my_page_tablayout_me)

            vp_my_page.addOnPageChangeListener(
                object : ViewPager.SimpleOnPageChangeListener() {
                    private var currentPosition = 0

                    override fun onPageSelected(position: Int) {
                        adapter.getItem(currentPosition).onPauseFragment()
                        adapter.getItem(position).onResumeFragment()
                        currentPosition = position

                        when (position) {
                            0 -> selectScrapTab()
                            1 -> selectMeTab()
                        }
                    }
                }
            )

            selectScrapTab() // 초기에는 scrap tab을 선택한다.

            btn_my_page_setting.setOnClickListener {
                startActivity<SettingActivity>()
            }

            mypage_plus_btn.setOnClickListener{
                startActivity<MakeNewArchiveActivity>()
            }

        }
    }

    override fun onResume() {
        super.onResume()
        activity?.run {
            repository.getMyInfo()
                .subscribe(
                    {
                        logger.log("mypage info success $it")
                        txt_my_page_name.text = it.name
                        txt__my_page_email.text = it.id
                        val img = it.profile_img
                        img_my_page_profile?.let {
                            Glide.with(ctx)
                                .load(img)
                                .apply(defaultHolderOptions)
                                .into(it)
                        }
                        txt_my_page_introduce.text = it.my_info
                    },
                    {
                        logger.error("my page fragment get my info error")
                        toast(R.string.network_error)
                    }
                ).apply { addDisposable(this) }
        }
    }

    private fun initAllTabItem() {
        activity?.run {
            txt_my_page_tablayout_scrap.setTextColor(ContextCompat.getColor(this, R.color.brown_grey))
            img_my_page_tablayout_scrap.visibility = View.INVISIBLE

            txt_my_page_tablayout_me.setTextColor(ContextCompat.getColor(this, R.color.brown_grey))
            img_my_page_tablayout_me.visibility = View.INVISIBLE
        }
    }

    private fun selectScrapTab() {
        activity?.run {
            initAllTabItem()
            txt_my_page_tablayout_scrap.setTextColor(ContextCompat.getColor(this, R.color.soft_blue))
            img_my_page_tablayout_scrap.visibility = View.VISIBLE
            mypage_plus_btn.visibility = View.GONE
        }
    }

    private fun selectMeTab() {
        activity?.run {
            initAllTabItem()
            txt_my_page_tablayout_me.setTextColor(ContextCompat.getColor(this, R.color.soft_blue))
            img_my_page_tablayout_me.visibility = View.VISIBLE

            meFragment.requireAddArchiveButton.subscribe(archiveAddButtonShowTask)
        }
    }

    override fun requestTopScroll() {
        super.requestTopScroll()
        sv_fragment_my_page.isSmoothScrollingEnabled = true
        sv_fragment_my_page.smoothScrollTo(0,0)
    }
}

