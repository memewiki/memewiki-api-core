package com.memewiki.core.domain.image.repository;

import com.memewiki.core.domain.image.domain.Image;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;

public class ImageRepositoryCustomImpl implements ImageRepositoryCustom {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ImageRepositoryCustomImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Long addImage(Image image) {
        String sql = "INSERT INTO image (name, type, pic_byte) VALUES ( :name, :type, :picByte )";
        SqlParameterSource parameter = new MapSqlParameterSource("name", image.getName())
                .addValue("type", image.getType())
                .addValue("picByte", image.getPicByte());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, parameter, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
