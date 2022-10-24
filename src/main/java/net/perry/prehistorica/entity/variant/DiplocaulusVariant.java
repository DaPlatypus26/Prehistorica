package net.perry.prehistorica.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum DiplocaulusVariant {
    BROWN(0),
    GREEN(1);

    private static final DiplocaulusVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(DiplocaulusVariant::getId)).toArray(DiplocaulusVariant[]::new);
    private final int id;

    DiplocaulusVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static DiplocaulusVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
