package ua.roman777.traumabook.utils

import android.view.View


/**
 * Created by Roman Fedchenko
 * date 24.02.2021
 * author email pomeo77777@gmail.com
 */
class DebounceOnClickListener(
        private val interval: Long,
        private val listenerBlock: (View) -> Unit
): View.OnClickListener {
    private var lastClickTime = 0L

    override fun onClick(v: View) {
        val time = System.currentTimeMillis()
        if (time - lastClickTime >= interval) {
            lastClickTime = time
            listenerBlock(v)
        }
    }
}

fun View.setOnClickListener(debounceInterval: Long, listenerBlock: (View) -> Unit) =
        setOnClickListener(DebounceOnClickListener(debounceInterval, listenerBlock))