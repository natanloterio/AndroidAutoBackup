package me.loterio.androidautobackup

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    lateinit var sharedPref : SharedPreferences
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedPref = requireActivity().getSharedPreferences (getString(R.string.preference_key),Context.MODE_PRIVATE)

        teFirstName.doAfterTextChanged { text -> text?.let { saveToPref(it,getString(R.string.preference_first_name)) } }
        teLastName.doAfterTextChanged { text -> text?.let { saveToPref(it,getString(R.string.preference_last_name)) } }
        teEmail.doAfterTextChanged { text -> text?.let { saveToPref(it,getString(R.string.preference_email)) } }
        etComments.doAfterTextChanged { text -> text?.let { saveToPref(it,getString(R.string.preference_comments)) } }
    }


    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        var firstName = sharedPref.getString(getString(R.string.preference_first_name),"")
        firstName?.let {
            teFirstName.setText(firstName)
        }

        var lastName = sharedPref.getString(getString(R.string.preference_last_name),"")
        lastName?.let {
            teLastName.setText(lastName)
        }

        var email = sharedPref.getString(getString(R.string.preference_email),"")
        email?.let {
            teEmail.setText(email)
        }

        var comments = sharedPref.getString(getString(R.string.preference_comments),"")
        comments?.let {
            etComments.setText(comments)
        }
    }

    private fun saveToPref(editable: Editable, prefKey: String){
        sharedPref.edit().putString(prefKey,editable.toString()).apply()
    }
}
