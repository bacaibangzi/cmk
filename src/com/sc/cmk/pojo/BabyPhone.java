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
public class BabyPhone extends CubeBaseEntity implements java.io.Serializable,Cloneable
{
	private static final long serialVersionUID = 5454155825314635342L;
	
	///alias
	public static final String TABLE_ALIAS = "BabyPhone";
	public static final String ALIAS_SN = "sn";
	public static final String ALIAS_BABY_ID = "babyId";
	public static final String ALIAS_BABY_NAME = "babyName";
	public static final String ALIAS_BABY_PHONE = "babyPhone";
	public static final String ALIAS_PARENT_PHONE = "parentPhone";
	public static final String ALIAS_EXTEND1 = "extend1";
	public static final String ALIAS_EXTEND2 = "extend2";
	
	///date formats
	
	///可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	///columns START
	private java.lang.Integer sn;
	private java.lang.String babyId;
	private java.lang.String babyName;
	private java.lang.String babyPhone;
	private java.lang.String parentPhone;
	private java.lang.String extend1;
	private java.lang.String extend2;
	///columns END

	public BabyPhone(){
	}

	public BabyPhone(
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
	public void setBabyName(java.lang.String value)
    {
		this.babyName = value;
	}
	
	public java.lang.String getBabyName()
    {
		return this.babyName;
	}
	public void setBabyPhone(java.lang.String value)
    {
		this.babyPhone = value;
	}
	
	public java.lang.String getBabyPhone()
    {
		return this.babyPhone;
	}
	public void setParentPhone(java.lang.String value)
    {
		this.parentPhone = value;
	}
	
	public java.lang.String getParentPhone()
    {
		return this.parentPhone;
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
     * @brief   功能: 将BabyPhone对象数据进行串化
     * @param   
     * @return  
     */
	public String toString()
    {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Sn",getSn())
			.append("BabyId",getBabyId())
			.append("BabyName",getBabyName())
			.append("BabyPhone",getBabyPhone())
			.append("ParentPhone",getParentPhone())
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
     * @brief   功能: 检测两个BabyPhone对象是否相同
     * @param   
     * @return  
     */
	public boolean equals(Object obj) {
		if(obj instanceof BabyPhone == false) return false;
		if(this == obj) return true;
		BabyPhone other = (BabyPhone)obj;
		return new EqualsBuilder()
			.append(getSn(),other.getSn())
			.isEquals();
	}
}

