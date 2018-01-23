package com.xingkelaochen.codedemo.springboot.guide.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * ʹ��@MappedSuperclass����һ������ʹ����Entity�̳еĸ��࣬���ж���һЩͨ�õ����ԡ�
 *
 *
 * @author xingkelaochen
 * 
 * <p>
 * E-MAIL: admin@xingkelaochen.com
 * <br />
 * SITE: http://www.xingkelaochen.com
 * </p>
 *
 */
@MappedSuperclass
public class EntitySuperclass implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="entity-uuid",strategy="uuid")
	@GeneratedValue(generator="entity-uuid")
	@Column(name="id",length=32)
	private String id;
	
	private boolean enabled = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
