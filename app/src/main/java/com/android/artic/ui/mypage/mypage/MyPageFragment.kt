package com.android.artic.ui.mypage.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.android.artic.R
import com.android.artic.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_my_page.*

class MyPageFragment : BaseFragment(R.layout.fragment_my_page) {



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.run {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.mypage_frame, MyPageScrapFragment(), "my page")
            transaction.commit()



            mypage_tab_scrap.setOnClickListener {
                replaceFragment(MyPageScrapFragment())
                //transaction.replace(R.id.mypage_frame, MyPageScrapFragment(),"my page scrap")


            }

            mypage_tab_me.setOnClickListener {
                replaceFragment(MyPageMeFragment())
                // transaction.replace(R.id.mypage_frame,MyPageMeFragment(),"my page me")
            }
        }
    }

    fun replaceFragment( fragment: Fragment) {
        activity?.run {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.mypage_frame, fragment).commit()
        }
    }
}
