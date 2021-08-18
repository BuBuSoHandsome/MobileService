package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 *  choose_number_column
 * 
 * @author ruoyi
 * @date 2020-01-01
 */
public class ChooseNumberColumn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编码（唯一id） */
    private String sid;

    /** 渠道 */
    @Excel(name = "渠道")
    private String channel;

    /** 显示位置 */
    @Excel(name = "显示位置")
    private String position;

    /** 按钮文字 */
    @Excel(name = "按钮文字")
    private String text;

    /** 按钮图标 */
    @Excel(name = "按钮图标")
    private String pic;

    /** 卡类约束 */
    @Excel(name = "卡类约束")
    private String constraint;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 按钮描述 */
    @Excel(name = "按钮描述")
    private String describe;

    /** 套餐 */
    @Excel(name = "套餐")
    private String pack;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatetime;

    public void setSid(String sid)
    {
        this.sid = sid;
    }

    public String getSid()
    {
        return sid;
    }
    public void setChannel(String channel)
    {
        this.channel = channel;
    }

    public String getChannel()
    {
        return channel;
    }
    public void setPosition(String position)
    {
        this.position = position;
    }

    public String getPosition()
    {
        return position;
    }
    public void setText(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return text;
    }
    public void setPic(String pic)
    {
        this.pic = pic;
    }

    public String getPic()
    {
        return pic;
    }
    public void setConstraint(String constraint)
    {
        this.constraint = constraint;
    }

    public String getConstraint()
    {
        return constraint;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setDescribe(String describe)
    {
        this.describe = describe;
    }

    public String getDescribe()
    {
        return describe;
    }
    public void setPack(String pack)
    {
        this.pack = pack;
    }

    public String getPack()
    {
        return pack;
    }

    public Date getupdatetime()
    {
        return updatetime;
    }

    public void setupdatetime(Date updatetime)
    {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sid", getSid())
            .append("channel", getChannel())
            .append("position", getPosition())
            .append("text", getText())
            .append("pic", getPic())
            .append("constraint", getConstraint())
            .append("status", getStatus())
            .append("updatetime", getupdatetime())
            .append("describe", getDescribe())
            .append("pack", getPack())
            .toString();
    }
}
