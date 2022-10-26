package com.solutionarchitectvn.awsassumedrole;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class AwsAssumedRoleApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AwsAssumedRoleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        DefaultAWSCredentialsProviderChain credentials = new DefaultAWSCredentialsProviderChain();

        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(credentials)
                .withRegion(Regions.AP_SOUTHEAST_1)
                .build();

        s3client.listBuckets().forEach(bucket -> {
            log.info("Bucket name {}", bucket.getName());
        });
    }
}
