package com.spk.base;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Gl552 on 4/24/2017.
 */
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public class McnBaseEntity implements Serializable {
  public static final String CREATED_DATE = "CREATED_DATE";

  public static final String ID = "ID";

  public static final String MARK_FOR_DELETE = "MARK_FOR_DELETE";

  public static final String OPTLOCK = "OPTLOCK";

  public static final String UPDATED_DATE = "UPDATED_DATE";

  private static final String CREATED_BY = "createdBy";

  private static final String UPDATED_BY = "updatedBy";

  private static final long serialVersionUID = 1L;

  @CreatedBy
  @Column(name = McnBaseEntity.CREATED_BY)
  private String createdBy;

  @CreatedDate
  @Temporal(value = TemporalType.TIMESTAMP)
  @Column(name = McnBaseEntity.CREATED_DATE,
      nullable = false)
  private Date createdDate;

  @Id
  @Column(name = McnBaseEntity.ID)
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid",
      strategy = "uuid2")
  private String id;

  @Column(name = McnBaseEntity.MARK_FOR_DELETE)
  private Boolean markForDelete = false;

  @LastModifiedBy
  @Column(name = McnBaseEntity.UPDATED_BY)
  private String updatedBy;

  @LastModifiedDate
  @Temporal(value = TemporalType.TIMESTAMP)
  @Column(name = McnBaseEntity.UPDATED_DATE)
  private Date updatedDate;

  @Column(name = McnBaseEntity.OPTLOCK)
  @Version
  private Long version;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }
    if (this.getClass() != o.getClass()) {
      return false;
    }
    McnBaseEntity other = (McnBaseEntity) o;
    if (this.id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!this.id.equals(other.id)) {
      return false;
    }
    return true;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Boolean getMarkForDelete() {
    return markForDelete;
  }

  public void setMarkForDelete(Boolean markForDelete) {
    this.markForDelete = markForDelete;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
    return result;
  }

  public Boolean isMarkForDelete() {
    return markForDelete;
  }
}
