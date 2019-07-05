package com.android.artic.ui.search_result

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.android.artic.data.Archive
import com.android.artic.data.Article
import com.android.artic.repository.ArticRepository
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject

// TODO 얘가 search result 결과를 전부 받아서 그려주기만 해야한다. 즉, Search Result Activity 에서 전부 데이터 통신이 끝나고 데이터를 넘겨주면 다시 적용하도록 구성해야 한다?
class SearchResultAdapter (
    fm:FragmentManager,
    private val num_fragmnet:Int,
    private val searchKeyword: String
) : FragmentStatePagerAdapter(fm) {

    companion object{
        private var archiveResultFragment: ArchiveListFragment?=null
        private var linkResultFragment: LinkResultFragment?=null
    }

    fun getArchiveResultFragment () : ArchiveListFragment {
        if(archiveResultFragment ==null)
            archiveResultFragment = ArchiveListFragment(searchKeyword)
        return archiveResultFragment!!
    }

    fun getLinkReultFragment () : LinkResultFragment {
        if(linkResultFragment ==null)
            linkResultFragment = LinkResultFragment(searchKeyword)
        return linkResultFragment!!
    }


    override fun getItem(position: Int): Fragment? {
        return when(position) {

            0-> ArchiveListFragment(searchKeyword)
            1-> LinkResultFragment(searchKeyword)
            else->null
        }

    }

    override fun getCount(): Int {
        return num_fragmnet

            }

}
