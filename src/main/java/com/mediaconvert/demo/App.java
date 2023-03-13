package com.mediaconvert.demo;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.mediaconvert.MediaConvertClient;
import software.amazon.awssdk.services.mediaconvert.model.CreateJobRequest;
import software.amazon.awssdk.services.mediaconvert.model.CreateJobResponse;
import software.amazon.awssdk.services.mediaconvert.model.DescribeEndpointsRequest;
import software.amazon.awssdk.services.mediaconvert.model.DescribeEndpointsResponse;
import software.amazon.awssdk.services.mediaconvert.model.Input;
import software.amazon.awssdk.services.mediaconvert.model.JobSettings;
import java.net.URI;

/**
 * Media Convert Demo
 *
 */
public class App 
{
    final static String accessKey = "xxx"; // 修改为自己的ak
    final static String secretKey = "xxx"; // 修改为自己的sk
    final static Region region = Region.US_EAST_1; //修改为自己的区域
    final static String jobRole = "arn:aws:iam::xxxxx:role/MediaConvert_Default_Role"; // 修改为自己的角色

    public static AwsCredentialsProvider credentialsProvider() {
        return StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey,
        secretKey));
    }

    public static MediaConvertClient mediaConvertClient() {
        AwsCredentialsProvider credentialsProvider = credentialsProvider();
        DescribeEndpointsResponse describeEndpoints = MediaConvertClient.builder()
                                                            .credentialsProvider(credentialsProvider)
                                                            .region(region)
                                                            .build()
                                                            .describeEndpoints(DescribeEndpointsRequest.builder().maxResults(20).build());

        return MediaConvertClient.builder()
                                .credentialsProvider(credentialsProvider)
                                .endpointOverride(URI.create(describeEndpoints.endpoints().get(0).url()))
                                .region(region)
                                .build();
    }
    public static void main(String[] args) {

        
        MediaConvertClient mc = mediaConvertClient();

        String template_name = "my-template";// 在AWS Console 中定义的模板名称
        String inputFile = "s3://mytest-hillday07/testvmaf/source/test-source-xxx.mp4";
        CreateJobRequest createJobRequest = CreateJobRequest.builder()
        .jobTemplate(template_name)
        .role(jobRole)
        .settings(JobSettings.builder()
                .inputs(Input.builder().fileInput(inputFile).build())
                .build())
        .build();
        CreateJobResponse reps =  mc.createJob(createJobRequest);

        System.out.println(reps);
    }
}
