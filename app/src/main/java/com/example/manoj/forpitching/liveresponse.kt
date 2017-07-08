package com.example.manoj.forpitching

/**
 * Created by Manoj on 7/6/2017.
 */

data class liveresponse(
        var status: String,
        var source : String,
        var sortby : String
){
    data class articles(
            var author : String,
            var title : String
    )
}