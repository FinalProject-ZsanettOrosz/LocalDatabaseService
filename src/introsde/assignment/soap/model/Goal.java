package introsde.assignment.soap.model;

import introsde.assignment.soap.dao.LifeCoachDao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Entity implementation class for Entity: Goal
 *
 */
@Entity
@Table(name = "Goal")
@NamedQuery(name = "Goal.findAll", query = "SELECT g FROM Goal g")
//@XmlType(propOrder = {"idGoal", "goalValue", "measureDefinition"})
public class Goal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "sqlite_goal")
	@TableGenerator(name = "sqlite_goal", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "Goal")
	@Column(name = "id")
	private int idGoal;

	@Column(name = "value")
	private int goalValue;
	
	//@OneToOne
	@OneToOne
	@JoinColumn(name = "idMeasureDef", referencedColumnName = "idMeasureDef", insertable = true, updatable = true)
	private MeasureDefinition measureDefinition;


	public Goal() {
		super();
	}


	public MeasureDefinition getMeasureDef() {
		return measureDefinition;
	}

	public void setMeasureDef(MeasureDefinition measureDef) {
		this.measureDefinition = measureDef;
	}
	
	public int getIdGoal() {
		return idGoal;
	}

	public void setIdGoal(int idGoal) {
		this.idGoal = idGoal;
	}

	public int getGoalValue() {
		return goalValue;
	}

	public void setGoalValue(int goalValue) {
		this.goalValue = goalValue;
	}

	public static Goal getGoalById(int goalId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Goal g = em.find(Goal.class, goalId);
		LifeCoachDao.instance.closeConnections(em);
		return g;
	}

	public static List<Goal> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<Goal> list = em.createNamedQuery("Goal.findAll", Goal.class)
				.getResultList();
		LifeCoachDao.instance.closeConnections(em);
		return list;
	}

}
