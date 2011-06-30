package org.hamcrest.domain;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

/**
 * QAddress is a Querydsl query type for Address
 */
public class QAddress extends BeanPath<Address> {

    private static final long serialVersionUID = 96340862;

    public final StringPath city = createString("city");

    public final StringPath street = createString("street");

    public QAddress(BeanPath<? extends Address> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QAddress(PathMetadata<?> metadata) {
        super(Address.class, metadata);
    }

}

