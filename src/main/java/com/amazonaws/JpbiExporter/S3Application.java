package com.amazonaws.JpbiExporter;

import java.io.File;
import java.io.IOException;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class S3Application {


	public static void main(String[] args) throws IOException {
		// set-up the client

		final AWSCredentials credentials;
		final String accessKey = "";
		final String secretKey = "";
		String bucketName;
		credentials = new BasicAWSCredentials(accessKey , secretKey);

		AmazonS3 s3client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_1).build();

		AWSS3ServiceJava awsService = new AWSS3ServiceJava(s3client);

		bucketName = "jpbi-s3";

		// creating if does not exist a bucket
		if (!awsService.doesBucketExist(bucketName)) {
			System.out.println("Bucket name does not exist." + " Keep calm and wait that one will be created.");
			awsService.createBucket(bucketName);
		}

		// list all the buckets
		for (Bucket s : awsService.listBuckets()) {
			System.out.println(s.getName());
		}

		// uploading object
		awsService.putObject(bucketName, "files/Hello.txt", new File("Hello.txt"));

		// listing objects
		ObjectListing objectListing = awsService.listObjects(bucketName);
		for (S3ObjectSummary os : objectListing.getObjectSummaries()) {
			System.out.println(os.getKey());
		}
	}
}