package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
import play.modules.s3blobs.S3Blob;

@Entity
public class Document extends Model
{
    public String fileName;
    public S3Blob file;
    public String comment;
}
