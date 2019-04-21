package me.devstar.web.devstarmallproduct.controller;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.text.StringTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import me.devstar.common.entity.SearchForm;
import me.devstar.web.devstarmallproduct.entity.DevStarMallProduct;
import me.devstar.web.devstarmallproduct.service.DevStarMallProductService;

@Controller
@RequestMapping(DevStarMallProductController.REQUEST_MAPPING_PREFIX)
public class DevStarMallProductController {
	
	public static final String REQUEST_MAPPING_PREFIX = "/product";
	
	private static final Logger LOG = LoggerFactory.getLogger(DevStarMallProductController.class);
	
	@Autowired
	private DevStarMallProductService service;

	@PostMapping
	public DevStarMallProduct create(@RequestBody final DevStarMallProduct t) {
		LOG.debug("create: {}", t.toString());
		return service.create(t);
	}

	@GetMapping("/{id}")
	public DevStarMallProduct get(@PathVariable final Long id) {
		return service.get(id);
	}

/*	@GetMapping
	public Page<CrackDown> getPages(final SearchForm searchForm) {
		LOG.debug("searchForm: {}", searchForm.toString());
		return service.getList(searchForm);
	}*/
	
	@GetMapping
	public String productlist(Model model, final SearchForm searchForm) {
		Page<DevStarMallProduct> page = service.getList(searchForm);
		model.addAttribute("list", page.getContent());
		return "devStarMall/productList";
	}

	@PutMapping("/{id}")
	public DevStarMallProduct modify(@PathVariable final Long id, @RequestBody DevStarMallProduct t) {
		t.setId(id);
		LOG.debug("modify: {}", t.toString());
		return service.modify(t);
	}

	@DeleteMapping("/{ids}")
	public String remove(@PathVariable final String ids, final boolean isPhysical) {
		LOG.debug("remove: {}", ids.toString());
		new StringTokenizer(ids, ",").getTokenList().stream().map(item -> NumberUtils.toLong(item)).forEach(action -> {
			if (isPhysical) {
				service.removeByPhysical(action);
			} else {
				service.remove(action);
			}
		});
		return ids;
	}
	
	@GetMapping("/productEdit")
	public String productedit(Model model, final SearchForm searchForm) {
		return "devStarMall/productEdit";
	}

}
