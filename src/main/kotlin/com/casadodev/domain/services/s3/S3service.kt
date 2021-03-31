package com.casadodev.domain.services.s3

interface S3service {

    fun getBucketFileList(bucketName: String) : List<String>

    fun downloadFile(bucketName: String, fileKey: String) : ByteArray

//    fun deleteFile(bucketName: String, fileKey: String) : SdkHttpResponse

}