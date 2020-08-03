package com.example.sandbox.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sandbox.R
import com.example.sandbox.model.Repository
import com.example.sandbox.model.database.PersonDatabase
import com.jakewharton.rxbinding4.view.clicks
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val personDB = PersonDatabase.getInstance(requireContext()).personDao()
        val repo = Repository(personDB)
        homeViewModel = ViewModelProvider(requireActivity(), HomeViewModelFactory(repo))
            .get(HomeViewModel::class.java)

        return  inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Adapter
        recyclerAdapter = RecyclerAdapter(emptyList());

        // Recycler View
        recycler.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = recyclerAdapter
        }
        homeViewModel.subscribe(add_btn.clicks(), delete_btn.clicks())

        observePersons()
    }

    fun observePersons() {
        homeViewModel.allPersons.observe(
            viewLifecycleOwner,
            Observer { persons -> recyclerAdapter.setData(persons) }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        homeViewModel.unsubscribe()
    }

}