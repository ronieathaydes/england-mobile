package com.england.android.ui.feed

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val feedModule = module {
    viewModel<FeedViewModel>()
}
