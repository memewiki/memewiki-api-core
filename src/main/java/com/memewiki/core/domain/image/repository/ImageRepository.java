package com.memewiki.core.domain.image.repository;

import com.memewiki.core.domain.image.domain.Image;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Blob;
import java.util.Map;

@Repository
public class ImageRepository  {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ImageRepository(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Long addImage(Map<String, Object> param) {
        String sql = "INSERT INTO image (name, type, pic_byte, meme_id) VALUES ( :name, :type, :picByte, :memeId )";
        SqlParameterSource parameter = new MapSqlParameterSource("name", (String)param.get("name"))
                .addValue("type", (String)param.get("type"))
                .addValue("picByte", (byte[])param.get("picByte"))
                .addValue("memeId", (Long)param.get("memeId"));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, parameter, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public Map<String, Object> findByMemeId(Long id) {
        String sql = "SELECT id, name, type, pic_byte, meme_id FROM image WHERE meme_id = :id";
        SqlParameterSource parameter = new MapSqlParameterSource("id", id);
        try {
            Map<String, Object> resultMap = namedParameterJdbcTemplate.queryForMap(sql, parameter);
            return resultMap;

        } catch (Exception e) {
            throw e;
        }
    }

}
