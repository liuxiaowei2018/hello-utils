package com.open.custom.data;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liuxiaowei
 * @date 2022年10月27日 13:42
 * @Description java8.Stream扩展
 */
public class StreamUtil {

    public static <E> Double avg(Stream<E> stream, ToDoubleFunction<? extends E> avgFiled) {
        return (Double) stream
                .filter(Objects::nonNull).collect(Collectors.averagingDouble(getFunction(avgFiled)));
    }

    public static <E> Double avg(List<E> list, ToDoubleFunction<? extends E> avgFiled) {
        if (list == null) {
            return null;
        }
        return avg(list.stream(), avgFiled);
    }

    public static <E> Long count(List<E> list) {
        if (list == null) {
            return null;
        }
        return list.stream()
                .filter(Objects::nonNull).collect(Collectors.counting());
    }

    public static <R, E> E min(Stream<E> stream, Function<? extends E, R> minField) {
        return (E) ((Optional) stream
                .filter(Objects::nonNull).collect(Collectors.minBy(comparing(getFunction(minField))))).orElse(null);
    }

    public static <R, E> E min(List<E> list, Function<? extends E, R> minField) {
        if (list == null) {
            return null;
        }
        return min(list.stream(), minField);
    }

    public static <R, E> E max(Stream<E> stream, Function<? extends E, R> maxField) {
        return (E) ((Optional) stream
                .filter(Objects::nonNull).collect(Collectors.maxBy(comparing(getFunction(maxField))))).orElse(null);
    }

    public static <R, E> E max(List<E> list, Function<? extends E, R> maxField) {
        if (list == null) {
            return null;
        }
        return max(list.stream(), maxField);
    }

    public static <E> Double sum(Stream<E> stream, ToDoubleFunction<? extends E> sumField) {
        return (Double) stream
                .filter(Objects::nonNull).collect(Collectors.summingDouble(getFunction(sumField)));
    }

    public static <E> Double sum(List<E> list, ToDoubleFunction<? extends E> sumField) {
        if (list == null) {
            return null;
        }
        return sum(list.stream(), sumField);
    }

    public static <R, E> List<E> sort(Stream<E> stream, Function<? extends E, R> sortFiled) {
        return (List<E>) stream
                .filter(Objects::nonNull)
                .sorted(comparing(getFunction(sortFiled)))
                .collect(Collectors.toList());
    }

    public static <R, E> List<E> sort(List<E> list, Function<? extends E, R> sortFiled) {
        if (list == null) {
            return null;
        }
        return sort(list.stream(), sortFiled);
    }

    public static <R, E> List<E> sortAndReversed(Stream<E> stream, Function<? extends E, R> sortFiled) {
        return (List<E>) stream
                .filter(Objects::nonNull)
                .sorted(comparing(getFunction(sortFiled)).reversed())
                .collect(Collectors.toList());
    }

    public static <R, E> List<E> sortAndReversed(List<E> list, Function<? extends E, R> sortFiled) {
        if (list == null) {
            return null;
        }
        return sortAndReversed(list.stream(), sortFiled);
    }

    public static <R, E> Map<R, List<E>> group(Stream<E> stream, Function<? extends E, R> groupFiled) {
        return (Map<R, List<E>>) stream
                .filter(Objects::nonNull)
                .filter(e -> isKeyNotNull(getFunction(groupFiled), e))
                .collect(Collectors.groupingBy(getFunction(groupFiled)));
    }

    public static <R, E> Map<R, List<E>> group(List<E> list, Function<? extends E, R> groupFiled) {
        if (list == null) {
            return null;
        }
        return group(list.stream(), groupFiled);
    }

    public static <R, E> List<E> groupAndGetMaxToList(List<E> list, Function<? extends E, R> groupFiled, Function<? extends E, R> sortFiled) {
        if (list == null) {
            return null;
        }
        return group(list, groupFiled)
                .values()
                .stream()
                .map(value ->
                        max(value, sortFiled))
                .collect(Collectors.toList());
    }

    public static <R, E> List<E> groupAndGetMinToList(List<E> list, Function<? extends E, R> groupFiled, Function<? extends E, R> sortFiled) {
        if (list == null) {
            return null;
        }
        return group(list, groupFiled)
                .values()
                .stream()
                .map(value ->
                        min(value, sortFiled))
                .collect(Collectors.toList());
    }

    private static Function getFunction(Function function) {
        return function;
    }

    private static ToDoubleFunction getFunction(ToDoubleFunction function) {
        return function;
    }

    private static BiFunction getFunction(BiFunction function) {
        return function;
    }

    private static <T, K, A> boolean isKeyNotNull(Function<? super T, ? extends K> classifier, T ty) {
        AtomicReference<Boolean> flag = new AtomicReference<>(true);
        BiConsumer<Map<K, A>, T> accumulator = (m, t) -> {
            if (classifier.apply(t) == null) {
                flag.set(false);
            }
        };
        accumulator.accept(new HashMap<>(), ty);
        return flag.get();
    }

    private static <T, U extends Comparable<? super U>> Comparator<T> comparing(
            Function<? super T, ? extends U> keyExtractor) {
        Objects.requireNonNull(keyExtractor);
        return (Comparator<T> & Serializable)
                (c1, c2) -> {
                    if (keyExtractor.apply(c1) == null) {
                        return -1;
                    }
                    if (keyExtractor.apply(c2) == null) {
                        return 1;
                    }
                    return keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
                };
    }

}
