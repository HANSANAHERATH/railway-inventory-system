package com.railway.railwayservice.entity;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.Entity;
import java.io.Serializable;

@Data
@Configuration
/*@EnableJpaAuditing(auditorAwareRef = "auditorAware")*/
public class BaseEntity implements Serializable {
}
