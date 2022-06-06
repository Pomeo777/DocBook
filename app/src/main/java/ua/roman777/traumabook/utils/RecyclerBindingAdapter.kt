package ua.roman777.traumabook.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber
import ua.roman777.traumabook.utils.diffUtilCallbacks.PatientDiffUtilCallback
import java.util.*


/**
 * Created by Roman Fedchenko
 * date 24.05.2022
 * author email pomeo77777@gmail.com
 */

class RecyclerBindingAdapter<T>(var holderLayout: Int, var variableId: Int, var items: MutableList<T>) :
    RecyclerView.Adapter<BindingHolder?>() {

    private var onItemClickListener: OnItemClickListener<T>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(holderLayout, parent, false)
        return BindingHolder(v)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val item = items[position]
        //        holder.getBinding().getRoot().setOnClickListener(v -> {
//            if (onItemClickListener != null)
//                onItemClickListener.onItemClick(position, item);
//        });
        holder.getBinding().setVariable(variableId, item)
        holder.getBinding().setVariable(BR.clickListener, onItemClickListener)
        holder.getBinding().setVariable(BR.position, position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(list: MutableList<T>, diffUtilCallback: DiffUtil.Callback ){
        Timber.d("update(). list: %s", list.toString())
        Timber.d("update(). items: %s", items.toString())
        val taskDiffResult = DiffUtil.calculateDiff(diffUtilCallback)
        items.clear()
        Timber.d("update(). list: %s", list.toString())
        items.addAll(list)
        Timber.d("update(). items: %s", items.toString())
        taskDiffResult.dispatchUpdatesTo(this)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<T>?) {
        this.onItemClickListener = onItemClickListener
    }
}

class BindingHolder(v: View) : RecyclerView.ViewHolder(v) {
    private val binding: ViewDataBinding
    fun getBinding(): ViewDataBinding {
        return binding
    }

    init {
        binding = DataBindingUtil.bind(v)!!
    }
}

interface OnItemClickListener<T> {
    fun onItemClick(item: T, position: Int, element: String)
}
