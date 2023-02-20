package com.memewiki.core.domain.meme.repository;

import com.memewiki.core.domain.meme.response.*;
import com.memewiki.core.domain.tag.response.TagMemeRecentResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.memewiki.core.domain.meme.domain.QMeme.meme;
import static com.memewiki.core.domain.memeTag.domain.QMemeTag.memeTag;
import static com.memewiki.core.domain.tag.domain.QTag.tag;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

import java.util.List;

@RequiredArgsConstructor
public class MemeRepositoryCustomImpl implements MemeRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MemePopularResponse> findPopularMemes(){
        return queryFactory.select(new QMemePopularResponse(
                    meme
                ))
                .from(meme)
                .where(meme.deletedAt.isNull())
                .orderBy(meme.memeHit.desc())
                .limit(10)
                .fetch()
                ;
    }

    @Override
    public List<MemeRecentResponse> findMemesWithPageable(Long pagingNum){
        return queryFactory
                .selectFrom(meme)
                .leftJoin(meme.memeTagList, memeTag)
                .leftJoin(memeTag.tag, tag)
                .where(
                        memeIdBetween(pagingNum)
                )
                .distinct()
                .transform(
                        groupBy(meme.id).list(
                                Projections.constructor(MemeRecentResponse.class
                                        , meme
                                        , list(tag)
                                )
                        )
                );
    }

    private BooleanExpression memeIdBetween(Long pagingNum){
        return pagingNum != null ? meme.id.between(pagingNum, pagingNum + 30) : null;
    }
}
