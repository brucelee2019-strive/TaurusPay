package com.mchaw.tauruspay.common.util;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Bruce Lee
 * @date : 2019/11/4 11:34
 * @description:
 */
public class NoNullUtils {
    /**
     * String 过滤
     *
     * @param str
     * @param def
     * @return
     */
    public static String filterNoNullString(String str, String def) {
        return (String) filterNoNullCharSequence(str, def);
    }

    /**
     * CharSequence 过滤
     *
     * @param chr
     * @param def 默认值
     * @return
     */
    public static CharSequence filterNoNullCharSequence(CharSequence chr, @NonNull CharSequence def) {
        if (isEmpty(chr)) {
            chr = def;
        }
        return chr;
    }

    /**
     * List 过滤
     *
     * @param c
     * @return
     */
    public static <T> List<T> filterNoNullList(List<T> c) {
        try {
            return (List<T>) filterNoNullCollection(c, ArrayList.class);
        } catch (Exception e) {
            if (checkNull(c)) {
                c = new ArrayList<>();
            }
            return c;
        }
    }

    /**
     * Set 过滤
     *
     * @param c
     * @return
     */
    public static <T> Set<T> filterNoNullSet(Set<T> c) {
        try {
            return (Set<T>) filterNoNullCollection(c, HashSet.class);
        } catch (Exception e) {
            if (checkNull(c)) {
                c = new HashSet<>();
            }
            return c;
        }
    }

    /**
     * Collection 过滤
     *
     * @param c
     * @param cls 默认类型，null时初始化
     * @return
     */
    public static <T> Collection<T> filterNoNullCollection(Collection<T> c, Class cls) throws IllegalAccessException, InstantiationException {
        if (checkNull(c)) {
            c = (Collection<T>) cls.newInstance();
        }
        Collection<T> def1 = (Collection<T>) c.getClass().newInstance();
        for (T o : c) {
            if (!checkNull(o)) {
                def1.add(o);
            }
        }
        c.clear();
        c.addAll(def1);
        return c;
    }

    /**
     * Map 过滤
     *
     * @param c
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> filterNoNullMap(Map<K, V> c) {
        if (checkNull(c)) {
            c = new HashMap();
        }
        try {
            Map<K, V> def = c.getClass().newInstance();
            if (!isEmpty(c)) {
                for (Map.Entry o : c.entrySet()) {
                    if (o != null && o.getValue() != null && o.getKey() != null) {
                        def.put((K) o.getKey(), (V) o.getValue());
                    }
                }
            }
            c.clear();
            c.putAll(def);
        } catch (Exception e) {
            //
        }
        return c;
    }

    /**
     * 判空 多
     * @param o
     * @return
     */
    public static boolean isEmpty(Object... o) {
        if (checkNoNull(o)) {
            return true;
        }
        for (Object obj : o) {
            if (isEmpty(obj)) {
                return isEmpty(obj);
            }
        }
        return false;
    }

    /**
     * 判空
     *
     * @param o
     * @return
     */
    public static boolean isEmpty(Object o) {
        if (checkNull(o)) {
            return true;
        }
        if (o instanceof Collection) {
            return ((Collection) o).isEmpty();
        }

        if (o instanceof CharSequence) {
            return TextUtils.isEmpty((CharSequence) o);
        }

        if (o instanceof Map) {
            return ((Map) o).isEmpty();
        }
        return false;
    }


    /**
     * null
     *
     * @param o
     * @return
     */
    public static boolean checkNull(Object o) {
        return o == null;
    }

    /**
     * noNull
     *
     * @param o
     * @return
     */
    public static boolean checkNoNull(Object o) {
        return !checkNull(o);
    }

}
