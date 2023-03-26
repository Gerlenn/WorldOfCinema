package app.worldofcinema.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

object NavigationHelper {

    fun Fragment.replaceGraph(graphId: Int) {
        findNavController().setGraph(graphId)
    }

    fun Fragment.navigateWithBundleID(destination: Int, bundle: Bundle) {
        findNavController().navigate(destination, bundle)
    }
}
