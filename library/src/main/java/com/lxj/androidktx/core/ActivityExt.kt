package com.lxj.androidktx.core

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.support.v4.app.Fragment
import android.view.View

/**
 * Description: Activity相关
 * Create by lxj, at 2018/12/7
 */

inline fun <reified T> Fragment.startActivity(flag: Int = -1, bundle: Array<out Pair<String, Any?>>? = null) {
    activity?.startActivity<T>(flag, bundle)
}

inline fun <reified T> Fragment.startActivityForResult(flag: Int = -1, bundle: Array<out Pair<String, Any?>>? = null, requestCode: Int = -1) {
    activity?.startActivityForResult<T>(flag, bundle, requestCode)
}

inline fun <reified T> Context.startActivity(flag: Int = -1, bundle: Array<out Pair<String, Any?>>? = null, requestCode: Int = -1) {
    val intent = Intent(this, T::class.java).apply {
        if (flag != -1) {
            this.addFlags(flag)
        } else if (this !is Activity) {
            this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        if (bundle != null) putExtras(bundle.toBundle())
    }
    startActivity(intent)
}

inline fun <reified T> View.startActivity(flag: Int = -1, bundle: Array<out Pair<String, Any?>>? = null) {
    context.startActivity<T>(flag, bundle)
}

inline fun <reified T> View.startActivityForResult(flag: Int = -1, bundle: Array<out Pair<String, Any?>>? = null, requestCode: Int = -1) {
    (context as Activity).startActivityForResult<T>(flag, bundle, requestCode)
}

inline fun <reified T> Activity.startActivityForResult(flag: Int = -1, bundle: Array<out Pair<String, Any?>>? = null, requestCode: Int = -1) {
    val intent = Intent(this, T::class.java).apply {
        if (flag != -1) {
            this.addFlags(flag)
        }
        if (bundle != null) putExtras(bundle.toBundle())
    }
    startActivityForResult(intent, requestCode)
}

fun Activity.finishDelay(delay: Long = 1) {
    Handler(Looper.getMainLooper()).postDelayed({ finish() }, delay)
}

//post, postDelay
fun Activity.post(action: ()->Unit){
    Handler().post { action() }
}

fun Activity.postDelay(delay:Long = 0, action: ()->Unit){
    Handler().postDelayed({ action() }, delay)
}