package ua.roman777.traumabook.ui.photoViewer

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ua.roman777.traumabook.dataBase.dataEntity.Photo


/**
 * Created by Roman Fedchenko
 * date 26.05.2022
 * author email pomeo77777@gmail.com
 */
class PhotoViewPagerAdapter(val fragment: Fragment, val list: MutableList<PhotoItemFragment>) :
    FragmentStateAdapter(fragment) {


    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): PhotoItemFragment {
        return list[position]
    }
}