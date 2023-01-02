package net.perry.prehistorica.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum TorvosaurusVariant {
    BROWN(0),
    GREEN(1);

    private static final TorvosaurusVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(TorvosaurusVariant::getId)).toArray(TorvosaurusVariant[]::new);
    private final int id;

    TorvosaurusVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static TorvosaurusVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
