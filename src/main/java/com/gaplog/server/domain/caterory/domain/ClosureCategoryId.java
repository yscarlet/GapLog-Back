package com.gaplog.server.domain.caterory.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClosureCategoryId implements Serializable {
    private Long ancestorId;
    private Long descendantId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClosureCategoryId that = (ClosureCategoryId) o;
        return Objects.equals(ancestorId, that.ancestorId) &&
                Objects.equals(descendantId, that.descendantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ancestorId, descendantId);
    }
}
