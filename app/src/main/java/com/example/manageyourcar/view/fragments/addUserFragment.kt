package com.example.manageyourcar.view.fragments

import android.os.Bundle
import android.telephony.SmsManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.manageyourcar.R
import com.example.manageyourcar.databinding.FragmentAddUserBinding
import com.example.manageyourcar.viewmodel.UserViewModel
import com.google.android.material.textfield.TextInputLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/**
 * A simple [Fragment] subclass.
 * Use the [addUserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class addUserFragment : Fragment() {

    private lateinit var binding: FragmentAddUserBinding
    private val userViewModel : UserViewModel by lazy { ViewModelProvider(this).get(UserViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddUserBinding.inflate(layoutInflater)
        val view = binding.root
        userViewModel.liveDataConnect.observe(viewLifecycleOwner, {
            it.
        })
        binding.mailUser
        binding.passwordUser
        binding.confPassword
        binding.layoutMailUser
        binding.layoutConfPassword
        binding.layoutPasswordUser
        binding.layoutNameUser
        binding.nameUser
        binding.buttonConf.setOnClickListener { println("f") }

        return view
    }

    companion object {
        fun newInstance() {}

    }

    // fun sendConfSMS(){ Faire si le temps
//       val smsManager= activity?.getSystemService(SmsManager::class.java)
//       smsManager?.sendTextMessage()
//    }
//}
}