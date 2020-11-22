package com.intellisoftkenya.android.mhis.ui.components.contact

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.google.android.material.snackbar.Snackbar
import com.intellisoftkenya.android.mhis.R


class ContactFragment : Fragment(), OnItemClickListener {

    companion object {
        fun newInstance() =
            ContactFragment()
    }

    private lateinit var viewModel: ContactViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            layoutInflater.inflate(R.layout.fragment_contact, container, false) as RecyclerView

        view.layoutManager = LinearLayoutManager(context)
        val contactItems = listOf(
            ContactItem("warning", "Quick check", "quick_check"),
            ContactItem("profile", "Profile", "profile"),
            ContactItem("symptoms", "Symptoms and followup", "symptoms"),
            ContactItem("physical_exam", "Physical exam", "physical_exam"),
            ContactItem("laboratory", "Tests", "tests"),
            ContactItem("counselling", "Counselling and treatment", "counselling")
        )
        view.adapter = ContactItemAdapter(contactItems, this)
        return view
    }

    override fun onItemClicked(contactItem: ContactItem) {
        val navController = this.findNavController()
        when (contactItem.key) {
            "profile" -> navController.navigate(R.id.nav_profile)
            "counselling" -> navController.navigate(R.id.nav_counselling)
            "tests" -> navController.navigate(R.id.nav_tests)
            "physical_exam" -> navController.navigate(R.id.nav_physical_exam)
            "symptoms" -> navController.navigate(R.id.nav_symptoms)
            "quick_check" -> navController.navigate(R.id.nav_quick_check)
        }
        Log.d("mhis", "Clicked on ${contactItem.name}")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
    }

    class ContactItemAdapter(private val list: List<ContactItem>, val itemClickListener: OnItemClickListener): Adapter<ContactItemHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactItemHolder {
            return ContactItemHolder(LayoutInflater.from(parent.context), parent)
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: ContactItemHolder, position: Int) {
            holder.bind(list[position], itemClickListener)
        }

    }

    class ContactItemHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.contact_item_layout, parent, false)) {

        private var iconImageView: ImageView? = null
        private var labelTextView: TextView? = null
        private var statusTextView: TextView? = null

        init {
            iconImageView = itemView.findViewById(R.id.icon)
            labelTextView = itemView.findViewById(R.id.mainLabel)
            statusTextView = itemView.findViewById(R.id.subMenu)
        }

        fun bind(contactItem: ContactItem, clickListener: OnItemClickListener) {
            val res: Resources = itemView.resources;
            val id = res.getIdentifier(contactItem.icon, "drawable", itemView.context.packageName)
            iconImageView?.setImageResource(id)
            labelTextView?.text = contactItem.name
            statusTextView?.text = "incomplete"

            itemView.setOnClickListener {
                clickListener.onItemClicked(contactItem)
            }
        }
    }

    data class ContactItem(val icon: String, val name: String, val key: String)
}

interface OnItemClickListener{
    fun onItemClicked(contactItem: ContactFragment.ContactItem)
}