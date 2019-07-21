package celsius.Model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U> {

    // @CreatedBy
    // @Column(name = "created_by")
    // private U createdBy;

    // @LastModifiedBy
    // @Column(name = "last_modified_by")
    // private U lastModifiedBy;


    @CreatedDate
    @Column(name = "fecha_creacion")
    private Date fecha_creacion;


    @LastModifiedDate
    @Column(name = "fecha_modificacion")
    private Date fecha_modificacion;
}
