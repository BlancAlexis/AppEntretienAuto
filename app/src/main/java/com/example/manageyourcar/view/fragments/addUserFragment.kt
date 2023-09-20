package com.example.manageyourcar.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.manageyourcar.R
import com.example.manageyourcar.databinding.FragmentAddUserBinding
import com.example.manageyourcar.view.activities.HomeActivity
import com.example.manageyourcar.viewmodel.UserViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/**
 * A simple [Fragment] subclass.
 * Use the [addUserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class addUserFragment : Fragment() {

    private lateinit var binding: FragmentAddUserBinding
    private val userViewModel: UserViewModel by lazy { ViewModelProvider(this).get(UserViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddUserBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        userViewModel.liveDataConnect.observe(viewLifecycleOwner) {
            if (it) {
                //Si le bool reçu est à true alors on lance l'activité suivante
                startActivity(Intent(activity, HomeActivity::class.java))
            }
        }
        binding.mailUser.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                if (!areTextInputLayoutsValid(binding.nameUser.text.toString(), "ccc")) {
                    binding.layoutNameUser.setBoxBackgroundColorResource(R.color.red_error)
                    //binding.layoutNameUser.setBoxBackgroundColorResource(R.color.red_error)
                }
            }
        }
        binding.passwordUser
        binding.confPassword
        binding.layoutMailUser
        binding.layoutConfPassword
        binding.layoutPasswordUser
        binding.layoutNameUser //"^[a-zA-Z\\- ]+$"
        binding.nameUser

        // Check des entrées dans variable, si oui envoie des infos au VM qui répond via liveData
        binding.buttonConf.setOnClickListener {
            if (areTextInputLayoutsValid(binding.nameUser.text.toString(), "\"^[a-zA-Z\\\\- ]+\$\"")) {
                return@setOnClickListener;
            }
            if (areTextInputLayoutsValid(binding.mailUser.text.toString(), "ccc")) {
                return@setOnClickListener
            }
            if (areTextInputLayoutsValid(binding.passwordUser.text.toString(), "/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/")) {
                return@setOnClickListener
            }
            if (binding.passwordUser.toString().equals(binding.confPassword.toString()))
                userViewModel.addNewUser(
                    binding.mailUser.toString(),
                    binding.passwordUser.toString(),
                    binding.nameUser.toString()
                )
        }

    }

    companion object {
        fun newInstance(): addUserFragment {
            return addUserFragment()
        }

    }

    fun areTextInputLayoutsValid(text: String, regexPattern: String): Boolean {
        if (!text.isNullOrEmpty()) {
            return Regex(regexPattern).matches(text)
        }
        return false
    }


    // fun sendConfSMS(){ Faire si le temps
//       val smsManager= activity?.getSystemService(SmsManager::class.java)
//       smsManager?.sendTextMessage()
//    }
//}
}