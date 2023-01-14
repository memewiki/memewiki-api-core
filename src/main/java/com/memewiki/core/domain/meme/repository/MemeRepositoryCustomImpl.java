package com.memewiki.core.domain.meme.repository;

import com.memewiki.core.domain.meme.response.MemeResponse;
import com.memewiki.core.domain.meme.response.PopularMemeResponse;
import com.memewiki.core.domain.meme.response.QMemeResponse;
import com.memewiki.core.domain.meme.response.QPopularMemeResponse;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.memewiki.core.domain.meme.domain.QMeme.meme;

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

    @Override
    public List<MemeResponse> findMemesWithPageable(Integer pagingNum){
        return queryFactory.select(new QMemeResponse(
                        meme
                ))
                .from(meme)
                .where(meme.deletedAt.isNull()
                        , memeIdLoe(pageFounder(pagingNum))
                )
                .orderBy(meme.id.desc())
                .limit(pageFounder(pagingNum+1))
                .fetch()
                ;
    }

    private Long pageFounder(Integer pagingNum){
        if(pagingNum!=0){
            pagingNum--;
        }
        return (long)(pagingNum*30);
    }

    private BooleanExpression memeIdLoe(Long pagingNum){
        return pagingNum != null ? meme.id.loe(pagingNum) : null;
    }
}
