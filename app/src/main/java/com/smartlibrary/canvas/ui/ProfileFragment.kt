package com.smartlibrary.canvas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smartlibrary.canvas.data.LibraryRepository
import com.smartlibrary.canvas.databinding.BottomSheetEditProfileBinding
import com.smartlibrary.canvas.databinding.FragmentProfileBinding
import com.smartlibrary.canvas.databinding.ItemQuickActionBinding
import com.smartlibrary.canvas.util.loadUrl

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindQuickActions()
        bindProfile()
        binding.editProfileButton.setOnClickListener { openEditSheet() }
    }

    override fun onResume() {
        super.onResume()
        bindProfile()
    }

    private fun bindProfile() {
        val profile = LibraryRepository.getProfile(requireContext())
        binding.profileName.text = profile.name
        binding.profileImage.loadUrl(profile.photo, LibraryRepository.defaultProfileRes)
    }

    private fun bindQuickActions() {
        binding.quickActionsContainer.removeAllViews()
        LibraryRepository.quickActions.forEach { action ->
            val itemBinding = ItemQuickActionBinding.inflate(layoutInflater, binding.quickActionsContainer, false)
            itemBinding.iconText.text = action.icon
            itemBinding.titleText.text = action.title
            itemBinding.subtitleText.text = action.subtitle
            binding.quickActionsContainer.addView(itemBinding.root)
        }
    }

    private fun openEditSheet() {
        val profile = LibraryRepository.getProfile(requireContext())
        val dialog = BottomSheetDialog(requireContext())
        val sheetBinding = BottomSheetEditProfileBinding.inflate(layoutInflater)
        dialog.setContentView(sheetBinding.root)

        sheetBinding.nameInput.setText(profile.name)
        sheetBinding.photoInput.setText(profile.photo)

        val dismissAction = { dialog.dismiss() }
        sheetBinding.closeButton.setOnClickListener { dismissAction() }
        sheetBinding.cancelButton.setOnClickListener { dismissAction() }
        sheetBinding.saveButton.setOnClickListener {
            val cleanName = sheetBinding.nameInput.text?.toString()?.trim().orEmpty()
            val cleanPhoto = sheetBinding.photoInput.text?.toString()?.trim().orEmpty()

            val nextName = if (cleanName.isNotEmpty()) cleanName else profile.name
            val nextPhoto = if (cleanPhoto.isNotEmpty()) cleanPhoto else profile.photo

            LibraryRepository.saveProfile(requireContext(), nextName, nextPhoto)
            bindProfile()
            dialog.dismiss()
        }

        dialog.setOnShowListener {
            dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)?.let { sheet ->
                sheet.setBackgroundResource(android.R.color.transparent)
                BottomSheetBehavior.from(sheet).state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
