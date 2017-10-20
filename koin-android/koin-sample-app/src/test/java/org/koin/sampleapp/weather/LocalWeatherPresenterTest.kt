package org.koin.sampleapp.weather

import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.koin.sampleapp.di.testLocalDatasource
import org.koin.sampleapp.repository.WeatherDatasource
import org.koin.sampleapp.util.any
import org.koin.standalone.startContext
import org.koin.test.components.TestComponent
import org.koin.test.components.inject
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LocalWeatherPresenterTest : TestComponent {

    val presenter by inject<WeatherContract.Presenter>()

    @Mock lateinit var view: WeatherContract.View
    @Mock lateinit var weatherWS: WeatherDatasource


    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        startContext(testLocalDatasource())

        presenter.view = view
    }

    @Test
    fun testDisplayWeather() {
        Assert.assertNotNull(presenter)

        val locationString = "Paris, france"
        presenter.getWeather(locationString)

        Mockito.verify(view).displayWeather(any(), any())
    }

}