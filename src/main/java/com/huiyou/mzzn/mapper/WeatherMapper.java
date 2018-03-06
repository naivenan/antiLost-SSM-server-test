package com.huiyou.mzzn.mapper;

import com.huiyou.mzzn.model.Weather;
import com.huiyou.mzzn.model.WeatherExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface WeatherMapper extends BaseMapper<Weather, WeatherExample, Long> {
}