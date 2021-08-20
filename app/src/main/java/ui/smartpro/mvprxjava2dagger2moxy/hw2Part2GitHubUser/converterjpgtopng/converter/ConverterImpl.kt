package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.converterjpgtopng

import android.content.Context
import android.net.Uri
import io.reactivex.Single

class ConverterImpl(private val context: Context) : Converter {

    override fun convert(uri: Uri): Single<Uri> =
        ConverterSingle(context, uri)

}