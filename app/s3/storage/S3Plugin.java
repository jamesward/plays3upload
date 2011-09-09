package s3.storage;

import play.Play;
import play.PlayPlugin;
import play.exceptions.ConfigurationException;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;

public class S3Plugin extends PlayPlugin {

  @Override
  public void onApplicationStart() {
    if (!Play.configuration.containsKey("s3.access.key")) {
      throw new ConfigurationException("Bad configuration for s3: no access key");
    } else if (!Play.configuration.containsKey("s3.access.secret")) {
      throw new ConfigurationException("Bad configuration for s3: no access secret");
    } else if (!Play.configuration.containsKey("s3.bucket")) {
      throw new ConfigurationException("Bad configuration for s3: no s3 bucket");
    }

    S3Blob.s3Bucket = Play.configuration.getProperty("s3.bucket");

    String accessKey = Play.configuration.getProperty("s3.access.key");
    String accessSecret = Play.configuration.getProperty("s3.access.secret");
    AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, accessSecret);
    S3Blob.s3Client = new AmazonS3Client(awsCredentials);

    if (!S3Blob.s3Client.doesBucketExist(S3Blob.s3Bucket)) {
      S3Blob.s3Client.createBucket(S3Blob.s3Bucket);
    }
  }
}
