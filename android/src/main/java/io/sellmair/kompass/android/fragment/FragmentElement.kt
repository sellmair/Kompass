package io.sellmair.kompass.android.fragment

import androidx.fragment.app.Fragment
import io.sellmair.kompass.core.Route
import io.sellmair.kompass.core.RoutingStack

interface FragmentElement<T : Route> : RoutingStack.Element<T> {

    interface Factory<T : Route> {
        operator fun invoke(element: RoutingStack.Element<T>): FragmentElement<T>
    }


    fun createFragment(): Fragment

}

