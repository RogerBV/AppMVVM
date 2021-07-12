package com.example.appmvvm.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.appmvvm.R
import com.example.appmvvm.databinding.FragmentGalleryBinding
import com.example.appmvvm.viewmodels.ListingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment(),View.OnClickListener {

    private lateinit var galleryViewModel: GalleryViewModel
    private var _binding: FragmentGalleryBinding? = null
    private val viewModel by viewModels<ListingViewModel>()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var txtEmployeeName: EditText
    private lateinit var txtEmployeeSurname: EditText
    private lateinit var btnSave:Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        txtEmployeeName = root!!.findViewById(R.id.txtEmployeeName)
        txtEmployeeSurname = root!!.findViewById(R.id.txtEmployeeSurname)
        btnSave = root!!.findViewById(R.id.btnSave)
        btnSave.setOnClickListener(this)
        return root
    }

    override fun onClick(v: View) {
        if (v.id == btnSave.id)
        {
            RegisterPermit()
        }

    }
    fun RegisterPermit()
    {
        viewModel.registerPermit(txtEmployeeName.text.toString(),txtEmployeeSurname.text.toString());
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}