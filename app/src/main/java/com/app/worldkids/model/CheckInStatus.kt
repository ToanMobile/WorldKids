package com.app.worldkids.model

data class CheckInStatus(
    var ABSENT: Int = 0,
    var PRESENT: Int = 0,
    var LATE: Int = 0,
    var ON_LEAVE: Int = 0,
    var OFF_WITH_LETTER: Int = 0,
)