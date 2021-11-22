package com.example.noticias_app.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noticias_app.R
import com.example.noticias_app.adapter.AdaptadorNoticias
import com.example.noticias_app.databinding.FragmentListadoBinding
import com.example.noticias_app.model.Article
import com.example.noticias_app.viewmodel.NoticiaViewModel


class ListadoFragment : Fragment() {

    private var _binding: FragmentListadoBinding? = null
    private val binding get() = _binding!!
    private lateinit var vmodel: NoticiaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListadoBinding.inflate(inflater, container, false)

        vmodel = ViewModelProvider(this).get(NoticiaViewModel::class.java)

        //val recycler = binding.rvNoticias
        val adapter = AdaptadorNoticias()

        //recycler.layoutManager = LinearLayoutManager(requireContext())
        with(binding)
        {
           rvNoticias.layoutManager = LinearLayoutManager(context)
            rvNoticias.adapter = adapter
            vmodel.traemeLaListaDeArticulosDelServer()
            adapter.setOnItemClickListener(object : AdaptadorNoticias.ListenerParaClicksEnRV {
                override fun alClickearItem(noticia: Article) {
                    //Listener para click en los objetos de la lista

                    var miBundle = Bundle()
                    miBundle.putSerializable("noticia", noticia)

                    findNavController().navigate(R.id.action_listadoFragment_to_detalleArticuloFragment, miBundle)
                }
            })


        }

        vmodel.exponeNoticiasDeLaApi_EnVM().observe(viewLifecycleOwner, Observer {

            Log.v("RecyclerViewNoticias", it.toString())
            adapter.setNoticia(it)

        })

        binding.fbBuscar.setOnClickListener() {
            findNavController().navigate(R.id.action_listadoFragment_to_buscarFragment)
        }

        binding.boton.setOnClickListener(){

            findNavController().navigate(R.id.action_listadoFragment_to_detalleArticuloFragment)

        }

        return binding.root

    }
}