package com.aurora.aurora.Common.Constant

import android.net.Uri
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import com.aurora.aurora.BuildConfig
import com.aurora.aurora.Common.CommonAdapter.CategoryOptionAdapter
import com.aurora.aurora.Common.CommonAdapter.VideoMainAdapter
import com.aurora.aurora.Model.CartModel
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
                    title = "Hành trình khám phá ngôn ngữ cho trẻ",
                    image = R.drawable.ic_material_banner1,
                    rating = 4.5,
                    reviews = 4242,
                    originalPrice = 400000.0,
                    discountedPrice = 350000.0
                ),

                VideoMainAdapter.Course(
                    title = "Hiểu rõ về Rối Loạn Phổ Tự Kỷ (ASD)",
                    image = R.drawable.ic_material_banner12jpg,
                    rating = 4.5,
                    reviews = 4242,
                    originalPrice = 400000.0,
                    discountedPrice = 350000.0
                ),

                VideoMainAdapter.Course(
                    title = "Liệu con có chậm nói không? Làm thế nào để nhận biết điều này?",
                    image = R.drawable.ic_material_banner3jpg,
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

        fun getListToys(): MutableList<ToyModel> {
            return mutableListOf(
                ToyModel(
                    id = 0,
                    toyName ="Bộ xếp hình nam châm 72 chi tiết",
                    toyImage = R.drawable.ic_image_toy_01,
                    toyDescription = "Khám phá bộ đồ chơi mới với bé yêu BỘ XẾP HÌNH NAM CHÂM 72 CHI TIẾT Với Bộ Xếp Hình Nam Châm, bé sẽ học được gì? - Phát triển tư duy sáng tạo thông qua việc tạo ra nhiều hình khối đa dạng, từ các hình dạng que đến hình tròn. - Tăng cường khả năng quan sát và phát triển khả năng tưởng tượng không gian thông",
                    toyRating = 4.5,
                    isLike = true,
                    toyPrice = 155000.0,
                    typePoppular = 1,
                    categoryModel = ToyModel.CategoryModel(
                        id = 1,
                        name = "Trẻ Em",
                        image = R.drawable.ic_teddy,
                    ),
                    sellerInfo = ToyModel.SellerInfoModel(
                        id = 1,
                        name = "Đạt Võ",
                        email = "quyettc@gmail.com",
                        phone = "0356790686"
                    ),
                    listImage = listOf(R.drawable.ic_image_toy_01,R.drawable.ic_image_1_2jpg, R.drawable.ic_image_1_3 ),
                    gender = "ALL",
                    idDb = "677f4d75-d540-431f-86e4-0be19f6f5227"
                ),
                ToyModel(
                    id = 1,
                    toyName ="8 CUỐN SÁCH VẢI MINI NHIỀU CHỦ ĐỀ",
                    toyImage = R.drawable.ic_image_toy_02,
                    toyDescription = "Khám phá bộ đồ chơi mới với bé yêu BỘ XẾP HÌNH NAM CHÂM 72 CHI TIẾT Với Bộ Xếp Hình Nam Châm, bé sẽ học được gì? - Phát triển tư duy sáng tạo thông qua việc tạo ra nhiều hình khối đa dạng, từ các hình dạng que đến hình tròn. - Tăng cường khả năng quan sát và phát triển khả năng tưởng tượng không gian thông",
                    toyRating = 4.5,
                    isLike = true,
                    toyPrice = 155000.0,
                    typePoppular = 1,
                    categoryModel = ToyModel.CategoryModel(
                        id = 1,
                        name = "Trẻ Em",
                        image = R.drawable.ic_teddy,
                    ),
                    sellerInfo = ToyModel.SellerInfoModel(
                        id = 1,
                        name = "Đạt Võ",
                        email = "quyettc@gmail.com",
                        phone = "0356790686"
                    ),
                    listImage = listOf(R.drawable.ic_material_toy,R.drawable.ic_material_toy, R.drawable.ic_material_toy ),
                    gender = "ALL",
                    idDb = "983a6cda-382d-4a13-8de0-cda97a2d9e83"
                ),
                ToyModel(
                    id = 2,
                    toyName ="Bộ xếp hình người thăng bằng",
                    toyImage = R.drawable.ic_image_03,
                    toyDescription = "ĐỒ CHƠI XẾP THĂNG BẰNG HÌNH NGƯỜI RẠP XIẾC VUI NHỘN Đồ Chơi Xếp Thăng Bằng với 32 nhân vật, 32 sticker đôi và 4 quả bóng sẽ mang lại những giờ phút vui vẻ cho bé và gia đình. ",
                    toyRating = 4.5,
                    isLike = true,
                    toyPrice = 99000.0,
                    typePoppular = 2,
                    categoryModel = ToyModel.CategoryModel(
                        id = 2,
                        name = "Giải Đố",
                        image = R.drawable.ic_teddy,
                    ),
                    sellerInfo = ToyModel.SellerInfoModel(
                        id = 1,
                        name = "Đạt Võ",
                        email = "quyettc@gmail.com",
                        phone = "0356790686"
                    ),
                    listImage = listOf(R.drawable.ic_material_toy,R.drawable.ic_material_toy, R.drawable.ic_material_toy ),
                    gender = "ALL",
                    idDb = "948a2ab9-14e3-4caf-a29f-439ba9442899"
                ),
                ToyModel(
                    id = 3,
                    toyName ="Mô hình con vật 58 chi tiết",
                    toyImage = R.drawable.ic_iamge_mo_hinh_con_vat,
                    toyDescription = "Đồ chơi trẻ em vip pro",
                    toyRating = 4.5,
                    isLike = true,
                    toyPrice = 199000.0,
                    typePoppular = 1,
                    categoryModel = ToyModel.CategoryModel(
                        id = 2,
                        name = "Giải Đố",
                        image = R.drawable.ic_teddy,
                    ),
                    sellerInfo = ToyModel.SellerInfoModel(
                        id = 1,
                        name = "Đạt Võ",
                        email = "quyettc@gmail.com",
                        phone = "0356790686"
                    ),
                    listImage = listOf(R.drawable.ic_material_toy,R.drawable.ic_material_toy, R.drawable.ic_material_toy ),
                    gender = "ALL",
                    idDb = "83645329-087b-4533-9785-40c4c708a5b8"
                ),
                ToyModel(
                    id = 4,
                    toyName ="Rối ngón tay gia đình 6 con",
                    toyImage = R.drawable.ic_image_6_ngon,
                    toyDescription = "Đồ chơi trẻ em vip pro",
                    toyRating = 4.5,
                    isLike = true,
                    toyPrice = 79000.0,
                    typePoppular = 1,
                    categoryModel = ToyModel.CategoryModel(
                        id = 1,
                        name = "Robot",
                        image = R.drawable.ic_teddy,
                    ),
                    sellerInfo = ToyModel.SellerInfoModel(
                        id = 1,
                        name = "Đạt Võ",
                        email = "quyettc@gmail.com",
                        phone = "0356790686"
                    ),
                    listImage = listOf(R.drawable.ic_material_toy,R.drawable.ic_material_toy, R.drawable.ic_material_toy ),
                    gender = "ALL",
                    idDb = "e01e9c9d-754b-479e-b093-8312db7e593b"
                ),
                ToyModel(
                    id = 5,
                    toyName ="Đồng hồ nước màu sắc kích thích thị giác",
                    toyImage = R.drawable.ic_image_dong_ho,
                    toyDescription = "Đồ chơi trẻ em vip pro",
                    toyRating = 4.5,
                    isLike = true,
                    toyPrice = 49000.0,
                    typePoppular = 1,
                    categoryModel = ToyModel.CategoryModel(
                        id = 1,
                        name = "Robot",
                        image = R.drawable.ic_teddy,
                    ),
                    sellerInfo = ToyModel.SellerInfoModel(
                        id = 1,
                        name = "Đạt Võ",
                        email = "quyettc@gmail.com",
                        phone = "0356790686"
                    ),
                    listImage = listOf(R.drawable.ic_material_toy,R.drawable.ic_material_toy, R.drawable.ic_material_toy ),
                    gender = "ALL",
                    idDb = "6933b5fc-125f-472e-95df-955182367955"
                ),
                ToyModel(
                    id = 6,
                    toyName ="Bóng bóp tay màu sắc vui nhộn",
                    toyImage = R.drawable.ic_image_bong_bong,
                    toyDescription = "Đồ chơi trẻ em vip pro",
                    toyRating = 4.5,
                    isLike = true,
                    toyPrice = 35000.0,
                    typePoppular = 1,
                    categoryModel = ToyModel.CategoryModel(
                        id = 1,
                        name = "Phương Tiện",
                        image = R.drawable.ic_teddy,
                    ),
                    sellerInfo = ToyModel.SellerInfoModel(
                        id = 1,
                        name = "Đạt Võ",
                        email = "quyettc@gmail.com",
                        phone = "0356790686"
                    ),
                    listImage = listOf(R.drawable.ic_material_toy,R.drawable.ic_material_toy, R.drawable.ic_material_toy ),
                    gender = "ALL",
                    idDb = "a432133d-32ce-47b7-b609-47d384624f83"
                ),
                ToyModel(
                    id = 7,
                    toyName ="Xe gỗ trượt cao tốc vui nhộn",
                    toyImage = R.drawable.ic_xe_go,
                    toyDescription = "Đồ chơi trẻ em vip pro",
                    toyRating = 4.5,
                    isLike = true,
                    toyPrice = 165000.0,
                    typePoppular = 1,
                    categoryModel = ToyModel.CategoryModel(
                        id = 1,
                        name = "Phương Tiện",
                        image = R.drawable.ic_teddy,
                    ),
                    sellerInfo = ToyModel.SellerInfoModel(
                        id = 1,
                        name = "Đạt Võ",
                        email = "quyettc@gmail.com",
                        phone = "0356790686"
                    ),
                    listImage = listOf(R.drawable.ic_material_toy,R.drawable.ic_material_toy, R.drawable.ic_material_toy ),
                    gender = "ALL",
                    idDb = "7ecd4222-814b-43e7-af22-3c293bb9c9c6"
                )
            )
        }

        fun getListToysId(id: Int): ToyModel{
            return getListToys().get(id)
        }

        fun getListVideos(): List<VideoModel> {
            return listOf(
                VideoModel("czbB8AQsBzU"),
                VideoModel("UFAKw0wgcjA"),
                VideoModel("hn_teOwL_iA"),
                VideoModel("3-gjAL20WGE"),
                VideoModel("9mL-TS0gcmA"),
                VideoModel("3Txw-0QStHQ"),
                VideoModel("iNoSL65z6Mc"),
                VideoModel("i0tIR-GWTso"),
            )
        }

        fun getListCart(): ArrayList<CartModel> {
            val toyModel = getListToys()[0]
            val toyModel2 = getListToys()[1]
            val toyMode3 = getListToys()[2]
            return arrayListOf(
                CartModel.create(toyModel, 2),
                CartModel.create(toyModel2, 2),
                CartModel.create(toyMode3, 2),

            )
        }


    }
}