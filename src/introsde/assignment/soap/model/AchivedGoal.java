package introsde.assignment.soap.model;

import introsde.assignment.soap.dao.LifeCoachDao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * Entity implementation class for Entity: AchivedGoal
 *
 */
@Entity
@Table(name = "AchivedGoal")
@NamedQuery(name = "AchivedGoal.findAll", query = "SELECT l FROM AchivedGoal l")
@XmlType(propOrder = { "idAchivement", "achivedGoal", "achivementDate"})
public class AchivedGoal implements Serializable {
	private static final long serialVersionUID = 1L;

	public AchivedGoal() {
		super();
	}
	
	@Id
	@GeneratedValue(generator = "sqlite_achivedgoal")
	@TableGenerator(name = "sqlite_achivedgoal", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "AchivedGoal")
	@Column(name = "idAchivement")
	@XmlTransient
	private int idAchivement;


	@ManyToOne
	@JoinColumn(name = "idPerson", referencedColumnName = "idPerson", insertable = true, updatable = true)
	private Person person;

	@OneToOne
	@JoinColumn(name = "idGoal", referencedColumnName = "id", insertable = true, updatable = true)
	private Goal achivedGoal;

	@Column(name = "dateAchivement")
	private String achivementDate;

	public int getIdAchivement() {
		return idAchivement;
	}

	public void setIdAchivement(int idAchivement) {
		this.idAchivement = idAchivement;
	}

	@XmlTransient
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Goal getAchivedGoal() {
		return achivedGoal;
	}

	public void setAchivedGoal(Goal achivedGoal) {
		this.achivedGoal = achivedGoal;
	}

	public String getAchivementDate() {
		return achivementDate;
	}

	public void setAchivementDate(String achivementDate) {
		this.achivementDate = achivementDate;
	}

	public static AchivedGoal getAchivedGoalById(int achivedGoalsId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		AchivedGoal p = em.find(AchivedGoal.class, achivedGoalsId);
		LifeCoachDao.instance.closeConnections(em);
		return p;
	}

	public static List<AchivedGoal> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<AchivedGoal> list = em.createNamedQuery("AchivedGoal.findAll",
				AchivedGoal.class).getResultList();
		LifeCoachDao.instance.closeConnections(em);
		return list;
	}

	public static AchivedGoal saveAchivedGoal(AchivedGoal p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(p);
		tx.commit();
		LifeCoachDao.instance.closeConnections(em);
		return p;
	}

	public static void removeAchivedGoal(AchivedGoal p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p = em.merge(p);
		em.remove(p);
		tx.commit();
		LifeCoachDao.instance.closeConnections(em);
		System.err.println("Achived Goal removed");
	}
   
}
