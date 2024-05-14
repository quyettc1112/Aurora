package com.aurora.aurora.Common.Constant

import com.aurora.aurora.Common.CommonAdapter.VideoMainAdapter
import com.aurora.aurora.R

class Constant {

    companion object{
        const val DEFAULT_ERROR_MESSAGE: String = "An error occurred"
        // Nice Bottom Nav Bar
        const val ITEM_TAG = "item"
        const val ICON_ATTRIBUTE = "icon"
        const val TITLE_ATTRIBUTE = "title"
        const val WHITE_COLOR_HEX = "#FFFFFF"

        const val DEFAULT_INDICATOR_COLOR = "#426dfe"
        const val DEFAULT_TEXT_COLOR = "#444444"
        const val DEFAULT_TEXT_COLOR_ACTIVE = "#426dfe"

        const val DEFAULT_PRIMARY_COLOR = "#FF4949"
        const val DEFAULT_PRIMARY_COLOR_ACTIVE = "#C9C9C9"


        fun getListCourse(): List<VideoMainAdapter.Course> {
            return listOf(
                VideoMainAdapter.Course(
                    title = "Độ tuổi dễ bị tổn thương sâu sắc về tình cảm",
                    image = R.drawable.ic_material_video,
                    rating = 4.5,
                    reviews = 4242,
                    originalPrice = 400000.0,
                    discountedPrice = 350000.0
                ),
                VideoMainAdapter.Course(
                    title = "Độ tuổi dễ bị tổn thương sâu sắc về tình cảm",
                    image = R.drawable.ic_material_video,
                    rating = 4.5,
                    reviews = 4242,
                    originalPrice = 400000.0,
                    discountedPrice = 350000.0
                ),

                VideoMainAdapter.Course(
                    title = "Độ tuổi dễ bị tổn thương sâu sắc về tình cảm",
                    image = R.drawable.ic_material_video,
                    rating = 4.5,
                    reviews = 4242,
                    originalPrice = 400000.0,
                    discountedPrice = 350000.0
                ),

                VideoMainAdapter.Course(
                    title = "Độ tuổi dễ bị tổn thương sâu sắc về tình cảm",
                    image = R.drawable.ic_material_video,
                    rating = 4.5,
                    reviews = 4242,
                    originalPrice = 400000.0,
                    discountedPrice = 350000.0
                )
            )


        }
    }
}