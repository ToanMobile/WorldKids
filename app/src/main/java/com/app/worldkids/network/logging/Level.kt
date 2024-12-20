/*
 * *Created by HuraTeamAndroid on 2022
 * Company: Netacom.
 *  *
 */

package com.app.worldkids.network.logging

enum class Level {
    /**
     * No logs.
     */
    NONE,

    /**
     *
     * Example:
     * <pre>`- URL
     * - Method
     * - Headers
     * - Body
     `</pre> *
     */
    BASIC,

    /**
     *
     * Example:
     * <pre>`- URL
     * - Method
     * - Headers
     `</pre> *
     */
    HEADERS,

    /**
     *
     * Example:
     * <pre>`- URL
     * - Method
     * - Body
     `</pre> *
     */
    BODY,

    /*CustomLog*/
    CloudHMS
}
