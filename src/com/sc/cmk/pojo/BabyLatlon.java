package com.sc.cmk.pojo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.sc.framework.base.pojo.CubeBaseEntity;

/**
 * @ingroup system
 * @author  
 * @brief   类简单描述
 *
 * 类功能详细描述
 */
public class BabyLatlon extends CubeBaseEntity implements java.io.Serializable,Cloneable
{
	private static final long serialVersionUID = 5454155825314635342L;
	
	///alias
	public static final String TABLE_ALIAS = "BabyLatlon";
	public static final String ALIAS_SN = "sn";
	public static final String ALIAS_BABY_ID = "babyId";
	public static final String ALIAS_PARENT_PHONE = "parentPhone";
	public static final String ALIAS_LAT = "lat";
	public static final String ALIAS_LON = "lon";
	public static final String ALIAS_SHIJIAN = "shijian";
	public static final String ALIAS_EXTEND1 = "extend1";
	public static final String ALIAS_EXTEND2 = "extend2";
	
	///date formats
	
	///可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	///columns START
	private java.lang.Integer sn;
	private java.lang.String babyId;
	private java.lang.String parentPhone;
	private java.lang.Double lat;
	private java.lang.Double lon;
	private java.lang.String shijian;
	private java.lang.String extend1;
	private java.lang.String extend2;
	///columns END

	public BabyLatlon(){
	}

	public BabyLatlon(
		java.lang.Integer sn
	){
		this.sn = sn;
	}

	public void setSn(java.lang.Integer value)
    {
		this.sn = value;
	}
	
	public java.lang.Integer getSn()
    {
		return this.sn;
	}
	public void setBabyId(java.lang.String value)
    {
		this.babyId = value;
	}
	
	public java.lang.String getBabyId()
    {
		return this.babyId;
	}
	public void setParentPhone(java.lang.String value)
    {
		this.parentPhone = value;
	}
	
	public java.lang.String getParentPhone()
    {
		return this.parentPhone;
	}
	public void setLat(java.lang.Double value)
    {
		this.lat = value;
	}
	
	public java.lang.Double getLat()
    {
		return this.lat;
	}
	public void setLon(java.lang.Double value)
    {
		this.lon = value;
	}
	
	public java.lang.Double getLon()
    {
		return this.lon;
	}
	public void setShijian(java.lang.String value)
    {
		this.shijian = value;
	}
	
	public java.lang.String getShijian()
    {
		return this.shijian;
	}
	public void setExtend1(java.lang.String value)
    {
		this.extend1 = value;
	}
	
	public java.lang.String getExtend1()
    {
		return this.extend1;
	}
	public void setExtend2(java.lang.String value)
    {
		this.extend2 = value;
	}
	
	public java.lang.String getExtend2()
    {
		return this.extend2;
	}

    /**
     * @brief   功能: 将BabyLatlon对象数据进行串化
     * @param   
     * @return  
     */
	public String toString()
    {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Sn",getSn())
			.append("BabyId",getBabyId())
			.append("ParentPhone",getParentPhone())
			.append("Lat",getLat())
			.append("Lon",getLon())
			.append("Shijian",getShijian())
			.append("Extend1",getExtend1())
			.append("Extend2",getExtend2())
			.toString();
	}
	
    /**
     * @brief   功能: 
     * @param   
     * @return  
     */
	public int hashCode()
    {
		return new HashCodeBuilder()
			.append(getSn())
			.toHashCode();
	}
	
    /**
     * @brief   功能: 检测两个BabyLatlon对象是否相同
     * @param   
     * @return  
     */
	public boolean equals(Object obj) {
		if(obj instanceof BabyLatlon == false) return false;
		if(this == obj) return true;
		BabyLatlon other = (BabyLatlon)obj;
		return new EqualsBuilder()
			.append(getSn(),other.getSn())
			.isEquals();
	}
}

