package com.example.leagueoflegendschampions.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.GrantPermissionRule
import com.example.leagueoflegendschampions.R
import com.example.leagueoflegendschampions.data.server.ChampionsDb
import com.example.leagueoflegendschampions.utils.fromJson
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.koin.test.KoinTest
import org.koin.test.get


class UiTest : KoinTest {

    private val mockWebServerRule = MockWebServerRule()

    @get:Rule
    val testRule: RuleChain =
        RuleChain.outerRule(mockWebServerRule)
            .around(GrantPermissionRule.grant(
                "android.permission.ACCESS_COARSE_LOCATION"
            ))
            .around(ActivityScenarioRule(NavHostActivity::class.java))
    @Before
    fun setUp() {
        mockWebServerRule.server.enqueue(
            MockResponse().fromJson("champions.json")
        )

        val resource = OkHttp3IdlingResource.create("OkHttp", get<ChampionsDb>().okHttpClient)
        IdlingRegistry.getInstance().register(resource)
    }


    @Test
    fun clickAChampionNavigateToDetail() {
        onView(withId(R.id.progress)).check(matches(isDisplayed()))
        onView(
            withId(R.id.championListView)
        ).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                5,
                click()
            )
        )

        onView(withId(
            R.id.championDetailToolbar
        )).check(matches(hasDescendant(withText(TITLE))))
    }

    companion object{
        private const val TITLE = "Anivia"
    }
}