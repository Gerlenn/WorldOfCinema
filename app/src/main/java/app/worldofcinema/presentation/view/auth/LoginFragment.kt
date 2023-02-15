package app.worldofcinema.presentation.view.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import app.worldofcinema.R
import app.worldofcinema.databinding.FragmentLoginBinding
import app.worldofcinema.utils.NavigationHelper.navigate
import app.worldofcinema.utils.NavigationHelper.replaceGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _viewBinding: FragmentLoginBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _viewBinding = FragmentLoginBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.signIn.setOnClickListener {
            if (viewBinding.etName.text.toString().isEmpty()
                || viewBinding.etName.length() > 10
                || viewBinding.etName.length() < 3
            ) {
                viewBinding.etName.error = getString(R.string.error_login)
            } else if (viewBinding.etPassword.text.toString().isEmpty()
                || viewBinding.etPassword.length() > 10
                || viewBinding.etPassword.length() < 6
            ) {
                viewBinding.etName.error = getString(R.string.error_password)
            } else {
                viewModel.loginUser(
                    viewBinding.etName.text.toString(),
                    viewBinding.etPassword.text.toString()
                )
            }
        }

        viewModel.navigation.observe(viewLifecycleOwner) {
            if (it != null) {
                replaceGraph(it)
                viewModel.userNavigated()
            }
        }
    }
}