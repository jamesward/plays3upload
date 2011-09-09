package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
import s3.storage.S3Blob;

@Entity
public class Document extends Model
{
    public String fileName;
    public S3Blob file;
    public String comment;
}
