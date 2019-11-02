package com.mchaw.tauruspay.common.util;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author : Bruce Lee
 * @description :
 * @date : 2019/10/26 0026 15:14
 */
public class GsonUtils {

    /**
     * 从JSON字符串中反序列化T对象
     *
     * @param jsonData
     * @param pClass
     * @param <T>
     * @return
     */
    public static <T> T parseTFromJson(String jsonData, Class<T> pClass) {
        T result;
        try {
            if (!TextUtils.isEmpty(jsonData)) {
                Gson gson = new Gson();
                result = gson.fromJson(jsonData, pClass);
            } else {
                result = null;
            }
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    /**
     * 从JSON字符串中反序列化List<T>集合对象
     *
     * @param jsonData Json字符串
     * @param pClass T对象的Class
     * @param <T> 对象类型
     * @return List<T>集合对象
     */
    public static <T> List<T> parseListFromJson(String jsonData, Class<T> pClass) {
        List<T> result;
        try {
            if (!TextUtils.isEmpty(jsonData)) {
                Gson gson = new Gson();
                result = gson.fromJson(jsonData, new ListOfJson<>(pClass));
            } else {
                result = null;
            }
        }catch (Exception e){
            result = null;
        }
        return result;
    }

    /**
     * @param t T对象
     * @param <T> 将要序列化的T对象
     * @return JSON字符串
     */
    public static <T> String parserTToJson(T t) {
        String jsonStr;
        try {
            if (t != null) {
                Gson gson = new Gson();
                jsonStr = gson.toJson(t);
            } else {
                jsonStr = "";
            }
        } catch (Exception e) {
            jsonStr = "";
        }
        return jsonStr;
    }

    /**
     * 序列化List<T>集合对象为JSON字符串
     *
     * @param list List<T>集合对象
     * @param <T> 对象类型
     * @return JSON字符串
     */
    public static <T> String parserListToJson(List<T> list) {
        String jsonStr;
        try {
            if (list != null) {
                Gson gson = new Gson();
                jsonStr = gson.toJson(list);
            } else {
                jsonStr = "";
            }
        } catch (Exception e){
            jsonStr = "";
        }
        return jsonStr;
    }

    static class ListOfJson<T> implements ParameterizedType {
        private Class<?> mType;

        public ListOfJson(Class<T> type) {
            this.mType = type;
        }

        @NonNull
        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{mType};
        }

        @NonNull
        @Override
        public Type getRawType() {
            return List.class;
        }

        @Nullable
        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}
