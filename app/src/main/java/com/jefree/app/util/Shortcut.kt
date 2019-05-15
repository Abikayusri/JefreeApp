package com.jefree.app.util

class Shortcut {

    companion object {
        fun ucFirst (str :String) : String {
            val words = str.split(" ").toMutableList()
            var output = ""
            if(words.size > 0){
                for(word in words){
                    output += word.capitalize()+ " "
                }
                return output.trim()
            }else{
                return str
            }
        }
    }

}