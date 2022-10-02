package com.memewiki.core.meme.repository;

import com.memewiki.core.meme.domain.QMeme;
import com.memewiki.core.meme.response.PopularMemeResponse;
import com.memewiki.core.meme.response.QPopularMemeResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.memewiki.core.meme.domain.QMeme.meme;

import java.util.List;

@RequiredArgsConstructor
public class MemeRepositoryCustomImpl implements MemeRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<PopularMemeResponse> findPopularMemes(){
        return queryFactory.select(new QPopularMemeResponse(
                    meme
                ))
                .from(meme)
                .where(meme.deletedAt.isNull())
                .orderBy(meme.memeHit.desc())
                .limit(10)
                .fetch()
                ;
    }


}
