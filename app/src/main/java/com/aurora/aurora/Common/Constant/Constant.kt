package com.aurora.aurora.Common.Constant

import android.net.Uri
import android.os.Environment
import com.aurora.aurora.Common.CommonAdapter.CategoryOptionAdapter
import com.aurora.aurora.Common.CommonAdapter.VideoMainAdapter
import com.aurora.aurora.Model.ToyModel
import com.aurora.aurora.Model.VideoModel
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


        fun getListString(): List<CategoryOptionAdapter.CategoryString> {
            return listOf(
                CategoryOptionAdapter.CategoryString("Tất Cả"),
                CategoryOptionAdapter.CategoryString("Mới Nhất"),
                CategoryOptionAdapter.CategoryString("Phổ Biến"),
                CategoryOptionAdapter.CategoryString("Đặt Biệt"),
            )

        }

        fun getListToys(): List<ToyModel> {
            return listOf(
                ToyModel(
                    id = 1,
                    toyName ="Đồ chơi trẻ em",
                    toyImage = R.drawable.ic_material_toy,
                    toyDescription = "Đồ chơi trẻ em vip pro",
                    toyRating = 4.5,
                    isLike = true,
                    toyPrice = 145000.0
                ),
                ToyModel(
                    id = 2,
                    toyName ="Đồ chơi trẻ em",
                    toyImage = R.drawable.ic_material_toy,
                    toyDescription = "Đồ chơi trẻ em vip pro",
                    toyRating = 4.5,
                    isLike = true,
                    toyPrice = 145000.0
                ),
                ToyModel(
                    id = 3,
                    toyName ="Đồ chơi trẻ em",
                    toyImage = R.drawable.ic_material_toy,
                    toyDescription = "Đồ chơi trẻ em vip pro",
                    toyRating = 4.5,
                    isLike = true,
                    toyPrice = 145000.0
                ),
                ToyModel(
                    id = 4,
                    toyName ="Đồ chơi trẻ em",
                    toyImage = R.drawable.ic_material_toy,
                    toyDescription = "Đồ chơi trẻ em vip pro",
                    toyRating = 4.5,
                    isLike = true,
                    toyPrice = 145000.0
                ),
                ToyModel(
                    id = 5,
                    toyName ="Đồ chơi trẻ em",
                    toyImage = R.drawable.ic_material_toy,
                    toyDescription = "Đồ chơi trẻ em vip pro",
                    toyRating = 4.5,
                    isLike = true,
                    toyPrice = 145000.0
                ),
                ToyModel(
                    id = 6,
                    toyName ="Đồ chơi trẻ em",
                    toyImage = R.drawable.ic_material_toy,
                    toyDescription = "Đồ chơi trẻ em vip pro",
                    toyRating = 4.5,
                    isLike = true,
                    toyPrice = 145000.0
                ),
                ToyModel(
                    id = 7,
                    toyName ="Đồ chơi trẻ em",
                    toyImage = R.drawable.ic_material_toy,
                    toyDescription = "Đồ chơi trẻ em vip pro",
                    toyRating = 4.5,
                    isLike = true,
                    toyPrice = 145000.0
                )
            )


        }


        fun getListVideos(): List<VideoModel> {
            val videoFileName = "PHƯƠNG MỸ CHI x DTAP - VŨ TRỤ CÓ ANH ft. Pháo _ Official Music Video.mp4"
            val downloadsPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath
            val videoFilePath = "$downloadsPath/$videoFileName"


            return listOf(
                VideoModel("Vũ Trụ Có Anh", "PHƯƠNG MỸ CHI x DTAP - VŨ TRỤ CÓ ANH ft. Pháo _ Official Music Video.mp4", Uri.parse(videoFilePath)),
                VideoModel("Vũ Trụ Có Anh", "PHƯƠNG MỸ CHI x DTAP - VŨ TRỤ CÓ ANH ft. Pháo _ Official Music Video.mp4", Uri.parse(videoFilePath)),
                VideoModel("Vũ Trụ Có Anh", "PHƯƠNG MỸ CHI x DTAP - VŨ TRỤ CÓ ANH ft. Pháo _ Official Music Video.mp4", Uri.parse(videoFilePath)),
                VideoModel("Vũ Trụ Có Anh", "PHƯƠNG MỸ CHI x DTAP - VŨ TRỤ CÓ ANH ft. Pháo _ Official Music Video.mp4", Uri.parse(videoFilePath)),
                VideoModel("Vũ Trụ Có Anh", "PHƯƠNG MỸ CHI x DTAP - VŨ TRỤ CÓ ANH ft. Pháo _ Official Music Video.mp4", Uri.parse(videoFilePath))
            )

        }


    }
}