/*
 * *
 *  * Created by NivilabsTeam on 12/19/21, 2:40 AM
 *  * Email: hvtoan.dev@gmail.com
 *  * Last modified 12/19/21, 2:38 AM
 *
 */

/*
 * *Created by HuraTeamAndroid on 2022
 * Company: Netacom.
 *  *
 */

package com.app.worldkids.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext

/**
Created by vantoan on 05/Aug/2022
Company: Netacom.
Email: huynhvantoan.itc@gmail.com
 **/

interface CoroutineDispatchers {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val socket: CoroutineDispatcher
    val call: CoroutineDispatcher
    val default: CoroutineDispatcher
}

class DefaultCoroutineDispatchers : CoroutineDispatchers {
    override val main: CoroutineDispatcher = Dispatchers.Main
    override val io: CoroutineDispatcher = Dispatchers.IO

    @OptIn(DelicateCoroutinesApi::class)
    override val socket: CoroutineDispatcher = newSingleThreadContext(name = "SocketManager")

    @OptIn(DelicateCoroutinesApi::class)
    override val call: CoroutineDispatcher = newSingleThreadContext(name = "call")
    override val default: CoroutineDispatcher = Dispatchers.Default
}
