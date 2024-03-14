package com.belkanoid.log_in.presentation


import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.belkanoid.core.ui.factory.ViewModelFactory
import com.belkanoid.log_in.R
import com.belkanoid.log_in.databinding.FragmentLoginBinding
import com.belkanoid.log_in.di.LoginComponentHolder
import com.belkanoid.core.ui.NumberTextWatcher
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
        observeUserInputs()

        binding.button.setOnClickListener {
            viewModel.saveUser(
                binding.tvNameInputLayout.loginEditText.text.toString(),
                binding.tvSurnameInputLayout.loginEditText.text.toString(),
                binding.tvPhoneInputLayout.loginEditText.text.toString(),
            )
        }
    }

    private fun initializeForceFocusOnEndIcon() {
        binding.tvNameInputLayout.loginEditText.setOnFocusChangeListener { _, _ ->
            binding.tvNameInputLayout.loginInputLayout.isEndIconVisible = true
        }
        binding.tvSurnameInputLayout.loginEditText.setOnFocusChangeListener { _, _ ->
            binding.tvSurnameInputLayout.loginInputLayout.isEndIconVisible = true
        }
        binding.tvPhoneInputLayout.loginEditText.setOnFocusChangeListener { _, _ ->
            binding.tvPhoneInputLayout.loginInputLayout.isEndIconVisible = true
        }
    }

    private fun observeUserInputs() {
        with(binding) {
            viewModel.fieldState.onEach { state ->
                tvNameInputLayout.loginInputLayout.error = state.nameErrorMessage
                tvSurnameInputLayout.loginInputLayout.error = state.surnameErrorMessage
            }.launchIn(lifecycleScope)

            viewModel.isAllFieldsValid.onEach {
                button.isEnabled = it
            }.launchIn(lifecycleScope)
        }
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
            tvNameInputLayout.loginEditText.addTextChangedListener {
                viewModel.updateNameField(it.toString())

                if (it.toString().isNotEmpty()) {
                    tvNameInputLayout.loginInputLayout.isHintEnabled = false
                }
            }
            tvSurnameInputLayout.loginEditText.addTextChangedListener {
                viewModel.updateSurnameField(it.toString())

                if (it.toString().isNotEmpty()) {
                    tvSurnameInputLayout.loginInputLayout.isHintEnabled = false
                }
            }

            tvPhoneInputLayout.loginEditText.addTextChangedListener {
                viewModel.updatePhoneField(it.toString())

                if (it.toString().isNotEmpty()) {
                    tvPhoneInputLayout.loginInputLayout.isHintEnabled = false
                }
            }
        }
    }

    private fun initializeErrorIconOnClick() {
        with(binding) {
            tvNameInputLayout.loginInputLayout.setErrorIconOnClickListener {
                tvNameInputLayout.loginEditText.text = null
                viewModel.updateNameField("")
            }

            tvSurnameInputLayout.loginInputLayout.setErrorIconOnClickListener {
                tvSurnameInputLayout.loginEditText.text = null
                viewModel.updateSurnameField("")
            }

            tvPhoneInputLayout.loginInputLayout.setErrorIconOnClickListener {
                tvPhoneInputLayout.loginEditText.text = null
                viewModel.updatePhoneField("")
            }
        }

    }

    override fun onDetach() {
        super.onDetach()
        LoginComponentHolder.clear()
    }
}

