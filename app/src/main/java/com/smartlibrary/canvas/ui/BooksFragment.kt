package com.smartlibrary.canvas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.smartlibrary.canvas.data.LibraryRepository
import com.smartlibrary.canvas.databinding.FragmentBooksBinding
import com.smartlibrary.canvas.databinding.ItemBookCardBinding
import com.smartlibrary.canvas.util.loadUrl

class BooksFragment : Fragment() {

    private var _binding: FragmentBooksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBooksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.booksContainer.removeAllViews()
        LibraryRepository.books.forEach { book ->
            val itemBinding = ItemBookCardBinding.inflate(layoutInflater, binding.booksContainer, false)
            itemBinding.bookTitle.text = book.title
            itemBinding.bookAuthor.text = book.author
            itemBinding.tagText.text = book.tag
            itemBinding.ratingText.text = "\u2B50 ${book.rating}"
            itemBinding.chevron.isVisible = true
            binding.booksContainer.addView(itemBinding.root)
            itemBinding.bookCover.loadUrl(book.cover, book.localCoverRes)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
