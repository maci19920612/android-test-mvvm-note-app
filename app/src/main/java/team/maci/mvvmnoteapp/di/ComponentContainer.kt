package team.maci.mvvmnoteapp.di

object ComponentContainer{
    private var component: Any? = null

    fun init(component: Any){
        this.component = component
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> get() : T{
        val component = this.component ?: throw IllegalStateException("The ComponentContainer wasn't initialized")
        return component as T
    }
}