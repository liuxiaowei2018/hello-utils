package com.open.spatial4j;

import cn.hutool.json.JSONUtil;
import com.open.spatial4j.cases.NearUser;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuxiaowei
 * @date 2022年09月27日 15:02
 * @Description
 */
public class SpatialDemo {

    private SpatialContext spatialContext;

    /**
     * 获取附近 x 米的人
     *
     * @param distance 搜索距离范围 单位km
     * @param userLng  当前用户的经度
     * @param userLat  当前用户的纬度
     */
    public String nearBySearch(double distance, double userLng, double userLat) {

        //1.获取外接正方形
        Rectangle rectangle = getRectangle(distance, userLng, userLat);

        //2.获取位置在正方形内的所有用户
        //    SELECT * FROM nearby_user
        //    WHERE 1=1
        //    AND (longitude BETWEEN #{minlng} AND #{maxlng})
        //    AND (latitude BETWEEN #{minlat} AND #{maxlat})
        // List<User> users = userMapper.selectUser(rectangle.getMinX(), rectangle.getMaxX(), rectangle.getMinY(), rectangle.getMaxY());
        List<NearUser> users = new ArrayList<>();

        //3.剔除半径超过指定距离的多余用户
        users = users.stream()
                .filter(a -> getDistance(a.getLongitude(), a.getLatitude(), userLng, userLat) <= distance)
                .collect(Collectors.toList());
        return JSONUtil.toJsonStr(users);
    }

    /**
     * 获取外接矩形
     *
     * @param distance
     * @param userLng
     * @param userLat
     * @return org.locationtech.spatial4j.shape.Rectangle
     */
    private Rectangle getRectangle(double distance, double userLng, double userLat) {
        return spatialContext.getDistCalc()
                .calcBoxByDistFromPt(spatialContext.makePoint(userLng, userLat),
                        distance * DistanceUtils.KM_TO_DEG, spatialContext, null);
    }

    /***
     * 球面中，两点间的距离
     * @param longitude 经度1
     * @param latitude  纬度1
     * @param userLng   经度2
     * @param userLat   纬度2
     * @return 返回距离，单位km
     */
    private double getDistance(Double longitude, Double latitude, double userLng, double userLat) {
        return spatialContext.calcDistance(spatialContext.makePoint(userLng, userLat),
                spatialContext.makePoint(longitude, latitude)) * DistanceUtils.DEG_TO_KM;
    }
}
