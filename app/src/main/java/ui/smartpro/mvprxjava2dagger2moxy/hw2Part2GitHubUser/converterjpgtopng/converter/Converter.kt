package ui.smartpro.mvprxjava2dagger2moxy.hw2Part2GitHubUser.converterjpgtopng

import android.net.Uri
import io.reactivex.Single

interface Converter {

    /**
     *
     */
    fun convert(uri: Uri): Single<Uri>

}