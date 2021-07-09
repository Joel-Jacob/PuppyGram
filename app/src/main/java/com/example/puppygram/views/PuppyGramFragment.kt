package com.example.puppygram.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.puppygram.R
import com.example.puppygram.adapters.PuppyGramAdapter
import com.example.puppygram.databinding.FragmentMainBinding
import com.example.puppygram.viewmodels.PuppyGramViewModel

class PuppyGramFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    lateinit var viewModel: PuppyGramViewModel
    private val puppyGramAdapter = PuppyGramAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(PuppyGramViewModel::class.java)

        setUpRecyclerView()

        viewModel.getPuppiesHelper()

        viewModel.puppyList.observe(viewLifecycleOwner, {
            if (it != null) {
                puppyGramAdapter.puppyList = it
                puppyGramAdapter.notifyDataSetChanged()
            }
        })

        viewModel.showErrorToast.observe(viewLifecycleOwner, {
            if (it) {
                Toast.makeText(
                    context,
                    getString(R.string.oops_something_went_wrong),
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun setUpRecyclerView(){
        binding.puppyRecyclerView.layoutManager = GridLayoutManager(this.context, 2)
        binding.puppyRecyclerView.adapter = puppyGramAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposeCompositeDisposable()
    }
}