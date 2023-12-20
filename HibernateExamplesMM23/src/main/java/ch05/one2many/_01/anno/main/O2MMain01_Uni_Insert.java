package ch05.one2many._01.anno.main;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch05.one2many._01.anno.model.Cart;
import ch05.one2many._01.anno.model.Item;

public class O2MMain01_Uni_Insert {
	public static void main(String[] args) {
		Cart cart = new Cart();
		cart.setName("範例購物車-1");
		Item item1 = new Item("I001", 10.0, 1);
		Item item2 = new Item("I002", 50.0, 2);
		Item item3 = new Item("I005", 30.0, 4);
		Set<Item> itemsSet = 
			new LinkedHashSet<Item>(Arrays.asList(item1, item2, item3));
		
		cart.setItemsAnno(itemsSet);
		double total  = 0 ;
		for(Item item: itemsSet){
			total += item.getUnitPrice() * item.getQuantity();
		}
		cart.setTotal(total);
		//------------------------------------
		Cart cart2 = new Cart();
		cart2.setName("範例購物車-2");
		Item item4 = new Item("I004", 18.0, 2);
		Item item5 = new Item("I006", 66.0, 7);
		Item item6 = new Item("I008", 37.0, 3);
		Item item7 = new Item("I010", 35.0, 1);
		Set<Item> itemsSet2 = 
			new LinkedHashSet<Item>(Arrays.asList(item4, item5, item6, item7));
		
		cart2.setItemsAnno(itemsSet2);
		double total2  = 0 ;
		for(Item item: itemsSet2){
			total2 += item.getUnitPrice() * item.getQuantity();
		}
		cart2.setTotal(total2);
		
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try{
		// 建立SessionFactory物件
		sessionFactory = HibernateUtils.getSessionFactory();
		// 取出Session物件
		session = sessionFactory.getCurrentSession();
		System.out.println("得到Session物件");
		//開啟交易
		tx = session.beginTransaction();
		
		session.persist(cart);
		session.persist(cart2); 
		tx.commit();
		session.close();
		}catch(Exception e){
			System.out.println("發生例外: "+e.getMessage());
			e.printStackTrace();
		}finally{
			if(!sessionFactory.isClosed()){
				System.out.println("關閉SessionFactory");
				sessionFactory.close();
			}
		}
	}
}

