package com.jobplanet.test.model

data class HorizontalThemeData(val cell_type : String, val themes : ArrayList<HorizontalItem>) {
    data class HorizontalItem(val color : String, val cover_image : String, val id : Int, val title : String)
}


