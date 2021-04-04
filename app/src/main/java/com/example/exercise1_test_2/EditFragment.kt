package com.example.exercise1_test_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import androidx.core.content.contentValuesOf
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

class EditFragment : Fragment() {
    private var param1: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as Note
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_edit, container, false)

        val titleEdit = rootView.findViewById<TextInputLayout>(R.id.titleEdit)
        val contentEdit = rootView.findViewById<TextInputLayout>(R.id.contentEdit)

        val txtDate = rootView.findViewById<TextView>(R.id.txtDate)
        val pickDateFab = rootView.findViewById<FloatingActionButton>(R.id.pickDate)
        val saveFab = rootView.findViewById<FloatingActionButton>(R.id.save)

        titleEdit.editText?.setText(param1?.title.toString())
        contentEdit.editText?.setText(param1?.content.toString())
        txtDate.setText(param1?.date.toString())

        pickDateFab.setOnClickListener {
            val builder = MaterialDatePicker.Builder.datePicker()
                    .also {}
            val datePicker = builder.build()
            datePicker.addOnPositiveButtonClickListener {
                val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                cal.time = Date(it)

                val day = cal.get(Calendar.DAY_OF_MONTH)
                val month = cal.get(Calendar.MONTH)
                val year = cal.get(Calendar.YEAR)

                txtDate.setText("${day}.${month + 1}.${year}")
            }
            datePicker.show(requireActivity().supportFragmentManager, "")
        }

        saveFab.setOnClickListener {
            val notto = Note("ss", "dd", "cc")

            notto.title = titleEdit.editText?.text.toString()
            notto.content = contentEdit.editText?.text.toString()
            notto.date = txtDate.text.toString()
//          notto.date = dateEdit.editText?.text.toString()

            val mainFragment = MainFragment.newInstance(notto)
            requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.flFragment, mainFragment)
                    .addToBackStack(null)
                    .commit()
        }
        return rootView
    }

    companion object {
        fun newInstance(param1: Note) =
                EditFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_PARAM1, param1)
                    }
                }
    }
}