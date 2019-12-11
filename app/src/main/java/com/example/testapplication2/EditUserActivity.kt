package com.example.testapplication2

import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.testapplication2.databinding.ActivityEditUserBinding

class EditUserActivity: AppCompatActivity() {

    private lateinit var activityEditUserBinding: ActivityEditUserBinding
    private lateinit var viewModel: EditUserActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this)[EditUserActivityViewModel::class.java]

        activityEditUserBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_user)

        if (intent.hasExtra(EDIT_USER) && intent.extras.getBoolean(EDIT_USER)) {
            viewModel.setUserData(intent.extras.getParcelable(USER))
            viewModel.setIsAddUser(false)
        } else {
            viewModel.setUserData(User())
            viewModel.setIsAddUser(true)
        }

        activityEditUserBinding.user = viewModel.getUser()?.value
        activityEditUserBinding.viewModel = viewModel

        activityEditUserBinding.name.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.getUser()?.value?.name = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        activityEditUserBinding.email.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.getUser()?.value?.email = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
    }

    companion object {
        const val ADD_USER = "add_user"
        const val USER = "user"
        const val EDIT_USER = "edit_user"
    }
}