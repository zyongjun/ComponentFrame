package com.joe.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.joe.base.extension.loge

open  class BaseActivity:AppCompatActivity(){

    open fun getLayoutId():Int{
        return  R.layout.activity_fragment_container
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        loge("--------------------------------------------------")
        var fragment:Fragment? = null
        if (intent != null && intent.getStringExtra(Navigator.KEY_FRAGMENT_NAME) != null) {
            val fragmentName = intent.getStringExtra(Navigator.KEY_FRAGMENT_NAME)
            val bundle = intent.getBundleExtra(Navigator.KEY_FRAGMENT_ARGS)
            fragment = Fragment.instantiate(this,fragmentName,bundle)
        }
        setContentView(getLayoutId())
        if (fragment != null) {
            replaceFragment(fragment)
        }
    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentTransient = supportFragmentManager.beginTransaction()
        fragmentTransient.replace(R.id.fragment_contrainer,fragment)
        fragmentTransient.commit()
    }

    override fun onBackPressed() {
        val fragments = supportFragmentManager.fragments
        if (fragments != null && fragments.size > 0) {
            if (!fragments[0].onBackPressed()) {
                super.onBackPressed()
            }
        }
        super.onBackPressed()
    }

}

object Navigator{
    val KEY_FRAGMENT_NAME = "KEY_FRAGMENT_NAME"
    val KEY_FRAGMENT_ARGS = "KEY_FRAGMENT_ARGS"
    fun startCommon(context:Context,fragmentName:String,bundle:Bundle,flags:Int = -1) {
        val intent = Intent(context,CommonActivity::class.java)
        intent.putExtra(KEY_FRAGMENT_NAME,fragmentName)
        intent.putExtra(KEY_FRAGMENT_ARGS,bundle)
        if (flags != -1) {
            intent.addFlags(flags)
        }
        context.startActivity(intent)
    }

    fun startFull(context:Context,fragmentName:String,bundle:Bundle,flags:Int = -1) {
        val intent = Intent(context,FullScreenActivity::class.java)
        intent.putExtra(KEY_FRAGMENT_NAME,fragmentName)
        intent.putExtra(KEY_FRAGMENT_ARGS,bundle)
        if (flags != -1) {
            intent.addFlags(flags)
        }
        context.startActivity(intent)
    }
}