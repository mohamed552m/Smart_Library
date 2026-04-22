package com.smartlibrary.canvas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.smartlibrary.canvas.R
import com.smartlibrary.canvas.data.LibraryRepository
import com.smartlibrary.canvas.databinding.FragmentBorrowingsBinding
import com.smartlibrary.canvas.databinding.ItemBorrowingCardBinding
import com.smartlibrary.canvas.util.loadUrl

class BorrowingsFragment : Fragment() {

    private var _binding: FragmentBorrowingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBorrowingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.borrowingsContainer.removeAllViews()
        LibraryRepository.borrowings.forEach { borrowing ->
            val itemBinding = ItemBorrowingCardBinding.inflate(layoutInflater, binding.borrowingsContainer, false)
            itemBinding.bookTitle.text = borrowing.title
            itemBinding.bookAuthor.text = borrowing.author
            itemBinding.bookDue.text = borrowing.due
            itemBinding.statusText.text = borrowing.state

            if (borrowing.state == "Due Soon") {
                itemBinding.statusText.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_status_due)
                itemBinding.statusText.setTextColor(ContextCompat.getColor(requireContext(), R.color.amber_text))
            } else {
                itemBinding.statusText.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_status_active)
                itemBinding.statusText.setTextColor(ContextCompat.getColor(requireContext(), R.color.emerald_text))
            }

            binding.borrowingsContainer.addView(itemBinding.root)
            itemBinding.bookCover.loadUrl(borrowing.cover, borrowing.localCoverRes)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
