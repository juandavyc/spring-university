package com.juandavyc.university.embeddables;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.io.Serializable;
import java.security.PublicKey;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Embeddable
@NoArgsConstructor
@AllArgsConstructor

@AttributeOverrides({
        @AttributeOverride(
                name = "createdAt",
                column = @Column(name = "created_at", updatable = false)
        ),
        @AttributeOverride(
                name = "updatedAt",
                column = @Column(name = "updated_at")
        )
})
public class AuditInfo implements Serializable {

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


}
