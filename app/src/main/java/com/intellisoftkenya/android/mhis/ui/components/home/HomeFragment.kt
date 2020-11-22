package com.intellisoftkenya.android.mhis.ui.components.home

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.intellisoftkenya.android.mhis.R

class HomeFragment : Fragment(), TextView.OnEditorActionListener{

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        /*val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        val search = root.findViewById<EditText>(R.id.search)
        search.setOnEditorActionListener(this)

        val alertView = root.findViewById<RelativeLayout>(R.id.alertView)
        alertView.setOnClickListener { v ->
            val navController = this.findNavController()
            navController.navigate(R.id.nav_client_list)
        }
        return root
    }

    override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent? ): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            return true
        }
        return false
    }
}