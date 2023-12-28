package com.example.canteen_review.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

@Component
public class OSSUtil {
    private static String endpoint = "https://oss-cn-shanghai.aliyuncs.com";
    private static String bucketName = "book-shop-bkt";
    private static String accessKeyId = "LTAI5tCzXxuLxLHkoD9fijTL";
    private static String accessKeySecret = "Wy4PteDRnXMpr6dI5l9rZdWKHkceo4";

    public static String upload(MultipartFile file) throws Exception {

        InputStream inputStream = file.getInputStream();

        String[] temp = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        String type = temp[temp.length - 1];
        String url = "";

//        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String objectName = UUID.randomUUID().toString() + "." + type;

        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);

            PutObjectResult result = ossClient.putObject(putObjectRequest);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        url = "https://" + bucketName + "." + endpoint.substring(endpoint.lastIndexOf("/") + 1) + "/" + objectName;
        return url;
    }
}   