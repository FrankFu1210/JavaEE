package ch05.one2one._02.anno.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch05.one2one._02.anno.model.PrincipalBi;
import ch05.one2one._02.anno.model.SchoolBi;


public class O2O_02_Main_Bi_Delete {

//	@SuppressWarnings("unused")
	public static void main(String[] args) {

		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		PrincipalBi p1 = session.get(PrincipalBi.class, 1);  // OK

		// 如果沒有編寫cascade= { CascadeType.REMOVE})，不能刪除被參考的紀錄。
		// 例如SchoolBi類別對應之表格中主鍵為2 的紀錄，假設有PrincipalBi類別對應之表格中，
		// 某紀錄的外鍵欄為 2，則刪除 SchoolBi類別中 OID為2的紀錄會丟出例外：
		// java.sql.SQLIntegrityConstraintViolationException: Cannot delete or update a parent row: a foreign key constraint fails (`jspdb`.`principal_table_02`, CONSTRAINT `FKi7kbrc972153f6q2qkcb0nrv1` FOREIGN KEY (`school_p_sid`) REFERENCES `school_table_02` (`sid`))
		// 除非先將 PrincipalBi物件(referencing object)之School變數(referenced object)設為null
		//	PrincipalBi p2 = session.get(PrincipalBi.class, 2);  // OK
		//	p2.setSchool(null);
		SchoolBi s2 = session.get(SchoolBi.class, 2);        // NG, 丟出例外		
		// 如果有加 cascade= { CascadeType.REMOVE})，刪除Principal表格中的紀錄時會連帶刪除School表格紀錄
		
		if (p1 != null) {
			session.delete(p1);
		} else {
			System.out.println("p1不存在");
		}
		if (s2 != null) {
			session.delete(s2);
		}else {
			System.out.println("s2不存在");
		}
        System.out.println("--------------------------------------");
		tx.commit();
		session.close();
		System.out.println("程式結束(Done...!!)");
		factory.close();
	}
}
