package me.devstar.web.devstarmallproduct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.devstar.common.entity.SearchForm;
import me.devstar.web.devstarmallproduct.entity.DevStarMallProduct;
import me.devstar.web.devstarmallproduct.repository.DevStarMallProductRepository;
import me.devstar.web.service.impl.AbstractBaseCrudService;

@Service
public class DevStarMallProductService extends AbstractBaseCrudService<DevStarMallProduct, SearchForm, DevStarMallProductRepository, Long> {

	@Override
	@Autowired
	public void setRepository(DevStarMallProductRepository repository) {
		super.setRepository(repository);
	}
	
}
