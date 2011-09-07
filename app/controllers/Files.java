package controllers;

import java.util.List;
import java.util.UUID;
import java.io.File;

import play.mvc.Controller;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3Object;

import models.Document;

public class Files extends Controller
{

  static final String BUCKET_NAME = "com.jamesward.plays3upload";
  static AmazonS3 s3Client;

  static
  {
    AWSCredentials awsCredentials = new BasicAWSCredentials(System.getenv("AWS_ACCESS_KEY"), System.getenv("AWS_SECRET_KEY"));
    s3Client = new AmazonS3Client(awsCredentials);
    s3Client.createBucket(BUCKET_NAME);
  }

  public static void uploadForm()
  {
    render();
  }

  public static void doUpload(String comment, File attachment)
  {
    String s3Key = UUID.randomUUID().toString();
    s3Client.putObject(BUCKET_NAME, s3Key, attachment);
    Document doc = new Document(comment, s3Key, attachment.getName());
    doc.save();
    listUploads();
  }

  public static void listUploads()
  {
    List<Document> docs = Document.findAll();
    render(docs);
  }

  public static void downloadFile(long id)
  {
    final Document doc = Document.findById(id);
    S3Object s3Object = s3Client.getObject(BUCKET_NAME, doc.s3Key);
    response.setContentTypeIfNotSet(s3Object.getObjectMetadata().getContentType());
    renderBinary(s3Object.getObjectContent(), doc.fileName);
  }

}
