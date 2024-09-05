package com.altaf.fininfodemoapp.adapter

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.altaf.fininfodemoapp.R
import com.altaf.fininfodemoapp.databinding.ItemNumberBinding
import com.altaf.fininfodemoapp.model.NumberItem

class NumberAdapter(private val items: List<NumberItem>) :
    RecyclerView.Adapter<NumberAdapter.NumberViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNumberBinding.inflate(inflater, parent, false)
        return NumberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.bind(items[position])
    }


    override fun getItemCount(): Int = items.size

    inner class NumberViewHolder(private val binding: ItemNumberBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(numberItem: NumberItem) {
            binding.numberTextView.text = numberItem.value.toString()
            if (numberItem.isHighlighted) {
                binding.numberTextView.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.highlighted
                    )
                )
                binding.numberTextView.setTypeface(
                    binding.numberTextView.typeface,
                    Typeface.BOLD_ITALIC
                )
            } else {
                binding.numberTextView.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.normal_num
                    )
                )
                binding.numberTextView.setTypeface(binding.numberTextView.typeface, Typeface.NORMAL)
            }
        }
    }
}

