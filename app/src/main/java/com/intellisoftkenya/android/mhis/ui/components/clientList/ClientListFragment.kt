package com.intellisoftkenya.android.mhis.ui.components.clientList

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.intellisoftkenya.android.mhis.R

class ClientListFragment : Fragment(), OnItemClickListener {

    companion object {
        fun newInstance() = ClientListFragment()
    }

    private lateinit var viewModel: ClientListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            layoutInflater.inflate(R.layout.fragment_client_list, container, false) as RecyclerView
        view.layoutManager = LinearLayoutManager(context)
        val contactItems = listOf<ClientItem>(
            ClientItem("Susan Doe", "5"),
            ClientItem("Mother Duck", "3")
        )
        val mDividerItemDecoration = DividerItemDecoration(
            view.getContext(),
            (view.layoutManager as LinearLayoutManager).getOrientation()
        )
        view.addItemDecoration(mDividerItemDecoration)
        view.adapter = ClientListFragment.ContactItemAdapter(contactItems, this)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ClientListViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onItemClicked(contactItem: ClientListFragment.ClientItem) {
        val navController = this.findNavController()
        navController.navigate(R.id.nav_contact)
        Log.d("mhis", "Clicked on ${contactItem.name}")
    }

    class ContactItemAdapter(private val list: List<ClientItem>, val itemClickListener: OnItemClickListener): RecyclerView.Adapter<ContactItemHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactItemHolder {
            return ContactItemHolder(LayoutInflater.from(parent.context), parent)
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: ContactItemHolder, position: Int) {
            holder.bind(list[position], itemClickListener)
        }

    }

    class ContactItemHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.client_item_layout, parent, false)) {

        private var nameTextView: TextView? = null
        private var monthsTextView: TextView? = null

        init {
            nameTextView = itemView.findViewById(R.id.clientName)
            monthsTextView = itemView.findViewById(R.id.clientMonthsPregnant)
        }

        fun bind(client: ClientItem, clickListener: OnItemClickListener) {
            val res: Resources = itemView.resources;
            nameTextView?.text = client.name
            monthsTextView?.text = "${client.months} months pregnant : "

            itemView.setOnClickListener {
                clickListener.onItemClicked(client)
            }
        }
    }

    data class ClientItem(val name: String, val months: String)
}

interface OnItemClickListener {
    fun onItemClicked(contactItem: ClientListFragment.ClientItem)
}