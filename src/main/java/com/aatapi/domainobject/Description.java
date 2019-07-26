package com.aatapi.domainobject;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class Description
{
    @Column(length=10485760)
    String en;
}
