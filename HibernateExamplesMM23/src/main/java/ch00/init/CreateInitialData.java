package ch00.init;

import ch00.HibernateUtils;
import ch04.ex01.model.Category;
import ch04.ex01.model.ProductEntity;
import ch04.ex01.service.ProductEntityService;
import ch04.ex01.service.impl.ProductEntityServiceImpl;

public class CreateInitialData {

	public static void main(String[] args) {
		ProductEntityService productService = new ProductEntityServiceImpl();
		Category cate1 = new Category("家電類", "家用電子產品");
		Category cate2 = new Category("運動器材類", "戶內外運動相關商品");
		Category cate3 = new Category("家俱類", "室內桌椅櫃床等");
		
		ProductEntity prod1 = new ProductEntity();
		prod1.setName("50吋液晶電視");
		prod1.setStock(20);
		prod1.setCategory(cate1);
		productService.save(prod1);

		ProductEntity prod2 = new ProductEntity();
		prod2.setName("三門豪華省電冰箱");
		prod2.setStock(12);
		prod2.setCategory(cate1);
		productService.save(prod2);
		
		ProductEntity prod3 = new ProductEntity();
		prod3.setName("奈米果汁機");
		prod3.setStock(25);
		prod3.setCategory(cate1);
		productService.save(prod3);
		
		ProductEntity prod4 = new ProductEntity();
		prod4.setName("登山背包");
		prod4.setStock(22);
		prod4.setCategory(cate2);
		productService.save(prod4);

		ProductEntity prod5 = new ProductEntity();
		prod5.setName("護膝");
		prod5.setStock(35);
		prod5.setCategory(cate2);
		productService.save(prod5);
		
		ProductEntity prod6 = new ProductEntity();
		prod6.setName("登山杖");
		prod6.setStock(40);
		prod6.setCategory(cate2);
		productService.save(prod6);
		
		ProductEntity prod7 = new ProductEntity();
		prod7.setName("羽球拍");
		prod7.setStock(37);
		prod7.setCategory(cate2);
		productService.save(prod7);
		
		ProductEntity prod8 = new ProductEntity();
		prod8.setName("沙發椅");
		prod8.setStock(37);
		prod8.setCategory(cate3);
		productService.save(prod8);
		
		HibernateUtils.getSessionFactory().close();
	}
}
// 執行session物件的persist()方法，將emp物件寫入資料庫的ProductEntity_Table表格
// session.persist(emp); ==> cascade=CascadeType.PERSIST
// session.save(emp); ==> cascade=CascadeType.ALL