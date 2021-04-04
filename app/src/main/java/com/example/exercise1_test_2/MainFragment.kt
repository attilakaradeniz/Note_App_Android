package com.example.exercise1_test_2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

class MainFragment() : Fragment() {
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
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)
        val editFab = rootView.findViewById<FloatingActionButton>(R.id.edit)
        val note = Note("title from hardcoded Note INSTANCE", "content HARD ", "date HARDo")
        val shareFab = rootView.findViewById<FloatingActionButton>(R.id.shareFab)

        val title: TextInputLayout
        val content: TextInputLayout
        val date: TextInputLayout

        title = rootView.findViewById<TextInputLayout>(R.id.txtFieldTitle)
        content = rootView.findViewById<TextInputLayout>(R.id.txtFieldContent)
        date = rootView.findViewById<TextInputLayout>(R.id.txtFieldDate)

        title?.editText?.setText(param1?.title.toString())
        content?.editText?.setText(param1?.content.toString())
        date?.editText?.setText(param1?.date.toString())

        title?.editText?.setText(" ")
        content?.editText?.setText(" ")
        date?.editText?.setText(" ")

        if (param1?.title != null && param1?.content != null && param1?.date != null) {
            title?.editText?.setText(param1?.title)
            content?.editText?.setText(param1?.content)
            date?.editText?.setText(param1?.date)
        }

        editFab.setOnClickListener {

            note.title = title.editText?.getText().toString()
            note.content = content.editText?.getText().toString()
            note.date = date.editText?.getText().toString()

            val editNote: EditFragment = EditFragment.newInstance(note)
            requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.flFragment, editNote)
                    .addToBackStack(null)
                    .commit()
        }

        shareFab.setOnClickListener {
            val myNote: String = "My note title is: ${param1?.title.toString()}\n to do: ${param1?.content.toString()}\n on: ${param1?.date.toString()}"
            val share = Intent()
            share.action = Intent.ACTION_SEND
            share.type = "text/plain"
            share.putExtra(Intent.EXTRA_TEXT, myNote)
            startActivity(Intent.createChooser(share, "Share via"))
        }
        return rootView
    }

    companion object {
        fun newInstance(param1: Note) =
                MainFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_PARAM1, param1)

                    }
                }
    }
}