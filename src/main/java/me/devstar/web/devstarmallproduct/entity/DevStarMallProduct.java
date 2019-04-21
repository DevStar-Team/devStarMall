package me.devstar.web.devstarmallproduct.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import me.devstar.common.entity.BaseModel;

@Entity
@Table(name = "product")
public class DevStarMallProduct extends BaseModel {

	private static final long serialVersionUID = -2338609640566469875L;
	
	private String name;
	
	private String price;
	
	private String category;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
