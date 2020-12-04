package com.jobplanet.test.model

data class JobPosting(val occumpation_ids:ArrayList<Int>, val is_interview : String, val job_type_ids : ArrayList<Int>,
                      val city_ids : ArrayList<Int>, val deadline : DeadLine, val recruitment_type_ids : ArrayList<Int>,
                      val logo : String, val id : String, val review_avg_cache : Float, val cell_type : String, val title : String,
                      val company_id : Long, val label_id : String, val is_saved : String, val company_name : String) {
    data class DeadLine(val color : String, val message:String, val type: String, val hex_color : String)
}
