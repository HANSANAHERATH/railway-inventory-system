package com.railway.railwayservice.entity;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Data
@Configuration
/*@EnableJpaAuditing(auditorAwareRef = "auditorAware")*/
public class BaseEntity implements Serializable {
}
