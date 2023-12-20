package ch03._00;

import java.io.Serializable;

// 參考文章
// https://www.journaldev.com/3481/hibernate-session-merge-vs-update-save-saveorupdate-persist-example
// https://www.baeldung.com/hibernate-save-persist-update-merge-saveorupdate

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch00.HibernateUtils;
import ch03._00.model.BookBean;

public class MethodsOfSession {
	SessionFactory factory;
	Session session;
	Transaction tx;

	@Before
	public void init() {
		factory = HibernateUtils.getSessionFactory();
		session = factory.openSession();
		tx = session.beginTransaction();
	}

	@After
	public void destroy() {
		try {
			System.out.println("3.----------------------------");
			tx.commit();
			System.out.println("4.----------------------------");
			session.close();
			factory.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ------------------------------------------------------------------------
	// 使用Session介面的save()來儲存臨時物件，如果紀錄的主鍵值是使用表格自增欄位的方式產生，
	// save()會立即發送SQL敘述，此時臨時物件不需要提供OID，因為無論OID為null或是任何整數，
	// 都會被資料庫產生的鍵值覆蓋。
	@Test
	public void saveDemo01() {
		// 新建三個BookBean物件，目前為臨時物件
		BookBean bb1 = new BookBean(1000, "美麗人生", "孫玲", 450.0, 150);
		BookBean bb2 = new BookBean(null, "健康人生", "張安國", 350.0, 100);
		BookBean bb3 = new BookBean(null, "幸福人生", "楊磊", 380.0, 325);
		// 利用session.save()儲存臨時物件，儲存後為永續物件
		session.save(bb1); // 雖然save()方法對bb1物件儲存多次，但只會寫入表格一次
		session.save(bb1);
		session.save(bb1);
		session.save(bb2);
		session.save(bb3);
		bb1.setPrice(950.0); // 永續物件位於Session快取中，受Hibernate監控，修改永續物件不需要
		bb2.setPrice(960.0); // 執行Session介面的update()方法
		bb3.setPrice(970.0);
	}

	// ------------------------------------------------------------------------
	// 修改永續物件的鍵值，會在執行tx.commit()時丟出例外。
	@Test
	public void saveDemo02() {
		BookBean bb1 = new BookBean(1000, "美麗人生-續集", "孫玲", 450.0, 100);
		BookBean bb2 = new BookBean(null, "健康人生-續集", "張安國", 350.0, 80);
		BookBean bb3 = new BookBean(null, "幸福人生-續集", "楊磊", 380.0, 75);
		Serializable o = session.save(bb1); // 儲存後bb1為永續物件
		System.out.println("Key=" + o);
		o = session.save(bb2); // 儲存後bb2為永續物件
		System.out.println("Key=" + o);
		o = session.save(bb3); // 儲存後bb3為永續物件
		System.out.println("Key=" + o);
		// 下列敘述會丟出例外
		// bb1.setBookId(1000);
		System.out.println("永續物件不能改鍵值，否則會在執行tx.commit()時丟出例外");
	}

	// ------------------------------------------------------------------------
	// Session#persist() 用來新增物件，如果對應紀錄的主鍵值是使用表格自增欄位的方式產生，
	// 它也會立即發送SQL敘述
	// save() vs persist():
	// persist()一定要在交易內執行，save()可以不必在交易內執行，但這樣做非常不好
	// 利用persist()儲存的臨時物件絕對不能有OID，
	// 利用save()儲存的臨時物件可以有OID，此OID會被覆蓋
	// persist()方法沒有傳回值，save()的傳回值型態為java.io.Serializable
	@Test
	public void persistDemo01() {
		BookBean bb1 = new BookBean(null, "永續經營自己的人生", "張芳芳", 450.0, 60);
		BookBean bb2 = new BookBean(null, "吃出健康，活的快樂", "張芳芳", 360.0, 72);
		session.persist(bb1);
		session.persist(bb2);
	}

	// ------------------------------------------------------------------------
	@Test
	// Session#persist() 儲存的臨時物件如果有OID，會得到
	// org.hibernate.PersistentObjectException: detached entity passed to persist:
	// ch03._00.model.BookBean
	public void persistDemo02() {
		try {
			// 下面的敘述因為有OID而丟出例外
			BookBean bb = new BookBean(1001, "正確規劃自己的人生", "劉啟民", 510.0, 48);
			session.persist(bb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// =======================================================================
//	--------------------------------------------------------	
//	session.get() vs session.load()
//	1. get()會立即由表格中讀取對應的紀錄(立即載入)，load()不會立即由表格中讀取對應的紀錄(延遲載入，Lazy Loading)而會傳回一個代理類別的物件
//	2. 由於load()方法傳回代理物件，因此當程式需要使用代理物件的屬性值時，如果session關閉，則 load()
//     會丟出LazyInitializationException: org.hibernate.LazyInitializationException: could not initialize proxy - no Session
//	3. 若表格中無欲讀取之紀錄，且Session未關閉，
//	   get()會傳回null，
//	   load()會於使用物件的屬性時(初始化時)，丟出例外org.hibernate.ObjectNotFoundException: No row with the given identifier exists: [_00.model.Employee#1991]
//	      若不使用屬性時，load()不會丟出例外
//
//	--------------------------------------------------------	
// get()方法會去讀取資料庫，傳回要讀取的永續物件。如果永續物件不存在，程式會傳回null。
	@Test
	public void getDemo01() {
	    BookBean bb1 = session.get(BookBean.class, 2);   // 將鍵值改為20再試一次
	    if (bb1 != null) {
	        System.out.println(bb1.getAuthor() + ", " + bb1.getPrice());
	    } else {
	        System.out.println("找不到bb物件");
	    }
	}


//	--------------------------------------------------------	
	// load()方法會傳回一個代理物件，而沒有真正去讀取資料庫內的資料。
	// 當程式需要使用物件的屬性值時，Hibernate才會進行資料存取。如果發現該永續物件不存在，
	// 程式會丟出例外。
	@Test
	public void loadDemo01() {
		BookBean bb1 = session.load(BookBean.class, 3);
		System.out.println("load()執行完畢...");
		System.out.println("----------------------------------------");
		System.out.println("代理類別的全名: " + bb1.getClass().getName());
		System.out.println("----------------------------------------");
		System.out.println("--------準備讀取BookBean物件的內容--------");
		// session.close(); // 如果執行session.close()來關閉session，程式會發生Exception:
		// could not initialize proxy [_00.model.BookBean#6] - no Session
		System.out.println(bb1.getAuthor() + ", " + bb1.getPrice());
	}

	// =======================================================================
	// 主動呼叫session.flush()方法，如果有任何永續物件的屬性值與表格內的紀錄欄位不一致
	// 本方法會發出UPDATE敘述
	@Test
	public void flushDemo01() {
		BookBean bb1 = session.get(BookBean.class, 3);
		if (bb1 == null) {
			System.out.println("該物件不存在");
			return;
		}
		bb1.setAuthor("劉麗芳-3");
		bb1.setStock(50);
		System.out.println("1.----------------------------");
		session.flush();
		System.out.println("2.----------------------------");
	}

	// 主動呼叫session.flush()方法，如果沒有任何永續物件的屬性值被修改
	// 本方法不會發出UPDATE敘述
	@Test
	public void flushDemo02() {
		BookBean bb1 = session.get(BookBean.class, 2);
		if (bb1 == null) {
			System.out.println("該物件不存在，請修改鍵值重新執行");
			return;
		}
		bb1.setStock(125);
		bb1.setStock(150);
		bb1.setStock(200);
		System.out.println("1.----------------------------");
		session.flush();
		System.out.println("2.----------------------------");
	}

	// 主動呼叫session.flush()方法，如果沒有任何永續物件的屬性值被修改
	// 本方法不會發出UPDATE敘述
	@Test
	public void flushDemo03() {
		BookBean bb1 = session.get(BookBean.class, 2);
		if (bb1 == null) {
			System.out.println("該物件不存在，請修改鍵值重新執行");
			return;
		}
		int stock = bb1.getStock();
		bb1.setStock(50);
		bb1.setStock(150);
		bb1.setStock(250);
		bb1.setStock(stock);
		System.out.println("1.----------------------------");
		session.flush();
		System.out.println("2.----------------------------");
	}
	// ====================================== ===================
//	--------------------------------------------------------	
//	delete()方法可以刪除一個分離物件或一個永續物件。
//	只要OID與某筆紀錄的主鍵相同就刪除該紀錄。
//	如OID沒有對應某筆紀錄，則丟出例外。
//	--------------------------------------------------------

	//
	// 1. session.delete(obj)方法只能刪除永續物件與分離物件，刪除臨時物件無任何"刪除"意義可言。
	// 2. session.delete(obj)若用來刪除臨時物件 會得到錯誤訊息[Batch update returned
	// unexpected row count from update [0]; actual row count: 0; expected: 1]
	@Test
	public void deleteDemo01() {
		// 執行session.get()後，bb0 永續物件
		BookBean bb0 = session.get(BookBean.class, 1);
		session.delete(bb0); // 刪除永續物件
		//
		BookBean bb1 = session.get(BookBean.class, 2);
		tx.commit();
		session.close();
		session = factory.openSession();
		tx = session.beginTransaction();
		session.delete(bb1); // 刪除分離物件
		System.out.println("此時bb0, bb1物件處於Removed狀態");
	}

	//
	// 使用 session.delete() 來刪除臨時物件會在執行tx.commit()時丟出例外。
	@Test
	public void deleteDemo02() {
		// 新建BookBean物件後，bb 為臨時物件
		// 下面的敘述刪除臨時物件
		BookBean bb = new BookBean(101, "校長流浪記-9", "張梅芳-9", 350.0, 600);
		session.delete(bb); // 不可以用session.delete()方法來刪除臨時物件會丟出例外。

	}

	@Test // 展示刪除分離物件。
	public void deleteDemo03() {
		BookBean bb = session.get(BookBean.class, 3);
		if (bb != null) {
			tx.commit();
			session.close(); // 關閉Session後，bb分離物件
			System.out.println("關閉Session後bb物件處於Detached狀態");
			session = factory.openSession(); // 重新開啟交易
			tx = session.beginTransaction();
			session.delete(bb); // 此時 bb 為分離物件
			System.out.println("刪除bb物件後，它處於Removed狀態");
		} else {
			System.out.println("查無此筆紀錄，請修改鍵值重新執行");
		}
	}

	/*
	 * session.delete(obj) : 可以刪除永續物件與分離物件，不可以刪除臨時物件，會得到錯誤訊息[Batch update returned
	 * Batch update returned unexpected row count from update [0]; actual row count:
	 * 0; expected: 1
	 */
	@Test
	public void deleteDemo04() {
		BookBean bb2 = new BookBean();
		bb2.setBookId(8);
		System.out.println("嘗試刪除分離物件bb2:, 主鍵為8, 只有主鍵值而無其它屬性");
		System.out.println("如果該物件存在，可刪除，如果該物件不存在會丟出例外");
		session.delete(bb2);
	}

	// ====================================== ===================
	@Test
	// Session#update() 用來更新分離物件。
	public void updateDemo01() {
		try {
			BookBean bb = new BookBean(null, "七天學會游泳", "林書豪", 520.0, 37);
			session.save(bb); 	// 執行session.save(bb)後，bb為永續物件
			tx.commit();
			session.close(); 	// 關閉session後，bb 變為分離物件
			session = factory.openSession(); // 開啟新session，bb不存在於這個session內
			tx = session.beginTransaction();
			bb.setTitle("八天學會游泳"); // 修改bb的title性質
			bb.setAuthor("林書濠"); 		// 修改bb的author性質
			session.update(bb); 		// session.update(bb)來更新分離物件。
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	@Test
	// Session#update() 更新臨時物件會在執行tx.commit()時丟出例外。
	public void updateDemo02() {
		BookBean bb = new BookBean(8, "古代詩詞欣賞", "張書豪", 520.0, 37);
		System.out.println(bb);
		bb.setAuthor("劉美華");
		bb.setStock(333);
		session.update(bb); // 對一個臨時物件執行update()方法

	}

	// ====================================== ===================
// saveOrUpdate():	
// 如果傳入的參數為 分離(detached)物件，進行更新操作，執行UPDATE敘述	
// 如果傳入的參數為 臨時(transient)物件，執行新增操作，執行INSERT敘述
	@Test
	public void saveOrUpdateDemo01() {
		BookBean bb1 = session.get(BookBean.class, 8);
		if (bb1 == null) {
			System.out.println("BookBean物件不存在，請調整主鍵值");
			return;
		}
		tx.commit();
		session.close();
		session = factory.openSession();
		tx = session.beginTransaction();
		// 此時bb1為分離(detached)物件
		bb1.setAuthor("劉麗芳-5");
		bb1.setStock(65);
		BookBean bb2 = new BookBean(null, "當Spring遇見Summer", "李建中", 330.0, 18);
		// 此時，目前的session物件中沒有控管任何永續物件。
		// 但程式中有一個分離(detached): bb1, 另外有一個臨時(transient)物件: bb2
		System.out.println("========================================");
		System.out.println("1.--準備執行saveOrUpdate(bb1), bb1為分離(detached)物件---");
		session.saveOrUpdate(bb1);
		session.flush();
		System.out.println("2.--準備執行saveOrUpdate(bb2), bb2為臨時(transient)物件---");
		session.saveOrUpdate(bb2);
		session.flush();
	}

	// ====================================== ===================
	//
	// session.merge():
	// 1. 新建(new)一個物件bb1，沒有OID, 執行session.merge(bb1)，會發出INSERT INTO的SQL敘述，此時
	// session.merge(bb1);與session.save(bb1)效果一樣 。
	// 2. 新建(new)一個物件bb2，有OID, 而且表格內有紀錄與之對應(即存在一筆記錄，其主鍵值與OID相等)，此時，
	// 此物件為detached物件, 接著執行session.merge(bb2);時，會發UPDATE的SQL敘述。
	// 3. 新建(new)一個物件bb3，有OID, 但表格內沒有紀錄與之對應(即沒有任何一筆記錄，其主鍵值與OID相等)，此時，
	// 此物件為transient物件, 接著執行session.merge(bb2);時，會發INSERT INTO的SQL敘述。
	//
	// 執行merge()方法後，傳入此方法的參數不會成為永續物件，但此方法的傳回值是永續物件。
	// -----------------------------------------------------------
	// 展示merge()處理沒有OID的臨時物件
	@Test
	public void mergeDemo01() {
		// case1: 新建一個物件bb1，沒有OID，故Session快取內不可能有該物件
		BookBean bb1 = new BookBean(null, "當薪水調高時-3", "李建國-3", 410.0, 33);
		System.out.println("case 1.--準備執行merge(臨時物件)---");
		System.out.println("新建一個物件bb1，沒有OID.，執行session.merge(bb1)，" 
			+ "會發出SQL INSERT INTO敘述");
		// 效果與session.save(bb1)一樣
		session.merge(bb1);
	}

	// 展示merge()處理有OID的分離物件
	@SuppressWarnings("unused")
	@Test
	public void mergeDemo02() {
		// case2: 新建一個物件bb2(即Session快取內沒有該物件)，有OID, 而且表格內有紀錄與之對應
		BookBean bb2 = new BookBean(5, "當Sally遇見Hally第4集", "李建國", 540.0, 14);
		System.out.println("case 2.--準備執行merge(bb2)---");
		// OK, 會發出SQL UPDATE敘述更新表格中主鍵等於OID的紀錄
		BookBean bb = (BookBean) session.merge(bb2); 
  
//		session.update(bb2); 
//		session.flush();	// 此敘述會丟出例外
// org.hibernate.NonUniqueObjectException: A different
// object with the same identifier value was already associated with the session
// : [_00.model.BookBean#10]		

//		session.save(bb2); // OK, 會發出INSERT INTO的SQL敘述 ，忽略原有的OID
//		session.flush();
//   	OK, 如果物件的屬性值不一致，會發出UPDATE的SQL敘述，更新表格中主鍵等於OID的紀錄
//		session.saveOrUpdate(bb2); 
//		session.flush();


	}

	@Test
	public void mergeDemo03() {
		// case3: 新建一個物件bb3(即Session快取內沒有該物件)，有OID, 但表格內沒有紀錄與之對應
		BookBean bb3 = new BookBean(301, "當Sally遇見Hally-3", "李建國-303", 410.0, 303);
		session.merge(bb3); // OK, 會發出INSERT INTO的SQL敘述 ，忽略原有的OID
		session.save(bb3);  // OK, 與session.merge()完全相同，即會發出SQL INSERT INTO敘述
		                    // ，忽略原有的OID
//		session.update(bb3); // NG, Row was updated or deleted by another transaction
		// (or unsaved-value mapping was incorrect) :
		// session.saveOrUpdate(bb3); // NG, Row was updated or deleted by another
		// transaction (or unsaved-value mapping was incorrect) :
	}

	// ------------------------------------------------------------------------
	/*
	 * session.merge(obj): 本方法會傳回更新或新增後的物件，此物件為永續物件。 傳入的參數不會是永續物件，而可能是： 1.
	 * 分離物件(具有效的主鍵值，更新它) 2. 臨時物件(OID為 null，或沒有對應的主鍵值，新增它)
	 */

	@SuppressWarnings("unused")
	@Test
	public void mergeDemo04() {
		BookBean bb1 = new BookBean(12, "快樂人生第二集", "張美春", 380.0, 25);
		BookBean bbR1 = (BookBean) session.merge(bb1);
		BookBean bb2 = new BookBean(null, "快樂人生第三集", "張大春", 390.0, 27);
		BookBean bbR2 = (BookBean) session.merge(bb2);
		BookBean bb3 = new BookBean(1000, "快樂人生第四集", "張小春", 400.0, 20);
		BookBean bbR3 = (BookBean) session.merge(bb3);

		System.out.println("--------------------------------------------");
		 bb1.setAuthor("張春芳-111(參數)");
		 bb2.setAuthor("張春芳-222(參數)");
		 bb3.setAuthor("張春芳-333(參數)");
//		bbR1.setAuthor("張春芳-111(傳回值)");
//		bbR2.setAuthor("張春芳-222(傳回值)");
//		bbR3.setAuthor("張春芳-333(傳回值)");
	}

	@Test
	public void clear01() {
		BookBean bb1 = session.get(BookBean.class, 15);
		System.out.println("bb1的作者: " + bb1.getAuthor());
	    bb1.setAuthor("丁丁");
		session.clear();
		System.out.println("----------------------------");
		bb1 = session.get(BookBean.class, 15);
		System.out.println("bb1的作者: " + bb1.getAuthor());
		System.out.println("觀察程式是否發出兩次SELECT敘述..., ");
		System.out.println("然後註解session.clear();後再執行一次，理解其中的差異");
	}

	@Test
	public void clear02() {
		BookBean bb1 = session.get(BookBean.class, 5);
		BookBean bb2 = session.get(BookBean.class, 6);
		System.out.println("bb1的作者: " + bb1.getAuthor());
		System.out.println("bb2的作者: " + bb2.getAuthor());
		bb1.setAuthor("黃中和");
		bb2.setAuthor("李良華");
		session.clear();
		System.out.println("觀察表格內的紀錄是否有變動..., ");
		System.out.println("然後註解session.clear();後再執行一次，理解其中的差異");
	}

	@Test
	public void evict() {
		BookBean bb1 = session.get(BookBean.class, 7);
		BookBean bb2 = session.get(BookBean.class, 8);
		BookBean bb3 = session.get(BookBean.class, 9);
		bb1.setAuthor("孫悟空");
		bb2.setAuthor("朱八戒");
		bb3.setAuthor("沙悟淨");
		session.evict(bb1);
		session.evict(bb3);
		System.out.println("觀察表格內的紀錄是否有變動...");
	}

	@Test
	public void refresh() {
		BookBean bb1 = session.get(BookBean.class, 15);
		System.out.println("bb1的作者: " + bb1.getAuthor());
		System.out.println("============按下任意鍵繼續============...");
		try {
			System.in.read();
		} catch (Exception e) {
		}
		session.refresh(bb1);
		System.out.println("bb1的作者: " + bb1.getAuthor());
	}
}
