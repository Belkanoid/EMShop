package com.belkanoid.log_in.presentation


import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.belkanoid.core.ui.factory.ViewModelFactory
import com.belkanoid.log_in.R
import com.belkanoid.log_in.databinding.FragmentLoginBinding
import com.belkanoid.log_in.di.LoginComponentHolder
import com.belkanoid.core.ui.NumberTextWatcher
import com.belkanoid.log_in.presentation.state.LoginScreenState
import com.belkanoid.log_in.presentation.viewModel.LoginViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class LoginFragment : Fragment(R.layout.fragment_login) {

    @Inject lateinit var factory: ViewModelFactory
    private val viewModel by viewModels<LoginViewModel>{factory}
    private val binding by viewBinding(FragmentLoginBinding::bind)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        LoginComponentHolder.get().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeForceFocusOnEndIcon()
        initializeErrorIconOnClick()
        initializeOnTextChanged()
        initializeInputLayouts()
        observeState()

        binding.button.setOnClickListener {
            viewModel.saveUser(
                binding.tvNameInputLayout.loginEditText.text.toString(),
                binding.tvSurnameInputLayout.loginEditText.text.toString(),
                binding.tvPhoneInputLayout.loginEditText.text.toString(),
            )
        }
    }

    private fun initializeForceFocusOnEndIcon() {
        binding.tvNameInputLayout.loginEditText.setOnFocusChangeListener { v, hasFocus ->
            binding.tvNameInputLayout.loginInputLayout.isEndIconVisible = true
        }
        binding.tvSurnameInputLayout.loginEditText.setOnFocusChangeListener { v, hasFocus ->
            binding.tvSurnameInputLayout.loginInputLayout.isEndIconVisible = true
        }
        binding.tvPhoneInputLayout.loginEditText.setOnFocusChangeListener { v, hasFocus ->
            binding.tvPhoneInputLayout.loginInputLayout.isEndIconVisible = true
        }
    }

    private fun observeState() {
        viewModel.state.onEach { state ->

            when (state) {
                LoginScreenState.Empty -> {
                    binding.button.isEnabled = false
                }
                is LoginScreenState.NameField -> {
                    binding.tvNameInputLayout.loginInputLayout.error = state.errorState
                }
                is LoginScreenState.SurnameField -> {
                    binding.tvSurnameInputLayout.loginInputLayout.error = state.errorState
                }
                is LoginScreenState.PhoneField -> {
                    binding.tvPhoneInputLayout.loginInputLayout.error = state.errorState
                }
                LoginScreenState.Success -> {
                    binding.button.isEnabled = true
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun initializeInputLayouts() {
        with(binding) {
            tvNameInputLayout.loginInputLayout.hint = getString(R.string.login_hint_name)

            tvSurnameInputLayout.loginInputLayout.hint = getString(R.string.login_hint_surname)

            tvPhoneInputLayout.loginInputLayout.hint = getString(R.string.login_hint_phone)
            tvPhoneInputLayout.loginEditText.inputType = InputType.TYPE_CLASS_PHONE
            tvPhoneInputLayout.loginEditText.addTextChangedListener(
                NumberTextWatcher("+# ### ###-##-##")
            )
        }
    }

    private fun initializeOnTextChanged() {
        with(binding) {
            tvNameInputLayout.loginEditText.doOnTextChanged { text, _, _, _ ->
                viewModel.validateName(text.toString())

                if (text.toString().isNotEmpty()) {
                    tvNameInputLayout.loginInputLayout.isHintEnabled = false
                    binding.tvNameInputLayout.loginInputLayout.isEndIconVisible = true
                } else {
                    viewModel.validateName("")
//                    binding.tvNameInputLayout.loginInputLayout.isEndIconVisible = false
                }
            }
            tvSurnameInputLayout.loginEditText.doOnTextChanged { text, _, _, _ ->
                viewModel.validateSurname(text.toString())

                if (text.toString().isNotEmpty()) {
                    tvSurnameInputLayout.loginInputLayout.isHintEnabled = false
//                    binding.tvSurnameInputLayout.loginInputLayout.isEndIconVisible = true
                } else {
                    viewModel.validateSurname("")
//                    binding.tvSurnameInputLayout.loginInputLayout.isEndIconVisible = false

                }
            }

            tvPhoneInputLayout.loginEditText.doOnTextChanged { text, _, _, _ ->
                viewModel.validatePhone(text.toString())

                if (text.toString().isNotEmpty()) {
                    tvPhoneInputLayout.loginInputLayout.isHintEnabled = false
//                    binding.tvPhoneInputLayout.loginInputLayout.isEndIconVisible = true
                } else {
                    viewModel.validatePhone("")
//                    binding.tvPhoneInputLayout.loginInputLayout.isEndIconVisible = false

                }
            }
        }
    }

    private fun initializeErrorIconOnClick() {
        with(binding) {
            tvNameInputLayout.loginInputLayout.setErrorIconOnClickListener {
                tvNameInputLayout.loginEditText.text = null
                viewModel.validateName("")
            }

            tvSurnameInputLayout.loginInputLayout.setErrorIconOnClickListener {
                tvSurnameInputLayout.loginEditText.text = null
                viewModel.validateSurname("")
            }

            tvPhoneInputLayout.loginInputLayout.setErrorIconOnClickListener {
                tvPhoneInputLayout.loginEditText.text = null
                viewModel.validatePhone("")
            }
        }

    }

    override fun onDetach() {
        super.onDetach()
        LoginComponentHolder.clear()
    }
}

