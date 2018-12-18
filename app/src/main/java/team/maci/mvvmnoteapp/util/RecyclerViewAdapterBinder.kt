package team.maci.mvvmnoteapp.util

import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods

@BindingMethods(
    value = [
        BindingMethod(
            type = RecyclerViewAdapterBinder::class,
            attribute = "app:adapter",
            method = "setAdapter"
        )
    ]
)
open class RecyclerViewAdapterBinder {}