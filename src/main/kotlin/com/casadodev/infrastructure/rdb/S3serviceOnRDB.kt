package com.casadodev.infrastructure.rdb

import com.casadodev.domain.services.s3.S3service
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class S3serviceOnRDB() : S3service {

    private val logger: Logger = LoggerFactory.getLogger(SubsRepositoryOnRDB::class.java)

    override fun getBucketFileList(bucketName: String): List<String> {
        logger.info("Método get bucket file list")
        TODO("Not yet implemented")
    }

    override fun downloadFile(bucketName: String, fileKey: String): ByteArray {
        logger.info("Método download file")
        TODO("Not yet implemented")
    }
}