package com.android.artic.ui.search_result

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class SearchResultAdapter (fm:FragmentManager,private val num_fragmnet:Int) : FragmentStatePagerAdapter(fm) {
    companion object{
        private var archiveResultFragment: ArchiveResultFragment?=null
        private var linkResultFragment: LinkResultFragment?=null
    }

    fun getArchiveResultFragment () : ArchiveResultFragment {
        if(archiveResultFragment ==null)  archiveResultFragment =
            ArchiveResultFragment()
        return archiveResultFragment!!
    }

    fun getLinkReultFragment () : LinkResultFragment {
        if(linkResultFragment ==null)  linkResultFragment = LinkResultFragment()
        return linkResultFragment!!
    }


    override fun getItem(position: Int): Fragment? {
        return when(position) {
            0-> ArchiveResultFragment()
            1-> LinkResultFragment()
            else->null
        }

    }

    override fun getCount(): Int {
        return num_fragmnet

            }

}
