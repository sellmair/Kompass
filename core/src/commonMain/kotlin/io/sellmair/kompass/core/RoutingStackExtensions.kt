package io.sellmair.kompass.core

import io.sellmair.kompass.core.internal.ElementImpl


operator fun <T : Route> RoutingStack<T>.plus(element: T): RoutingStack<T> {
    return push(element)
}

fun <T : Route> RoutingStack<T>.push(element: T): RoutingStack<T> {
    return with(elements + ElementImpl(element))
}

fun <T : Route> RoutingStack<T>.push(element: RoutingStack.Element<T>): RoutingStack<T> {
    return with(elements.filterNot { it.key == element.key } + element)
}

fun <T : Route> RoutingStack<T>.clear(): RoutingStack<T> {
    return with(elements = emptyList())
}

fun <T : Route> RoutingStack<T>.pop(): RoutingStack<T> {
    return if (elements.isEmpty()) this
    else with(elements = elements.subList(0, elements.lastIndex))
}

inline fun <T : Route> RoutingStack<T>.popUntil(predicate: (T) -> Boolean): RoutingStack<T> {
    if (elements.isEmpty()) return this
    return with(elements = elements.dropLastWhile { entry -> !predicate(entry.route) })
}

fun <T : Route> RoutingStack<T>.popUntilRoute(element: T): RoutingStack<T> {
    return popUntil { it == element }
}


operator fun RoutingStack<*>.contains(key: Key): Boolean {
    return this.elements.any { it.key == key }
}

operator fun RoutingStack<*>.contains(element: RoutingStack.Element<*>): Boolean {
    return this.elements.any { it == element }
}