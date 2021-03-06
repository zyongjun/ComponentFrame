package com.joe.frame.fragment

import android.os.Bundle
import android.view.View
import com.joe.base.BaseFragment
import com.joe.base.Navigator
import com.joe.base.widget.appBarBuilder
import com.joe.frame.R
import kotlinx.android.synthetic.main.fragment_teststageandroid_main.*

class TestStageAndroidFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_teststageandroid_main
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appBarBuilder().showLeft(true)
                .withTitle("进阶之光")
                .show()
        tvAnimator.setOnClickListener {
            Navigator.startCommon(it.context,AnimatorFragment::class.java.name,Bundle())
        }
    }
}