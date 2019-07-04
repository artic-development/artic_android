package com.android.artic.ui.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.android.artic.R
import kotlinx.android.synthetic.main.activity_my_page.*

class MyPageActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.mypage_frame,MyPageScrapFragment(),"my page")
        transaction.commit()

        mypage_tab_scrap.setOnClickListener{
            replaceFragment(MyPageScrapFragment())
            //transaction.replace(R.id.mypage_frame, MyPageScrapFragment(),"my page scrap")

        }

        mypage_tab_me.setOnClickListener{
           replaceFragment(MyPageMeFragment())
            // transaction.replace(R.id.mypage_frame,MyPageMeFragment(),"my page me")
        }



    }

    fun replaceFragment( fragment: Fragment) {
        val transaction= supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mypage_frame,fragment).commit()
    }
}
