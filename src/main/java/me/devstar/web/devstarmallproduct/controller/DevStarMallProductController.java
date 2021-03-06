package me.devstar.web.devstarmallproduct.controller;

import javax.transaction.Transactional;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import me.devstar.common.entity.SearchForm;
import me.devstar.web.devstarmallproduct.entity.DevStarMallProduct;
import me.devstar.web.devstarmallproduct.service.DevStarMallProductService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

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
		
/*        String host = "devstar-redis-001.wp4vum.0001.apn2.cache.amazonaws.com";
        int port = 6379;
        int timeout = 3000;
        int db = 0;
        
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        
        JedisPool pool = new JedisPool(jedisPoolConfig,host,port,timeout,null,db);
        
        Jedis jedis = pool.getResource();
        //Connect 체크 
        System.out.println(jedis.isConnected());
        
        jedis.set("key4", "6");
        jedis.set("key5", "6");
        
        // 데이터의 만료시간을 지정
        jedis.expire("key5",1);
        
        System.out.println(jedis.get("key5"));
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(jedis.get("key5"));
    
        if( jedis != null ){
            jedis.close();
        }
        pool.close();*/

        
		
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
	
	@RequestMapping(value= "/productInsertExecute", method=RequestMethod.POST)
	public @ResponseBody DevStarMallProduct AjaxView(DevStarMallProduct devstarmallproduct)  {

	    return service.create(devstarmallproduct);
	}
	
	@RequestMapping(value= "/productEditExecute", method=RequestMethod.POST)
	public @ResponseBody DevStarMallProduct AjaxView2(DevStarMallProduct devstarmallproduct)  {

	    return service.modify(devstarmallproduct);
	}
	
	@GetMapping("/productInsert")
	public String productinsert(Model model, final SearchForm searchForm, DevStarMallProduct devstarmallproduct) {
		return "devStarMall/productInsert";
	}
	
    @Transactional
	@PostMapping("/productDetail")
	public String productdetail(Model model, final SearchForm searchForm, DevStarMallProduct devstarmallproduct) {
    	System.out.println("??????????????? = " + devstarmallproduct.getId());
		DevStarMallProduct product = service.get(devstarmallproduct.getId());
		model.addAttribute("detail", product);
		System.out.println("tset1 = " + product.getName());
		System.out.println("tset2 = " + product.getPrice());
		return "devStarMall/productDetail";
	}
	
	
	@PostMapping("/productEdit")
	public String productedit(Model model, final SearchForm searchForm) {
		
		return "devStarMall/productEdit";
	}

}
