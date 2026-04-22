package com.smartlibrary.canvas

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.smartlibrary.canvas.databinding.ActivityMainBinding
import com.smartlibrary.canvas.ui.BooksFragment
import com.smartlibrary.canvas.ui.BorrowingsFragment
import com.smartlibrary.canvas.ui.HomeFragment
import com.smartlibrary.canvas.ui.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentTab: Tab = Tab.HOME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navHome.setOnClickListener { switchTab(Tab.HOME) }
        binding.navBooks.setOnClickListener { switchTab(Tab.BOOKS) }
        binding.navBorrowings.setOnClickListener { switchTab(Tab.BORROWINGS) }
        binding.navProfile.setOnClickListener { switchTab(Tab.PROFILE) }

        if (savedInstanceState == null) {
            switchTab(Tab.HOME)
        } else {
            updateNavState(currentTab)
        }
    }

    private fun switchTab(tab: Tab) {
        if (tab == currentTab && supportFragmentManager.findFragmentById(R.id.fragmentContainer) != null) {
            updateNavState(tab)
            return
        }

        currentTab = tab
        val fragment = when (tab) {
            Tab.HOME -> HomeFragment()
            Tab.BOOKS -> BooksFragment()
            Tab.BORROWINGS -> BorrowingsFragment()
            Tab.PROFILE -> ProfileFragment()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()

        updateNavState(tab)
    }

    private fun updateNavState(tab: Tab) {
        updateItem(binding.navHome, binding.navHomeIcon, binding.navHomeLabel, tab == Tab.HOME)
        updateItem(binding.navBooks, binding.navBooksIcon, binding.navBooksLabel, tab == Tab.BOOKS)
        updateItem(binding.navBorrowings, binding.navBorrowingsIcon, binding.navBorrowingsLabel, tab == Tab.BORROWINGS)
        updateItem(binding.navProfile, binding.navProfileIcon, binding.navProfileLabel, tab == Tab.PROFILE)
    }

    private fun updateItem(item: LinearLayout, icon: TextView, label: TextView, selected: Boolean) {
        if (selected) {
            item.background = ContextCompat.getDrawable(this, R.drawable.bg_nav_active)
            icon.setTextColor(ContextCompat.getColor(this, R.color.white))
            label.setTextColor(ContextCompat.getColor(this, R.color.white))
        } else {
            item.background = null
            val color = ContextCompat.getColor(this, R.color.slate_500)
            icon.setTextColor(color)
            label.setTextColor(color)
        }
    }

    private enum class Tab {
        HOME,
        BOOKS,
        BORROWINGS,
        PROFILE
    }
}
