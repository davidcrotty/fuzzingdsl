package net.davidcrotty.unittestdsl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class DrinkUseCaseTest {

    @get:Rule
    val instant: TestRule = InstantTaskExecutorRule()

    @Test
    fun `when calculating an order`() {
        Assert.assertEquals(true, true)
    }
}