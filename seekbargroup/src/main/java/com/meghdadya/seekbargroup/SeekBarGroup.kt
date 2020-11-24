package com.meghdadya.seekbargroup

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.SeekBar
import androidx.core.view.children


class SeekBarGroup @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
        defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes), SeekBar.OnSeekBarChangeListener {
    private var mTotalPercentage = 100

    init {
        orientation = VERTICAL
    }

    fun addSeekBarView(child: SeekBar?) {
        if (children.count() < mTotalPercentage)
            child?.let {
                this.addView(child)
                setPriceTemplateValue(child)
                child.setOnSeekBarChangeListener(this)
                configChildrenViewRules()
            }
    }

    fun addSeekBarViewList(children: List<SeekBar?>) {
        children.forEach { priceTemplate ->
            addSeekBarView(priceTemplate)
        }
    }

    private fun setPriceTemplateValue(seekBar: SeekBar) {
        children.forEachIndexed { index, view ->
            if (childCount > 1) {
                val preItem = children.elementAt((childCount - 1) - index) as SeekBar
                if (preItem.progress > 1) {
                    preItem.progress = preItem.progress - 1
                    seekBar.progress = 1
                    return
                }
            } else {
                seekBar.progress = mTotalPercentage
            }
        }
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        seekBar?.let {
            if (fromUser) {
                val currentIndex = children.indexOf(seekBar)
                val nextItem = children.elementAt(currentIndex + 1) as SeekBar
                var usedPercentage = 0
                children.forEachIndexed { index, priceTemplate ->
                    val item = priceTemplate as SeekBar
                    usedPercentage += if (index != currentIndex && index != currentIndex + 1) {
                        item.progress
                    } else {
                        0
                    }
                }
                val leftProgress = mTotalPercentage - usedPercentage
                if (progress > leftProgress) {
                    seekBar.progress = leftProgress
                }
                nextItem.progress = mTotalPercentage - (usedPercentage + progress)
            }
        }
    }


    private fun configChildrenViewRules() {
        children.forEachIndexed { index, view ->
            val item = view as SeekBar
            // disable the last item always
            item.isEnabled = childCount != (index + 1)
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        seekBar?.let {
            //
        }
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        seekBar?.let {
            //
        }
    }
}