package com.example.noticias_app.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noticias_app.R
import com.example.noticias_app.adapter.AdaptadorNoticias
import com.example.noticias_app.databinding.FragmentBuscarBinding
import com.example.noticias_app.model.Article
import com.example.noticias_app.viewmodel.NoticiaViewModel
import java.lang.Exception

class BuscarFragment : Fragment() {

    private var _binding: FragmentBuscarBinding? = null
    private val binding get() = _binding!!
    private lateinit var vmodel: NoticiaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBuscarBinding.inflate(inflater, container, false)

        vmodel = ViewModelProvider(this).get(NoticiaViewModel::class.java)

        val adapter = AdaptadorNoticias()
        with(binding)
        {
            rvBuscarNoticia.layoutManager = LinearLayoutManager(context)

            rvBuscarNoticia.adapter = adapter
            vmodel.buscarListaDeArticulosEnVM("Chile", "es","6d648fd5802448b09fe8a59863c5efc1")

            adapter.setOnItemClickListener(object : AdaptadorNoticias.ListenerParaClicksEnRV {
                override fun alClickearItem(noticia: Article) {
                    //Lo que quiero al hacer click al recicler en el fragmemnt buscar

                    var miBundleEnBuscar = Bundle()
                    miBundleEnBuscar.putSerializable("noticia", noticia)

                    findNavController().navigate(
                        R.id.action_buscarFragment_to_detalleArticuloFragment,
                        miBundleEnBuscar
                    )

                }
            })

        }

        binding.svNoticia.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query!!.isNotEmpty()) {
                    Log.v("buscarNoticia", "La query no esta vacia")
                    vmodel.buscarListaDeArticulosEnVM(query, "es","6d648fd5802448b09fe8a59863c5efc1")
                } else {
                    Toast.makeText(
                        context,
                        "Debes completar el campo de busqueda antes de realizarla",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        vmodel.exponeBusquedaDeNoticiasEnVM().observe(viewLifecycleOwner, Observer {

            try {
                adapter.setNoticia(it)
            } catch (ex: Exception) {
                Log.e("ErrorBusqueda", ex.message.toString())
                Toast.makeText(context, "No hay resultados para tú busqueda", Toast.LENGTH_SHORT)
                    .show()
            }

        })

        return binding.root
    }
}