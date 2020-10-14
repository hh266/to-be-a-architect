package com.zch.pojo.vo;

/**
 * 商品评论等级数量
 *
 * @author zch
 * @date 2020/10/14 16:05
 */
public class ItemCommentLevelCountsVO {

    private Integer totalCounts;
    private Integer goodCounts;
    private Integer normalCounts;
    private Integer badCounts;

    public Integer getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(Integer totalCounts) {
        this.totalCounts = totalCounts;
    }

    public Integer getGoodCounts() {
        return goodCounts;
    }

    public void setGoodCounts(Integer goodCounts) {
        this.goodCounts = goodCounts;
    }

    public Integer getNormalCounts() {
        return normalCounts;
    }

    public void setNormalCounts(Integer normalCounts) {
        this.normalCounts = normalCounts;
    }

    public Integer getBadCounts() {
        return badCounts;
    }

    public void setBadCounts(Integer badCounts) {
        this.badCounts = badCounts;
    }

    public ItemCommentLevelCountsVO(Integer goodCounts, Integer normalCounts, Integer badCounts){
        this.goodCounts = goodCounts;
        this.normalCounts = normalCounts;
        this.badCounts = badCounts;
        this.totalCounts = goodCounts + normalCounts + badCounts;
    }
}
