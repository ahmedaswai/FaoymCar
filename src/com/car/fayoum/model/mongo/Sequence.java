package com.car.fayoum.model.mongo;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;
import org.mongodb.morphia.utils.IndexType;

@Entity("sequence")
@Indexes(
        {@Index(value = "colName", fields =@Field("colName"))

        }
)
public class Sequence {


    @Id
    private ObjectId objectId;

    private Long id;
    private String colName;

    public Sequence(){}
    public Sequence(final String name,final Long id){
        this.colName=name;
        this.id=id;
    }
    public static final String ID="id";
    public static final String COLLECTION_NAME="colName";
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }
}
