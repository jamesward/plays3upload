package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import models.Document;
import play.libs.MimeTypes;
import play.modules.s3blobs.S3Blob;
import play.mvc.Controller;

public class Files extends Controller
{

  public static void uploadForm()
  {
    render();
  }

  public static void doUpload(File file, String comment) throws FileNotFoundException
  {
    final Document doc = new Document();
    doc.fileName = file.getName();
    doc.comment = comment;
    doc.file = new S3Blob();
    doc.file.set(new FileInputStream(file), MimeTypes.getContentType(file.getName()));
    
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
    notFoundIfNull(doc);
    response.setContentTypeIfNotSet(doc.file.type());
    renderBinary(doc.file.get(), doc.fileName);
  }

}
