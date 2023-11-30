package ru.nsu.bolotov.util;

public final class UtilConsts {
    public static final class ComputationConsts {
        public static final int NUMBER_OF_SEGMENTS = 100;

        private ComputationConsts() {
            throw new IllegalStateException(StringConsts.INSTANTIATION_MESSAGE);
        }
    }

    public static final class StringConsts {
        public static final String INSTANTIATION_MESSAGE = "Instantiation of util class";

        private StringConsts() {
            throw new IllegalStateException(INSTANTIATION_MESSAGE);
        }
    }

    private UtilConsts() {
        throw new IllegalStateException(StringConsts.INSTANTIATION_MESSAGE);
    }
}
