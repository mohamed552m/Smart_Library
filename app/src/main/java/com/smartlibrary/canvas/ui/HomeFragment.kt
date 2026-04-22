package com.smartlibrary.canvas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.smartlibrary.canvas.data.LibraryRepository
import com.smartlibrary.canvas.databinding.FragmentHomeBinding
import com.smartlibrary.canvas.databinding.ItemBookCardBinding
import com.smartlibrary.canvas.util.loadUrl

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val books = LibraryRepository.books
        binding.recommendedContainer.removeAllViews()

        books.take(2).forEach { book ->
            val itemBinding = ItemBookCardBinding.inflate(layoutInflater, binding.recommendedContainer, false)
            itemBinding.bookTitle.text = book.title
            itemBinding.bookAuthor.text = book.author
            itemBinding.tagText.text = book.tag
            itemBinding.ratingText.text = "\u2B50 ${book.rating}"
            itemBinding.chevron.isVisible = false
            binding.recommendedContainer.addView(itemBinding.root)
            itemBinding.bookCover.loadUrl(book.cover, book.localCoverRes)
        }

        binding.continueTitle.text = books[2].title
        binding.continueAuthor.text = books[2].author
        binding.continueCover.loadUrl(books[2].cover, books[2].localCoverRes)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
