package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.converterjpgtopng

import android.content.Context

object ConverterFactory {

    fun create(context: Context): Converter = ConverterImpl(context)

}