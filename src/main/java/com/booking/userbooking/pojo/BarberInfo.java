package com.booking.userbooking.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 理发师信息表
 * @TableName barber_info
 */
@TableName(value ="barber_info")
@Data
public class BarberInfo implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 理发师编号
     */
    private String barberNo;

    /**
     * 理发师姓名
     */
    private String barberName;

    /**
     * 理发师登录密码
     */
    private String barberPassword;

    /**
     * 理发师年龄
     */
    private Integer barberAge;

    /**
     * 理发师电话
     */
    private String barberPhone;

    /**
     * 理发师技能
     */
    private String barberSkillNo;


    /**
     * 剩余上午可预约数量
     */
    private Integer morningBookingNum;

    /**
     * 剩余下午可预约数量
     */
    private Integer afternoonBookingNum;

    /**
     * 评分
     */
    private Double score;

    /**
     * 图片
     */
    private String image;


    /**
     * 创建人
     */
    private String createId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String updateId;

    /**
     * 修改时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BarberInfo other = (BarberInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBarberNo() == null ? other.getBarberNo() == null : this.getBarberNo().equals(other.getBarberNo()))
            && (this.getBarberName() == null ? other.getBarberName() == null : this.getBarberName().equals(other.getBarberName()))
            && (this.getBarberPassword() == null ? other.getBarberPassword() == null : this.getBarberPassword().equals(other.getBarberPassword()))
            && (this.getBarberAge() == null ? other.getBarberAge() == null : this.getBarberAge().equals(other.getBarberAge()))
            && (this.getBarberPhone() == null ? other.getBarberPhone() == null : this.getBarberPhone().equals(other.getBarberPhone()))
            && (this.getBarberSkillNo() == null ? other.getBarberSkillNo() == null : this.getBarberSkillNo().equals(other.getBarberSkillNo()))
            && (this.getMorningBookingNum() == null ? other.getMorningBookingNum() == null : this.getMorningBookingNum().equals(other.getMorningBookingNum()))
            && (this.getAfternoonBookingNum() == null ? other.getAfternoonBookingNum() == null : this.getAfternoonBookingNum().equals(other.getAfternoonBookingNum()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()))
            && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
            && (this.getCreateId() == null ? other.getCreateId() == null : this.getCreateId().equals(other.getCreateId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateId() == null ? other.getUpdateId() == null : this.getUpdateId().equals(other.getUpdateId()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBarberNo() == null) ? 0 : getBarberNo().hashCode());
        result = prime * result + ((getBarberName() == null) ? 0 : getBarberName().hashCode());
        result = prime * result + ((getBarberPassword() == null) ? 0 : getBarberPassword().hashCode());
        result = prime * result + ((getBarberAge() == null) ? 0 : getBarberAge().hashCode());
        result = prime * result + ((getBarberPhone() == null) ? 0 : getBarberPhone().hashCode());
        result = prime * result + ((getBarberSkillNo() == null) ? 0 : getBarberSkillNo().hashCode());
        result = prime * result + ((getMorningBookingNum() == null) ? 0 : getMorningBookingNum().hashCode());
        result = prime * result + ((getAfternoonBookingNum() == null) ? 0 : getAfternoonBookingNum().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
        result = prime * result + ((getCreateId() == null) ? 0 : getCreateId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateId() == null) ? 0 : getUpdateId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", barberNo=").append(barberNo);
        sb.append(", barberName=").append(barberName);
        sb.append(", barberPassword=").append(barberPassword);
        sb.append(", barberAge=").append(barberAge);
        sb.append(", barberPhone=").append(barberPhone);
        sb.append(", barberSkillNo=").append(barberSkillNo);
        sb.append(", morningBookingNum=").append(morningBookingNum);
        sb.append(", afternoonBookingNum=").append(afternoonBookingNum);
        sb.append(", score=").append(score);
        sb.append(", image=").append(image);
        sb.append(", createId=").append(createId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateId=").append(updateId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}