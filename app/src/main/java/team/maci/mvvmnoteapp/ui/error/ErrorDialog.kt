package team.maci.mvvmnoteapp.ui.error

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface

class ErrorDialog : Dialog{
    constructor(context: Context) : super(context)
    constructor(context: Context, themeResId: Int) : super(context, themeResId)
    constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?) : super(
        context,
        cancelable,
        cancelListener
    )

    private fun init(){}
}