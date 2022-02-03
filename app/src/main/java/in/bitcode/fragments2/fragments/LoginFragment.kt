package `in`.bitcode.fragments2.fragments

import `in`.bitcode.fragments2.R
import `in`.bitcode.fragments2.databinding.LoginFragmentBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(
            inflater,
            container,
            false
        )

        setUpListeners()

        return binding.root
    }

    private fun setUpListeners() {
        binding.btnLogin.setOnClickListener(BtnLoginClickListener())
        //binding.btnLogin.setOnClickListener(btnLoginClickListener)
    }

    private inner class BtnLoginClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            if (binding.edtUsername.text.toString().equals("bitcode")) {

                var input = Bundle()
                input.putString("username", binding.edtUsername.text.toString())

                var homeFragment = HomeFragment()
                homeFragment.arguments = input
                homeFragment.onUserLogoutListener = UserLogoutListener()

                parentFragmentManager.beginTransaction()
                    .add(R.id.mainContainer, homeFragment, HomeFragment::class.java.name)
                    .addToBackStack(null)
                    .commit()

            }
        }
    }

    inner class UserLogoutListener : HomeFragment.OnUserLogoutListener {
        override fun onUserLogout(username: String) {
            Toast.makeText(
                requireContext(),
                "$username has logged out!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

}