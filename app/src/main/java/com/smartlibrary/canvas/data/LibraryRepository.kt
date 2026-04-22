package com.smartlibrary.canvas.data

import android.content.Context
import com.smartlibrary.canvas.R
import com.smartlibrary.canvas.model.Book
import com.smartlibrary.canvas.model.Borrowing
import com.smartlibrary.canvas.model.ProfileState
import com.smartlibrary.canvas.model.QuickAction

object LibraryRepository {
    private const val PREFS_NAME = "smart_library_prefs"
    private const val KEY_NAME = "profile_name"
    private const val KEY_PHOTO = "profile_photo"

    private const val DEFAULT_NAME = "Mohamed Elshahat"
    private const val DEFAULT_PHOTO =
        "https://images.unsplash.com/photo-1512820790803-83ca734da794?auto=format&fit=crop&w=600&q=80"

    val defaultProfileRes = R.drawable.profile_mohamed

    val books = listOf(
        Book(
            title = "Atomic Habits",
            author = "James Clear",
            rating = "4.8",
            tag = "Popular",
            cover = "https://images.unsplash.com/photo-1544947950-fa07a98d237f?auto=format&fit=crop&w=600&q=80",
            localCoverRes = R.drawable.cover_atomic_habits
        ),
        Book(
            title = "Deep Work",
            author = "Cal Newport",
            rating = "4.7",
            tag = "Recommended",
            cover = "https://images.unsplash.com/photo-1512820790803-83ca734da794?auto=format&fit=crop&w=600&q=80",
            localCoverRes = R.drawable.cover_deep_work
        ),
        Book(
            title = "Clean Code",
            author = "Robert C. Martin",
            rating = "4.9",
            tag = "In Progress",
            cover = "https://images.unsplash.com/photo-1521587760476-6c12a4b040da?auto=format&fit=crop&w=600&q=80",
            localCoverRes = R.drawable.cover_clean_code
        ),
        Book(
            title = "Design Patterns",
            author = "GoF",
            rating = "4.6",
            tag = "Available",
            cover = "https://images.unsplash.com/photo-1516979187457-637abb4f9353?auto=format&fit=crop&w=600&q=80",
            localCoverRes = R.drawable.cover_design_patterns
        )
    )

    val borrowings = listOf(
        Borrowing(
            title = "Atomic Habits",
            author = "James Clear",
            due = "Due in 3 days",
            state = "Active",
            cover = books[0].cover,
            localCoverRes = books[0].localCoverRes
        ),
        Borrowing(
            title = "Deep Work",
            author = "Cal Newport",
            due = "Due tomorrow",
            state = "Due Soon",
            cover = books[1].cover,
            localCoverRes = books[1].localCoverRes
        ),
        Borrowing(
            title = "Clean Code",
            author = "Robert C. Martin",
            due = "Due in 6 days",
            state = "Active",
            cover = books[2].cover,
            localCoverRes = books[2].localCoverRes
        )
    )

    val quickActions = listOf(
        QuickAction("My Borrowings", "Track active books", "\uD83D\uDCDA"),
        QuickAction("Saved Books", "Keep your favorites", "\uD83D\uDD16"),
        QuickAction("Settings", "Manage your account", "\u2699\uFE0F")
    )

    fun getProfile(context: Context): ProfileState {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return ProfileState(
            name = prefs.getString(KEY_NAME, DEFAULT_NAME).orEmpty(),
            photo = prefs.getString(KEY_PHOTO, DEFAULT_PHOTO).orEmpty()
        )
    }

    fun saveProfile(context: Context, name: String, photo: String) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_NAME, name)
            .putString(KEY_PHOTO, photo)
            .apply()
    }
}

