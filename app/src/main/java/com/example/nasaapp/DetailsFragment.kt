package com.example.nasaapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.nasaapp.adapter.DetailsAdapter
import com.example.nasaapp.databinding.FragmentDetailsBinding
import com.example.nasaapp.domain.entity.NasaDataEntityItem
import com.example.nasaapp.viewmodel.NasaViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private var nasaUUID: String = ""
    private val nasaViewModel: NasaViewModel by activityViewModels()
    private var updatedList: List<NasaDataEntityItem> = mutableListOf()
    private lateinit var adapter: DetailsAdapter
    private var itemPosition: Int = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nasaUUID = arguments?.getString("nasa_uuid") ?: ""
        Log.d("TAG", "onViewCreated: $nasaUUID")
        setRecyclerView()
        setObserver()
        initialiseList()
    }


    private fun setItemPosition(updatedList: MutableList<NasaDataEntityItem>) {

        var position = -1

        for (item in updatedList) {
            position++
            if (item.id == nasaUUID) {
                break
            }
        }

        itemPosition = position
        binding.detailsRV.layoutManager?.scrollToPosition(itemPosition)
    }

    private fun initialiseList() {
        nasaViewModel.getUpdatedNasaData()
    }

    private fun setObserver() {

        nasaViewModel.updatedNasaLiveData.observe(viewLifecycleOwner, Observer {
            updatedList = it
            adapter.setData(updatedList)
            setItemPosition(updatedList.toMutableList())
        })

    }

    private fun setRecyclerView() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val snapHelper: SnapHelper = PagerSnapHelper()
        binding.detailsRV.layoutManager = layoutManager
        snapHelper.attachToRecyclerView(binding.detailsRV)
        adapter = DetailsAdapter()
        binding.detailsRV.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }


}