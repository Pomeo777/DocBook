package ua.roman777.traumabook.application

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


/**
 * Created by Roman Fedchenko
 * date 26.05.2022
 * author email pomeo77777@gmail.com
 */
object DataBindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["src"], requireAll = false)
    fun loadImage(view: ImageView, url: String?) {
        if (!TextUtils.isEmpty(url)) {


            val ro: RequestOptions = RequestOptions
                .diskCacheStrategyOf(DiskCacheStrategy.DATA)
                .centerCrop()
            //                    .override(1000, 600);
            Glide.with(view.context)
                .load( url)
                .apply(ro)
                .into(view)
        }
    }
}