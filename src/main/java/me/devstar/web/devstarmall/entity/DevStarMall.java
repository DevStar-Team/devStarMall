package me.devstar.web.devstarmall.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import me.devstar.common.entity.BaseModel;

@Entity
@Table(name = "devstarmall")
public class DevStarMall extends BaseModel {

	private static final long serialVersionUID = -2338609640566469875L;
	
	private String carNo;
	
	private String location;

	private String price;

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}


}
