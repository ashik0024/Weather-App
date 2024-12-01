package com.example.weatherapp.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentSearchBinding
import com.example.weatherapp.utils.Utils
import com.google.gson.Gson


class SearchFragment : Fragment() {
    private var _binding:FragmentSearchBinding?=null
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val json = Utils.readJsonFromRaw(requireContext(), R.raw.zila_list)
        val gson = Gson()
        val itemList: Array<ZilaInfo> = gson.fromJson(json, Array<ZilaInfo>::class.java)
        val zilaList = itemList.toList()

        binding?.searchPageCompose?.setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        binding?.searchPageCompose?.setContent {
            SearchScreen(zilaList,
                onItemClicked = { zilaInfo ->
                    findNavController().navigate(
                        R.id.search_to_home,
                        bundleOf("zilaInfo" to zilaInfo)
                    )
                })
        }
    }
}