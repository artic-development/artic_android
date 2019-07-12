package com.articrew.artic

import com.articrew.artic.util.getDomainName
import junit.framework.Assert.assertEquals
import org.junit.Test

class GetDomainNameTest {
    @Test
    fun getMediumDomainTest() {
        val url = "https://medium.com/@navalkishorebdev/tablayout-gravity-fill-fixed-mode-f48ac83ce2d9"
        assertEquals("medium.com", getDomainName(url))
    }

    @Test
    fun getTistoryDomainTest() {
        var url = "https://greedy0110.tistory.com/28"
        assertEquals("tistroy.com", getDomainName(url))
    }
}