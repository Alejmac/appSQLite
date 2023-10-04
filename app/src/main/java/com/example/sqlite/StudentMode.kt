package com.example.sqlite

import java.util.Random

class StudentMode (    var id:Int = getAutoId(),
                       var name :String = "",
                       var email :String = "")
{

    companion object{
       fun getAutoId():Int{
            val random = Random()
           return random.nextInt(100)
        }
    }
}
