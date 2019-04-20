package me.devstar.web.devstarmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.devstar.common.entity.SearchForm;
import me.devstar.web.devstarmall.entity.DevStarMall;
import me.devstar.web.devstarmall.repository.DevStarMallRepository;
import me.devstar.web.service.impl.AbstractBaseCrudService;

@Service
public class DevStarMallService extends AbstractBaseCrudService<DevStarMall, SearchForm, DevStarMallRepository, Long> {

	@Override
	@Autowired
	public void setRepository(DevStarMallRepository repository) {
		super.setRepository(repository);
	}
	
}
