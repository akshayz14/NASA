package com.example.nasaapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.nasaapp.action.ActionPerformer
import com.example.nasaapp.action.NasaAction
import com.example.nasaapp.action.openImageInHD
import com.example.nasaapp.adapter.NasaDataAdapter
import com.example.nasaapp.databinding.FragmentHomeBinding
import com.example.nasaapp.domain.entity.NasaDataEntityItem
import com.example.nasaapp.viewmodel.NasaViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment(), ActionPerformer<NasaAction> {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var adapter: NasaDataAdapter

    private val nasaViewModel: NasaViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setUpObserver()
    }

    private fun setRecyclerView() {
        binding.nasaRV.layoutManager = StaggeredGridLayoutManager(2, 1)
        adapter = NasaDataAdapter(this)
        binding.nasaRV.adapter = adapter
    }

    private fun setUpObserver() {

        nasaViewModel.nasaLiveData.observe(viewLifecycleOwner, Observer {
            val list: MutableList<NasaDataEntityItem> = mutableListOf()
            if (it != null) {
                for (item in it.iterator()) {
                    item.id = UUID.randomUUID().toString()
                    list.add(item)
                }
                adapter.setData(list)
                nasaViewModel.setNasaData(list)
            }
        })

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun performAction(action: NasaAction) {

        when (action) {
            is openImageInHD -> {
                Log.d("TAG", "performAction: ")
                openDetailsFragment(action.id)
            }
        }
    }

    private fun openDetailsFragment(uuid: String) {
        val bundle = Bundle()
        bundle.putString("nasa_uuid", uuid)
        findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
    }

}



