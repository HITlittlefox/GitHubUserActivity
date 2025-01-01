package com.example.githubapi.model

import com.chenliang.library.adapter.MyRecyclerViewModel

class Product : MyRecyclerViewModel() {
    var name: String = ""
    var price: String = ""
    var src: String = ""
    var from: String = ""
    var des: String = ""
    var state: Int = 0
}