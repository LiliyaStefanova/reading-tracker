package org.elstere.reading.tracker.dao;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Data
@MappedSuperclass
public class EntityWithUUID {

    @Id @Type(type = "pg-uuid")
    private UUID id;

    public EntityWithUUID(){
        this.id = UUID.randomUUID();
    }
}
