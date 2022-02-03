package `in`.bitcode.fragments2.fragments

import `in`.bitcode.fragments2.databinding.HomeFragmentBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding
    private var username = "Guest"

    interface OnUserLogoutListener {
        fun onUserLogout(username: String)
    }

    var onUserLogoutListener: OnUserLogoutListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mt("onCreateView : HomeFragment")

        binding = HomeFragmentBinding.inflate(
            inflater,
            container,
            false
        )

        binding.root.setOnClickListener { }

        username = arguments?.getString("username") ?: "Guest"

        binding.txtUsername.text = "Welcome $username!"


        binding.btnLogout.setOnClickListener {

            onUserLogoutListener?.onUserLogout(username)

            parentFragmentManager.beginTransaction()
                .remove(this)
                .commit()
        }

        return binding.root

    }

    private fun mt(text: String) {
        Toast.makeText(requireActivity(), text, Toast.LENGTH_LONG).show()
    }
}