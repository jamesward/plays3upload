package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.jpa.Model;

@Entity
public class Document extends Model
{

  public Document(String comment, String s3Key, String fileName)
  {
    this.comment = comment;
    this.s3Key = s3Key;
    this.fileName = fileName;
  }

  public String comment;

  public String s3Key;

  public String fileName;

}
