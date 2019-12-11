package com.example.testapplication2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication2.databinding.ActivityMainBinding

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), UsersListAdapter.OnUserItemClickListener {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var usersListAdapter: UsersListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        activityMainBinding.content.rv.layoutManager = LinearLayoutManager(this)
        usersListAdapter = UsersListAdapter(this)

        activityMainBinding.content.rv.adapter = usersListAdapter

        // Init the MainActivityViewModel
        viewModel = ViewModelProviders.of(this)[MainActivityViewModel::class.java]

        viewModel.getUsersList().observe(this, Observer {
            // Update RecyclerView
            usersListAdapter.setUsers(it)
        })

        // Set the MainActivityViewModel to ActivityMainBinding as the data variable
        activityMainBinding.viewModel = viewModel
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onUserItemClicked(user: User) {
        viewModel.onUserItemClicked(user, this)
    }
}
