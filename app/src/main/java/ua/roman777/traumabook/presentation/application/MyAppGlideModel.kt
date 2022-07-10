package ua.roman777.traumabook.presentation.application

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions


/**
 * Created by Roman Fedchenko
 * date 24.05.2022
 * author email pomeo77777@gmail.com
 */
@GlideModule
class MyAppGlideModel: AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {

        val ro: RequestOptions = RequestOptions
            .diskCacheStrategyOf(DiskCacheStrategy.DATA)
            .error(android.R.drawable.stat_notify_error)
            .centerCrop()
        builder.setDefaultRequestOptions(ro)
    }
}