package app.worldofcinema.utils

import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

object NavigationHelper {

    fun Fragment.navigate(destination: Int) {
        findNavController().navigate(destination)
    }

    fun Fragment.replaceGraph(graphId: Int) {
        findNavController().setGraph(graphId)
    }
}