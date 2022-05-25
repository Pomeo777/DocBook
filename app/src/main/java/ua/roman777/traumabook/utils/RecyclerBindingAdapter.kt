package ua.roman777.traumabook.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import java.util.*


/**
 * Created by Roman Fedchenko
 * date 24.05.2022
 * author email pomeo77777@gmail.com
 */

class RecyclerBindingAdapter<T>(var holderLayout: Int, var variableId: Int, var items: MutableList<T>) :
    RecyclerView.Adapter<RecyclerBindingAdapter.BindingHolder?>() {

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
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<T>?) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener<T> {
        fun onItemClick(position: Int, item: T)
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

}