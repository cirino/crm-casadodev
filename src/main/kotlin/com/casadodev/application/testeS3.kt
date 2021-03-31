package com.casadodev.application

import software.amazon.awssdk.core.ResponseBytes
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.net.URI
import kotlin.system.exitProcess




object App {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val region = Region.US_EAST_1
        val endpointOverride = URI("http://localhost:4566")

        val s3 = S3Client.builder()
            .endpointOverride(endpointOverride)
            .region(region)
            .build()

//        val bucket = "bucket" + System.currentTimeMillis()
        val bucket = "crm"
        val key = "casadodev"

//        tutorialSetup(s3, bucket, region)

        // List buckets
        val listBucketsRequest = ListBucketsRequest.builder().build()
        val listBucketsResponse = s3.listBuckets(listBucketsRequest)
        listBucketsResponse.buckets().stream().forEach {
            println(it)
        }

        println("Uploading object...")
        s3.putObject(
            PutObjectRequest.builder().bucket(bucket).key(key)
                .build(),
            RequestBody.fromString("Testing with the AWS SDK for Java")
        )
        println("Upload complete")
        System.out.printf("%n")
        getObjectBytes(s3, bucket, key, "D:/CODES/BACKEND/KOTLIN/s3crm/subscriber-list.csv")

        cleanUp(s3, bucket, key)
        println("Closing the connection to Amazon S3")
        s3.close()
        println("Connection closed")
        println("Exiting...")
    }

    private fun getObjectBytes(s3: S3Client, bucketName: String?, keyName: String?, path: String?) {
        try {
            val objectRequest = GetObjectRequest
                .builder()
                .key(keyName)
                .bucket(bucketName)
                .build()
            val objectBytes: ResponseBytes<GetObjectResponse> = s3.getObjectAsBytes(objectRequest)
            val data: ByteArray = objectBytes.asByteArray()

            // Write the data to a local file
            val myFile = File(path)
            val os: OutputStream = FileOutputStream(myFile)
            os.write(data)
            println(data)
            println("Successfully obtained bytes from an S3 object")
            os.close()
        } catch (ex: IOException) {
            ex.printStackTrace()
        } catch (e: S3Exception) {
            System.err.println(e.awsErrorDetails().errorMessage())
            exitProcess(1)
        }
        // snippet-end:[s3.java2.getobjectdata.main]
    }

    private fun tutorialSetup(s3Client: S3Client, bucketName: String, region: Region) {
        try {
            s3Client.createBucket(
                CreateBucketRequest
                    .builder()
                    .bucket(bucketName)
                    .createBucketConfiguration(
                        CreateBucketConfiguration.builder()
                            .locationConstraint(region.id())
                            .build()
                    )
                    .build()
            )
            println("Creating bucket: $bucketName")
            s3Client.waiter().waitUntilBucketExists(
                HeadBucketRequest.builder()
                    .bucket(bucketName)
                    .build()
            )
            println("$bucketName is ready.")
            System.out.printf("%n")
        } catch (e: S3Exception) {
            System.err.println(e.awsErrorDetails().errorMessage())
            exitProcess(1)
        }
    }

    private fun cleanUp(s3Client: S3Client, bucketName: String, keyName: String) {
        println("Cleaning up...")
        try {
            println("Deleting object: $keyName")
            val deleteObjectRequest = DeleteObjectRequest.builder().bucket(bucketName).key(keyName).build()
            s3Client.deleteObject(deleteObjectRequest)
            println("$keyName has been deleted.")
            println("Deleting bucket: $bucketName")
            val deleteBucketRequest = DeleteBucketRequest.builder().bucket(bucketName).build()
            s3Client.deleteBucket(deleteBucketRequest)
            println("$bucketName has been deleted.")
            System.out.printf("%n")
        } catch (e: S3Exception) {
            System.err.println(e.awsErrorDetails().errorMessage())
            exitProcess(1)
        }
        println("Cleanup complete")
        System.out.printf("%n")
    }
}