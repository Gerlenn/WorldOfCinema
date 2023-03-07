package app.worldofcinema.presentation.view.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import app.worldofcinema.databinding.FragmentLoginBinding
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

            val username = viewBinding.etName.text.toString()
            val password = viewBinding.etPassword.text.toString()
            viewModel.validateCredentials(username, password)
        }
        viewModel.loginState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoginState.Success -> {
                    viewModel.loginUser(
                        viewBinding.etName.toString(),
                        viewBinding.etPassword.toString()
                    )
                }
                is LoginState.Error -> {
                    val message = getString(state.messageResId)
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                }
            }

            viewModel.errorLoginUser.observe(viewLifecycleOwner) { errLoginUser ->
                Toast.makeText(context, getString(errLoginUser), Toast.LENGTH_SHORT).show()
            }

            viewModel.navigation.observe(viewLifecycleOwner) {
                if (it != null) {
                    replaceGraph(it)
                    viewModel.userNavigated()
                }
            }
        }
    }
}