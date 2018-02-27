package com.icelabs.dothislist

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.Checkable

class CustomListItemLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs), Checkable {

    private var itemChecked = false

    override fun isChecked(): Boolean {
        return itemChecked
    }

    override fun setChecked(b: Boolean) {
        if (b != itemChecked) {
            itemChecked = b
            refreshDrawableState()
        }
    }

    override fun toggle() {
        isChecked = !itemChecked
    }

    public override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableState = super.onCreateDrawableState(extraSpace + 1)

        if (isChecked) {
            View.mergeDrawableStates(drawableState, CHECKED_STATE_SET)
        }

        return drawableState
    }

    override fun performClick(): Boolean {
        toggle()
        return super.performClick()
    }

    companion object {
        private val CHECKED_STATE_SET = intArrayOf(android.R.attr.state_checked)
    }

}
