/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.watchface.watchfacekotlin

import android.graphics.Color
import com.example.android.watchface.watchfacekotlin.model.AnalogWatchFaceStyle
import com.example.android.watchface.watchfacekotlin.service.AbstractKotlinWatchFace
import com.example.android.watchface.watchfacekotlin.service.analogWatchFaceStyle
import java.text.SimpleDateFormat
import java.util.*


/**
 * Renders watch face via data object created by DSL.
 */
class AnalogDslWatchFace : AbstractKotlinWatchFace() {

    override fun getWatchFaceStyle(): AnalogWatchFaceStyle {

        /**
         * Initializes colors and dimensions of watch face. Review [AnalogWatchFaceStyle] for
         * detailed explanation of each field.
         */
        return analogWatchFaceStyle {
            watchFaceColors {
                main = Color.GREEN
                highlight = Color.parseColor("#00BB32")
                background = Color.BLACK
                shadow = Color.WHITE
            }
            watchFaceDimensions {
                hourHandRadiusRatio = 0.2f
                minuteHandRadiusRatio = 0.5f
                secondHandRadiusRatio = 0.9f
            }
            watchFaceBackgroundImage {
                backgroundImageResource = when {
                    getAMPM() === "GM" -> R.drawable.img1
                    getAMPM() === "GN" -> R.drawable.img2
                    getAMPM() === "SL" -> R.drawable.img3
                    else -> R.drawable.club
                }
            }
        }
    }
    private fun getAMPM(): String {
        val date = Date()
        return when (SimpleDateFormat("H", Locale.getDefault()).format(date)){
            "06", "07", "08" -> {
                "GM"
            }
            "09", "10" -> {
                "SL"
            }
            "22", "23", "0" -> {
                "GN"
            }
            else -> {
                "other"
            }
        }
        }
}
