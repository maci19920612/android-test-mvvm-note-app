package team.maci.mvvmnoteapp.widget

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import team.maci.mvvmnoteapp.di.ComponentContainer.init

class VerticalRecyclerView : RecyclerView{
    constructor(context: Context) : super(context){
        init()
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        init()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle){
        init()
    }

    private fun init(){
        layoutManager = LinearLayoutManager(context)
    }
}