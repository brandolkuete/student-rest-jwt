package com.brandolkuete.scolairebackendrest.dto;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
public class AuditableDTO {
    private String createdBy;
    private Date createdAt;
    private String lastModifyBy;
    private Date lastModifiedAt;
}
